package com.smarthome.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zhengcong on 14-4-23.
 */
public class HttpRequestUtil {

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
}
