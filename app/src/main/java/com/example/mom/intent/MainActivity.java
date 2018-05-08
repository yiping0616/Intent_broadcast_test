package com.example.mom.intent;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MyReceiver myReceiver = new MyReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    //動態註冊Receiver , 也可以在AndroidManifest中靜態設定Receiver
    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.CHAT_INCOMING");
        registerReceiver(myReceiver , intentFilter); //registerReceiver()註冊myReceiver , 並提供Intent-Filter
    }
    //取消註冊

    @Override
    protected void onPause() {
        super.onPause();
        //取消廣播接收器的聆聽 ,之後出現符合的Intent不會自動執行MyReceiver裡的onReceive() , 常放在onPause() or onStop()
        unregisterReceiver(myReceiver);
    }

    //Intent意圖
    //每個Intent都有一個Action : 開啟另一個Activity , 撥電話 , 打開網址 , 寄信 , 啟動服務 ..等 , 過程中也能傳遞資料

    //自訂action
    //在Manifest中設定 ActionActivity的<intent-filter>  <action android:name="com.Action"/>
    public void intent1(View view){
        Intent intent = new Intent();
        intent.setAction("com.Action");
        if(intent.resolveActivity(getPackageManager()) !=null){   //先判斷該intent是否至少有一個元件符合,以免直接發出intent出現Exception
            startActivity(intent);
        }
        else{
            Log.d("DEBUG" , "null");
        }
    }
    //系統中的implicit intent撥打電話 Intent.ACTION_DIAL
    //在Manifest中設定 DialerActivity的<intent-filter> <action android:name="android.intent.action.DIAL" />
    //呼叫intent2() 會出現兩種選擇
    public void intent2(View view){
        Intent dial = new Intent(Intent.ACTION_DIAL);
        startActivity(dial);
    }

    //Broadcast廣播
    //在應用程式中傳遞資料,目的是傳送 不是開啟Activity or Service ,可利用Broadcast
    public void broadcast(View view){
        //在Manifest中receiver裡面加<intent-filter> <action android:name="com.CHAT_INCOMING"></action>
        //代表MyReceiver類別只對Action值為"com.CHAT_INCOMING"的Intent有興趣
        //或是onStart()中的動態註冊也是設定Intent-Filter
        Intent intent = new Intent();
        intent.setAction("com.CHAT_INCOMING");
        sendBroadcast(intent);
    }
}
