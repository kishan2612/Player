package com.example.player.video;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.ui.widget.VideoView;
import com.example.player.R;

public class Videoplayer extends AppCompatActivity implements OnPreparedListener {
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_layout);
        videoView = (VideoView)findViewById(R.id.video_view);
        videoView.setOnPreparedListener(this);

        Intent intent = getIntent();

        String id = intent.getStringExtra("path");

        videoView.setVideoURI(Uri.parse(id));


    }

    @Override
    public void onPrepared() {
        videoView.start();

    }
}
