package com.example.gupo_android.test.App;

import android.app.Application;

import com.example.gupo_android.test.App.init.ImageLoaderConfig;
import com.facebook.drawee.backends.pipeline.Fresco;



public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        RongIMClient.init(this,"pgyu6atqpexyu");

        //图片缓存初始化
        Fresco.initialize(this, ImageLoaderConfig.getImagePipelineConfig(this));

    }
}
