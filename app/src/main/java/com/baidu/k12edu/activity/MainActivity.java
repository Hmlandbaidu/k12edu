package com.baidu.k12edu.activity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.k12edu.R;
import com.baidu.k12edu.service.MusicPlayService;
import com.baidu.k12edu.service.PushService;


public class MainActivity extends AppCompatActivity {
    private TextView mPushText;  //成员变量
    private Button mButton;
    private Button button1;
    private ServiceConnection serviceConnection;
    private MusicPlayService.MyBinder myBinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);    //UI布局
        mPushText = (TextView) findViewById(R.id.push_text);
        mPushText.setText("Here displays push content");
        startPush();
        bindMusic();
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
        button1=(Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               if (myBinder!=null){
                   myBinder.onStartPlay();
               }
            }
        });
    }

    private void startPush() {
        startService(new Intent(MainActivity.this, PushService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(MainActivity.this, PushService.class));
        unbindService(serviceConnection);
    }

    private void bindMusic() {
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder services) {
                myBinder = (MusicPlayService.MyBinder) services;
                Log.i("1","Serice is connected");
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                myBinder=null;
            }
        };
        bindService(new Intent(MainActivity.this, MusicPlayService.class), serviceConnection, Context.BIND_AUTO_CREATE);
    }


}
