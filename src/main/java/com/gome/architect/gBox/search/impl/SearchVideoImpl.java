package com.gome.architect.gBox.search.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.gome.architect.gBox.pojos.Video;
import com.gome.architect.gBox.pojos.VideoField;
import com.gome.architect.gBox.search.SearchVideo;
import com.gome.architect.gBox.search.utils.Constants;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Component;
import org.wltea.analyzer.lucene.IKAnalyzer;



@Component
public class SearchVideoImpl implements SearchVideo {

	private String indexDirStr = Constants.indexDir;
	private Analyzer analyzer = new IKAnalyzer();
    private String[] fields = new String[]{VideoField.name,VideoField.lecturer,VideoField.introduce};
    private BooleanClause.Occur[] clauses = new BooleanClause.Occur[]{
            BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD};
	
	public SearchVideoImpl() { }

	public List<Video> search(String keywords) {
        Directory indexDir = null;
        try {
            indexDir = FSDirectory.open(new File(indexDirStr));
        } catch (IOException e) {
            e.printStackTrace();
        }
        IndexSearcher indexSearcher = null;
        try {
            indexSearcher = new IndexSearcher(indexDir,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Query multiFieldQuery = null;
        try {
            multiFieldQuery = MultiFieldQueryParser.parse(
                    Version.LUCENE_30, keywords, fields, clauses,
                    analyzer);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        TopScoreDocCollector collector = TopScoreDocCollector.create(100, false);
        try {
            indexSearcher.search(multiFieldQuery, collector);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ScoreDoc[] docs =collector.topDocs().scoreDocs;
		if( docs == null || docs.length == 0 ){ return null; }
		List<Video> videos = new ArrayList<Video>();
		for( ScoreDoc doc : docs ){
            Video v = new Video();
			try {
				Document d = indexSearcher.doc(doc.doc);
                v.setName(d.getField(VideoField.name).stringValue());
                v.setIntroduce(d.getField(VideoField.introduce).stringValue());
                v.setLecturer(d.getField(VideoField.lecturer).stringValue());
                v.setPlayAddr(d.getField(VideoField.playAddr).stringValue());
                v.setScreenShotAddr(d.getField(VideoField.screenShotAddr).stringValue());
                videos.add(v);
			} catch (CorruptIndexException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		return videos;
	}
}
