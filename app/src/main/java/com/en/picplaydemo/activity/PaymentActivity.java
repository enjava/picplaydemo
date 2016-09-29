package com.en.picplaydemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.en.picplaydemo.R;

/**
 * Created by en on 2016/9/29.
 */
public class PaymentActivity extends Activity {
    private ImageView zhufu_iv;
    private  int position;
    private String[] mitemname=new String[]{"百事可乐330ml","可口可乐330ml","芬达橙味","红牛","加多宝",
            "脉动","美年达", "启力","雀巢咖啡","统一冰红茶","统一绿茶","统一奶茶","怡宝纯净水","营养快线" };
    private String [] mprice=new String[]{"2.5元","2.5元","2.5元","5.5元","3元",
            "4元","2.5元","2.5元","3.5元","3元","2.5元","3元","2元","3.5元"};
    private int[] mImageID = new int[]{R.mipmap.bskl,R.mipmap.kekoukele,R.mipmap.fenda,R.mipmap.hongniu,R.mipmap.jiaduobao,R.mipmap.maidong,R.mipmap.meinianda,
            R.mipmap.qili,R.mipmap.quechaokafei,R.mipmap.tongyibinghongcha,R.mipmap.tylvcha,R.mipmap.tynaicha,R.mipmap.yibaochunjingshui,R.mipmap.yingyangkuaixian};
    private ImageView productID;
    private TextView mproductprice;
    private TextView mproductname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.activity_payment);
        Intent intent=getIntent();
        position=intent.getIntExtra("position",0);
        zhufu_iv = (ImageView) findViewById(R.id.zhifu_iv);
        productID = (ImageView) findViewById(R.id.product_id);
        mproductprice = (TextView) findViewById(R.id.tv_price);
        mproductname = (TextView) findViewById(R.id.tv_name);

        mproductprice.setText("价格："+mprice[position]);
        mproductname.setText("名称："+mitemname[position]);
        productID.setImageResource(mImageID[position]);


    }
    @Override
    protected void onResume() {
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }

    public void zhifubo(View view) {
        zhufu_iv.setImageResource(R.mipmap.zhifubao);
    }

    public void weixin(View view) {
        zhufu_iv.setImageResource(R.mipmap.weixin);
    }
    public void xianjin(View view) {
        zhufu_iv.setImageResource(R.mipmap.xianjin01);
    }
    public void reback(View view){
        finish();
    }
}
