package com.ft.first_interview.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.ft.first_interview.Fragment.HomeFragment;
import com.ft.first_interview.Fragment.MycenterFragment;
import com.ft.first_interview.Fragment.NotificationFragment;
import com.ft.first_interview.R;

public class NavigationActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setTitle(R.string.app_name);
                    changeFragment(new HomeFragment());
                    return true;
                case R.id.navigation_notifications:
                    setTitle(R.string.title_notifications);
                    changeFragment(new NotificationFragment());
                    return true;
                case R.id.navigation_mycenter:
                    setTitle(R.string.title_mycenter);
                    changeFragment(new MycenterFragment());
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
//        setCustomActionBar();

        mFragmentManager = getSupportFragmentManager();
        changeFragment(new HomeFragment());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void changeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_content, fragment);
        fragmentTransaction.commit();
    }

//    private void setCustomActionBar() {
//        ActionBar.LayoutParams lp =new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
//        View mActionBarView = LayoutInflater.from(this).inflate(R.layout.actionbar_layout, null);
//        ActionBar actionBar = getActionBar();
//        actionBar.setCustomView(mActionBarView, lp);
//        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        actionBar.setDisplayShowCustomEnabled(true);
//        actionBar.setDisplayShowHomeEnabled(false);
//        actionBar.setDisplayShowTitleEnabled(false);
//    }

}
