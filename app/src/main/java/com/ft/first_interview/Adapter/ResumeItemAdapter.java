package com.ft.first_interview.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ft.first_interview.R;
import com.ft.first_interview.bean.Resume;

import java.util.List;

public class ResumeItemAdapter extends ArrayAdapter<Resume> {

    private int layoutId;

    public ResumeItemAdapter(Context context, int layoutId, List<Resume> list) {
        super(context, layoutId, list);
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Resume item = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);

        TextView name = (TextView) view.findViewById(R.id.recruitlistitem_name);
        name.setText(item.getName());
        TextView age = (TextView) view.findViewById(R.id.recruitlistitem_age);
        age.setText(item.getAge());
        TextView posit = (TextView) view.findViewById(R.id.recruitlistitem_detailposition);
        posit.setText(item.getDetailposition());
        TextView salary = (TextView) view.findViewById(R.id.recruitlistitem_salary);
        salary.setText(item.getSalary());
        TextView place = (TextView) view.findViewById(R.id.recruitlistitem_place);
        place.setText(item.getWorkplace());
        TextView tag = (TextView) view.findViewById(R.id.recruitlistitem_tag);
        String s = null;
        s = item.getMajor()+" | "+item.getRecord()+" | " +item.getGraduation();
        tag.setText(s);
        TextView posttime = (TextView) view.findViewById(R.id.recruitlistitem_time);
        posttime.setText(item.getPosttime());

        return view;
    }
}

