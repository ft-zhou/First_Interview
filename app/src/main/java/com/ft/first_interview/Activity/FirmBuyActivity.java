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

public class FirmBuyActivity extends AppCompatActivity {

    private ListView buy_resumelist;
    private List<Resume> buy_data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firm_buy);

        initList();

        ResumeItemAdapter itemAdapter = new ResumeItemAdapter(this, R.layout.recruit_list_item, buy_data);
        buy_resumelist = (ListView) findViewById(R.id.buy_resume_list);
        buy_resumelist.setAdapter(itemAdapter);

        buy_resumelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent intent=new Intent(FirmBuyActivity.this, FirmResumeDetailActivity.class);
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
            buy_data.add(item);
        }
    }
}
