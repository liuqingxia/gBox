package com.gome.architect.gbox.service.impl;

import com.gome.architect.gbox.dao.IndexVideo;
import com.gome.architect.gbox.dao.LatestVideoList;
import com.gome.architect.gbox.dao.SearchVideo;
import com.gome.architect.gbox.pojos.Video;
import com.gome.architect.gbox.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lqx on 2015/12/13.
 */
@Component
public class VideoServiceImpl implements VideoService {
    @Autowired
    private LatestVideoList latestVideoList;
    @Autowired
    private SearchVideo searchVideo;
    @Autowired
    private IndexVideo indexVideo;

    @Override
    public List<Video> getLatestVideos(int count) {
        List<Video> videos = latestVideoList.get(count);
        if ( null == videos || 0 == videos.size() ){
            return null;
        }
        return videos;
    }

    @Override
    public List<Video> search(String keywords) {
        List<Video> videos = searchVideo.search(keywords);
        if ( null == videos || 0 == videos.size() ){
            return null;
        }
        return videos;
    }

    @Override
    public void add(Video v) {
        indexVideo.index(v);
    }
}
