package com.felis.cloudmusic.utils;

public class RandomChineseCharUtil {
    public static char get() {
        return (char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00 + 1)));
    }
}
