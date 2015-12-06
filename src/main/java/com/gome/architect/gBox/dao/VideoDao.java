package com.gome.architect.gBox.dao;

import com.gome.architect.gBox.dao.impl.VideoDaoImplByJedisExecption;
import com.gome.architect.gBox.pojos.Video;

/**
 * Created by lqx on 2015/11/29.
 */
public interface VideoDao {

    public void addVideo(Video v) throws VideoDaoImplByJedisExecption;
    public void deleteVideo(int id) throws VideoDaoImplByJedisExecption;
    public Video getVideo(int id);
    public void updateVideo(Video v) throws VideoDaoImplByJedisExecption;
}
