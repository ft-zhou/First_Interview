package com.ft.first_interview.PictureSelector;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ft.first_interview.R;

import java.util.List;

/**
 * 添加上传图片适配器
 */

public class GridViewAdapter extends android.widget.BaseAdapter {

    private Context mContext;
    private List<String> mList;
    private LayoutInflater inflater;
    private int maxNum;

    public GridViewAdapter(Context mContext, List<String> mList, int maxNum) {
        this.mContext = mContext;
        this.mList = mList;
        this.maxNum = maxNum;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        //return mList.size() + 1;//因为最后多了一个添加图片的ImageView
        int count = mList == null ? 1 : mList.size() + 1;
        if (count > maxNum) {
            return mList.size();
        } else {
            return count;
        }
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.grid_item, parent,false);
        ImageView iv = (ImageView) convertView.findViewById(R.id.pic_iv);
        if (position < mList.size()) {
            //代表+号之前的需要正常显示图片
            String picUrl = mList.get(position); //图片路径
            Glide.with(mContext).load(picUrl).into(iv);
        } else {
            iv.setImageResource(R.drawable.add_pic);//最后一个显示加号图片
        }
        return convertView;
    }
}
