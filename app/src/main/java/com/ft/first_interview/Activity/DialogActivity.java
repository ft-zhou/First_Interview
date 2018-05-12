package com.ft.first_interview.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.ft.first_interview.R;

public class DialogActivity extends AppCompatActivity {

    private TextView dialog_title;
    private EditText dialog_edittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        getSupportActionBar().hide();

        dialog_title = (TextView) findViewById(R.id.dialog_title);
        dialog_edittext = (EditText) findViewById(R.id.dialog_edittext);

        Intent intent = getIntent();//获取传来的intent对象
        String data = intent.getStringExtra("username");//获取键值对的键名
        dialog_title.setText(data);
    }
}
