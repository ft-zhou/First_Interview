package com.ft.first_interview.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ft.first_interview.R;
import com.ft.first_interview.bean.Job;

import java.util.ArrayList;
import java.util.List;

public class JobItemAdapter extends ArrayAdapter<Job> {

    private int layoutId;

    public JobItemAdapter(Context context, int layoutId, List<Job> list) {
        super(context, layoutId, list);
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Job item = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);

        TextView title = (TextView) view.findViewById(R.id.joblistitem_title);
        title.setText(item.getJob_title());
        TextView company = (TextView) view.findViewById(R.id.joblistitem_company);
        company.setText(item.getCompany());
        TextView salary = (TextView) view.findViewById(R.id.joblistitem_salary);
        salary.setText(item.getJob_salary());
        TextView place = (TextView) view.findViewById(R.id.joblistitem_place);
        place.setText(item.getJob_place());
        TextView tag = (TextView) view.findViewById(R.id.joblistitem_tag);
        ArrayList<String> s;
        s = item.getJob_tag();
        String ts = s.get(0);
        for(int i = 1;i < s.size(); i ++){
            ts = ts +" | "+ s.get(i);
        }
        tag.setText(ts);
        TextView posttime = (TextView) view.findViewById(R.id.joblistitem_time);
        posttime.setText(item.getPosttime());

        return view;
    }
}
