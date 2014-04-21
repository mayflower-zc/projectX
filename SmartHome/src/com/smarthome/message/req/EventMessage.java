package com.smarthome.message.req;

/**
 * 事件推送只支持微信4.5版本，目前仅开启自定义菜单接口事件推送。其余功能即将开放，敬请期待。
 * Created by zhengcong on 14-4-19.
 */
public class EventMessage {
    //开发者微信号
    private String ToUserName;
    //发送方账号(一个OpenID)
    private String FromUserName;
    //消息创建时间
    private long CreateTime;
    //消息类型(event)
    private String MsgType;
    //事件类型，subscribe(订阅)、unsubscribe(取消订阅)、CLICK(自定义菜单点击事件)
    private String Event;
    //事件KEY值，与自定义菜单接口中KEY值对应
    private String EventKey;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }
}
