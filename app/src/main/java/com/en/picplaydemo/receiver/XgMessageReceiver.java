package com.en.picplaydemo.receiver;

import android.content.Context;

import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushRegisterResult;
import com.tencent.android.tpush.XGPushShowedResult;
import com.tencent.android.tpush.XGPushTextMessage;

/**
 * Created by en on 2016/9/26.
 */
public class XgMessageReceiver extends XGPushBaseReceiver {

    // 通知展示
    @Override
    public void onNotifactionShowedResult(Context context, XGPushShowedResult notifiShowedRlt) {

        System.out.println("onNotifactionShowedResult");
    }

    @Override
    public void onUnregisterResult(Context context, int errorCode) {


        System.out.println("onUnregisterResult");

    }

    @Override
    public void onSetTagResult(Context context, int errorCode, String tagName) {


        System.out.println("onSetTagResult");

    }

    @Override
    public void onDeleteTagResult(Context context, int errorCode, String tagName) {

        System.out.println("onDeleteTagResult");

    }

    // 通知点击回调 actionType=1为该消息被清除，actionType=0为该消息被点击
    @Override
    public void onNotifactionClickedResult(Context context, XGPushClickedResult message) {

        System.out.println("onNotifactionClickedResult");
    }

    @Override
    public void onRegisterResult(Context context, int errorCode, XGPushRegisterResult message) {
        if (context == null || message == null) {
            return;
        }
        String text = "";
        if (errorCode == XGPushBaseReceiver.SUCCESS) {
            text = message + "注册成功";
            String token = message.getToken();//此处获取token
            System.out.println("token:" + token);
        } else {
            text = message + "注册失败，错误码：" + errorCode;
        }

        System.out.println("text" + text);
        System.out.println("onRegisterResult");
    }

    // 消息透传
    @Override
    public void onTextMessage(Context context, XGPushTextMessage message) {
        System.out.println("onTextMessage");
    }

}