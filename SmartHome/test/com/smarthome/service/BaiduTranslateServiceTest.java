package com.smarthome.service;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by zhengcong on 14-4-23.
 */
public class BaiduTranslateServiceTest {
    @Test
    public void testTranslate() throws Exception {
        Assert.assertEquals("The network really powerful", BaiduTranslateService.translate("网络真强大"));
    }
}
