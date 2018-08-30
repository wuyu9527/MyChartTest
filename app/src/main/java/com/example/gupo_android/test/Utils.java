package com.example.gupo_android.test;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    /**
     *    String str="{\"keyword\":\"xin \"hua\",\"type\":\"news\",\"countryName\":\"not中国\"," +
     *   "\"pageNo\":1,\"pageSize\":10,\"beginDate\":\"\"\"2018-05-01 00:00:00\"\"\"," +
     *  "\"endDate\":\"2018-05-09 23:59:59\"}";
     * Pattern pattern = Pattern.compile("\"([a-zA-z0-9]{0,})\":\"{1}([a-zA-z0-9\\-\\s\\:\\u4e00-\\u9fa5\"]{0,})\"{1}[\\,\\}]{1}|" +
     * "\"([a-zA-z0-9]{0,})\":([a-zA-z0-9\\-\\s\\:\\u4e00-\\u9fa5\"]{0,})[\\,\\}]{1}");
     * Matcher matcher = pattern.matcher(str);
     * while (matcher.find()){
     * if(matcher.group(1)!=null) {
     * System.out.println("key:" + matcher.group(1));
     *System.out.println("value:" + matcher.group(2));
     *}else{
     *System.out.println("key:" + matcher.group(3));
     *System.out.println("value:" + matcher.group(4));
     *}
     *}
     *
     */

    /**
     * 对输入的字符串进行处理
     *
     * @param input 传入的需要处理的字符串
     * @return
     */
    private String regexChange(String input) {
        String result = input;
        //匹配规则是当avatar是{}包装的对象
        Pattern mPAvatar = Pattern.compile("\"avatar\":\\{([^\\}]*)\\}");
        Matcher mMAvatar = mPAvatar.matcher(result);
        while (mMAvatar.find()) {//如果找到 开始替换
            result = result.replaceFirst("\"avatar\":\\{([^\\}]*)\\}", getKey(mMAvatar.group()));
        }
        return result;
    }

    /**
     * 取出关键值返回
     *
     * @param group
     * @return
     */
    private String getKey(String group) {
        Pattern pattern = Pattern.compile("\"([a-zA-z0-9]{0,})\":\"{1}([a-zA-z0-9\\-\\s\\:\\u4e00-\\u9fa5\"]{0,})\"{1}[\\,\\}]{1}|" +
                "\"([a-zA-z0-9]{0,})\":([a-zA-z0-9\\-\\s\\:\\u4e00-\\u9fa5\"]{0,})[\\,\\}]{1}");
        Matcher matcher = pattern.matcher(group);
        StringBuffer buffer = new StringBuffer();
        buffer.append("\"avatar\":\"http:///");
        while (matcher.find()) {
            buffer.append(matcher.group(1));
        }
        buffer.append("\"");
        return buffer.toString();
    }

    /**
     * 找出json所有key value
     */
    private void getJsonKeyAndVlue(){
        String str = "{\"docw\":\"sdafgf\",\"examine_sheet\":[{\"id\":\"20180417G0080078\",\"items\":[{\"id\":34583983,\"item_name\":\"纤维蛋白原\",\"status\":0,\"type\":\"1\",\"unit\":\"mg/L\",\"value\":\"251.5\",\"value_range\":\"200.0-400.0\"},{\"id\":34583982,\"item_name\":\"凝血酶原时间正常对照\",\"status\":0,\"type\":\"0\",\"unit\":\"秒\",\"value\":\"11.05\"},{\"id\":34583981,\"item_name\":\"凝血酶原时间\",\"status\":0,\"type\":\"1\",\"unit\":\"秒\",\"value\":\"12.30\",\"value_range\":\"10.00-14.50\"},{\"id\":34583980,\"item_name\":\"凝血酶时间\",\"status\":0,\"type\":\"1\",\"unit\":\"秒\",\"value\":\"20.0\",\"value_range\":\"15.0-22.0\"},{\"id\":34583979,\"item_name\":\"国际标准化比值\",\"status\":0,\"type\":\"1\",\"value\":\"1.12\",\"value_range\":\"0.82-1.50\"},{\"id\":34583978,\"item_name\":\"部分凝血活酶时间\",\"status\":1,\"type\":\"1\",\"unit\":\"秒\",\"value\":\"40.9\",\"value_range\":\"26.0-40.0\"},{\"id\":34583977,\"item_name\":\"D-二聚体\",\"status\":0,\"type\":\"1\",\"unit\":\"ug/L\",\"value\":\"270\",\"value_range\":\"0-550\"}],\"sheet_name\":\"凝血检测\",\"sheet_time\":\"2018-04-17 09:38:42.0\",\"item\":[{\"unit\":\"ug/L\",\"value_range\":\"0-550\",\"item_name\":\"D-二聚体\",\"value\":\"270\",\"status\":0},{\"unit\":\"秒\",\"value_range\":\"26.0-40.0\",\"item_name\":\"部分凝血活酶时间\",\"value\":\"40.9\",\"status\":1},{\"unit\":null,\"value_range\":\"0.82-1.50\",\"item_name\":\"国际标准化比值\",\"value\":\"1.12\",\"status\":0},{\"unit\":\"秒\",\"value_range\":\"15.0-22.0\",\"item_name\":\"凝血酶时间\",\"value\":\"20.0\",\"status\":0},{\"unit\":\"秒\",\"value_range\":\"10.00-14.50\",\"item_name\":\"凝血酶原时间\",\"value\":\"12.30\",\"status\":0},{\"unit\":\"秒\",\"value_range\":null,\"item_name\":\"凝血酶原时间正常对照\",\"value\":\"11.05\",\"status\":0},{\"unit\":\"mg/L\",\"value_range\":\"200.0-400.0\",\"item_name\":\"纤维蛋白原\",\"value\":\"251.5\",\"status\":0}]}],\"images\":\"\"}";
        Log.i("whx", "str:" + str);
        Pattern pattern = Pattern.compile("\"([a-zA-z0-9]{0,})\":\"{1}([a-zA-z0-9\\-\\s\\:\\u4e00-\\u9fa5\"]{0,})\"{1}[\\,\\}]{1}|" +
                "\"([a-zA-z0-9]{0,})\":([a-zA-z0-9\\-\\s\\:\\u4e00-\\u9fa5\"]{0,})[\\,\\}]{1}");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            if (matcher.group(1) != null) {
                Log.i("whx", "key:" + matcher.group(1));
                Log.i("whx", "value:" + matcher.group(2));
            } else {
                Log.i("whx", "key:" + matcher.group(3));
                Log.i("whx", "value:" + matcher.group(4));
            }
        }
    }

}
