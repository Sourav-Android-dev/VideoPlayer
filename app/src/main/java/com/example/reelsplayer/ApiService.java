package com.example.reelsplayer;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("index.php?p=showAllVideos")
    Call<VideoResponse> getAllVideos();
}
