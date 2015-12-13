package com.gome.architect.gbox.search.test;

import com.gome.architect.gBox.dao.LatestVideoList;
import com.gome.architect.gBox.dao.impl.LatestVideoListImpl;
import com.gome.architect.gBox.pojos.Video;
import org.junit.Test;

import java.util.List;

/**
 * Created by lqx on 2015/12/13.
 */
public class LatestVideoListTest {
    private LatestVideoList latestVideoList = new LatestVideoListImpl();
    @Test
    public void getTest(){
        List<Video> videos = latestVideoList.get(5);
        if ( null == videos ){
            System.out.println("List == null");
        }else {
            for ( Video v : videos ){
                System.out.println(v);
            }
        }
    }
}
