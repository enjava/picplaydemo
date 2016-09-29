package com.en.picplaydemo.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.VideoView;

import com.en.picplaydemo.R;

;

/**
 * Created by en on 2016/9/28.
 */
public class Mp4PlayActivity extends Activity {

    private VideoView mVideoView;
    private int[] mMp4 = new int[]{R.raw.a, R.raw.b, R.raw.c, R.raw.d};
    int position =(int)(Math.random()*4);
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.activity_mp4play);
        mVideoView = (VideoView) this.findViewById(R.id.mp4);
        System.out.println("position"+position);
        String uri = "android.resource://" + getPackageName() + "/" + mMp4[position];
        mVideoView.setVideoPath(uri);
        mVideoView.start();
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (position == 3)
                    position = 0;
                else
                    position += 1;
                String uri = "android.resource://" + getPackageName() + "/" + mMp4[position];
                mVideoView.setVideoPath(uri);
                mVideoView.start();
            }
        });

        mVideoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoView.pause();
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }

    public void stopPlay(View view) {
        mVideoView.suspend();
        finish();
    }

    @Override
    protected void onDestroy() {
        mVideoView.suspend();
        super.onDestroy();
    }
}
