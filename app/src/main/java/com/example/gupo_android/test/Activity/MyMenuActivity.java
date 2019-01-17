package com.example.gupo_android.test.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.gupo_android.test.Bean.MenuBean;
import com.example.gupo_android.test.R;
import com.example.gupo_android.test.View.MyMenu;

import java.util.ArrayList;
import java.util.List;

public class MyMenuActivity extends AppCompatActivity {

    private MyMenu myMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_menu);
        myMenu = new MyMenu(this);
        List<MenuBean> menuBeans = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            MenuBean menuBean = new MenuBean(i, "第" + i + "个", 0, R.mipmap.ic_launcher);
            menuBeans.add(menuBean);
        }
        myMenu.setMenuBeans(menuBeans);

        myMenu.showPopupWindow(findViewById(R.id.tvLiftUp));
        myMenu.showPopupWindow(findViewById(R.id.tvRightUp));
        myMenu.showPopupWindow(findViewById(R.id.tvLiftDown));
        myMenu.showPopupWindow(findViewById(R.id.tvRightDown));
        myMenu.showPopupWindow(findViewById(R.id.tvCenter));
    }


    public void onClick(View v) {


    }
}
