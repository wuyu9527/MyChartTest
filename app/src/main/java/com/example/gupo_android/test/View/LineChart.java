package com.example.gupo_android.test.View;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;


/**
 * @author whx
 */
public class LineChart extends BaseChart {


    public LineChart(Context context) {
        super(context);

    }

    public LineChart(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public LineChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void init() {
        super.init();

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).layout(l, t, r, b);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }




}
