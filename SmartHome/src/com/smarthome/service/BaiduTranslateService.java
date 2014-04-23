package com.smarthome.service;

import com.google.gson.Gson;
import com.smarthome.pojo.baidu.translate.TranslateResult;
import com.smarthome.util.HttpRequestUtil;

/**
 * Created by zhengcong on 14-4-23.
 */
public class BaiduTranslateService {

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
        requestUrl = requestUrl.replace("{keyWord}", HttpRequestUtil.urlEncodeUTF8(source));
        try {
            //查询并获取返回结果
            String requestJson = HttpRequestUtil.httpRequest(requestUrl);
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

    public static void main(String args[]) {
        // 翻译结果：The network really powerful
        System.out.println(translate("网络真强大"));
    }
}
