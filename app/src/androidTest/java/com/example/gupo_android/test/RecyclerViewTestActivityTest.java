package com.example.gupo_android.test;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.gupo_android.test.Activity.RecyclerViewTestActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * @author : whx
 * @date : 2018/12/6 12:17
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class RecyclerViewTestActivityTest {

    @Rule
    public ActivityTestRule<RecyclerViewTestActivity> mActivityTestRule = new ActivityTestRule<>(RecyclerViewTestActivity.class,true);

    @Before
    public void setUp(){

    }

    public void recyclerViewTestActivityTest(){

    }

}
