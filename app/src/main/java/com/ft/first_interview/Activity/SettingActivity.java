package com.ft.first_interview.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.ft.first_interview.R;

public class SettingActivity extends AppCompatActivity {

    private Switch switch_post;
    private RelativeLayout rl_account_setting;
    private RelativeLayout rl_about;
    private Button btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        switch_post = (Switch) findViewById(R.id.switch_post);
        rl_account_setting = (RelativeLayout) findViewById(R.id.rl_account_setting);
        rl_about = (RelativeLayout) findViewById(R.id.rl_about);
        btn_logout = (Button) findViewById(R.id.btn_logout);

        switch_post.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    //开启通知
                    Toast.makeText(getApplicationContext(),"已开启通知！",Toast.LENGTH_SHORT).show();
                }else {
                    //关闭通知
                    Toast.makeText(getApplicationContext(),"已关闭通知！",Toast.LENGTH_SHORT).show();
                }
            }
        });
        rl_account_setting.setOnClickListener(new SOnClickListener());
        rl_about.setOnClickListener(new SOnClickListener());
        btn_logout.setOnClickListener(new SOnClickListener());
    }

    private class SOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent;
            if(v.getId() == R.id.rl_account_setting) {
                intent = new Intent(SettingActivity.this,AccountSettingActivity.class);
                startActivity(intent);
            }else if(v.getId() == R.id.rl_about) {
                intent = new Intent(SettingActivity.this,AboutActivity.class);
                startActivity(intent);
            }else if(v.getId() == R.id.btn_logout) {
                //退出登录
                intent = new Intent(SettingActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
