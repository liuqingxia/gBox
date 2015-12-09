package com.gome.architect.gbox.search.test;

import com.gome.architect.gBox.pojos.Video;
import com.gome.architect.gBox.search.IndexVideo;
import com.gome.architect.gBox.search.impl.IndexVideoImpl;
import org.junit.Test;

/**
 * Created by lqx on 2015/12/6.
 */
public class IndexVideoTest {
    private IndexVideo indexVideo = new IndexVideoImpl();

    @Test
    public void indexTest(){
        Video v = new Video();
        v.setName("haizeiwang-719");
        v.setIntroduce("索隆：三刀流奥义爆发");
        v.setLecturer("尾田");
        v.setPlayAddr("http://123.56.168:80/haizeiwang_719.flv");
        v.setScreenShotAddr("http://123.56.168:80/haizeiwang_719.flv");

        indexVideo.index(v);
    }
}
