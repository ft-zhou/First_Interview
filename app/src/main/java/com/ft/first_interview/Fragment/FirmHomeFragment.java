package com.ft.first_interview.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.ft.first_interview.Activity.FirmResumeDetailActivity;
import com.ft.first_interview.Activity.JobDetailActivity;
import com.ft.first_interview.Activity.SearchActivity;
import com.ft.first_interview.Adapter.JobItemAdapter;
import com.ft.first_interview.Adapter.ResumeItemAdapter;
import com.ft.first_interview.R;
import com.ft.first_interview.bean.Job;
import com.ft.first_interview.bean.Resume;

import java.util.ArrayList;
import java.util.List;

public class FirmHomeFragment extends Fragment {

    private EditText firm_etSearch;
    private ListView resumelist;
    private List<Resume> data = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_firm_home, container,false);
        firm_etSearch= (EditText) view.findViewById(R.id.firm_etSearch) ;
        firm_etSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });

        initList();

        ResumeItemAdapter itemAdapter = new ResumeItemAdapter(getActivity(), R.layout.recruit_list_item, data);
        resumelist = (ListView)view.findViewById(R.id.resume_list);
        resumelist.setAdapter(itemAdapter);

        resumelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent intent=new Intent(getContext(), FirmResumeDetailActivity.class);
                startActivity(intent);
            }
        });

        View header = View.inflate(getActivity(), R.layout.headview, null);//自定义一个头布局和顶部执行动画的布局等高就行
        resumelist.addHeaderView(header);//加载头布局

        return view;
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
            data.add(item);
        }
    }
}