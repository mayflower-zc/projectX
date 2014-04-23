package com.smarthome.service;

import com.google.gson.Gson;
import com.smarthome.pojo.baidu.translate.TranslateResult;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zhengcong on 14-4-23.
 */
public class BaiduTranslateService {
    /**
     * 发起http请求获取返回结果
     *
     * @param requestUrl 请求地址
     * @return
     */
    public static String httpRequest(String requestUrl) {
        StringBuffer buffer = new StringBuffer();
        URL url = null;
        try {
            url = new URL(requestUrl);
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();

            httpConnection.setDoOutput(false);
            httpConnection.setDoInput(true);
            httpConnection.setDefaultUseCaches(false);
            httpConnection.setRequestMethod("GET");
            httpConnection.connect();

            InputStream inputStream = httpConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferReader = new BufferedReader(reader);
            String str = null;
            while ((str = bufferReader.readLine()) != null) {
                buffer.append(str);
            }
            //释放资源
            bufferReader.close();
            reader.close();
            inputStream.close();
            inputStream = null;
            httpConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    /**
     * 翻译(中->英 英->中 日->中)
     *
     * @param source
     * @return
     */
    public static String translate(String source) {
        String dst = null;

        //组装查询地址
        String requestUrl = "http://openapi.baidu.com/public/2.0/bmt/translate?client_id=WA3U6474gAFZKWwF1WloCdCN&q={keyWord}&from=auto&to=auto";
        //
        requestUrl = requestUrl.replace("{keyWord}", urlEncodeUTF8(source));
        try {
            //查询并获取返回结果
            String requestJson = httpRequest(requestUrl);
            //通过Gson工具将json转换成TranslateResult对象
            TranslateResult translateResult = new Gson().fromJson(requestJson, TranslateResult.class);
            // 取出translateResult中的译文
            dst = translateResult.getTrans_result().get(0).getDst();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (null == dst)
            dst = "翻译系统异常，请稍候尝试！";
        return dst;
    }

    /**
     * URL utf-8 编码
     *
     * @param source
     * @return
     */
    public static String urlEncodeUTF8(String source) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String args[]) {
        // 翻译结果：The network really powerful
        System.out.println(translate("网络真强大"));
    }
}
