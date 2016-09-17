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
    public IBinder onBind(Intent intent) {  //onBind方法中返回为空
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        pushService();
        Log.i(" ", "PushServices is onCreate.");  //adb logcat | grep “PushServices is onCreate.”可以看到log
    }

    private static class PushThread extends Thread {    //将后台每隔5s接收消息写成进程
        private Context mContext;    //声明一个变量mContext

        public PushThread(Context context) {
            mContext = context;    //将context赋值给mContext
        }

        @Override
        public void run() {    //程序运行
            super.run();
            //  循环5s查询服务端是否有新消息
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    sleep(5000);//每隔5s
                    // 此处模拟每5秒可以接收到一个消息
                    sendBroacast(); //调用发送广播方法
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void sendBroacast() {   //自定义一个发送广播的方法
            Intent intent = new Intent("com.edu.k12.receiver.PushRecevier");//使用PushReceiver
            mContext.sendBroadcast(intent);//mContext的发送广播

            Intent intent1=new Intent("MusicReceiver");
            intent1.putExtra("eventTime",System.currentTimeMillis());
            mContext.sendBroadcast(intent1);
        }
    }

    private void pushService() {  //推送一个消息
        if (mPushThread == null) {   //如果进程为空
            mPushThread = new PushThread(getApplicationContext()); //创建一个新的进程mPushThread
            mPushThread.start();  //进程开始
        }

    }

    @Override
    public void onDestroy() { //结束Service
        super.onDestroy();
        Log.i(" ", "PushService s is onDestory.");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {  //只要没有执行onDestory，就执行这个方法
        Log.i(" ", "PushServices is StartCommand.");
        return super.onStartCommand(intent, flags, startId);

    }
}
