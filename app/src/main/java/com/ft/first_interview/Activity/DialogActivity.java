package com.ft.first_interview.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ft.first_interview.R;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        Intent intent = getIntent();//获取传来的intent对象
        String data = intent.getStringExtra("username");//获取键值对的键名
        this.setTitle(data);
    }
}
