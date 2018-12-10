package com.example.gupo_android.test.Adapter.Base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gupo_android.test.Bean.BaseBean;
import com.example.gupo_android.test.R;

import org.bouncycastle.jcajce.provider.symmetric.ARC4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : whx
 * @date : 2018/12/7 15:01
 */
public abstract class BaseRecyclerView<T extends BaseBean> extends RecyclerView.Adapter<BaseHolder> {

    private Context context;
    private List<Integer> layout;
    private List<T> list;

    public BaseRecyclerView(Context context, int layout, List<T> list) {
        this.context = context;
        this.layout = new ArrayList<>();
        this.layout.add(layout);
        this.list = list;
    }

    public BaseRecyclerView(Context context, List<T> list, int... layout) {
        this.context = context;
        this.layout = new ArrayList<>();
        for (int aLayout : layout) {
            this.layout.add(aLayout);
        }
        this.list = list;
    }

    @NonNull
    @Override
    public BaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        if (layout.size() == 1) {
            view = LayoutInflater.from(context).inflate(layout.get(0), viewGroup, false);
        } else {
            view = LayoutInflater.from(context).inflate(layout.get(0), viewGroup, false);
        }
        return new BaseHolder(view) {
            @Override
            public void setOnBindViewHolder(int position, Object o) {

            }
        };
    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
}
