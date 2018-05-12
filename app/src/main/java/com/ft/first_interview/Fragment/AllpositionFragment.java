package com.ft.first_interview.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.ft.first_interview.Activity.FavoriteActivity;
import com.ft.first_interview.Activity.JobDetailActivity;
import com.ft.first_interview.Adapter.JobItemAdapter;
import com.ft.first_interview.R;
import com.ft.first_interview.bean.Job;

import java.util.ArrayList;
import java.util.List;

public class AllpositionFragment extends Fragment {

    private ListView allposition_list;
    private List<Job> allposition_data = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company_allposition, container, false);

        initList();

        JobItemAdapter itemAdapter = new JobItemAdapter(getContext(), R.layout.job_list_item, allposition_data);
        allposition_list = (ListView) view.findViewById(R.id.allposition_list);
        allposition_list.setAdapter(itemAdapter);
        setListViewHeightBasedOnChildren(allposition_list);

        allposition_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent intent = new Intent(getActivity(), JobDetailActivity.class);
                startActivity(intent);
            }
        });

        return view;
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
            allposition_data.add(item);
        }
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }
}
