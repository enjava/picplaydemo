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
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

public class PicActivity extends Activity {

    private SimpleDraweeView mSimpleDraweeView;
    private String uriStr = "";
    private  Boolean isStop=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.activity_pic);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //启动播放线程
         setThread();
         initUI();
    }

    //写一个监听器 监听图片加载
    ControllerListener listener = new BaseControllerListener(){

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
        mSimpleDraweeView = (SimpleDraweeView) findViewById(R.id.picPlayView);
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

    public void setThread(){
        new Thread() {
            public void run() {
                try {


                    int i=0;
                    int send=0;
                    while (!isStop) {

                        Thread.sleep(2000);
                        i++;
                        if (i%3==0){
                            Message msg = Message.obtain();
                            if(send==0){
                                send=1;
                                msg.what=1;
                            }
                            else if (send==1) {
                                send=2;
                                msg.what = 2;
                            }
                            else if (send==2) {
                                send=3;
                                msg.what = 3;
                            }

                            else if (send==3) {
                                send=4;
                                msg.what = 4;
                            }

                            else if  (send==4){
                                send=0;
                                msg.what=0;
                            }

                            mHandler.sendMessage(msg);
                        }
                        if (i>10000)
                            i=0;

                    }
                }catch (Exception e){}
            }
        }.start();

    }

    public void setURI(String uri) {
        this.uriStr = uri;
    }

    public void setStop(Boolean stop) {
        isStop = stop;
    }
    public void stopPlay(View view){
        setStop(true);
        finish();
    }
    public void getURI() {
        this.uriStr = "http://www.zhn.party/a.gif";
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:

                    setURI("http://www.zhn.party/a.gif");//asset://com.gz01.baochen.testgif/a.gif
                    initUI();
                    break;
                case 1:

                    setURI("http://www.zhn.party/b.gif");
                    initUI();
                    break;

                case 2:

                    setURI("http://www.zhn.party/c.gif");
                    initUI();
                    break;
                case 3:

                    setURI("http://www.zhn.party/d.jpg");
                    initUI();
                    break;
                case 4:

                    setURI("http://www.zhn.party/e.jpg");
                    initUI();
                    break;
                default:
                    break;
            }
        }
    };
}
