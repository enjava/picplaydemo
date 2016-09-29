package com.en.picplaydemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.en.picplaydemo.R;
import com.en.picplaydemo.bussiness.JSONUtils;
import com.en.picplaydemo.utils.XGUtil;

import java.lang.reflect.Method;

public class MainActivity extends Activity {
    private GridView gv_home;
    //private List<Advertise> list;
   private String[] mitemname=new String[]{"百事可乐330ml","可口可乐330ml","芬达橙味","红牛","加多宝",
            "脉动","美年达", "启力","雀巢咖啡","统一冰红茶","统一绿茶","统一奶茶","怡宝纯净水","营养快线" };
    private String [] mprice=new String[]{"2.5元","2.5元","2.5元","5.5元","3元",
            "4元","2.5元","2.5元","3.5元","3元","2.5元","3元","2元","3.5元"};
    private int[] mImageID=new int[]{R.mipmap.bskl,R.mipmap.kekoukele,R.mipmap.fenda,R.mipmap.hongniu,R.mipmap.jiaduobao,R.mipmap.maidong,R.mipmap.meinianda,
            R.mipmap.qili,R.mipmap.quechaokafei,R.mipmap.tongyibinghongcha,R.mipmap.tylvcha,R.mipmap.tynaicha,R.mipmap.yibaochunjingshui,R.mipmap.yingyangkuaixian};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //XGPushConfig.enableDebug(this, true);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.activity_main);
        JSONUtils.parseListURL();
        //注册信鸽推送
        XGUtil.registerPush(this);
        
        initUI();
    }

    private void initUI() {
        gv_home = (GridView) findViewById(R.id.gv);
        gv_home.setAdapter(new Madapter());

        gv_home.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.putExtra("position", position);
                intent.setClass(getApplicationContext(), PaymentActivity.class);
                startActivity(intent);
               }
        });
    }

    public void play(View view){

      startActivity(new Intent(this,Mp4PlayActivity.class));
    }

    @Override
    protected void onResume() {
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }

    class Madapter extends BaseAdapter {

        @Override
        public int getCount() {
            // 条目的总数 文字组数 == 图片张数
            return mitemname.length;
        }

        @Override
        public Object getItem(int position) {
            return mitemname[position];
        }

        @Override
        public long getItemId(int position) {
            return mImageID[position];
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(getApplicationContext(), R.layout.gridview_item, null);
            TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
            TextView tv_price = (TextView) view.findViewById(R.id.tv_price);
            ImageView iv_icon = (ImageView) view.findViewById(R.id.gridview_item_iv);

            tv_name.setText(mitemname[position]);
            tv_price.setText(mprice[position]);
            iv_icon.setBackgroundResource(mImageID[position]);
            return view;
        }
    }

    //关闭下拉
    @ Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
//        // TODO Auto-generated method stub
//        System.out.println("hasfocus--->>>" + hasFocus);
//        super.onWindowFocusChanged(hasFocus);
//        try
//        {
//            Object service = getSystemService("statusbar");
//            Class<?> statusbarManager =
//                    Class.forName("android.app.StatusBarManager");
//            Method test = statusbarManager.getMethod("collapse");
//            test.invoke(service);
//        }
//        catch (Exception ex)
//        {
//            ex.printStackTrace();
//        }

        // TODO Auto-generated method stub
        super.onWindowFocusChanged(hasFocus);
        try {


            Object service = getSystemService("statusbar");
            Class<?> statusbarManager = Class.forName("android.app.StatusBarManager");
            Method test = statusbarManager.getMethod("collapse");
            test.invoke(service);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
