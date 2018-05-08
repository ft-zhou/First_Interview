package com.ft.first_interview.Activity;

import android.support.annotation.ColorRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ft.first_interview.Fragment.AllpositionFragment;
import com.ft.first_interview.Fragment.HomeFragment;
import com.ft.first_interview.Fragment.IntroductionFragment;
import com.ft.first_interview.R;

public class CompanyInformation extends AppCompatActivity {

    private FragmentManager mFragmentManager;

    private FrameLayout frameLayout;
    private ImageView company_head;

    private RelativeLayout rl_company_introduction;
    private RelativeLayout rl_company_allposition;
    private TextView tv_company_introduction;
    private ImageView iv_company_introduction;
    private TextView tv_company_allposition;
    private ImageView iv_company_allposition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_information);

        frameLayout = (FrameLayout) findViewById(R.id.company_content);
        company_head = (ImageView) findViewById(R.id.company_head);

        rl_company_introduction = (RelativeLayout) findViewById(R.id.rl_company_introduction);
        rl_company_allposition = (RelativeLayout) findViewById(R.id.rl_company_allposition);
        rl_company_introduction.setOnClickListener(new CompanyListener());
        rl_company_allposition.setOnClickListener(new CompanyListener());
        tv_company_introduction = (TextView) findViewById(R.id.tv_company_introduction);
        iv_company_introduction = (ImageView) findViewById(R.id.iv_company_introduction);
        tv_company_allposition = (TextView) findViewById(R.id.tv_company_allposition);
        iv_company_allposition = (ImageView) findViewById(R.id.iv_company_allposition);

        mFragmentManager = getSupportFragmentManager();
        changeFragment(new IntroductionFragment());

        frameLayout.setFocusable(false);
        company_head.setFocusable(true);
        company_head.setFocusableInTouchMode(true);
        company_head.requestFocus();
    }

    private class CompanyListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.rl_company_introduction) {
                tv_company_introduction.setTextColor(getResources().getColor(R.color.colorPrimary));
                iv_company_introduction.setVisibility(View.VISIBLE);
                tv_company_allposition.setTextColor(getResources().getColor(R.color.darkgray));
                iv_company_allposition.setVisibility(View.GONE);
                changeFragment(new IntroductionFragment());
            }
            else if (v.getId() == R.id.rl_company_allposition) {
                tv_company_introduction.setTextColor(getResources().getColor(R.color.darkgray));
                iv_company_introduction.setVisibility(View.GONE);
                tv_company_allposition.setTextColor(getResources().getColor(R.color.colorPrimary));
                iv_company_allposition.setVisibility(View.VISIBLE);
                changeFragment(new AllpositionFragment());
            }
        }
    }

    private void changeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.company_content, fragment);
        fragmentTransaction.commit();
    }
}
