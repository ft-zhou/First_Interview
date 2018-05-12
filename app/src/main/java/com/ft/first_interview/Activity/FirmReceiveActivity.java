package com.ft.first_interview.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ft.first_interview.Adapter.ResumeItemAdapter;
import com.ft.first_interview.R;
import com.ft.first_interview.bean.Resume;

import java.util.ArrayList;
import java.util.List;

public class FirmReceiveActivity extends AppCompatActivity {

    private ListView receive_resumelist;
    private List<Resume> receive_data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firm_receive);

        initList();

        ResumeItemAdapter itemAdapter = new ResumeItemAdapter(this, R.layout.recruit_list_item, receive_data);
        receive_resumelist = (ListView) findViewById(R.id.receive_resume_list);
        receive_resumelist.setAdapter(itemAdapter);

        receive_resumelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent intent=new Intent(FirmReceiveActivity.this, FirmResumeDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initList() {
        for (int i = 0; i < 10; i++) {
            Resume item = new Resume();
            item.setName("王先生");
            item.setDetailposition("Android开发工程师");
            item.setWorkplace("上海");
            item.setSalary("8000-10000/月");
            item.setPosttime("5分钟前");
            item.setAge("22岁");
            item.setRecord("本科");
            item.setGraduation("应届毕业生");
            item.setMajor("软件工程");
            receive_data.add(item);
        }
    }
}
