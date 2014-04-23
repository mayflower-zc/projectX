package com.smarthome.service;

import com.smarthome.message.resp.TextMessage;
import com.smarthome.util.MessageUtil;
import com.smarthome.util.QQFaceUtil;
import com.smarthome.util.TextMessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * 核心服务类
 * <p/>
 * Created by zhengcong on 14-4-19.
 */
public class CoreService {
    private static Logger log = LoggerFactory.getLogger(CoreService.class);

    /**
     * 处理微信发来的请求
     *
     * @param request
     * @return
     */
    public static String processRequest(HttpServletRequest request) {
        String respMessage = null;
        try {
            //默认返回的文本消息内容
            String respContent = "请求处理异常，请稍后尝试";

            //xml请求解析
            Map<String, String> requestMap = MessageUtil.parseXml(request);

            //发送方账号 (open_id)
            String fromUserName = requestMap.get("FromUserName");
            //公众账号
            String toUserName = requestMap.get("ToUserName");
            //消息类型
            String msgType = requestMap.get("MsgType");

            //打印日志
            log.debug("processRequest p");
            System.out.printf("CoreService.processRequest {FromUserName:%s , ToUserName:%s , MsgType:%s }\n", fromUserName, toUserName, msgType);

            //回复文本消息
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            textMessage.setFuncFlag(0);

            //文本消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                //文本消息内容
                String content = requestMap.get("Content").trim();
                if (QQFaceUtil.isQqFace(content)) {
                    respContent = content;
                } else if (TextMessageUtil.WEATHER.equals(content)) {
                    respContent = TextMessageUtil.getWeatherMenu();
                } else if (TextMessageUtil.BUS_STATION_SEARCH.equals(content)) {
                    respContent = TextMessageUtil.getBusStationSearchMenu();
                } else if (TextMessageUtil.NEAR_SEARCH.equals(content)) {
                    respContent = TextMessageUtil.getNearSearchMenu();
                } else if (TextMessageUtil.SONG.equals(content)) {
                    respContent = TextMessageUtil.getSongMenu();
                } else if (TextMessageUtil.GAME.equals(content)) {
                    respContent = TextMessageUtil.getGameMenu();
                } else if (TextMessageUtil.BEAUTY_RADIO.equals(content)) {
                    respContent = TextMessageUtil.getBeautyRadioMenu();
                } else if (TextMessageUtil.CHAT.equals(content)) {
                    respContent = TextMessageUtil.getChatMenu();
                } else if (TextMessageUtil.HELP.equals(content)) {
                    respContent = TextMessageUtil.getMainMenu();
                } else if (content.startsWith("翻译")) {
                    String keyWord = content.replaceAll("^翻译", "").trim();
                    if ("".equals(keyWord)) {
                        respContent = TextMessageUtil.getTranslateUsage
                                ();
                    } else {
                        respContent = BaiduTranslateService.translate(keyWord);
                    }
                } else {
//                    respContent = "您发送的是文本消息！";
                    respContent = QQFaceUtil.emoji(0x1F6B2);
                }

            }
            //图片消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                respContent = "您发送的是图片消息！";
            }
            //地理位置消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "您发送的是地理位置消息！";
            }
            // 链接消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                respContent = "您发送的是链接消息！";
            }
            //音频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "您发送的是音频消息！";
            }
            //事件推送
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                //事件类型
                String eventType = requestMap.get("Event");
                //订阅
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
//                    respContent = "谢谢您的关注！";
                    respContent = TextMessageUtil.getMainMenu();
                }
                //取消订阅
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
                }
                // 自定义菜单点击事件
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    // 事件KEY值，与创建自定义菜单时指定的KEY值对应
                    String eventKey = requestMap.get("EventKey");

                    if (eventKey.equals("11")) {
                        respContent = "天气预报菜单项被点击！";
                    } else if (eventKey.equals("12")) {
                        respContent = "公交查询菜单项被点击！";
                    } else if (eventKey.equals("13")) {
                        respContent = "周边搜索菜单项被点击！";
                    } else if (eventKey.equals("14")) {
                        respContent = TodayInHistoryService.getTodayInHistoryInfo();
                    } else if (eventKey.equals("21")) {
                        respContent = "歌曲点播菜单项被点击！";
                    } else if (eventKey.equals("22")) {
                        respContent = "经典游戏菜单项被点击！";
                    } else if (eventKey.equals("23")) {
                        respContent = "美女电台菜单项被点击！";
                    } else if (eventKey.equals("24")) {
                        respContent = "聊天唠嗑菜单项被点击！";
                    } else if (eventKey.equals("31")) {
                        respContent = TextMessageUtil.getTranslateUsage();
                    }

                }
            }

            textMessage.setContent(respContent);
            respMessage = MessageUtil.textMessageToXml(textMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respMessage;
    }
}
