package com.gome.architect.gbox.test.dao.impl;

import com.gome.architect.gBox.dao.impl.VideoDaoImplByJedis;
import com.gome.architect.gBox.dao.impl.VideoDaoImplByJedisExecption;
import com.gome.architect.gBox.pojos.Video;
import org.junit.Test;

/**
 * Created by lqx on 2015/11/29.
 */
public class VideoDaoImplByJedisTest {
    private VideoDaoImplByJedis videoDaoImplByJedis = new VideoDaoImplByJedis();

    @Test
    public void addVideoTest(){
        Video v = new Video();
        v.setId(1);
        v.setName("海贼王-719集");
        v.setIntroduce("这是日本人气动漫《海贼王》719集：索隆三刀流奥义爆发");
        v.setLecturer("weitian");
        v.setPlayAddr("http://123.56.168.49:80/haizeiwang_719.flv");
        v.setScreenShotAddr("http://123.56.168.49:80/screenshot/haizeiwang_719.jpg");

        try {
            videoDaoImplByJedis.addVideo(v);
        } catch (VideoDaoImplByJedisExecption e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getVideo(){
        Video v = videoDaoImplByJedis.getVideo(2);
        System.out.println(v);
    }

    @Test
    public void deleteTest() throws VideoDaoImplByJedisExecption {
        videoDaoImplByJedis.deleteVideo(10);
    }

    @Test
    public void updateTest() throws VideoDaoImplByJedisExecption {
        Video v = new Video();
        v.setId(3);
        v.setName("海贼王-720集");
        v.setIntroduce("这是日本人气动漫《海贼王》720集：索隆三刀流奥义爆发");
        v.setLecturer("weitian");
        v.setPlayAddr("http://123.56.168.49:80/haizeiwang_719.flv");
        v.setScreenShotAddr("http://123.56.168.49:80/screenshot/haizeiwang_719.jpg");
        videoDaoImplByJedis.updateVideo(v);
    }



    public static void main(String[] args){
        VideoDaoImplByJedisTest test = new VideoDaoImplByJedisTest();
        test.addVideoTest();
    }
}
