package com.en.picplaydemo.utils;

import android.content.Context;

import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushManager;

/**
 * Created by en on 2016/9/26.
 */
public class XGUtil {
    public static void registerPush(Context context){
        XGPushManager.registerPush(context, new XGIOperateCallback() {

            @Override
            public void onFail(Object arg0, int arg1, String arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onSuccess(Object arg0, int arg1) {
                // TODO Auto-generated method stub

            }
        });
    }
}
