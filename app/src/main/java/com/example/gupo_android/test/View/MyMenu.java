package com.example.gupo_android.test.View;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.gupo_android.test.Bean.MenuBean;
import com.example.gupo_android.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : whx
 * @date : 2019/1/16 14:20
 */
public class MyMenu extends PopupWindow {



    public MyMenu(Context context) {
        super(context);
    }

    public MyMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private ListView lvContent;
    private List<MenuBean> menuBeans;

    private void init(){
        LayoutInflater inflater = (LayoutInflater) getContentView().getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        View contentView = inflater.inflate(R.layout.menu_popup_window, null);
        menuBeans = new ArrayList<>();
        lvContent = contentView.findViewById(R.id.lvMenu);

        int h = getContentView().getContext().getResources().getDisplayMetrics().heightPixels;
        int w = getContentView().getContext().getResources().getDisplayMetrics().widthPixels;
        // 设置SelectPicPopupWindow的View
        this.setContentView(contentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(w / 3-30);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.PopupAnimation);
    }



    /**
     * 显示popupWindow
     *
     * @param parent
     */
    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            this.showAsDropDown(parent);
        } else {
            this.dismiss();
        }
    }

}
