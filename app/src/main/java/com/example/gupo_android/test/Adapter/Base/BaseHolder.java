package com.example.gupo_android.test.Adapter.Base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gupo_android.test.R;

import java.util.LinkedHashMap;

/**
 * @author : whx
 * @date : 2018/12/7 14:41
 */
public abstract class BaseHolder extends RecyclerView.ViewHolder {


    public BaseHolder(@NonNull View itemView) {
        super(itemView);

    }

    public abstract void setOnBindViewHolder(int position);



}
