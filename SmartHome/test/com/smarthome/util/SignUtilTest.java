package com.smarthome.util;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by zhengcong on 14-4-17.
 */
public class SignUtilTest {
    @Test
    public void testCheckSignature() throws Exception {
        boolean b = SignUtil.checkSignature("DF189887D57FFF640EB967FAF61C73AECADC4606", "20140417", "zhengcong");
        assertTrue(b);
    }
}
