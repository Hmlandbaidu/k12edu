package com.baidu.k12edu.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by dengshengjin on 16/9/4.
 */
public class MusicPlayService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) { //使用onBind方法
        return new MyBinder();   //返回一个对象MyBinder
    }

    public class MyBinder extends Binder {  //定义一个类MyBinder，定义一个开始播放的方法
        public void onStartPlay(){
            //弹出一个toast：显示Start Play
            Toast.makeText(getApplicationContext(), "Start Play", Toast.LENGTH_SHORT).show();
        }
    }
}

