package com.smarthome.pojo;

/**
 * 复杂按钮 (父菜单)
 * Created by zhengcong on 14-4-20.
 */
public class ComplexButton extends Button {
    private Button[] sub_button;

    public Button[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }
}
