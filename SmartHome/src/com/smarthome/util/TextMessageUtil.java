package com.smarthome.util;

import java.io.UnsupportedEncodingException;

/**
 * Created by zhengcong on 14-4-20.
 */
public class TextMessageUtil {
    public static final String WEATHER = "1"; //天气预报
    public static final String BUS_STATION_SEARCH = "2"; //公交查询
    public static final String NEAR_SEARCH = "3";  //周边搜索
    public static final String SONG = "4"; //歌曲点播
    public static final String GAME = "5"; //经典游戏
    public static final String BEAUTY_RADIO = "6"; //美女电台
    public static final String CHAT = "7"; //聊天唠嗑
    public static final String HELP = "?"; //显示帮助菜单

    /**
     * 主菜单
     *
     * @return
     */
    public static String getMainMenu() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("您好，我是小q，请回复数字选择服务：").append("\n\n");
        buffer.append("1  天气预报").append("\n");
        buffer.append("2  公交查询").append("\n");
        buffer.append("3  周边搜索").append("\n");
        buffer.append("4  歌曲点播").append("\n");
        buffer.append("5  经典游戏").append("\n");
        buffer.append("6  美女电台").append("\n");
        buffer.append("7  聊天唠嗑").append("\n\n");
        buffer.append("回复“?”显示此帮助菜单");
        return buffer.toString();
    }

    /**
     * 天气主菜单
     *
     * @return
     */
    public static String getWeatherMenu() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("/:sun天气预报使用指南").append("\n");
        buffer.append("回复 ：天气+城市名称").append("\n");
        buffer.append("例如 ：天气上海").append("\n");
        buffer.append("或者 ：上海天气").append("\n");
        buffer.append("回复\"?\"显示主菜单").append("\n");
        return buffer.toString();
    }

    /**
     * 公交查询主菜单
     *
     * @return
     */
    public static String getBusStationSearchMenu() {
        String tree_icon = QQFaceUtil.emoji(0x1F334);
        StringBuffer buffer = new StringBuffer();
        buffer.append("公交查询使用指南").append("\n");
        buffer.append(tree_icon + "查询城市公交线路").append("\n");
        buffer.append("格式 ：城市 , 线路名称").append("\n");
        buffer.append("例如 ：北京 , 636路").append("\n");
        buffer.append(tree_icon + "查询城市公交驾乘方案").append("\n");
        buffer.append("例如 ：北京 , 西二旗至新龙城").append("\n");
        buffer.append("回复\"?\"显示主菜单").append("\n");
        return buffer.toString();
    }

    public static String FUNCTION_NOT_SUPPORTED = "小q还没有这项本领。主人还在拼命的创造中。";

    /**
     * 周边搜索主菜单
     *
     * @return
     */
    public static String getNearSearchMenu() {
        return FUNCTION_NOT_SUPPORTED;
    }

    /**
     * 歌曲点播主菜单
     *
     * @return
     */
    public static String getSongMenu() {
        return FUNCTION_NOT_SUPPORTED;
    }

    /**
     * 游戏主菜单
     *
     * @return
     */
    public static String getGameMenu() {
        return FUNCTION_NOT_SUPPORTED;
    }

    /**
     * 美女电台主菜单
     *
     * @return
     */
    public static String getBeautyRadioMenu() {
        return FUNCTION_NOT_SUPPORTED;
    }

    /**
     * 聊天主菜单
     *
     * @return
     */
    public static String getChatMenu() {
        return FUNCTION_NOT_SUPPORTED;
    }

    /**
     * 计算采用utf-8编码方式时字符串所占字节数
     *
     * @param content
     * @return
     */
    public static int getByteSize(String content) {
        int size = 0;
        if (null != content) {
            try {
                // 汉字采用utf-8编码时占3个字节
                size = content.getBytes("utf-8").length;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return size;
    }

    /**
     * Q译通使用指南
     *
     * @return
     */
    public static String getTranslateUsage() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(QQFaceUtil.emoji(0xe148)).append("Q译通使用指南").append("\n\n");
        buffer.append("Q译通为用户提供专业的多语言翻译服务，目前支持以下翻译方向：").append("\n");
        buffer.append("    中 -> 英").append("\n");
        buffer.append("    英 -> 中").append("\n");
        buffer.append("    日 -> 中").append("\n\n");
        buffer.append("使用示例：").append("\n");
        buffer.append("    翻译我是中国人").append("\n");
        buffer.append("    翻译dream").append("\n");
        buffer.append("    翻译さようなら").append("\n\n");
        buffer.append("回复“?”显示主菜单");
        return buffer.toString();
    }
}
