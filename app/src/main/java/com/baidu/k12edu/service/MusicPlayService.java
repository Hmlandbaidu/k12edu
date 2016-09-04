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
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public class MyBinder extends Binder {
        public void onStartPlay(){
            Toast.makeText(getApplicationContext(), "Start Play", Toast.LENGTH_SHORT).show();
        }
    }
}

