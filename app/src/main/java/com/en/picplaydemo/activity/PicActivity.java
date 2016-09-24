package com.en.picplaydemo.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;

import com.en.picplaydemo.R;
import com.en.picplaydemo.bussiness.JSONUtils;
import com.en.picplaydemo.entity.Advertise;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class PicActivity extends Activity {

    private SimpleDraweeView mSimpleDraweeView;
    private String uriStr = "";
    private Boolean isStop = false;
    private List<Advertise> list;
    private boolean first = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.activity_pic);
        mSimpleDraweeView = (SimpleDraweeView) findViewById(R.id.picPlayView);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        list = JSONUtils.getAdvertiseList();
        //启动播放线程

        setThread();
        initUI();
    }

    //写一个监听器 监听图片加载
    ControllerListener listener = new BaseControllerListener() {

        /**
         * 当图片加载成功时会执行的方法
         * @param id
         * @param imageInfo
         * @param animatable
         */
        @Override
        public void onFinalImageSet(String id, Object imageInfo, Animatable animatable) {
            super.onFinalImageSet(id, imageInfo, animatable);
        }


        /**
         * 图片加载失败时调用的方法
         * @param id
         * @param throwable
         */
        @Override
        public void onFailure(String id, Throwable throwable) {
            super.onFailure(id, throwable);
        }


        /**
         *  如果图片使用渐进式，这个方法将会被回调
         * @param id
         * @param throwable
         */
        @Override
        public void onIntermediateImageFailed(String id, Throwable throwable) {
            super.onIntermediateImageFailed(id, throwable);
        }
    };

    private void initUI() {

        if (TextUtils.isEmpty(uriStr))
            getURI();
        DraweeController draweeController1 = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse(uriStr))
                .setAutoPlayAnimations(true)
                .build();
        mSimpleDraweeView.setController(draweeController1);
    }

    @Override
    protected void onResume() {
        /**
         * 设置为横屏
         */
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }

    public void setThread() {
        new Thread() {
            public void run() {
                try {
                    while (!isStop) {
                        if (list != null)
                            for (int k = 0; k < list.size(); k++) {
                                if (isStop)
                                    break;
                                Message msg = Message.obtain();
                                int  time = list.get(k).getTime();
                                msg.what = k;
                                mHandler.sendMessage(msg);
                                Thread.sleep(time * 1000);
                            }
                    }
                } catch (Exception e) {
                }
            }
        }.start();

    }

    public void setURI(String uri) {
        this.uriStr = uri;
    }

    public void setStop(Boolean stop) {
        isStop = stop;
    }

    public void stopPlay(View view) {
        setStop(true);
        finish();
    }

    public void getURI() {
        this.uriStr = "http://www.zhn.party/a.gif";
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int i = msg.what;
            setURI(list.get(i).getURl());
            initUI();
        }
    };
}
