package com.smarthome.pojo;

/**
 * 普通按钮 (子按钮)
 * Created by zhengcong on 14-4-20.
 */
public class CommonButton extends Button {
    private String type;
    private String key;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
