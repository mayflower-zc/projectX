package com.smarthome.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by zhengcong on 14-4-20.
 */
public class QQFaceUtilTest {
    @Test
    public void testIsQqFace() throws Exception {

    }

    @Test
    public void testEmoji() throws Exception {
        String bike = String.valueOf(Character.toChars(0x1F6B2));
        assertEquals(bike, QQFaceUtil.emoji(0x1F6B2));
    }
}
