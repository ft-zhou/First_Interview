package com.ft.first_interview.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ft.first_interview.Adapter.JobItemAdapter;
import com.ft.first_interview.R;
import com.ft.first_interview.bean.Job;

import java.util.ArrayList;
import java.util.List;

public class ApplyActivity extends AppCompatActivity {

    private ListView apply_joblist;
    private List<Job> apply_data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);

        initList();

        JobItemAdapter itemAdapter = new JobItemAdapter(this, R.layout.job_list_item, apply_data);
        apply_joblist = (ListView) findViewById(R.id.apply_job_list);
        apply_joblist.setAdapter(itemAdapter);

        apply_joblist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent intent = new Intent(ApplyActivity.this, JobDetailActivity.class);
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
            apply_data.add(item);
        }
    }
}
