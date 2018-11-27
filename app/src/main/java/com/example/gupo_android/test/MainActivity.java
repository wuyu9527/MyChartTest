package com.example.gupo_android.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.ImageWriter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.Toast;

import com.example.gupo_android.test.Protect.ProtectAppActivity;
import com.example.gupo_android.test.Util.FingerUtils;
import com.example.gupo_android.test.View.BaseChart;
import com.example.gupo_android.test.View.ChartData;
import com.example.gupo_android.test.View.LineChart;
import com.example.gupo_android.test.lib.biometriclib.BiometricPromptManager;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 测试用
 *
 * @author whx
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private HorizontalScrollView hsv;
    private LineChart lc;
    //指纹第二个方式
    private BiometricPromptManager mManager;

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

        String msg = "{\"content\":\"sets alder no\",\"to_username\":\"\",\"user_id\":\"\"}";
        String msgSubstring = msg.substring(msg.length() - 3, msg.length());
        boolean isJson = msg.length() >= 7 && msgSubstring.contains("\"}");
        Log.i("whx", "isJson:" + isJson);
        //指纹第一个方式
        FingerUtils.instance().init(this);
        mManager = BiometricPromptManager.from(this);
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                launchAppDetail("com.groupds.android", "com.tencent.android.qqdownloader");
                break;
            case R.id.button1:
                startActivity(new Intent(MainActivity.this, HorizontalActivity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(MainActivity.this, HtmlJSActivity.class));
                break;
            case R.id.button3:
                startActivity(new Intent(MainActivity.this, ProtectAppActivity.class));
                break;
            case R.id.button4:
                FingerUtils.instance().startListener(new FingerUtils.OnFingerSucceededListener() {
                    @Override
                    public void onSucceed() {
                        Toast.makeText(MainActivity.this, "指纹验证成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(MainActivity.this, code + ":" + msg, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailed(int failedNum) {
                        Toast.makeText(MainActivity.this, "指纹验证失败:" + failedNum, Toast.LENGTH_SHORT).show();
                    }
                });
                //第二个指纹
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && mManager.isBiometricPromptEnable()) {
                    mManager.authenticate(new BiometricPromptManager.OnBiometricIdentifyCallback() {
                        @Override
                        public void onUsePassword() {
                            Toast.makeText(MainActivity.this, "onUsePassword", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onSucceeded() {

                            Toast.makeText(MainActivity.this, "onSucceeded", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailed() {

                            Toast.makeText(MainActivity.this, "onFailed", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(int code, String reason) {
                            Log.i("whx", code + ":" + reason);
                            Toast.makeText(MainActivity.this, "onError", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancel() {

                            Toast.makeText(MainActivity.this, "onCancel", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;
            case R.id.button5:

                break;
        }
    }

    /*public static Object parsePdf(File pdfFile) {
        PDDocument document = null;
        try {
            long start = System.currentTimeMillis();
            document = PDDocument.load(pdfFile);
            if (document == null) {
                return null;
            }
            int size = document.getNumberOfPages();
            BufferedImage image = null;
            FileOutputStream out = null;
            for (int i = 0; i < size; i++) {
                image = new PDFRenderer(document).renderImageWithDPI(i, 130, ImageType.RGB);
                out = new FileOutputStream("D://12//" + i + ".jpg");
                ImageIO.write(image, "jpg", out);
                out.close();
            }
            long end = System.currentTimeMillis();
            System.out.println("解析成功耗时：" + (end - start) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(document != null){
                    document.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return document;
    }


    *//**
     * 将宽度相同的图片，竖向追加在一起 ##注意：宽度必须相同,纵向处理图片
     *
     * @param piclist 文件流数组
     * @param outPath 输出路径
     *//*
    public static void y2Pic(List<BufferedImage> piclist, String outPath) {
        if (piclist == null || piclist.size() <= 0) {
            System.out.println("图片数组为空!");
            return;
        }
        try {
            int height = 0, // 总高度
                    width = 0, // 总宽度
                    _height = 0, // 临时的高度 , 或保存偏移高度
                    __height = 0, // 临时的高度，主要保存每个高度
                    picNum = piclist.size();// 图片的数量
            File fileImg = null; // 保存读取出的图片
            int[] heightArray = new int[picNum]; // 保存每个文件的高度
            BufferedImage buffer = null; // 保存图片流
            List<int[]> imgRGB = new ArrayList<int[]>(); // 保存所有的图片的RGB
            int[] _imgRGB; // 保存一张图片中的RGB数据
            for (int i = 0; i < picNum; i++) {
                buffer = piclist.get(i);
                heightArray[i] = _height = buffer.getHeight();// 图片高度
                if (i == 0) {
                    width = buffer.getWidth();// 图片宽度
                }
                height += _height; // 获取总高度
                _imgRGB = new int[width * _height];// 从图片中读取RGB
                _imgRGB = buffer.getRGB(0, 0, width, _height, _imgRGB, 0, width);
                imgRGB.add(_imgRGB);
            }
            _height = 0; // 设置偏移高度为0
            // 生成新图片
            BufferedImage imageResult = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
            for (int i = 0; i < picNum; i++) {
                __height = heightArray[i];
                if (i != 0)
                    _height += __height; // 计算偏移高度
                imageResult.setRGB(0, _height, width, __height, imgRGB.get(i), 0, width); // 写入流中
            }
            File outFile = new File(outPath);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(imageResult, "jpg", out);// 写图片
            byte[] b = out.toByteArray();
            FileOutputStream output = new FileOutputStream(outFile);
            output.write(b);
            out.close();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FingerUtils.instance().cancel();
    }
}
