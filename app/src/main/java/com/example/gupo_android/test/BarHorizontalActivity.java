package com.example.gupo_android.test;

import android.os.Bundle;
import android.app.Activity;

import com.example.gupo_android.test.Adapter.BarAdapter;
import com.example.gupo_android.test.View.AutoLocateHorizontalView;

import java.util.ArrayList;
import java.util.List;

public class BarHorizontalActivity extends Activity {
    private List<Integer> ages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_horizontal);
        initData();
        AutoLocateHorizontalView horizontalView = findViewById(R.id.auto_locate_horizontal_view);
        BarAdapter adapter  = new BarAdapter(this,ages);
        horizontalView.setItemCount(10);
        horizontalView.setAdapter(adapter);
    }

    private void initData(){
        ages.add(100);
        ages.add(50);
        ages.add(40);
        ages.add(30);
        ages.add(60);
        ages.add(80);
        ages.add(90);
        ages.add(100);
        ages.add(11);
        ages.add(19);
        ages.add(23);
        ages.add(55);
        ages.add(55);
        ages.add(100);
        ages.add(100);
        ages.add(100);
        ages.add(66);
        ages.add(89);
        ages.add(22);
        ages.add(11);
        ages.add(10);
    }

}
