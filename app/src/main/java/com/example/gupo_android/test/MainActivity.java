package com.example.gupo_android.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.HorizontalScrollView;

import com.example.gupo_android.test.View.BaseChart;
import com.example.gupo_android.test.View.ChartData;
import com.example.gupo_android.test.View.LineChart;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试用
 *
 * @author whx
 */
public class MainActivity extends Activity {

    private HorizontalScrollView hsv;
    private LineChart lc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hsv = findViewById(R.id.hsv);
        lc = findViewById(R.id.lc);
        List<ChartData.Coordinate> coordinates = new ArrayList<>();
        ChartData chartData = new ChartData(300, -100, 300+"",-100+"",coordinates);
        ChartData.Coordinate coordinate;
        /*for (int i = 10; i < 20; i++) {
            switch (i) {
                case 15:
                    coordinate = new ChartData.Coordinate(i, "2018-08-" + i + " " + i + ":00:00", i * 10, true, 0, 0);
                    break;
                default:
                    coordinate = new ChartData.Coordinate(i, "2018-08-" + i + " " + i + ":00:00", i * 10, false, 0, 0);
                    break;
            }
            coordinates.add(coordinate);
        }*/

        coordinates.add(new ChartData.Coordinate(12, "2018-08-" + 23 + " " + 19 + ":00:00", -240, false, 0, 0));
        coordinates.add(new ChartData.Coordinate(12, "2018-08-" + 23 + " " + 19 + ":00:00", -191.4, false, 0, 0));
        coordinates.add(new ChartData.Coordinate(12, "2018-08-" + 23 + " " + 19 + ":00:00", -142.9, false, 0, 0));
        coordinates.add(new ChartData.Coordinate(12, "2018-08-" + 23 + " " + 19 + ":00:00", -94.3, true, 1, 0));
        coordinates.add(new ChartData.Coordinate(12, "2018-08-" + 23 + " " + 19 + ":00:00", -45.7, false, 0, 0));
        coordinates.add(new ChartData.Coordinate(12, "2018-08-" + 23 + " " + 19 + ":00:00", 2.9, false, 0, 0));
        coordinates.add(new ChartData.Coordinate(12, "2018-08-" + 23 + " " + 19 + ":00:00", 51.4, false, 0, 0));
        coordinates.add(new ChartData.Coordinate(12, "2018-08-" + 23 + " " + 19 + ":00:00", 100, false, 0, 0));
        coordinates.add(new ChartData.Coordinate(12, "2018-08-" + 23 + " " + 19 + ":00:00", 148.6, false, 0, 0));
        coordinates.add(new ChartData.Coordinate(12, "2018-08-" + 23 + " " + 19 + ":00:00", 197.1, false, 0, 0));
        coordinates.add(new ChartData.Coordinate(12, "2018-08-" + 23 + " " + 19 + ":00:00", 245.7, false, 0, 0));
        coordinates.add(new ChartData.Coordinate(12, "2018-08-" + 23 + " " + 19 + ":00:00", 294.3, false, 0, 0));
        coordinates.add(new ChartData.Coordinate(12, "2018-08-" + 23 + " " + 19 + ":00:00", 342.9, false, 0, 0));
        coordinates.add(new ChartData.Coordinate(12, "2018-08-" + 23 + " " + 19 + ":00:00", 391.4, false, 0, 0));
        coordinates.add(new ChartData.Coordinate(12, "2018-08-" + 23 + " " + 19 + ":00:00", 440, false, 0, 0));



        lc.setData(chartData);
    }


}
