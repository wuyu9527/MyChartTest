package com.example.gupo_android.test.Adapter.Holder;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gupo_android.test.Adapter.Base.BaseHolder;
import com.example.gupo_android.test.Adapter.RecyclerviewAdapter;
import com.example.gupo_android.test.Bean.MenuBean;
import com.example.gupo_android.test.R;

/**
 * @author : whx
 * @date : 2019/1/17 10:05
 */
public class MenuHolder extends RecyclerView.ViewHolder {

    public ImageView iv;
    public TextView tv;

    public MenuHolder(@NonNull View itemView) {
        super(itemView);
        iv = itemView.findViewById(R.id.iv);
        tv = itemView.findViewById(R.id.tv);
    }


}
