package com.example.gupo_android.test.Activity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gupo_android.test.R;
import com.example.gupo_android.test.View.ZoomableDraweeView;

public class BigImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_image);
        ZoomableDraweeView draweeView = findViewById(R.id.big_image);
        Uri uri = Uri.parse("http://gp-media.oss-cn-hangzhou.aliyuncs.com/f169b1a771215329737c91f70b5bf05c/image/1071c15c-c731-425e-b5c4-288e098f43ef1545895547739.jpg");
        draweeView.setImageURI(uri);
    }
}
