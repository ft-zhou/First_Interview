package com.ft.first_interview.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ft.first_interview.R;

import org.w3c.dom.Text;

public class FirmDialogActivity extends AppCompatActivity {

    private TextView firm_dialog_title;
    private ImageView firm_dialog_back;
    private ImageView firm_dialog_video;
    private EditText firm_dialog_edittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firm_dialog);
        getSupportActionBar().hide();

        firm_dialog_title = (TextView) findViewById(R.id.firm_dialog_title);
        firm_dialog_back = (ImageView) findViewById(R.id.firm_dialog_back);
        firm_dialog_video = (ImageView) findViewById(R.id.firm_dialog_video);
        firm_dialog_edittext = (EditText) findViewById(R.id.firm_dialog_edittext);

        Intent intent = getIntent();//获取传来的intent对象
        String data = intent.getStringExtra("username");//获取键值对的键名
        firm_dialog_title.setText(data);
    }
}
