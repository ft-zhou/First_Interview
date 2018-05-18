package com.ft.first_interview.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ft.first_interview.Activity.DialogActivity;
import com.ft.first_interview.Adapter.MessageItemAdapter;
import com.ft.first_interview.R;
import com.ft.first_interview.bean.Message;

import java.util.ArrayList;
import java.util.List;

public class NotificationFragment extends Fragment {

    private ListView messagelist;
    private List<Message> data = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        initList();

        MessageItemAdapter itemAdapter = new MessageItemAdapter(getActivity(), R.layout.message_list_item, data);
        messagelist = (ListView)view.findViewById(R.id.message_list);
        messagelist.setAdapter(itemAdapter);

        messagelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//                TextView choose_company_name = (TextView) messagelist.getChildAt(arg2).findViewById(R.id.tv_company_name);
//                Toast toast = Toast.makeText(getContext(), choose_company_name.getText().toString(), Toast.LENGTH_SHORT);
//                toast.setGravity(Gravity.CENTER, 0, 0);
//                toast.show();
                Intent intent = new Intent(getContext(), DialogActivity.class);
                intent.putExtra("username","一面科技");
                startActivity(intent);
            }
        });

        return view;
    }

    private void initList() {
        for (int i = 0; i < 10; i++) {
            Message item = new Message();
            item.setUsername("一面科技");
            item.setUserhead("R.mipmap.ic_launcher_round");
            item.setContent("这里是聊天内容……");
            item.setPosttime("2018/05/01");
            data.add(item);
        }
    }
}
