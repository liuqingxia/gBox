package com.gome.architect.gbox.search.test;

import com.gome.architect.gBox.pojos.Video;
import com.gome.architect.gBox.dao.SearchVideo;
import com.gome.architect.gBox.dao.impl.SearchVideoImpl;
import org.junit.Test;

import java.util.List;

/**
 * Created by lqx on 2015/12/6.
 */
public class SearchVideoTest {
    private SearchVideo searchVideo = new SearchVideoImpl();

    @Test
    public void searchTest(){
        String keywords = "索隆";
        List<Video> videos = searchVideo.search(keywords);
        if ( null != videos && 0 != videos.size() ){
            for ( Video v : videos ){
                System.out.println(v);
            }
        }
    }
}
