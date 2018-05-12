package com.ft.first_interview.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ft.first_interview.Activity.GoodsDetailActivity;
import com.ft.first_interview.Adapter.MessageItemAdapter;
import com.ft.first_interview.R;
import com.ft.first_interview.bean.Goods;
import com.ft.first_interview.bean.Job;
import com.ft.first_interview.bean.Message;

import java.util.ArrayList;
import java.util.List;

public class ShoppingFragment extends Fragment {

    private ListView shoppinglist;
    private List<Goods> data = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shopping, container, false);
        initList();

        GoodsItemAdapter itemAdapter = new GoodsItemAdapter(getActivity(), R.layout.shopping_list_item, data);
        shoppinglist = (ListView)view.findViewById(R.id.shopping_list);
        shoppinglist.setAdapter(itemAdapter);

        shoppinglist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent intent = new Intent(getContext(), GoodsDetailActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void initList() {
        for (int i = 0; i < 10; i++) {
            Goods item = new Goods();
            item.setTitle("西服套装男三件套商务职业正装修身西装男士");
            item.setImage("R.drawable.shopping_image");
            item.setPrice("161");
            item.setNum("已有666个人参团");
            data.add(item);
        }
    }

    private class GoodsItemAdapter extends ArrayAdapter<Goods> {

        private int layoutId;

        public GoodsItemAdapter(Context context, int layoutId, List<Goods> list) {
            super(context, layoutId, list);
            this.layoutId = layoutId;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Goods item = getItem(position);
            View view = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);

            TextView title = (TextView) view.findViewById(R.id.shoppinglistitem_title);
            title.setText(item.getTitle());
            ImageView image = (ImageView) view.findViewById(R.id.shopplistitem_image);
            image.setImageResource(R.drawable.shopping_image);
            TextView price = (TextView) view.findViewById(R.id.shoppinglistitem_price);
            price.setText(item.getPrice());
            TextView num = (TextView) view.findViewById(R.id.shoppinglistitem_num);
            num.setText(item.getNum());

            return view;
        }
    }
}
