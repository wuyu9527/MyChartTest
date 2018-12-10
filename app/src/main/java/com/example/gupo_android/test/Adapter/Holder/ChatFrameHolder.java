package com.example.gupo_android.test.Adapter.Holder;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gupo_android.test.Adapter.Base.BaseHolder;
import com.example.gupo_android.test.Bean.ChatBean;
import com.example.gupo_android.test.R;

/**
 * @author : whx
 * @date : 2018/12/7 15:06
 */
public class ChatFrameHolder extends BaseHolder {


    private TextView tv;
    private ImageView iv;
    private ChatBean chatBean;

    public ChatFrameHolder(@NonNull View itemView, ChatBean chatBean) {
        super(itemView);
        tv = itemView.findViewById(R.id.tv);
        iv = itemView.findViewById(R.id.iv);
        this.chatBean = chatBean;
    }

    @Override
    public void setOnBindViewHolder(int position) {


    }


}
