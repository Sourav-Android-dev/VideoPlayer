package com.example.reelsplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private VideoViewModel videoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Intialize viewpager
        ViewPager2 viewPager2 = findViewById(R.id.pager);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        // Prepare list of video URLs
        final List<String>[] videoUrls = new List[]{new ArrayList<>()};

        // Initialize adapter
        VideoPlayerRecyclerAdapter adapter = new VideoPlayerRecyclerAdapter(this, videoUrls[0]);
        viewPager2.setAdapter(adapter);

        videoViewModel = new ViewModelProvider(this).get(VideoViewModel.class);

        videoViewModel.getAllVideos().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> videoUrls) {
                // Update RecyclerView with video URLs
                adapter.setVideoUrls(videoUrls);
                adapter.notifyDataSetChanged();
            }
        });
        videoViewModel.fetchVideos();

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                adapter.setCurrentItemPosition(position);
                adapter.notifyDataSetChanged(); // Refresh the adapter to handle playback
            }
        });


}
}
