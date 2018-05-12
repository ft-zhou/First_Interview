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

    private EditText search_etSearch;
    private ImageView search_ivDelete;
    private GridView search_tips_gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        search_etSearch = (EditText) findViewById(R.id.search_etSearch);
        search_ivDelete = (ImageView) findViewById(R.id.search_ivDelete);
        search_tips_gridView = (GridView) findViewById(R.id.search_tips_gridView);
    }
}
