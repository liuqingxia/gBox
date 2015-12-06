package com.gome.architect.gBox.dao.impl;

import com.gome.architect.gBox.dao.VideoDao;
import com.gome.architect.gBox.pojos.Video;
import com.gome.architect.gBox.tools.SerializeUtil;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by lqx on 2015/11/29.
 */
public class VideoDaoImplByJedis implements VideoDao{

    private static Properties redisProps;

    static{
        redisProps = new Properties();
        try {
            redisProps.load(VideoDaoImplByJedis.class.getResourceAsStream("/redisAddr.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Jedis jedis =
            new Jedis(redisProps.getProperty("ip"),
                    Integer.valueOf((String)redisProps.get("port")));

    private String idPrefix = "video_number_";

    @Override
    public void addVideo(Video v) throws VideoDaoImplByJedisExecption {
        Integer id = v.getId();
        if ( null == id ){
            throw new VideoDaoImplByJedisExecption("The video id is null");
        }

        String videoNum = idPrefix + v.getId();
        if ( jedis.exists(videoNum) ){
            throw new VideoDaoImplByJedisExecption("The videoNum already exists");
        }
        byte[] bytes = SerializeUtil.serialize(v);
        jedis.set(videoNum.getBytes(),bytes);

    }

    @Override
    public void deleteVideo(int id) throws VideoDaoImplByJedisExecption {
        String videoNum = idPrefix + id;
        if ( !jedis.exists(videoNum) ){
            throw new VideoDaoImplByJedisExecption("The videoNum does not exist");
        }
        jedis.del(videoNum);
    }

    @Override
    public Video getVideo(int id) {
        String videoId = idPrefix + id;
        if ( !jedis.exists(videoId) ){
            return null;
        }
        byte[] videoBytes = jedis.get(videoId.getBytes());
        Video v = (Video)SerializeUtil.deSerialize(videoBytes);
        return v;
    }

    @Override
    public void updateVideo(Video v) throws VideoDaoImplByJedisExecption {
        Integer id = v.getId();
        if ( null == id ){
            throw new VideoDaoImplByJedisExecption("The video does not cantains id");
        }
        String videoNum = idPrefix + id;
        if ( !jedis.exists(videoNum) ){
            throw new VideoDaoImplByJedisExecption("The videoNum does not exist");
        }
        jedis.set(videoNum.getBytes(),SerializeUtil.serialize(v));
    }
}
