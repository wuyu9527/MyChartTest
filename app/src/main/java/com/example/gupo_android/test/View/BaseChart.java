package com.example.gupo_android.test.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 初始化横向图
 *
 * @author whx
 */
public abstract class BaseChart extends ViewGroup {

    //以下是以720*1280分辨率计算的比例
    public static final String TAG = "whx";
    //网格高间隔 18*2 36
    private static final Double GRID_HEIGHT = 0.0140625;
    //网格宽间隔 50*2 100
    private static final Double GRID_WIDTH = 0.138888;
    //网格线高度
    private static final Double GRID_LINE_H = 0.015625;
    //网格线宽度
    private static final Double GRID_LINE_W = 0.0028;
    //12sp 720
    private static final Double TEXT_SIZE = 0.016667;
    // 720*0.016667 动画速度
    private static final Double ANIMATION_SPEED = 0.016667;
    // 10 小圆点
    private static final Double round_point_10 = 0.0138883;
    // 14 大圆点
    private static final Double round_circle_14 = 0.019444;
    //显示字体点的距离 4dp
    private static final Double text_margin = 0.005556;
    //高度比例
    private static final Double MAX_HEIGHT = 0.5;
    //left 30 top 20 bottom 20 right 30 720
    private static final Double PADDING_LEFT = 0.083332;
    private static final Double PADDING_TOP = 0.055554;
    private static final Double PADDING_BOTTOM = 0.055554;
    private static final Double PADDING_RIGHT = 0.083332;
    //线宽
    private static final Double GIRD_STROKE_WIDTH = 0.00555;

    public static final String LEFT = "left";
    public static final String CHART_LEFT = "chartLeft";
    public static final String CENTER = "center";
    public static final String CHART_CENTER = "chartCenter";
    public static final String RIGHT = "right";
    public static final String CHART_RIGHT = "chartRight";

    DecimalFormat mFormatOne = new DecimalFormat("###,###,###,##0");
    DecimalFormat mFormatTwo = new DecimalFormat("###########0.00");
    DecimalFormat mFormatThree = new DecimalFormat("###########0.###");


    public BaseChart(Context context) {
        super(context);
        init();
    }

    public BaseChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化
     */
    protected void init() {
        setWillNotDraw(false);
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        Log.i(TAG, screenWidth + "*" + screenHeight);
        barTotal = 0;
        yBarTotal = 0;
        lineWidth = (int) (screenWidth * GIRD_STROKE_WIDTH);
        gridBG = new Paint(Paint.ANTI_ALIAS_FLAG);
        gridBG.setColor(Color.parseColor("#35ADA7"));//网格线颜色
        gridBG.setStyle(Paint.Style.FILL);
        gridBG.setStrokeWidth(lineWidth);

        safeNum = new Paint(Paint.ANTI_ALIAS_FLAG);
        safeNum.setColor(Color.parseColor("#F53E31"));//安全线
        safeNum.setStyle(Paint.Style.FILL);
        safeNum.setStrokeWidth(lineWidth);
        safeNum.setAlpha(50);

        numP = new Paint(Paint.ANTI_ALIAS_FLAG);
        numP.setColor(Color.parseColor("#FF000000"));//数值字颜色
        numP.setStrokeWidth(1);

        round = new Paint(Paint.ANTI_ALIAS_FLAG);
        round.setColor(Color.parseColor("#35ADA7"));//正常圆点颜色
        round.setStrokeWidth(1);

        stopX = screenWidth;
        stopY = screenHeight;
        gridHeight = (float) (screenHeight * GRID_HEIGHT);
        gridWidth = (float) (screenWidth * GRID_WIDTH);
        maxHeight = (int) (screenHeight * MAX_HEIGHT);
        padding_left = (int) (screenWidth * PADDING_LEFT);
        padding_top = (int) (screenWidth * PADDING_TOP);
        padding_right = (int) (screenWidth * PADDING_RIGHT);
        padding_bottom = (int) (screenWidth * PADDING_BOTTOM);
        textSize = (int) (screenWidth * TEXT_SIZE);
        startX = padding_left;

        startY = maxHeight - padding_bottom - 2 * textSize - padding_bottom / 2;

        round_10 = (float) (screenWidth * round_point_10);
        round_14 = (float) (screenWidth * round_circle_14);
        numP.setTextSize(textSize);
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
     * 数据的总数 x轴
     */
    protected int barTotal;
    /**
     * y轴总数
     */
    protected int yBarTotal;
    /**
     * 设置最大值，用于计算比例
     */
    protected double max;
    /**
     * 设置最小值
     */
    protected double min;
    /**
     * 开始X坐标
     */
    protected int startX = 0;
    /**
     * 开始Y坐标
     */
    protected int startY = 0;
    /**
     * 结束X坐标
     */
    protected int stopX;
    /**
     * 结束Y坐标
     */
    protected int stopY;
    /**
     * 背景网格高比例 默认公式 screenHeight*GRID_HEIGHT=132
     */
    protected float gridHeight;
    /**
     * 背景网格宽比例 默认公式 screenWidth*GRID_WIDTH=180
     */
    protected float gridWidth;
    /**
     * 表格最大Height
     */
    protected int maxHeight;
    //padding
    protected int padding_left;
    protected int padding_top;
    protected int padding_right;
    protected int padding_bottom;
    /**
     * 网格画笔
     */
    protected Paint gridBG;
    protected int lineWidth;
    /**
     * 字体大小
     */
    protected int textSize;
    /**
     * 字体一半高度
     */
    protected float numPHeight;
    /**
     * 数值画笔
     */
    protected Paint numP;
    /**
     * 限定值
     */
    protected Paint safeNum;
    /**
     * 圆点画笔
     */
    protected Paint round;
    /**
     * 小圆点10px
     */
    protected float round_10;
    /**
     * 大圆点14px
     */
    protected float round_14;
    /**
     * 设置数值显示圆点的 默认 RIGHT
     */
    protected String gravity = RIGHT;


    /**
     * 设置总体高度
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).layout(left, top, right, bottom);
        }
    }

    /**
     * startX startY stopX stopY
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (xName.size() > 0) {
            float startX = (gridWidth + textSize) / 2 + this.startX + xName.size() * gridWidth;
            if (startX < screenWidth) {
                setMeasuredDimension(screenWidth, maxHeight);
            } else {
                setMeasuredDimension((int) (startX + padding_right), maxHeight);
            }
        } else {
            setMeasuredDimension(screenWidth, maxHeight);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Log.i(TAG, "onDraw");
        drawVerticalLine(canvas);
        drawHorizontalLine(canvas);
    }

    /**
     * 获得文字宽度
     */
    public float getTextWidth(String text, float size) { //第一个参数是要计算的字符串，第二个参数是字提大小
        TextPaint FontPaint = new TextPaint();
        FontPaint.setTextSize(size);
        if (numPHeight == 0) {
            getTextHeight();
        }
        return FontPaint.measureText(text);
    }

    /**
     * 获取字体一半高度
     */
    public void getTextHeight() {
        Paint.FontMetrics fontMetrics = numP.getFontMetrics();
        numPHeight = (fontMetrics.ascent + fontMetrics.descent) / 2;
    }

    /**
     * 画Y轴竖线
     */
    private void drawVerticalLine(Canvas c) {
        c.drawLine(startX, padding_top / 2, padding_left, startY, gridBG);

        for (int i = 0; i < xName.size(); i++) {
            if (i > 0) {
                gridBG.setStrokeWidth(4);
                float startXLine = (gridWidth + textSize) / 2 + this.startX + (i - 1) * gridWidth;
                float stopX = (gridWidth + textSize) / 2 + this.startX + i * gridWidth;
                float stopY = (float) (num.get(i) * (startY - padding_top * 2) + padding_top);
                c.drawLine(startXLine, (float) (num.get(i - 1) * (startY - padding_top * 2) + padding_top), stopX, stopY, gridBG);
            }
        }

        //日期刻度
        for (int i = 0; i < xName.size(); i++) {
            gridBG.setStrokeWidth(lineWidth);
            float startX = (gridWidth + textSize) / 2 + this.startX + i * gridWidth;
            c.drawLine(startX, startY - padding_bottom + round_14, startX, startY, gridBG);
            String[] strings = xName.get(i).split(" ");
            switch (strings.length) {
                case 1:
                    c.drawText(xName.get(i), startX - getTextWidth(xName.get(i), textSize) / 2, startY + padding_bottom, numP);
                    break;
                case 2:
                    int textPaddingTop = padding_bottom / 20;
                    c.drawText(strings[0], startX - getTextWidth(strings[0], textSize) / 2, startY + padding_bottom - textSize - textPaddingTop, numP);
                    c.drawText(strings[1], startX - getTextWidth(strings[1], textSize) / 2, startY + padding_bottom + textPaddingTop, numP);
                    break;
            }


            switch (numColor.get(i)) {
                case -1:
                    //小于安全值
                    round.setColor(Color.parseColor("#1684DD"));
                    break;
                case 0:
                    //正常圆点颜色
                    round.setColor(Color.parseColor("#35ADA7"));
                    break;
                case 1:
                    //大于安全值
                    round.setColor(Color.parseColor("#F53E31"));
                    break;
                default:
                    //正常圆点颜色
                    round.setColor(Color.parseColor("#35ADA7"));
                    break;
            }

            if (isNow.get(i)) {
                round.setStyle(Paint.Style.STROKE);
                round.setStrokeWidth(3);
                c.drawCircle(startX, (float) (num.get(i) * (startY - padding_top * 2) + padding_top), round_14 / 2, round);
            }
            round.setStyle(Paint.Style.FILL);
            //测试
            //startX = (gridWidth + textSize) / 2 + this.startX + 0 * gridWidth;
            c.drawCircle(startX, (float) (num.get(i) * (startY - padding_top * 2) + padding_top), round_10 / 2, round);
            switch (gravity) {
                case LEFT:
                    break;
                case CHART_LEFT:
                    break;
                case CENTER:
                    break;
                case CHART_CENTER:
                    break;
                case RIGHT:
                    c.drawText(numName.get(i), startX + textSize + round_14 / 2, (float) (num.get(i) * (startY - padding_top * 2) + padding_top - numPHeight), numP);
                    break;
                case CHART_RIGHT:
                    break;
                default:
                    c.drawText(numName.get(i), startX + textSize + round_14 / 2, (float) (num.get(i) * (startY - padding_top * 2) + padding_top - numPHeight), numP);
                    break;
            }


        }


    }


    /**
     * 画X轴横线
     */
    private void drawHorizontalLine(Canvas c) {
        gridBG.setStrokeWidth(lineWidth);
        float stopX = screenWidth - padding_right;
        if (barTotal * gridWidth > screenWidth) {
            stopX = (gridWidth + textSize) / 2 + startX + barTotal * gridWidth - padding_right;
            c.drawLine(startX, startY, stopX, startY, gridBG);
        } else {
            c.drawLine(startX, startY, stopX, startY, gridBG);
        }

        if (chartData != null) {
            if (chartData.getSafeMax() != null && !chartData.getSafeMax().isEmpty()) {
                float safeMax = (float) (Double.parseDouble(chartData.getSafeMax()) * (startY - padding_top * 2) + padding_top);
                c.drawLine(startX, safeMax, stopX, safeMax, safeNum);
            }
            if (chartData.getSafeMin() != null && !chartData.getSafeMin().isEmpty()) {
                float safeMIn = (float) (Double.parseDouble(chartData.getSafeMin()) * (startY - padding_top * 2) + padding_top);
                c.drawLine(startX, safeMIn, stopX, safeMIn, safeNum);
            }
        }

        //数据刻度线
        for (int i = 0; i < yName.size(); i++) {
            gridBG.setStrokeWidth(lineWidth);
            float startY = (float) (this.mean.get(i) * (this.startY - padding_top * 2)) + padding_top;
            c.drawLine(startX, startY, startX + padding_right - round_14, startY, gridBG);
            c.drawText(yName.get(i), startX - getTextWidth(yName.get(i), textSize) - (float) (padding_left / 10), startY - numPHeight, numP);
            double maxAndMinMean = ((this.maxAndMinMean + minMean) / 5 / max) * (this.startY - padding_top * 2);
            gridBG.setStrokeWidth(lineWidth / 2);
            if (i > 0) {
                for (int i1 = 1; i1 < 5; i1++) {
                    float minMeanY = (float) (startY + maxAndMinMean * i1);
                    c.drawLine(startX, minMeanY, startX + padding_right / 2, minMeanY, gridBG);
                }
            }
        }


    }


    /**
     * X轴坐标名字
     */
    private List<String> xName = new ArrayList<>();
    /**
     * y轴坐标名字
     */
    private List<String> yName = new ArrayList<>();
    /**
     * 坐标数值名字
     */
    private List<String> numName = new ArrayList<>();
    /**
     * 坐标数值
     */
    private List<Double> num = new ArrayList<>();
    /**
     * 刻度线
     */
    private List<Double> mean = new ArrayList<>();
    /**
     * 是否是当前点
     */
    private List<Boolean> isNow = new ArrayList<>();
    /**
     * 点颜色  0正常值颜色:#35ADA7   -1小于最小值:#1684DD    1大于最大值:#F53E31
     */
    private List<Integer> numColor = new ArrayList<>();

    private double maxAndMinMean;
    private double minMean;

    private ChartData chartData;


    public void setData(ChartData chartData) {
        this.chartData = chartData;
        xName.clear();
        yName.clear();
        numName.clear();
        num.clear();
        mean.clear();
        isNow.clear();
        max = chartData.getMax();
        min = chartData.getMin();
        //重复使用的
        double difference = max * 0.1;
        max += difference;
        min -= difference;

        maxAndMinMean = (max - min) / 14;
        difference = max - min;
        int afterLength;
        if (1 < difference && difference <= 30) {
            afterLength = 2;
        } else if (0.9 < difference && difference <= 1) {
            afterLength = 3;
        } else if (difference <= 0.9) {
            afterLength = 4;
        } else {
            afterLength = 1;
        }
        minMean = min / 14;
        for (int i = 0; i < 15; i++) {
            BigDecimal bigDecimalMinMean = new BigDecimal(maxAndMinMean);
            BigDecimal bigDecimalI = new BigDecimal(i);
            BigDecimal bigDecimalMin = new BigDecimal(min);
            switch (afterLength) {
                case 0:
                    yName.add(String.valueOf(bigDecimalMinMean.multiply(bigDecimalI).add(bigDecimalMin).setScale(0, BigDecimal.ROUND_HALF_UP)));
                    break;
                case 1:
                    yName.add(String.valueOf(bigDecimalMinMean.multiply(bigDecimalI).add(bigDecimalMin).setScale(1, BigDecimal.ROUND_HALF_UP)));
                    break;
                case 2:
                    yName.add(String.valueOf(bigDecimalMinMean.multiply(bigDecimalI).add(bigDecimalMin).setScale(2, BigDecimal.ROUND_HALF_UP)));
                    break;
                case 3:
                    yName.add(String.valueOf(bigDecimalMinMean.multiply(bigDecimalI).add(bigDecimalMin).setScale(3, BigDecimal.ROUND_HALF_UP)));
                    break;
                case 4:
                    yName.add(String.valueOf(bigDecimalMinMean.multiply(bigDecimalI).add(bigDecimalMin).setScale(4, BigDecimal.ROUND_HALF_UP)));
                    break;
                default:
                    yName.add(String.valueOf(bigDecimalMinMean.multiply(bigDecimalI).add(bigDecimalMin).setScale(0, BigDecimal.ROUND_HALF_UP)));
                    break;
            }

            mean.add(1 - (Double.parseDouble(yName.get(i)) - min) / (max - min));

        }


        for (int i = 0; i < chartData.getCoordinate().size(); i++) {
            ChartData.Coordinate coordinate = chartData.getCoordinate().get(i);
            xName.add(coordinate.getDate());
            double percent = (coordinate.getNum() - min) / (max - min);
            this.num.add(1 - percent);
            isNow.add(coordinate.isNow());
            numColor.add(coordinate.getNumColor());
            numName.add(String.valueOf(coordinate.getNum()));
        }

        if (chartData.getSafeMax() != null && !chartData.getSafeMax().isEmpty()) {
            chartData.setSafeMax(String.valueOf(1 - (Double.parseDouble(chartData.getSafeMax()) - min) / (max - min)));
        }

        if (chartData.getSafeMin() != null && !chartData.getSafeMin().isEmpty()) {
            chartData.setSafeMin(String.valueOf(1 - (Double.parseDouble(chartData.getSafeMin()) - min) / (max - min)));
        }
        barTotal = numName.size();
        postInvalidate();
    }


    public String getFormatted(String num) {
        Double d = Double.parseDouble(num);
        String v = String.valueOf(d);
//        if (v.split("\\.")[1].equals("00") || v.split("\\.")[1].equals("0")) {
//            return mFormatOne.format(Float.valueOf(v));
//        } else {
        return mFormatTwo.format(Double.valueOf(v));
        //}
    }

}
