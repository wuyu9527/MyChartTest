package com.example.gupo_android.test.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gupo_android.test.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Arrays;
import java.util.List;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {

    private Context context;
    private List<String> data;

    String[] strings = new String[]{"http://k.zol-img.com.cn/dcbbs/18007/a18006489_s.jpg"
            , "http://img3.fengniao.com/forum/secpics/203/17/8083209.jpg"
            , "http://pic1.win4000.com/wallpaper/2018-09-06/5b9094eebf947.jpg"
            , "http://c.hiphotos.baidu.com/zhidao/pic/item/242dd42a2834349b193b6c82caea15ce36d3bef3.jpg"
            , "http://pic14.photophoto.cn/20100312/0005018312012169_b.jpg"
    };

    public RecyclerviewAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;

    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.name.setText(data.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("whx", holder.name.getText().toString());
            }
        });
        if (position % 2 == 0) {
            holder.mRecyclerView.setVisibility(View.VISIBLE);
            holder.mRecyclerView.setLayoutManager(new GridLayoutManager(context, 3));
            holder.mRecyclerView.setAdapter(new ImageAdapter(Arrays.asList(strings)));
        } else {
            holder.mRecyclerView.setVisibility(View.GONE);
        }

        if (data.size() / 2 == position) {
            holder.setInTheMiddle(true);
            Log.i("whx","中间:"+holder.name.getText().toString());
        } else {
            holder.setInTheMiddle(false);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private RecyclerView mRecyclerView;
        private boolean isInTheMiddle;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            mRecyclerView = itemView.findViewById(R.id.mRecyclerView);

        }

        public TextView getName() {
            return name;
        }

        /**
         * 中间位置
         *
         * @return true
         */
        public boolean getIsInTheMiddle() {
            return isInTheMiddle;
        }

        public void setInTheMiddle(boolean inTheMiddle) {
            isInTheMiddle = inTheMiddle;
        }
    }


    public class ImageAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ImageViewHolder> {

        List<String> imagePath;

        public ImageAdapter(List<String> imagePath) {
            this.imagePath = imagePath;
        }

        @NonNull
        @Override
        public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_image, viewGroup, false);
            return new ImageViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ImageViewHolder imageViewHolder, final int i) {
            imageViewHolder.sdvImage.setImageURI(imagePath.get(i));
            imageViewHolder.sdvImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("whx", "i:" + i + ":" + imagePath.get(i));
                }
            });
        }


        @Override
        public int getItemCount() {
            return imagePath.size();
        }


    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView sdvImage;

        ImageViewHolder(View itemView) {
            super(itemView);
            sdvImage = itemView.findViewById(R.id.sdvImage);

        }
    }
}
