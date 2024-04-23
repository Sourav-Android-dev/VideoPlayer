package com.example.reelsplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.SimpleExoPlayer;

import java.util.List;

public class VideoPlayerRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private List<String> videoUrls;
    private int currentItemPosition = -1;

    private SimpleExoPlayer currentPlayer;
    public VideoPlayerRecyclerAdapter(Context context, List<String> videoUrls) {
        this.context = context;
        this.videoUrls = videoUrls;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        /*if(holder instanceof VideoViewHolder){
            ((VideoViewHolder) holder).setVideo(context, videoUrls.get(position));
        }*/
        if (holder instanceof VideoViewHolder) {
            VideoViewHolder videoViewHolder = (VideoViewHolder) holder;
            videoViewHolder.setVideo(context, videoUrls.get(position));
            videoViewHolder.setIsPlaying(position == currentItemPosition);
        }
    }


    @Override
    public int getItemCount() {
        return videoUrls.size();
    }


    public void setVideoUrls(List<String> videoUrls) {
        this.videoUrls = videoUrls;
    }

    public void setCurrentItemPosition(int position) {
        currentItemPosition = position;
        if (currentPlayer != null) {
            currentPlayer.stop();
            currentPlayer.release();
            currentPlayer = null;
        }
    }


}
