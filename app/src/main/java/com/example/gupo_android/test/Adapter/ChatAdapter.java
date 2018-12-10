package com.example.gupo_android.test.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gupo_android.test.Adapter.Base.BaseHolder;
import com.example.gupo_android.test.Adapter.Holder.ChatFrameHolder;
import com.example.gupo_android.test.Bean.ChatBean;
import com.example.gupo_android.test.R;

import java.util.List;

/**
 * @author : whx
 * @date : 2018/12/7 16:28
 */
public class ChatAdapter extends RecyclerView.Adapter<BaseHolder> {

    private List<ChatBean> chatBeans;
    private Context context;

    public ChatAdapter(Context context, List<ChatBean> chatBeans) {
        this.chatBeans = chatBeans;
        this.context = context;
    }

    @NonNull
    @Override
    public BaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        if (chatBeans.get(i).getType() == 0) {
            view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview, viewGroup, false);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview, viewGroup, false);
        }
        return new ChatFrameHolder(view, chatBeans.get(i));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder viewHolder, int i) {
        viewHolder.setOnBindViewHolder(i);
    }

    @Override
    public int getItemCount() {
        return chatBeans == null ? 0 : chatBeans.size();
    }
}
