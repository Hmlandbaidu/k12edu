package com.baidu.k12edu.activity;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.k12edu.R;
import com.baidu.k12edu.service.MusicPlayService;
import com.baidu.k12edu.service.PushService;


public class MainActivity extends AppCompatActivity { //主程序继承AppCompatActivity
    private TextView mPushText;  //成员变量
    private Button mButton;
    private Button button1;
    private ServiceConnection serviceConnection;
    private MusicRecevier musicRecevier;
    private MusicPlayService.MyBinder myBinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);    //UI布局
        mPushText = (TextView) findViewById(R.id.push_text);
        mPushText.setText("Here displays push content");
        startPush();
        bindMusic();
        regitMusic();
        mButton = (Button) findViewById(R.id.button); //赋值button
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                //startActivity(intent);
                startActivity(new Intent(MainActivity.this,LoginDemoActivity.class));
            }
        });
        button1 = (Button) findViewById(R.id.button1);//赋值button1
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  //定义点击事件
                if (myBinder != null) {   //如果不为空
                    myBinder.onStartPlay();   //则开始播放
                }
            }
        });

    }

    private void startPush() {   //开始推送消息
        startService(new Intent(MainActivity.this, PushService.class));  //从MainActivity跳到PushService
    }

    @Override
    protected void onDestroy() {   //点击返回键
        super.onDestroy();
        stopService(new Intent(MainActivity.this, PushService.class));  //结束Service
        unbindService(serviceConnection);  //解绑Service
        unregisterReceiver(musicRecevier);  //暂停播放
    }

    private void bindMusic() {  //绑定Service
        serviceConnection = new ServiceConnection() { //建立新的对象，有两个方法
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder services) {
                myBinder = (MusicPlayService.MyBinder) services;
                Log.i("1", "Serice is connected");
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                myBinder = null;
            }
        };
        bindService(new Intent(MainActivity.this, MusicPlayService.class), serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void regitMusic() {
        musicRecevier=new MusicRecevier();
        registerReceiver(musicRecevier, new IntentFilter(MusicRecevier.MUSIC_ACTION));
    }


    private class MusicRecevier extends BroadcastReceiver {
        public static final String MUSIC_ACTION = "MusicReceiver";

        @Override
        public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("MusicReceiver")){
                    Log.i("1","MusicReceiver is Recevied");
                    long eventTime=intent.getLongExtra("eventTime",0);
                    mPushText.setText(eventTime+"");
                }
        }
    }
}
