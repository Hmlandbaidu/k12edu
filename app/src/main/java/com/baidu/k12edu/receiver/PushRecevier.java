package com.baidu.k12edu.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by dengshengjin on 16/9/4.
 */
public class PushRecevier extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {  //接收广播
        if (intent.getAction().equals("com.edu.k12.receiver.PushRecevier")){//只有监听到是这个接收器，才会打出log
            Log.i("1","Recevier is onRecevie!");
        }
    }
}
