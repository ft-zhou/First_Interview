package com.ft.first_interview.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ft.first_interview.R;

public class LoginActivity extends AppCompatActivity{

    private TimeCount timeCount;

    private Boolean firstlogin = true;
    private EditText et_cellphone;
    private EditText et_vcode;
    private Button btn_getVcode;
    private Button btn_login;
    private ImageView iv_wechat_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_cellphone = (EditText) findViewById(R.id.et_cellphone);
        et_vcode = (EditText) findViewById(R.id.et_vcode);
        btn_getVcode = (Button) findViewById(R.id.btn_getVcode);
        btn_login = (Button) findViewById(R.id.btn_login);
        iv_wechat_login = (ImageView) findViewById(R.id.iv_wechat_login);

        btn_getVcode.setOnClickListener(new loginOnClickListener());
        btn_login.setOnClickListener(new loginOnClickListener());
        iv_wechat_login.setOnClickListener(new loginOnClickListener());
    }

    private class loginOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent;
            if(v.getId() == R.id.btn_getVcode) {
                if(et_cellphone.length() == 11) {
                    //获取验证码
                    Toast.makeText(getApplicationContext(),"验证码已发送",Toast.LENGTH_SHORT).show();
                    timeCount = new TimeCount(60000, 1000, btn_getVcode);
                    timeCount.start();
                } else {
                    Toast.makeText(getApplicationContext(),"请输入正确的手机号",Toast.LENGTH_SHORT).show();
                }
            }else if(v.getId() == R.id.btn_login) {
                //验证验证码
                if(et_cellphone.getText().toString().trim().length() != 11 || et_vcode.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(),"请输入手机号及验证码",Toast.LENGTH_SHORT).show();
                }
                else {
                    //验证是否第一次登录
                    timeCount.cancel();
                    timeCount.onFinish();
                    if (firstlogin) {
                        intent = new Intent(LoginActivity.this,ChoosetypeActivity.class);
                        startActivity(intent);
                    }else {
                        intent = new Intent(LoginActivity.this,NavigationActivity.class);
                        startActivity(intent);
                    }
                }

            }else if(v.getId() == R.id.iv_wechat_login) {
                //微信登录
                //验证是否第一次登录
                if (firstlogin) {
                    intent = new Intent(LoginActivity.this,ChoosetypeActivity.class);
                    startActivity(intent);
                }else {
                    intent = new Intent(LoginActivity.this,NavigationActivity.class);
                    startActivity(intent);
                }
            }
        }
    }

    private class TimeCount extends CountDownTimer {

        private Button btn_count;

        public TimeCount(long millisInFuture, long countDownInterval,Button btn_count) {
            super(millisInFuture, countDownInterval);
            this.btn_count = btn_count;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            btn_count.setEnabled(false);
            btn_count.setText(millisUntilFinished / 1000 + "秒");
        }

        @Override
        public void onFinish() {
            btn_count.setEnabled(true);
            btn_count.setText("获取验证码");
        }

    }
}
