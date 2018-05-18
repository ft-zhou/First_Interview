package com.ft.first_interview.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ft.first_interview.R;

public class ChoosetypeActivity extends AppCompatActivity {

    private Button btn_type_person;
    private Button btn_type_company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosetype);

        btn_type_person = (Button) findViewById(R.id.btn_type_person);
        btn_type_company = (Button) findViewById(R.id.btn_type_company);

        btn_type_person.setOnClickListener(new chooseOnClickListener());
        btn_type_company.setOnClickListener(new chooseOnClickListener());
    }

    private class chooseOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent;
            if(v.getId() == R.id.btn_type_person) {
                intent = new Intent(ChoosetypeActivity.this,NavigationActivity.class);
                startActivity(intent);
                finish();
            }else if(v.getId() == R.id.btn_type_company) {
                intent = new Intent(ChoosetypeActivity.this,FirmNavigationActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
