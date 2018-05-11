package com.ft.first_interview.Activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.ft.first_interview.Fragment.FirmHomeFragment;
import com.ft.first_interview.Fragment.FirmMycenterFragment;
import com.ft.first_interview.Fragment.FirmNotificationFragment;
import com.ft.first_interview.Fragment.HomeFragment;
import com.ft.first_interview.Fragment.MycenterFragment;
import com.ft.first_interview.Fragment.NotificationFragment;
import com.ft.first_interview.R;

public class FirmNavigationActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setTitle(R.string.app_name);
                    changeFragment(new FirmHomeFragment());
                    return true;
                case R.id.navigation_notifications:
                    setTitle(R.string.title_notifications);
                    changeFragment(new FirmNotificationFragment());
                    return true;
                case R.id.navigation_mycenter:
                    setTitle(R.string.title_mycenter);
                    changeFragment(new FirmMycenterFragment());
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firm_navigation);

        mFragmentManager = getSupportFragmentManager();
        changeFragment(new FirmHomeFragment());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.firm_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void changeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.firm_main_content, fragment);
        fragmentTransaction.commit();
    }
}
