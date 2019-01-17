package com.example.gupo_android.test.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gupo_android.test.Adapter.Holder.MenuHolder;
import com.example.gupo_android.test.Bean.MenuBean;
import com.example.gupo_android.test.R;

import java.util.List;

/**
 * @author : whx
 * @date : 2019/1/17 9:54
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuHolder> {

    private Context context;
    private List<MenuBean> menuBeans;

    public MenuAdapter(Context context, List<MenuBean> menuBeans) {
        this.context = context;
        this.menuBeans = menuBeans;
    }

    public void setMenuBeans(List<MenuBean> menuBeans) {
        this.menuBeans = menuBeans;
    }

    @NonNull
    @Override
    public MenuHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_menu, viewGroup, false);
        return new MenuHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuHolder menuHolder, int i) {
        menuHolder.iv.setVisibility(View.VISIBLE);
        if (menuBeans.get(i).getImage() != 0) {
            menuHolder.iv.setImageResource(menuBeans.get(i).getImage());
        } else if (menuBeans.get(i).getColor() != 0) {
            menuHolder.iv.setBackgroundColor(menuBeans.get(i).getColor());
        } else {
            menuHolder.iv.setVisibility(View.GONE);
        }
        menuHolder.tv.setText(menuBeans.get(i).getText());
    }


    @Override
    public int getItemCount() {
        return menuBeans != null ? menuBeans.size() : 0;
    }
}
