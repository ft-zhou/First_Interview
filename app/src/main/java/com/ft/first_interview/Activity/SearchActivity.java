package com.ft.first_interview.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.ft.first_interview.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchActivity extends AppCompatActivity{

    private EditText etSearch;
    private ImageView ivDelete;
    private GridView gv_search_tips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        etSearch = (EditText) findViewById(R.id.etSearch);
        ivDelete = (ImageView) findViewById(R.id.ivDelete);
        gv_search_tips = (GridView) findViewById(R.id.gv_search_tips);

    }
}
