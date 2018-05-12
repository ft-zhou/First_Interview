package com.ft.first_interview.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ft.first_interview.R;

public class FirmResumeDetailActivity extends AppCompatActivity {

    private TextView tv_resumedetial_name;
    private Button btn_resume_favorite;
    private Button btn_resume_buy;
    private Button btn_resume_interview;

    private Toast toast;

    private int[] resume_pictures = new int[] { R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e};
    private LinearLayout resumedetail_pictures;
    private LayoutInflater mInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firm_resumedetail);

        tv_resumedetial_name = (TextView) findViewById(R.id.tv_resumedetial_name);
        btn_resume_favorite = (Button) findViewById(R.id.btn_resume_favorite);
        btn_resume_buy = (Button) findViewById(R.id.btn_resume_buy);
        btn_resume_interview = (Button) findViewById(R.id.btn_resume_interview);

        btn_resume_favorite.setOnClickListener(new ResumeDetailListener());
        btn_resume_buy.setOnClickListener(new ResumeDetailListener());
        btn_resume_interview.setOnClickListener(new ResumeDetailListener());

        resumedetail_pictures = (LinearLayout) findViewById(R.id.resumedetail_pictures);
        mInflater = LayoutInflater.from(this);

        for (int i = 0; i < resume_pictures.length; i++) {

            View v = mInflater.inflate(R.layout.horizontal_image_item, resumedetail_pictures, false);
            ImageView img = (ImageView) v.findViewById(R.id.iv_company_image);
            img.setImageResource(resume_pictures[i]);
            resumedetail_pictures.addView(v);
        }
    }

    private class ResumeDetailListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.btn_resume_favorite:
                    if (btn_resume_favorite.getText().toString().equals("收藏")) {
                        btn_resume_favorite.setText("取消收藏");
                        toast = Toast.makeText(getApplicationContext(), "收藏成功！", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                    else{
                        btn_resume_favorite.setText("收藏");
                        toast = Toast.makeText(getApplicationContext(), "已取消收藏！", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                    break;
                case R.id.btn_resume_buy:
                    if(btn_resume_buy.getText().toString().equals("购买简历")) {
                        new AlertDialog.Builder(FirmResumeDetailActivity.this)
                                .setTitle("确认购买")
                                .setMessage("购买该简历需支付10元，当前账户余额100元，是否确认购买该简历？")
                                .setPositiveButton("确定",//提示框的两个按钮
                                        new android.content.DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                toast = Toast.makeText(getApplicationContext(), "购买成功！", Toast.LENGTH_SHORT);
                                                toast.setGravity(Gravity.CENTER, 0, 0);
                                                toast.show();
                                                btn_resume_buy.setText("已购买");
                                            }
                                        })
                                .setNegativeButton("取消", null).create().show();
                    }
                    break;
                case R.id.btn_resume_interview:
                    intent = new Intent(FirmResumeDetailActivity.this, FirmDialogActivity.class);
                    intent.putExtra("username",tv_resumedetial_name.getText().toString());
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    }
}
