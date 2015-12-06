package com.gome.architect.gBox.search.impl;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import com.gome.architect.gBox.pojos.Video;
import com.gome.architect.gBox.pojos.VideoField;
import com.gome.architect.gBox.search.utils.Constants;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Component;
import com.gome.architect.gBox.search.IndexVideo;

@Component
public class IndexVideoImpl implements IndexVideo {

	private String indexDirStr = Constants.indexDir;

    @Override
	public void index(Video v) {
        Directory indexDir = null;
        try {
            indexDir = FSDirectory.open(new File(indexDirStr));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_30);
        IndexWriter iWriter = null;
        try {
            iWriter = new IndexWriter(indexDir, analyzer, false, IndexWriter.MaxFieldLength.UNLIMITED);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Document doc = new Document();
		try {
            doc.add(new Field(VideoField.name,v.getName(), Field.Store.YES, Field.Index.NOT_ANALYZED));
            doc.add(new Field(VideoField.introduce,v.getIntroduce(),Field.Store.YES,Field.Index.ANALYZED));
            doc.add(new Field(VideoField.lecturer,v.getLecturer(),Field.Store.YES,Field.Index.NOT_ANALYZED));
            doc.add(new Field(VideoField.playAddr,v.getPlayAddr(),Field.Store.YES,Field.Index.NOT_ANALYZED));
            doc.add(new Field(VideoField.screenShotAddr,v.getScreenShotAddr(),Field.Store.YES,Field.Index.NOT_ANALYZED));
			iWriter.addDocument(doc);
			iWriter.optimize();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if( iWriter != null )
				try {
					iWriter.close();
				} catch (CorruptIndexException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	
	}
}