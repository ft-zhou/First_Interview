package com.ft.first_interview.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.ft.first_interview.Adapter.JobItemAdapter;
import com.ft.first_interview.R;
import com.ft.first_interview.bean.Job;

import java.util.ArrayList;
import java.util.List;

public class FirmRecruitActivity extends AppCompatActivity {

    private ListView recruit_joblist;
    private List<Job> recruit_data = new ArrayList<>();
    private Button btn_recruit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firm_recruit);

        btn_recruit = (Button) findViewById(R.id.btn_recruit);
        btn_recruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirmRecruitActivity.this, FirmPublishActivity.class);
                startActivity(intent);
            }
        });

        initList();

        JobItemAdapter itemAdapter = new JobItemAdapter(this, R.layout.job_list_item, recruit_data);
        recruit_joblist = (ListView) findViewById(R.id.recruit_job_list);
        recruit_joblist.setAdapter(itemAdapter);

        recruit_joblist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent intent = new Intent(FirmRecruitActivity.this, JobDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initList() {
        for (int i = 0; i < 10; i++) {
            Job item = new Job();
            item.setCompanyname("一面科技");
            item.setJob_title("Android开发工程师");
            item.setJob_place("上海-闵行区");
            item.setJob_salary("8000-10000/月");
            item.setPosttime("5分钟前");
            ArrayList<String> tag = new ArrayList<>();
            tag.add("五险一金");
            tag.add("应届毕业生");
            item.setJob_tag(tag);
            recruit_data.add(item);
        }
    }
}
