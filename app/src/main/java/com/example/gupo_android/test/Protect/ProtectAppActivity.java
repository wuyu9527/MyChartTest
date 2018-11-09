package com.example.gupo_android.test.Protect;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.example.gupo_android.test.R;
import com.example.gupo_android.test.View.IconFontView;

/**
 * @author whx
 * 密码保护
 */
public class ProtectAppActivity extends AppCompatActivity {
    String[] num = new String[]{
            "1", "2", "3",
            "4", "5", "6",
            "7", "8", "9",
            "", "0", ""
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protect_app);
        RecyclerView recyclerView = findViewById(R.id.rv);
        recyclerView.setAdapter(new NumKeypadAdapter(this, num, new NumKeypadAdapter.ItemOnClickListener() {
            @Override
            public void onClick(int i) {
                Log.i("whx", "num:" + i + ":" + num[i]);
            }
        }));

    }
}
