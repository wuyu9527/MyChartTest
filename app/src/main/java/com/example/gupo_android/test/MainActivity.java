package com.example.gupo_android.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;

import com.example.gupo_android.test.View.BaseChart;
import com.example.gupo_android.test.View.ChartData;
import com.example.gupo_android.test.View.LineChart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        String range = "1-1";
        String[] ranges = range.split("-");
        List<String> rangesList = Arrays.asList(ranges);
        Log.i("whx", "ranges.length:" + ranges.length + ":" + rangesList);


        List<ChartData.Coordinate> coordinates = new ArrayList<>();
        ChartData chartData = new ChartData(300, -100, 300 + "", -100 + "", coordinates);
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
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchAppDetail("com.groupds.android", "com.tencent.android.qqdownloader");
            }
        });

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HorizontalActivity.class));
            }
        });

        String msg = "{\"content\":\"sets alder no\",\"to_username\":\"\",\"user_id\":\"\"}";
        String msgSubstring = msg.substring(msg.length() - 3, msg.length());
        boolean isJson = msg.length() >= 7 && msgSubstring.contains("\"}");
        Log.i("whx", "isJson:" + isJson);
    }


    /**
     * 获取已安装应用商店的包名列表
     * 获取有在AndroidManifest 里面注册<category android:name="android.intent.category.APP_MARKET" />的app
     *
     * @param context
     * @return
     */
    public ArrayList<String> getInstallAppMarkets(Context context) {
        //默认的应用市场列表，有些应用市场没有设置APP_MARKET通过隐式搜索不到
        ArrayList<String> pkgList = new ArrayList<>();
        pkgList.add("com.xiaomi.market");
        pkgList.add("com.qihoo.appstore");
        pkgList.add("com.wandoujia.phoenix2");
        pkgList.add("com.tencent.android.qqdownloader");
        pkgList.add("com.taptap");
        ArrayList<String> pkgs = new ArrayList<String>();
        if (context == null)
            return pkgs;
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.APP_MARKET");
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> infos = pm.queryIntentActivities(intent, 0);
        if (infos == null || infos.size() == 0)
            return pkgs;
        int size = infos.size();
        for (int i = 0; i < size; i++) {
            String pkgName = "";
            try {
                ActivityInfo activityInfo = infos.get(i).activityInfo;
                pkgName = activityInfo.packageName;


            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!TextUtils.isEmpty(pkgName))
                pkgs.add(pkgName);

        }
        //取两个list并集,去除重复
        pkgList.removeAll(pkgs);
        pkgs.addAll(pkgList);
        return pkgs;
    }


    /**
     * 跳转到应用市场app详情界面
     *
     * @param appPkg    App的包名
     * @param marketPkg 应用市场包名
     */
    public void launchAppDetail(String appPkg, String marketPkg) {
        try {
            if (TextUtils.isEmpty(appPkg))
                return;
            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(marketPkg))
                intent.setPackage(marketPkg);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
