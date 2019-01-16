package com.example.gupo_android.test.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.gupo_android.test.Adapter.RecyclerviewAdapter;
import com.example.gupo_android.test.R;

import java.util.ArrayList;
import java.util.List;

public class FloatingActionButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_action_button);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //设置一个垂直方向的layout manager
        int orientation = LinearLayoutManager.VERTICAL;
        recyclerView.setLayoutManager(new LinearLayoutManager(this, orientation, false));

        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            strings.add("位置为：" + i);
        }

        RecyclerviewAdapter adapter = new RecyclerviewAdapter(this, strings);
        recyclerView.setAdapter(adapter);
    }
}
