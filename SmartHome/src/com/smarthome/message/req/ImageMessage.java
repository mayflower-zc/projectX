package com.smarthome.message.req;

/**
 * 图片消息
 * Created by zhengcong on 14-4-19.
 */
public class ImageMessage extends BaseMessage {
    //图片链接
    private String PicUrl;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        this.PicUrl = picUrl;
    }
}
