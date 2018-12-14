//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.gupo_android.test.View.Rong.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class DeviceUtils {
    public DeviceUtils() {
    }

    public static String getDeviceId(Context context, String appKey) {
        SharedPreferences sp = context.getSharedPreferences("Statistics", 0);
        String deviceId = sp.getString("deviceId", "");
        if (TextUtils.isEmpty(deviceId)) {
            String[] params = new String[]{getDeviceId(context), appKey, context.getPackageName()};
            deviceId = ShortMD5(params);
            Editor editor = sp.edit();
            editor.putString("deviceId", deviceId);
            editor.commit();
        }

        return deviceId;
    }

    public static String getDeviceId(Context context) {
        SharedPreferences sp = context.getSharedPreferences("Statistics", 0);
        String deviceId = sp.getString("ANDROID_ID", "");
        if (TextUtils.isEmpty(deviceId)) {
            deviceId = Secure.getString(context.getApplicationContext().getContentResolver(), "android_id");
            if (TextUtils.isEmpty(deviceId)) {
                SecureRandom random = new SecureRandom();
                deviceId = (new BigInteger(64, random)).toString(16);
            }

            Editor editor = sp.edit();
            editor.putString("ANDROID_ID", deviceId);
            editor.commit();
        }

        return deviceId;
    }

    public static String getDeviceIMEI(Context context) {
        return getDeviceId(context);
    }

    public static String ShortMD5(String... args) {
        try {
            StringBuilder builder = new StringBuilder();
            String[] var2 = args;
            int var3 = args.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String arg = var2[var4];
                builder.append(arg);
            }

            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(builder.toString().getBytes());
            byte[] mds = mdInst.digest();
            mds = Base64.encode(mds, 0);
            String result = new String(mds);
            result = result.replace("=", "").replace("+", "-").replace("/", "_").replace("\n", "");
            return result;
        } catch (Exception var6) {
            return "";
        }
    }

    public static String getPhoneInformation(Context context) {
        String MCCMNC = "";

        try {

            TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
            MCCMNC = telephonyManager.getNetworkOperator();
        } catch (SecurityException var8) {
            Log.e("DeviceUtils", "SecurityException!!!");
        }

        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        String network = "";
        NetworkInfo networkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        if (networkInfo != null) {
            network = networkInfo.getTypeName();
        }

        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (manufacturer == null) {
            manufacturer = "";
        }

        if (model == null) {
            model = "";
        }

        String devInfo = manufacturer + "|";
        devInfo = devInfo + model;
        devInfo = devInfo + "|";
        devInfo = devInfo + String.valueOf(VERSION.SDK_INT);
        devInfo = devInfo + "|";
        devInfo = devInfo + network;
        devInfo = devInfo + "|";
        devInfo = devInfo + MCCMNC;
        devInfo = devInfo + "|";
        devInfo = devInfo + context.getPackageName();
        devInfo = devInfo.replace("-", "_");
        Log.i("DeviceUtils", "getPhoneInformation.the phone information is: " + devInfo);
        return devInfo;
    }

    public static String getDeviceManufacturer() {
        String line = "";
        BufferedReader input = null;
        String propName = "ro.miui.ui.version.name";

        try {
            Process p = Runtime.getRuntime().exec("getprop " + propName);
            input = new BufferedReader(new InputStreamReader(p.getInputStream()), 1024);
            line = input.readLine();
            input.close();
        } catch (IOException var12) {
            Log.e("DeviceUtils", "Unable to read sysprop " + propName);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException var11) {
                    ;
                }
            }

        }

        if (TextUtils.isEmpty(line)) {
            String manufacturer = Build.MANUFACTURER.replace("-", "_");
            return manufacturer;
        } else {
            return "Xiaomi";
        }
    }

    public static boolean isEMUI() {
        return Build.MANUFACTURER.equalsIgnoreCase("HUAWEI");
    }

    public static String getNetworkType(Context context) {
        String strNetworkType = "";

        NetworkInfo networkInfo = ((ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            try {
                if (networkInfo.getType() == 1) {
                    strNetworkType = "WIFI";
                } else if (networkInfo.getType() == 0) {
                    String _strSubTypeName = networkInfo.getSubtypeName();
                    int networkType = networkInfo.getSubtype();
                    switch(networkType) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                        strNetworkType = "2G";
                        break;
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                        strNetworkType = "3G";
                        break;
                    case 13:
                        strNetworkType = "4G";
                        break;
                    default:
                        if (!_strSubTypeName.equalsIgnoreCase("TD-SCDMA") && !_strSubTypeName.equalsIgnoreCase("WCDMA") && !_strSubTypeName.equalsIgnoreCase("CDMA2000")) {
                            strNetworkType = _strSubTypeName;
                        } else {
                            strNetworkType = "3G";
                        }
                    }
                }
            } catch (Exception var5) {
                Log.e("DeviceUtils", var5.getMessage());
            }
        }

        return strNetworkType;
    }
}
