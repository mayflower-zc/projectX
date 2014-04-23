package com.smarthome.service;

import com.smarthome.pojo.AccessToken;
import com.smarthome.pojo.Menu;
import com.smarthome.util.MyX509TrustManager;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.SecureRandom;

/**
 * Created by zhengcong on 14-4-21.
 */
public class MenuService {
    // 获取access_token的接口地址（GET） 限200（次/天）
    public final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    //菜单生成url (https POST方式)
    public final static String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    //菜单查询url (https GET方式)
    public final static String MENU_GET_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
    //菜单删除接口 (https GET方式)
    public final static String MENU_DELETE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

    private static Logger log = LoggerFactory.getLogger(MenuService.class);

    /**
     * 获取access_token
     *
     * @param appid     凭证
     * @param appsecret 密钥
     * @return
     */
    public static AccessToken getAccessToken(String appid, String appsecret) {
        AccessToken accessToken = null;
        String reqestUrl = ACCESS_TOKEN_URL.replace("APPID", appid).replace("APPSECRET", appsecret);
        JSONObject jsonObject = httpRequest(reqestUrl, "GET", null);
        if (jsonObject != null) {
            try {
                accessToken = new AccessToken();
                accessToken.setToken(jsonObject.getString("access_token"));
                accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
            } catch (Exception e) {
                accessToken = null;
                // 获取token失败
                log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }

        }
        return accessToken;
    }

    /**
     * 创建自定义菜单
     *
     * @param menu        菜单实例
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public static int createMenu(Menu menu, String accessToken) {
        int result = -1;
        //拼装创建菜单的url
        String url = MENU_CREATE_URL.replace("ACCESS_TOKEN", accessToken);
        //将菜单对象转换成json串
        String jsonMenu = JSONObject.fromObject(menu).toString();
        JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);
        if (null != jsonObject) {
            result = jsonObject.getInt("errcode");
            if (0 != result) {
                log.error("创建菜单失败 errcode:{} errmsg:{}", result, jsonObject.getString("errmsg"));
            }
        }
        return result;
    }

    /**
     * 删除自定义菜单
     *
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public static int deleteMenu(String accessToken) {
        int result = -1;
        String url = MENU_DELETE_URL.replace("ACCESS_TOKEN", accessToken);
        JSONObject jsonObject = httpRequest(url, "GET", null);
        if (null != jsonObject) {
            result = jsonObject.getInt("errcode");
            if (0 != result) {
                log.error("创建菜单失败 errcode:{} errmsg:{}", result, jsonObject.getString("errmsg"));
            }
        }
        return result;
    }

    /**
     * 发起https请求并获取结果
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式 (GET、POST)
     * @param outputStr     提交的数据
     * @return (通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            //设置请求方式(GET/POST)
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            //当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                //注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            //将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException e) {
            log.error("Weixin server connection timed out.");
        } catch (Exception e) {
            log.error("https request error:{}", e);
        }
        return jsonObject;
    }

}
