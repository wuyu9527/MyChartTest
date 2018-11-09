package com.example.gupo_android.test.Protect;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.gupo_android.test.R;
import com.example.gupo_android.test.View.IconFontView;

import java.util.List;

/**
 * @author : whx
 * @date : 2018/10/30 10:53
 */
public class NumKeypadAdapter extends RecyclerView.Adapter<NumKeypadAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private String[] strings;

    private ItemOnClickListener listener;

    public interface ItemOnClickListener {
        void onClick(int i);
    }

    public NumKeypadAdapter(Context context, String[] strings, ItemOnClickListener listener) {
        this.mInflater = LayoutInflater.from(context);
        this.strings = strings;
        this.listener = listener;
    }

    @Override
    public NumKeypadAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = this.mInflater.inflate(R.layout.item_num_keypad, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        if (i == strings.length - 1) {
            viewHolder.textView.setVisibility(View.GONE);
            viewHolder.iconFontView.setVisibility(View.VISIBLE);
        } else {
            viewHolder.textView.setVisibility(View.VISIBLE);
            viewHolder.iconFontView.setVisibility(View.GONE);
            viewHolder.textView.setText(strings[i]);
        }
        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(i);
            }
        });
        viewHolder.iconFontView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return strings.length;//数据数量，不想搞太复杂
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        IconFontView iconFontView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvNum);
            iconFontView = itemView.findViewById(R.id.ifv);
        }
    }
}
