package com.example.gupo_android.test;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HtmlJSActivity extends Activity {

    private WebView webView;
    String url = "http://scan.s1.group-ds.com/test.html";
    String baidu = "http://baidu.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html_js);
        webView = findViewById(R.id.webView);
        webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        webView.addJavascriptInterface(new AndroidObjectInJavascript(), "gupo");
        setWebViewSet2();
        webView.loadUrl(url);
    }

    private void setWebViewSet2(){
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setJavaScriptEnabled(true);
    }


    private void setWebViewSet(){
        WebSettings settings = webView.getSettings();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        settings.setSavePassword(false);
        //webVIew是否支持js
        settings.setJavaScriptEnabled(true);
        settings.setBlockNetworkImage(false);
        // 设置允许JS弹窗
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        //settings.setPluginState(WebSettings.PluginState.ON);  //支持插件
        settings.setUseWideViewPort(false);  //将图片调整到适合webview的大小
        settings.setSupportZoom(true);  //支持缩放
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //支持内容重新布局
        settings.supportMultipleWindows();  //多窗口
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);  //关闭webview中缓存      LOAD_CACHE_ELSE_NETWORK
        settings.setAllowFileAccess(true);  //设置可以访问文件
        settings.setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点
        settings.setBuiltInZoomControls(true); //设置支持缩放
        settings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        settings.setLoadsImagesAutomatically(true);  //支持自动加载图片
        settings.setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            settings.setAllowUniversalAccessFromFileURLs(true);
        } else {
            try {
                Class<?> clazz = settings.getClass();
                Method method = clazz.getMethod("setAllowUniversalAccessFromFileURLs", boolean.class);
                if (method != null) {
                    method.invoke(settings, true);
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }

        });
    }


    public class AndroidObjectInJavascript {


        @JavascriptInterface
        public String getCurrentDoctorInfo() {
            String json = "";
            //hospital_no,doctor_no
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("hospital_no", "111111");
                jsonObject.put("doctor_no", "222222");
                json = jsonObject.toString();
            } catch (JSONException e) {
                json = "";
                e.printStackTrace();
            }
            return json;
        }

        @JavascriptInterface
        public String getCurrentPatientInfo(){
            String json;
            //hospital_no,patient_no
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("hospital_no", "3333");
                jsonObject.put("patient_no", "4444");
                json = jsonObject.toString();
            } catch (JSONException e) {
                json = "";
                e.printStackTrace();
            }
            return json;
        }

        @JavascriptInterface
        public void close(){
            finish();
        }

    }

}
