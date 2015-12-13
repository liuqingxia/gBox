package com.gome.architect.gBox.dao.impl;

import com.gome.architect.gBox.dao.LatestVideoList;
import com.gome.architect.gBox.dao.utils.Constants;
import com.gome.architect.gBox.pojos.Video;
import com.gome.architect.gBox.pojos.VideoField;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lqx on 2015/12/13.
 */
public class LatestVideoListImpl implements LatestVideoList {
    private String indexDirStr = Constants.indexDir;

    @Override
    @SuppressWarnings("deprecation")
    public List<Video> get(int count) {
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
        Sort sort = new Sort(new SortField[]{new SortField("createTime", SortField.STRING, true)});
        Query query = new MatchAllDocsQuery();
        TopFieldDocs docs = null;
        try {
            docs = indexSearcher.search(query,count,sort);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if ( null == docs ){
            return null;
        }
        ScoreDoc[] scoreDocs = docs.scoreDocs;
        List<Video> latestVideoList = new ArrayList<Video>();
        for ( ScoreDoc scoreDoc : scoreDocs ){
            Document doc = null;
            try {
                doc = indexSearcher.doc(scoreDoc.doc);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if ( null != doc ){
                Video v = new Video();
                v.setName(doc.getFieldable(VideoField.name).stringValue());
                v.setIntroduce(doc.getFieldable(VideoField.introduce).stringValue());
                v.setLecturer(doc.getFieldable(VideoField.lecturer).stringValue());
                v.setPlayAddr(doc.getFieldable(VideoField.playAddr).stringValue());
                v.setScreenShotAddr(doc.getFieldable(VideoField.screenShotAddr).stringValue());
                v.setCreateTime(doc.getFieldable(VideoField.createTime).stringValue());
                latestVideoList.add(v);
            }
        }
        return latestVideoList;
    }
}
