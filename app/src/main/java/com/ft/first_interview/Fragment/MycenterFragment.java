package com.ft.first_interview.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ft.first_interview.Activity.ApplyActivity;
import com.ft.first_interview.Activity.FavoriteActivity;
import com.ft.first_interview.Activity.FeedbackActivity;
import com.ft.first_interview.Activity.ResumeActivity;
import com.ft.first_interview.Activity.SettingActivity;
import com.ft.first_interview.R;

public class MycenterFragment extends Fragment {
    RelativeLayout rl_resume;
    RelativeLayout rl_apply;
    RelativeLayout rl_favorite;
    RelativeLayout rl_feedback;
    RelativeLayout rl_setting;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mycenter, container, false);
        rl_resume = (RelativeLayout)view.findViewById(R.id.rl_resume);
        rl_apply = (RelativeLayout)view.findViewById(R.id.rl_apply);
        rl_favorite = (RelativeLayout)view.findViewById(R.id.rl_favorite);
        rl_feedback = (RelativeLayout)view.findViewById(R.id.rl_feedback);
        rl_setting = (RelativeLayout)view.findViewById(R.id.rl_setting);

        rl_resume.setOnClickListener(new MyListener());
        rl_apply.setOnClickListener(new MyListener());
        rl_favorite.setOnClickListener(new MyListener());
        rl_feedback.setOnClickListener(new MyListener());
        rl_setting.setOnClickListener(new MyListener());

        return view;
    }

    public class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.rl_resume:
                    startActivity(new Intent(getActivity(),ResumeActivity.class));
                    break;
                case R.id.rl_apply:
                    startActivity(new Intent(getActivity(),ApplyActivity.class));
                    break;
                case R.id.rl_favorite:
                    startActivity(new Intent(getActivity(),FavoriteActivity.class));
                    break;
                case R.id.rl_feedback:
                    startActivity(new Intent(getActivity(),FeedbackActivity.class));
                    break;
                case R.id.rl_setting:
                    startActivity(new Intent(getActivity(),SettingActivity.class));
                    break;
                default:
                    break;
            }
        }
    }

}
