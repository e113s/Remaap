package anel.com.mx.kgardenapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

import anel.com.mx.kgardenapp.impl.DrawingActivity;

public class VideoNumeros extends NumParentActivity {

    private  VideoView videoView ;
    private int mCurrentPosition = 0;
    private static final String PLAYBACK_TIME = "play_time";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_numeros);

        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(PLAYBACK_TIME);
        }

        String fileName = "video_numeros";
        String filePlace = "android.resource://" + getPackageName() + "/raw/" + fileName;
        //String filePlace = "https://www.youtube.com/watch?v=pSqnl2eSu9Y";
        videoView= findViewById(R.id.videoViewId);
        videoView.setVideoURI(Uri.parse(filePlace));

        videoView.start();



        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                Intent intent = new Intent(VideoNumeros.this, DrawingActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(VideoNumeros.this, DrawingActivity.class);
        startActivity(intent);
        //backPrincipalMenu(0);
    }


    private void initializePlayer() {
        if (mCurrentPosition > 0) {
            videoView.seekTo(mCurrentPosition);
        } else {
            // Skipping to 1 shows the first frame of the video.
            videoView.seekTo(1);
        }
    }

    private void releasePlayer() {
        videoView.stopPlayback();
    }

    @Override
    protected void onStart() {
        super.onStart();

        initializePlayer();
    }

    @Override
    protected void onStop() {
        super.onStop();

        releasePlayer();
    }


    @Override
    protected void onPause() {
        super.onPause();

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            videoView.pause();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(PLAYBACK_TIME, videoView.getCurrentPosition());
    }
}

