package com.baidu.k12edu.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    private TextView loginTextView;
    private TextView pwdTextView;
    private EditText mUserEdit;
    private EditText passWordEdit;
    private ImageView eyeImage;
    private ListView Ls;
    private UserListAdapter ada;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logindemo);
        loginTextView = (TextView) findViewById(R.id.ok);
        loginTextView.setOnClickListener(new View.OnClickListener() {
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
        mUserEdit = (EditText) findViewById(R.id.username_text);
        //mUserEdit.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        //mUserEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
        //adapter调用setData方法，引入数据
        ada.setData(mData);
        mUserEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int textLenth = s.toString().length();
                if (textLenth == 0) {
                    Ls.setVisibility(View.VISIBLE);
                } else {
                    Ls.setVisibility(View.GONE);
                }

            }
        });
        Ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String ite = ada.getItem(i);
                mUserEdit.setText(ite);
                mUserEdit.setSelection(mUserEdit.getText().length());

            }
        });
        passWordEdit=(EditText)findViewById(R.id.userpwd_text);
        passWordEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
        eyeImage=(ImageView)findViewById(R.id.eye);
        eyeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (eyeImage.isSelected()){
                    eyeImage.setSelected(false);
                    //passWordEdit.setInputType(InputType.TYPE_CLASS_TEXT);

                    passWordEdit.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else{
                    eyeImage.setSelected(true);
                    passWordEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

    }
}
