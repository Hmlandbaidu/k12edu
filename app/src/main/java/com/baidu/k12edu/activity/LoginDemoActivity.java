package com.baidu.k12edu.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.k12edu.R;
import com.baidu.k12edu.adapter.UserListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dengshengjin on 16/9/11.
 */

public class LoginDemoActivity extends AppCompatActivity {
    private Button loginButton;
    private TextView pwdTextView;
    private ListView Ls;
    private UserListAdapter ada;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logindemo);
        loginButton = (Button) findViewById(R.id.ok);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Login success", Toast.LENGTH_SHORT).show();
            }
        });
        pwdTextView = (TextView) findViewById(R.id.cannot);
        pwdTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Login fail", Toast.LENGTH_SHORT).show();
            }
        });
        // ListView与adapter的的关联
        //引入ListView，new adapter适配器，调用listView的setAdapter方法引入adapter
        Ls = (ListView) findViewById(R.id.list_view);
        ada = new UserListAdapter(this);
        Ls.setAdapter(ada);
        //new 数组，添加数据
        List<String> mData = new ArrayList<>();
        mData.add("dengshengjin");
        mData.add("dengshengjin");
        mData.add("dengshengjin");
        mData.add("dengshengjin");
        mData.add("dengshengjin");
        mData.add("dengshengjin");
        mData.add("dengshengjin");
        mData.add("dengshengjin");
        mData.add("dengshengjin");
        mData.add("dengshengjin");
        mData.add("dengshengjin");
        mData.add("dengshengjin");
        mData.add("dengshengjin");
        mData.add("dengshengjin");
        mData.add("dengshengjin");
        //adapter调用setData方法，引入数据
        ada.setData(mData);


    }


}
