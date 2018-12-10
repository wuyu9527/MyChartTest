package com.example.gupo_android.test.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.example.gupo_android.test.Adapter.AudioRecorderAdapter;
import com.example.gupo_android.test.Adapter.ChatAdapter;
import com.example.gupo_android.test.Adapter.Holder.ChatFrameHolder;
import com.example.gupo_android.test.Bean.ChatBean;
import com.example.gupo_android.test.R;

import java.util.ArrayList;
import java.util.List;

public class AudioRecorderActivity extends AppCompatActivity {

    RecyclerView rvAudioRecorder;
    Button btnStart;
    List<ChatBean> holders;
    ChatAdapter audioRecorderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_recorder);
        rvAudioRecorder = findViewById(R.id.rvAudioRecorder);
        btnStart = findViewById(R.id.btnStart);
        holders = new ArrayList<>();
        audioRecorderAdapter = new ChatAdapter(this, holders);
        rvAudioRecorder.setAdapter(audioRecorderAdapter);

    }


}
