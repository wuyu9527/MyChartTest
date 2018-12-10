package com.example.gupo_android.test.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.example.gupo_android.test.Adapter.Base.BaseHolder;
import com.example.gupo_android.test.Adapter.Base.BaseRecyclerView;
import com.example.gupo_android.test.Adapter.Holder.ChatFrameHolder;
import com.example.gupo_android.test.Bean.BaseBean;

import java.util.List;

/**
 * @author : whx
 * @date : 2018/12/7 15:03
 */
public class AudioRecorderAdapter extends BaseRecyclerView<BaseBean> {


    public AudioRecorderAdapter(Context context, int layout, List<BaseBean> list) {
        super(context, layout, list);
    }

    @NonNull
    @Override
    public BaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return super.onCreateViewHolder(viewGroup, i);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder baseHolder, int i) {
        baseHolder.setOnBindViewHolder(i);
    }
}
