package com.gome.architect.gBox.dao.impl;

import java.io.File;
import java.io.IOException;
import com.gome.architect.gBox.pojos.Video;
import com.gome.architect.gBox.pojos.VideoField;
import com.gome.architect.gBox.dao.utils.Constants;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import com.gome.architect.gBox.dao.IndexVideo;
import org.wltea.analyzer.lucene.IKAnalyzer;


public class IndexVideoImpl implements IndexVideo {

	private String indexDirStr = Constants.indexDir;

    @Override
    @SuppressWarnings("deprecation")
	public void index(Video v) {
        File f = null;
        Directory indexDir = null;
        try {
            f = new File(indexDirStr);
            indexDir = FSDirectory.open(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean isCreated = isEmptyDir(f) ? true : false;
        Analyzer analyzer = new IKAnalyzer();
        IndexWriter iWriter = null;
        try {
            iWriter = new IndexWriter(indexDir, analyzer,isCreated, IndexWriter.MaxFieldLength.UNLIMITED);
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
            doc.add(new Field(VideoField.createTime,v.getCreateTime(),Field.Store.YES,Field.Index.NOT_ANALYZED));
			iWriter.addDocument(doc);
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

    /**
     * 判断给定目录是否为空
     * @return
     */
    public boolean isEmptyDir(File f){
        if ( f.isDirectory() ) {
            String[] files = f.list();
            if ( files.length > 0 ) {
                return false;
            }
        }else {
            throw new IllegalArgumentException("The file is not a directory");
        }
        return true;
    }
}
