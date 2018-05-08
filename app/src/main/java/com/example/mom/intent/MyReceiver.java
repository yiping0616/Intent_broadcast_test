package com.example.mom.intent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver { // New/Other/Broadcast Receiver

    //當收到廣播時 , 會自動執行onReceive()
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.d("DEBUG" , "onReceive");
        //throw new UnsupportedOperationException("Not yet implemented");  //自動產生預設會拋出UnsupportedOperationException例外物件
    }
}
