package com.smarthome.message.resp;

/**
 * 文本消息
 * Created by zhengcong on 14-4-19.
 */
public class TextMessage extends BaseMessage {
    //回复的消息内容,限制长度不超过2048字节
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        this.Content = content;
    }
}
