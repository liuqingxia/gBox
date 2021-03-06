package com.gome.architect.gbox.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by lqx on 2015/12/5.
 */
public class Constants {

    private static Properties searchProps;

    static{
        searchProps = new Properties();
        try {
            searchProps.load(Constants.class.getResourceAsStream("/search.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /****** 索引文件所在目录 *******/
    public static final String indexDir = searchProps.getProperty("indexDir");

    /***********最新视频列表的默认大小***************/
    public static final int VideoListSize = 10;
}
