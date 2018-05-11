package com.ft.first_interview.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ft.first_interview.R;
import com.ft.first_interview.bean.Message;

import java.util.List;

public class MessageItemAdapter extends ArrayAdapter<Message> {

    private int layoutId;

    public MessageItemAdapter(Context context, int layoutId, List<Message> list) {
        super(context, layoutId, list);
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Message item = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);

        TextView username = (TextView) view.findViewById(R.id.messagelistitem_username);
        username.setText(item.getUsername());
        TextView content = (TextView) view.findViewById(R.id.messagelistitem_content);
        content.setText(item.getContent());
        ImageView userhead = (ImageView) view.findViewById(R.id.messagelistitem_userhead);
        userhead.setImageResource(R.mipmap.ic_launcher_round);
        TextView posttime = (TextView) view.findViewById(R.id.messagelistitem_time);
        posttime.setText(item.getPosttime());

        return view;
    }
}
