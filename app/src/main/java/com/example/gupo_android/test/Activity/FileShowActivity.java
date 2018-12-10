package com.example.gupo_android.test.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gupo_android.test.R;

import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;


/**
 * @author : whx
 * @date : 2018/11/13 17:44
 */

/**
 * 文件预览页
 */
public class FileShowActivity extends AppCompatActivity implements DownloadFile.Listener {


    RelativeLayout content;
    LinearLayout pdfcontent;
    private WebView webView;
    private String urlString;
    private ProgressBar progressBar;
    private LinearLayout linearLayout;
    //微软在线预览office文档
    private final String MicrosoftOnlinePreviewUrl = "https://view.officeapps.live.com/op/view.aspx?src=";
    //谷歌在线预览——不推荐使用
    private final String GoogleOnlinePreviewUrl = "http://docs.google.com/gview?embedded=true&url=";
    private RemotePDFViewPager remotePDFViewPager;
    private PDFPagerAdapter adapter;
    private String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_show);
        content = findViewById(R.id.content);
        pdfcontent = findViewById(R.id.pdfcontent);

        initData();
        initView();
    }



    /**
     * 初始化数据
     */
    private void initData() {
        Intent intent = this.getIntent();
        if (intent == null) return;
        urlString = intent.getStringExtra("data");//接收上一个页面传过来的文件地址
        fileName = urlString.substring(urlString.lastIndexOf("/") + 1);//取文件名

    }

    private void initView() {
        linearLayout =  findViewById(R.id.linear);
        progressBar =  findViewById(R.id.progressbar);
        progressBar.setMax(100);
        webView =  findViewById(R.id.webview);

        if (urlString.endsWith("pdf")) {//是pdf文件
            checkOsVersion();
        } else if (isPicture(urlString)) {//是图片，webview可以直接加载网络图片

            webView.loadUrl( urlString);
        }else {//是office文档，使用 微软在线预览地址+文件地址 预览，Google在线预览需会存在问题
            webView.loadUrl(MicrosoftOnlinePreviewUrl  + urlString);
        }


        webView.setWebViewClient(new MyWebViewClient());    //设置在本页显示
        webView.setWebChromeClient(new MyWebChromeClient());  //设置进度监听，自定义类MyWebChromeClient继承自WebChromeClient
        webView.getSettings().setLoadWithOverviewMode(true);  //设置加载在本页
        webView.getSettings().setJavaScriptEnabled(true);   //加载javascript
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
    }

    /**
     * 验证版本号，5.0以上可以预览pdf,否则...
     * 使用RemotePDFViewPager需添加依赖：【支持5.0以上Android系统】
     * compile 'es.voghdev.pdfviewpager:library:1.0.1'
     */
    private void checkOsVersion() {
        webView.setVisibility(View.GONE);


            /**
             * 这里添加了pdf文件加载事件
             * Tools.getServerPath(this) + urlString  是pdf的网络地址
             */
            remotePDFViewPager = new RemotePDFViewPager(this,  urlString, this);

    }

    /**
     * 判断是不是office文档
     */
    private boolean isPicture(String filePath) {
        if (filePath.endsWith("doc") || filePath.endsWith("docx") ||
                filePath.endsWith("xls") || filePath.endsWith("xlsx") ||
                filePath.endsWith("ppt") || filePath.endsWith("pptx"))
            return false;
        return true;
    }



    /**
     * pdf预览下载事件
     * 加载成功
     *
     * @param url
     * @param destinationPath
     */
    @Override
    public void onSuccess(String url, String destinationPath) {
        content.setVisibility(View.GONE);
        pdfcontent.setVisibility(View.VISIBLE);
        adapter = new PDFPagerAdapter(this, fileName);
        remotePDFViewPager.setAdapter(adapter);
        pdfcontent.addView(remotePDFViewPager);
    }

    @Override
    public void onFailure(Exception e) {
        content.setVisibility(View.GONE);
        pdfcontent.setVisibility(View.VISIBLE);

        Toast.makeText(this, "文件预览失败", Toast.LENGTH_SHORT).show();
    }

    /**
     * 加载pdf进度
     *
     * @param progress 当前进度——字节数
     * @param total    总进度——字节数
     */
    @Override
    public void onProgressUpdate(int progress, int total) {
        progressBar.setProgress((int) (((float) progress) / total * 100));
    }


    //webview页面加载进度监听类
    class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            //加载进度newProgress
            progressBar.setProgress(newProgress);
            if (progressBar.getProgress() > 80) {
                webView.setVisibility(View.VISIBLE);
            }
            if (progressBar.getProgress() >= 100) {
                linearLayout.setVisibility(View.GONE);
            }
        }
    }

    //页面加载监听类
    class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);//本webView加载【不会去调用系统的加载】
            return true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}


