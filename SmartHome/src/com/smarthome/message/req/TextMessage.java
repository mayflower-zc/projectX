package com.smarthome.message.req;

/**
 * 文本消息
 * Created by zhengcong on 14-4-19.
 */
public class TextMessage extends BaseMessage {
    //消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        this.Content = content;
    }
}
