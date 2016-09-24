package com.en.picplaydemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.en.picplaydemo.R;
import com.en.picplaydemo.bussiness.JSONUtils;

public class MainActivity extends Activity {
   //private List<Advertise> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JSONUtils.parseListURL();
    }

    public void play(View view){

      startActivity(new Intent(this,PicActivity.class));
    }
}
