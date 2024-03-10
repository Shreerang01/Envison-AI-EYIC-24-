package com.example.exoplayer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.StyledPlayerView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private ExoPlayer player;
    private String videoKey = "videos/0057f9d5-06b7-49ec-85e1-914f8f0f458a_Test.mp4"; // replace with your S3 video key
    private AmazonS3 s3Client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BasicAWSCredentials credentials = new BasicAWSCredentials("AKIATJP6II4GP2T23LQN", "N9FEXzWenQsPsp6qdxLdhgYkLxMl8ISnrGIRxZl1");
        AmazonS3 s3Client = new AmazonS3Client(credentials);
        s3Client.setRegion(Region.getRegion(Regions.US_EAST_1)); // replace with your region

        StyledPlayerView playerView = findViewById(R.id.player_view);
        player = new ExoPlayer.Builder(MainActivity.this).build();
        playerView.setPlayer(player);

        // Fetch S3 video URL
        String s3VideoURL = getS3VideoURL(videoKey);

        MediaItem mediaItem = MediaItem.fromUri(s3VideoURL);
        player.setMediaItem(mediaItem);
        player.prepare();
        player.setPlayWhenReady(true);
    }

    private String getS3VideoURL(String key) {
        // Generate a presigned URL for the S3 object
        return s3Client.generatePresignedUrl("test1770", key, new Date(System.currentTimeMillis() + 3600000)).toString();
    }

    @Override
    protected void onStop() {
        super.onStop();
        player.setPlayWhenReady(false);
        player.release();
        player = null;
    }
}