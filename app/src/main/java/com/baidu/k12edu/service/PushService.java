package com.baidu.k12edu.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by dengshengjin on 16/9/4.
 */
public class PushService extends Service {
    private Thread mPushThread;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        pushService();
        Log.i(" ", "PushServices is onCreate.");
    }

    private static class PushThread extends Thread {
        private Context mContext;

        public PushThread(Context context) {
            mContext = context;
        }

        @Override
        public void run() {
            super.run();
            //TODO  循环5s查询服务端是否有新消息
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    sleep(5000);
                    //TODO 此处模拟每5秒可以接收到一个消息
                    sendBroacast();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void sendBroacast() {
            Intent intent = new Intent("com.edu.k12.receiver.PushRecevier");
            mContext.sendBroadcast(intent);
        }
    }

    private void pushService() {
        if (mPushThread == null) {
            mPushThread = new PushThread(getApplicationContext());
            mPushThread.start();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(" ", "PushService s is onDestory.");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(" ", "PushServices is StartCommand.");
        return super.onStartCommand(intent, flags, startId);

    }
}
