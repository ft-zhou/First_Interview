package com.ft.first_interview.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ft.first_interview.R;

import java.util.ArrayList;
import java.util.List;

public class IntroductionFragment extends Fragment {

    private int[] mImgIds = new int[] { R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e};
    private LinearLayout ll_iv_horizontal;
    private LayoutInflater mInflater;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company_introduction, container, false);

        ll_iv_horizontal = (LinearLayout) view.findViewById(R.id.ll_iv_horizontal);
        mInflater = LayoutInflater.from(getContext());

        for (int i = 0; i < mImgIds.length; i++) {

            View v = mInflater.inflate(R.layout.horizontal_image_item, ll_iv_horizontal, false);
            ImageView img = (ImageView) v.findViewById(R.id.iv_company_image);
            img.setImageResource(mImgIds[i]);
            ll_iv_horizontal.addView(v);
        }

        return view;
    }
}
