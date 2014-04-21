package com.smarthome.message.resp;

/**
 * 消息基类(公众账号 -> 普通账号)
 * Created by zhengcong on 14-4-19.
 */
public class BaseMessage {
    //接收方账号(收到的OpenID)
    private String ToUserName;
    //开发者微信号
    private String FromUserName;
    //消息创建时间（整型）
    private long CreateTime;
    //消息类型（text/music/news）
    private String MsgType;
    //位0x0001被标志时，星标刚收到的消息
    private int FuncFlag;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        this.ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        this.CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        this.MsgType = msgType;
    }

    public int getFuncFlag() {
        return FuncFlag;
    }

    public void setFuncFlag(int funcFlag) {
        this.FuncFlag = funcFlag;
    }
}
