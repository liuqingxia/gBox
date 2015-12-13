package com.gome.architect.gBox.dao;

import com.gome.architect.gBox.pojos.Video;
import java.util.List;

/**
 * Created by lqx on 2015/12/13.
 */
public interface LatestVideoList {
    /**
     * 获取最新的视频列表
     * @param count
     * @return
     */
    public List<Video> get(int count);
}
