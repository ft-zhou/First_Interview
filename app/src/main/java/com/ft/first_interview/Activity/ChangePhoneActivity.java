package com.ft.first_interview.Activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ft.first_interview.R;

public class ChangePhoneActivity extends AppCompatActivity {

    private TimeCount tCount;
    private EditText et_change_cellphone;
    private EditText et_change_vcode;
    private Button btn_getcode;
    private Button btn_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone);

        et_change_cellphone = (EditText) findViewById(R.id.et_change_cellphone);
        et_change_vcode = (EditText) findViewById(R.id.et_change_vcode);
        btn_getcode = (Button) findViewById(R.id.btn_getcode);
        btn_confirm = (Button) findViewById(R.id.btn_confirm);

        btn_getcode.setOnClickListener(new changeOnClickListener());
        btn_confirm.setOnClickListener(new changeOnClickListener());
    }

    private class changeOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent;
            if(v.getId() == R.id.btn_getcode) {
                if(et_change_cellphone.length() == 11) {
                    //获取验证码
                    Toast.makeText(getApplicationContext(),"验证码已发送",Toast.LENGTH_SHORT).show();
                    tCount = new TimeCount(60000, 1000, btn_getcode);
                    tCount.start();
                } else {
                    Toast.makeText(getApplicationContext(),"请输入正确的手机号",Toast.LENGTH_SHORT).show();
                }
            }else if(v.getId() == R.id.btn_confirm) {
                if(et_change_cellphone.getText().toString().trim().length() != 11
                        || et_change_vcode.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(),"请输入手机号及验证码",Toast.LENGTH_SHORT).show();
                }
                else {
                    //验证验证码
                    tCount.cancel();
                    tCount.onFinish();
                    Toast.makeText(getApplicationContext(),"手机号绑定成功！",Toast.LENGTH_SHORT).show();
                    intent = getIntent();
                    //这里使用bundle绷带来传输数据
                    Bundle bundle = new Bundle();
                    //传输的内容仍然是键值对的形式
                    bundle.putString("phone", et_change_cellphone.getText().toString().trim());//回发的消息
                    System.out.println("newphone:"+et_change_cellphone.getText().toString().trim());
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    System.out.println("setResult"+RESULT_OK);
                    finish();
                }

            }
        }
    }

    private class TimeCount extends CountDownTimer {

        private Button btn_count;

        public TimeCount(long millisInFuture, long countDownInterval, Button btn_count) {
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
