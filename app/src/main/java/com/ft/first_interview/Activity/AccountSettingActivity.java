package com.ft.first_interview.Activity;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ft.first_interview.R;

import org.w3c.dom.Text;

public class AccountSettingActivity extends AppCompatActivity {

    private RelativeLayout rl_setting_username;
    private TextView tv_setting_username;
    private RelativeLayout rl_setting_email;
    private TextView tv_setting_email;
    private RelativeLayout rl_setting_phone;
    private TextView tv_setting_phone;
    private RelativeLayout rl_setting_wechat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);

        rl_setting_username = (RelativeLayout) findViewById(R.id.rl_setting_username);
        tv_setting_username = (TextView) findViewById(R.id.tv_setting_username);
        rl_setting_email = (RelativeLayout) findViewById(R.id.rl_setting_email);
        tv_setting_email = (TextView) findViewById(R.id.tv_setting_email);
        rl_setting_phone = (RelativeLayout) findViewById(R.id.rl_setting_phone);
        tv_setting_phone = (TextView) findViewById(R.id.tv_setting_phone);
        rl_setting_wechat = (RelativeLayout) findViewById(R.id.rl_setting_wechat);

        rl_setting_username.setOnClickListener(new asOnClickListener());
        rl_setting_email.setOnClickListener(new asOnClickListener());
        rl_setting_phone.setOnClickListener(new asOnClickListener());
        rl_setting_wechat.setOnClickListener(new asOnClickListener());
    }

    private class asOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rl_setting_username:
                    LayoutInflater factory = LayoutInflater.from(AccountSettingActivity.this);//提示框
                    final View view = factory.inflate(R.layout.editbox_layout, null);//这里必须是final的
                    final EditText edit = (EditText)view.findViewById(R.id.editText);//获得输入框对象
                    new AlertDialog.Builder(AccountSettingActivity.this)
                            .setTitle("请输入用户名（10个字以内）")//提示框标题
                            .setView(view)
                            .setPositiveButton("确定",//提示框的两个按钮
                                    new android.content.DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if(edit.getText().length() > 10 ) {
                                                Toast.makeText(getApplicationContext(),
                                                        "修改失败！用户名不能超过10个字！",Toast.LENGTH_SHORT).show();
                                            }else {
                                                tv_setting_username.setText(edit.getText().toString().trim());
                                            }
                                        }
                                    })
                            .setNegativeButton("取消", null).create().show();
                    break;
                case R.id.rl_setting_email:
                    LayoutInflater factory2 = LayoutInflater.from(AccountSettingActivity.this);//提示框
                    final View view2 = factory2.inflate(R.layout.editbox_layout, null);//这里必须是final的
                    final EditText edit2=(EditText)view2.findViewById(R.id.editText);//获得输入框对象
                    new AlertDialog.Builder(AccountSettingActivity.this)
                            .setTitle("请输入邮箱")//提示框标题
                            .setView(view2)
                            .setPositiveButton("确定",//提示框的两个按钮
                                    new android.content.DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            tv_setting_email.setText(edit2.getText().toString().trim());
                                        }
                                    })
                            .setNegativeButton("取消", null).create().show();
                    break;
                case R.id.rl_setting_phone:
                    Intent intent = new Intent(AccountSettingActivity.this,ChangePhoneActivity.class);
                    startActivityForResult(intent,20);
                    break;
                case R.id.rl_setting_wechat:
                    //绑定微信
                    break;
                default:
                    break;
            }
        }
    }

    //结果处理函数，当从secondActivity中返回时调用此函数
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("onActivtiyResult-requestCode:" +requestCode+",resultCode:" +resultCode);
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 20 && resultCode == RESULT_OK){
            Bundle bundle = data.getExtras();
            String text = null;
            if(bundle != null)
                text = bundle.getString("phone");
            System.out.println("phone:"+text);
            tv_setting_phone.setText(text);
        }
    }
}
