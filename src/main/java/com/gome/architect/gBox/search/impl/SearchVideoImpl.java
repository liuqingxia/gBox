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
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Component;


@Component
public class SearchVideoImpl implements SearchVideo {

	private String indexDirStr = Constants.indexDir;
	private String[] fields = {VideoField.name,VideoField.introduce,VideoField.lecturer};
	private BooleanClause.Occur[] clauses = {BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD};
	private Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_30);
	
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
        Query query = null;
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_30);
        try {
            query = MultiFieldQueryParser.parse(Version.LUCENE_30, keywords, fields, clauses, analyzer);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        TopScoreDocCollector collector = TopScoreDocCollector.create(100, false);
        try {
            indexSearcher.search(query, collector);
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
			} catch (CorruptIndexException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		return videos;
	}
}
