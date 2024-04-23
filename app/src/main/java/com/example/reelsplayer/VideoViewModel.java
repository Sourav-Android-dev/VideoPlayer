package com.example.reelsplayer;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoViewModel extends ViewModel {
    private ApiService apiService;
    private MutableLiveData<List<String>> videoUrlsLiveData;

    public LiveData<List<String>> getAllVideos() {
        if (videoUrlsLiveData == null) {
            videoUrlsLiveData = new MutableLiveData<>();
            fetchVideos();
        }
        return videoUrlsLiveData;
    }

    void fetchVideos() {
        apiService = RetrofitClient.getClient("https://fatema.takatakind.com/app_api/").create(ApiService.class);
        apiService.getAllVideos().enqueue(new Callback<VideoResponse>() {
            @Override
            public void onResponse(Call<VideoResponse> call, Response<VideoResponse> response) {
                if (response.isSuccessful()) {
                    List<String> videoUrls = new ArrayList<>();
                    VideoResponse videoResponse = response.body();
                    if (videoResponse != null && videoResponse.getVideos() != null) {
                        for (VideoModel video : videoResponse.getVideos()) {
                            videoUrls.add(video.getVideoUrl());
                        }
                        videoUrlsLiveData.postValue(videoUrls);

                        // Prefetch the next videos
                        new PrefetchVideosTask().execute(videoUrls.subList(1, videoUrls.size()));
                    }
                } else {
                    // Handle error response
                }
            }

            @Override
            public void onFailure(Call<VideoResponse> call, Throwable t) {
                // Handle failure
            }
        });
    }

    private static class PrefetchVideosTask extends AsyncTask<List<String>, Void, Void> {
        @Override
        protected Void doInBackground(List<String>... videoUrlsList) {
            List<String> videoUrls = videoUrlsList[0];
            Log.d("Video","Url"+videoUrls);
            for (String url : videoUrls) {
                // Prefetch each video URL
                prefetchVideo(url);
            }
            return null;
        }

        private void prefetchVideo(String videoUrl) {
            try {
                // Open a connection to the video URL
                URL url = new URL(videoUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                // Read the video data (this example reads the input stream)
                InputStream inputStream = connection.getInputStream();
                // Simulate reading the stream by consuming it (replace this with your actual logic)
                byte[] buffer = new byte[1024];
                while (inputStream.read(buffer) != -1) {
                    // Do nothing, just read the stream
                }
                // Close the input stream
                inputStream.close();

                // Close the connection
                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


