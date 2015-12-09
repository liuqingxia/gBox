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
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Component;
import org.wltea.analyzer.lucene.IKAnalyzer;


@Component
public class SearchVideoImpl implements SearchVideo {

	private String indexDirStr = Constants.indexDir;
	private Analyzer analyzer = new IKAnalyzer();
	
	public SearchVideoImpl() { }

	public List<Video> search(String keywords) {
        Directory indexDir = null;
        try {
            indexDir = FSDirectory.open(new File(indexDirStr));
        } catch (IOException e) {
            e.printStackTrace();
        }
        IndexSearcher indexSearcher = null;
        DirectoryReader indexReader = null;
        try {
            indexReader = DirectoryReader.open(indexDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        indexSearcher = new IndexSearcher(indexReader);
        BooleanQuery query = new BooleanQuery();
        query.add(new TermQuery(new Term(VideoField.name,keywords)), BooleanClause.Occur.SHOULD);
        query.add(new TermQuery(new Term(VideoField.introduce,keywords)),BooleanClause.Occur.SHOULD);
        query.add(new TermQuery(new Term(VideoField.lecturer,keywords)),BooleanClause.Occur.SHOULD);
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
