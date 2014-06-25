package com.baobaotao.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 所有PO都直接或间接地继承BaseDomain类
 * Created by zhengcong on 14-6-25.
 */
public class BaseDomain implements Serializable {
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
