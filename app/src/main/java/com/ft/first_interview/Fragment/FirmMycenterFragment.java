package com.ft.first_interview.Fragment;

import android.app.Activity;
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
import com.ft.first_interview.Activity.FirmBuyActivity;
import com.ft.first_interview.Activity.FirmCompanyActivity;
import com.ft.first_interview.Activity.FirmFavoriteActivity;
import com.ft.first_interview.Activity.FirmReceiveActivity;
import com.ft.first_interview.Activity.FirmRecruitActivity;
import com.ft.first_interview.Activity.InviteActivity;
import com.ft.first_interview.Activity.ResumeActivity;
import com.ft.first_interview.Activity.SettingActivity;
import com.ft.first_interview.Activity.WalletActivity;
import com.ft.first_interview.R;

public class FirmMycenterFragment extends Fragment {
    RelativeLayout rl_firm_company;
    RelativeLayout rl_firm_recruit;
    RelativeLayout rl_firm_buy;
    RelativeLayout rl_firm_receive;
    RelativeLayout rl_firm_favorite;
    RelativeLayout rl_firm_wallet;
    RelativeLayout rl_firm_feedback;
    RelativeLayout rl_firm_setting;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_firm_mycenter, container, false);
        rl_firm_company = (RelativeLayout)view.findViewById(R.id.rl_firm_company);
        rl_firm_recruit = (RelativeLayout)view.findViewById(R.id.rl_firm_recruit);
        rl_firm_buy = (RelativeLayout)view.findViewById(R.id.rl_firm_buy);
        rl_firm_receive = (RelativeLayout)view.findViewById(R.id.rl_firm_receive);
        rl_firm_favorite = (RelativeLayout)view.findViewById(R.id.rl_firm_favorite);
        rl_firm_wallet = (RelativeLayout)view.findViewById(R.id.rl_firm_wallet);
        rl_firm_feedback = (RelativeLayout)view.findViewById(R.id.rl_firm_feedback);
        rl_firm_setting = (RelativeLayout)view.findViewById(R.id.rl_firm_setting);

        rl_firm_company.setOnClickListener(new MyFirmListener());
        rl_firm_recruit.setOnClickListener(new MyFirmListener());
        rl_firm_buy.setOnClickListener(new MyFirmListener());
        rl_firm_receive.setOnClickListener(new MyFirmListener());
        rl_firm_favorite.setOnClickListener(new MyFirmListener());
        rl_firm_wallet.setOnClickListener(new MyFirmListener());
        rl_firm_feedback.setOnClickListener(new MyFirmListener());
        rl_firm_setting.setOnClickListener(new MyFirmListener());

        return view;
    }

    private class MyFirmListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.rl_firm_company:
                    startActivity(new Intent(getActivity(),FirmCompanyActivity.class));
                    break;
                case R.id.rl_firm_recruit:
                    startActivity(new Intent(getActivity(),FirmRecruitActivity.class));
                    break;
                case R.id.rl_firm_buy:
                    startActivity(new Intent(getActivity(),FirmBuyActivity.class));
                    break;
                case R.id.rl_firm_receive:
                    startActivity(new Intent(getActivity(),FirmReceiveActivity.class));
                    break;
                case R.id.rl_firm_favorite:
                    startActivity(new Intent(getActivity(),FirmFavoriteActivity.class));
                    break;
                case R.id.rl_firm_wallet:
                    startActivity(new Intent(getActivity(),WalletActivity.class));
                    break;
                case R.id.rl_firm_feedback:
                    startActivity(new Intent(getActivity(),FeedbackActivity.class));
                    break;
                case R.id.rl_firm_setting:
                    startActivity(new Intent(getActivity(),SettingActivity.class));
                    break;
                default:
                    break;
            }
        }
    }

}
