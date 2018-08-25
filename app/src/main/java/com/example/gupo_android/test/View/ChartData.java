package com.example.gupo_android.test.View;

import java.util.List;

public class ChartData {


    //范围最大值
    private double max;
    //范围最小值
    private double min;
    //安全值最大值
    private String safeMax;
    //安全值最小值
    private String safeMin;
    //坐标
    private List<Coordinate> coordinate;

    public ChartData() {

    }

    public ChartData(double max, double min, String safeMax, String safeMin, List<Coordinate> coordinate) {
        this.max = max;
        this.min = min;
        this.safeMax = safeMax;
        this.safeMin = safeMin;
        this.coordinate = coordinate;
    }

    public String getSafeMax() {
        return safeMax;
    }

    public void setSafeMax(String safeMax) {
        this.safeMax = safeMax;
    }

    public String getSafeMin() {
        return safeMin;
    }

    public void setSafeMin(String safeMin) {
        this.safeMin = safeMin;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public void setCoordinate(List<Coordinate> coordinate) {
        this.coordinate = coordinate;
    }

    public List<Coordinate> getCoordinate() {
        return coordinate;
    }



    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }


    public static class Coordinate {
        //id
        private int id;
        //日期
        private String date;
        //数值
        private double num;
        //当前点
        private boolean isNow;
        //点的颜色 0正常值颜色 -1小于最小值 1大于最大值
        private int numColor;
        //颜色
        private int color;

        public Coordinate(int id, String date, double num, boolean isNow, int numColor, int color) {
            this.id = id;
            this.date = date;
            this.num = num;
            this.isNow = isNow;
            this.numColor = numColor;
            this.color = color;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public double getNum() {
            return num;
        }

        public void setNum(double num) {
            this.num = num;
        }

        public boolean isNow() {
            return isNow;
        }

        public void setNow(boolean now) {
            isNow = now;
        }

        public int getNumColor() {
            return numColor;
        }

        public void setNumColor(int numColor) {
            this.numColor = numColor;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }
    }


}
