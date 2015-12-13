package com.gome.architect.gbox.service;

import com.gome.architect.gbox.pojos.Video;

import java.util.List;

/**
 * Created by lqx on 2015/12/13.
 */
public interface VideoService {
    /**
     * 获取最新的视频列表
     * @param count
     * @return
     */
    public List<Video> getLatestVideos(int count);

    /**
     * 根据关键词搜索视频
     * @param keywords
     * @return
     */
    public List<Video> search(String keywords);

    /**
     * 新建Video
     * @param v
     */
    public void add(Video v);
}
