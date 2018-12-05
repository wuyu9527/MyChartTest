package com.example.gupo_android.test;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.gupo_android.test.Adapter.RecyclerviewAdapter;
import com.example.gupo_android.test.View.MyRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewTestActivity extends AppCompatActivity {

    RecyclerView title;
    RecyclerView myTitle;
    MyRecyclerView myRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_test);
        myRV = findViewById(R.id.myRV);
        title = findViewById(R.id.title);
        myTitle = findViewById(R.id.myTitle);

        myRV.setLayoutManager(new LinearLayoutManager(this));
        //title.setLayoutManager(new LinearLayoutManager(this));
        //myTitle.setLayoutManager(new LinearLayoutManager(this));
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("序列号 : " + i);
        }

        myRV.setAdapter(new RecyclerviewAdapter(this, list));
        //title.setAdapter(new RecyclerviewAdapter(this, list));
        //myTitle.setAdapter(new RecyclerviewAdapter(this, list));
    }
}
