package com.gome.architect.gBox.tools;

import java.io.IOException;
import java.util.Properties;

public class Constants {
	
	private static Properties consProp;
	
	static{
		consProp = new Properties();
		try {
			consProp.load(Constants.class.getResourceAsStream("/gBoxProperties/videoProps.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**The video file's save path*/
	public static final String videoPath = consProp.getProperty("videoPath");
	
	/**The thumbnail file's save path*/
	public static final String thumbnailPath = consProp.getProperty("thumbnailPath");
	
	/**The directory of the ffmpeg.exe*/
	public static final String ffmpeg_home = consProp.getProperty("ffmpeg_home");
	
	/**The ffmpeg convert directive*/
	public static final String processFLV = consProp.getProperty("processFLV");
	
	/**The ffmpeg screenshot directive*/
	public static final String screenShot = consProp.getProperty("screenShot");
	
	/**The prefix of the vNginxAddr*/
	public static final String vNginxAddr_prefix = consProp.getProperty("vNginxAddr_prefix");
	
	/**The postfix of the vNginxAddr*/
	public static final String vNginxAddr_postfix = consProp.getProperty("vNginxAddr_postfix");
	
	/**The prefix of the vThumbnail*/
	public static final String vThumbnail_prefix = consProp.getProperty("vThumbnail_prefix");
	
	/**The postfix of the vThumbnail*/
	public static final String vThumbnail_postfix = consProp.getProperty("vThumbnail_postfix");
	
	/** 默认一页记录数 */
	public static final int DEFAULT_PAGE_SIZE = 6;
	
	/** 默认页码 */
	public static final int DEFAULT_PAGE_NUM = 1;
	
	/**一页记录数限制*/
	public static final int PAGE_SIZE_LIMIT = 9;

}
