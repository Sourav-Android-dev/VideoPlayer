package com.example.reelsplayer;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoResponse {
    @SerializedName("s")
    private String s;

    @SerializedName("code")
    private String code;

    @SerializedName("msg")
    private List<VideoModel> videos;

    // Getters and setters
    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<VideoModel> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoModel> videos) {
        this.videos = videos;
    }
}

