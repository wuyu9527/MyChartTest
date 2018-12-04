package com.example.gupo_android.test.View;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @author : whx
 * @date : 2018/11/27 10:07
 */
public class VitalSignsBaseChart extends View {

    public static String tag = "VitalSigns";

    //线宽
    private static final Double GIRD_STROKE_WIDTH = 0.00555;
    //字体大小
    private static final Double TEXT_SIZE = 0.04;

    public VitalSignsBaseChart(Context context) {
        super(context);
        init();
    }

    public VitalSignsBaseChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VitalSignsBaseChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 屏幕宽
     */
    protected int screenWidth;
    /**
     * 屏幕高
     */
    protected int screenHeight;
    /**
     * 文字画笔
     */
    private Paint textPaint;
    /**
     * 背景网格
     */
    private Paint backgroundGridPaint;
    /**
     * 值
     */
    private Paint valuePaint;
    /**
     * 文字大小
     */
    private float textSize;
    /**
     * 画笔
     */
    private float lineWidth;


    public void init() {
        setWillNotDraw(false);
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        Log.i(tag, "屏幕宽高:" + screenWidth + "*" + screenHeight);

        //横竖屏
        if (screenWidth <= screenHeight) {
            textSize = (float) (TEXT_SIZE * screenWidth);
            lineWidth = (float) (GIRD_STROKE_WIDTH * screenWidth);
        } else {
            textSize = (float) (TEXT_SIZE * screenHeight);
            lineWidth = (float) (GIRD_STROKE_WIDTH * screenHeight);
        }

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundGridPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        valuePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        textPaint.setColor(Color.parseColor("#AA000000"));
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setStrokeWidth(lineWidth);
        textPaint.setTextSize(textSize);

        backgroundGridPaint.setColor(Color.RED);
        backgroundGridPaint.setStyle(Paint.Style.FILL);
        backgroundGridPaint.setStrokeWidth(lineWidth);

        valuePaint.setColor(Color.BLUE);
        valuePaint.setStyle(Paint.Style.FILL);
        valuePaint.setStrokeWidth(lineWidth);




    }
}
