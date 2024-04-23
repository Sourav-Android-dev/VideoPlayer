package com.example.reelsplayer;

import com.google.gson.annotations.SerializedName;

public class VideoModel {
    @SerializedName("id")
    private int id;

    @SerializedName("uid")
    private String userId;

    @SerializedName("description")
    private String description;

    @SerializedName("video")
    private String videoUrl;

    @SerializedName("thum")
    private String thumbnailUrl;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}

