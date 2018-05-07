package com.ft.first_interview.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ft.first_interview.R;

public class JobDetailActivity extends AppCompatActivity {

    private LinearLayout ll_to_company;
    private TextView tv_company_name;
    private Button btn_job_favorite;
    private Button btn_job_delivery;
    private Button btn_job_consult;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobdetail);

        tv_company_name = (TextView) findViewById(R.id.tv_company_name);
        ll_to_company = (LinearLayout) findViewById(R.id.ll_to_company);
        btn_job_favorite = (Button) findViewById(R.id.btn_job_favorite);
        btn_job_delivery = (Button) findViewById(R.id.btn_job_delivery);
        btn_job_consult = (Button) findViewById(R.id.btn_job_consult);

        ll_to_company.setOnClickListener(new JobDetailListener());
        btn_job_favorite.setOnClickListener(new JobDetailListener());
        btn_job_delivery.setOnClickListener(new JobDetailListener());
        btn_job_consult.setOnClickListener(new JobDetailListener());
    }

    private class JobDetailListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.ll_to_company:
                    intent = new Intent(JobDetailActivity.this, CompanyInformation.class);
                    intent.putExtra("username",tv_company_name.getText().toString());
                    startActivity(intent);
                    break;
                case R.id.btn_job_favorite:
                    toast = Toast.makeText(getApplicationContext(), "收藏成功！", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    break;
                case R.id.btn_job_delivery:
                    toast = Toast.makeText(getApplicationContext(), "简历投递成功！", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    break;
                case R.id.btn_job_consult:
                    intent = new Intent(JobDetailActivity.this, DialogActivity.class);
                    intent.putExtra("username",tv_company_name.getText().toString());
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    }
}
