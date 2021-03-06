package com.gome.architect.gBox.pojos;


import java.io.Serializable;

public class Video {

    private String name;//视频名称
    private String introduce;//视频介绍
    private String lecturer;//讲师
    private String playAddr;//播放地址
    private String screenShotAddr;//截图地址
    private String createTime;//创建时间

    public Video() {
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getPlayAddr() {
        return playAddr;
    }

    public void setPlayAddr(String playAddr) {
        this.playAddr = playAddr;
    }

    public String getScreenShotAddr() {
        return screenShotAddr;
    }

    public void setScreenShotAddr(String screenShotAddr) {
        this.screenShotAddr = screenShotAddr;
    }

    @Override
    public String toString() {
        return "Video{" +
                "name='" + name + '\'' +
                ", introduce='" + introduce + '\'' +
                ", lecturer='" + lecturer + '\'' +
                ", playAddr='" + playAddr + '\'' +
                ", screenShotAddr='" + screenShotAddr + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
