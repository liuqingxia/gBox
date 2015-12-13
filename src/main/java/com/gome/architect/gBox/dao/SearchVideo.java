package com.gome.architect.gBox.dao;

import com.gome.architect.gBox.pojos.Video;
import java.util.List;

public interface SearchVideo {

    /**
     * 根据关键词搜索对应的视频
     * @param keywords 关键词
     * @return 返回搜索到的视频
     */
	public List<Video> search(String keywords);
	
}
