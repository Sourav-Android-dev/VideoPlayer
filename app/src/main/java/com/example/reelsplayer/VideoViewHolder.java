package com.example.reelsplayer;

import android.content.Context;
import android.net.Uri;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

public class VideoViewHolder extends RecyclerView.ViewHolder {
    public PlayerView playerView;
    public SimpleExoPlayer player;

    private boolean isPlaying;
    public VideoViewHolder(@NonNull View itemView){
        super(itemView);
        playerView = itemView.findViewById(R.id.playerView);
    }


    public void setVideo(Context context, String videoUrl) {
        if (player == null) {
            // Initialize ExoPlayer if not already initialized
            player = new SimpleExoPlayer.Builder(context).build();
            playerView.setPlayer(player);
        } else {
            // Reset player state if already initialized
            player.stop();
            player.clearMediaItems();
        }

        // Prepare the MediaItem
        MediaItem mediaItem = MediaItem.fromUri(Uri.parse(videoUrl));

        // Set media item to player and start playback
        player.setMediaItem(mediaItem);
        player.prepare();
        player.play();

        player.addListener(new Player.Listener() {
            @Override
            public void onPlaybackStateChanged(int playbackState) {
                if(playbackState == Player.STATE_ENDED){
                    player.seekTo(0);
                    player.play();
                }
            }
        });

    }
    public void setIsPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
        if (isPlaying) {
            player.setPlayWhenReady(true);
        } else {
            player.setPlayWhenReady(false);
        }
    }
}
