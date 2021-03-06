package com.ft.first_interview.Activity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_share:
                Toast.makeText(this, "share", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_report:
                Toast.makeText(this, "report", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobdetail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
                    if (btn_job_favorite.getText().equals("收藏")) {
                        btn_job_favorite.setText("取消收藏");
                        toast = Toast.makeText(getApplicationContext(), "收藏成功！", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                    else{
                        btn_job_favorite.setText("收藏");
                        toast = Toast.makeText(getApplicationContext(), "已取消收藏！", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
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
