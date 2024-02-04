package com.yusufsezer.util;

public class ClampUtil {

    public static int clamp(int value, int min, int max) {
        return Math.min(Math.max(value, min), max);
    }

    public static int clampToByte(int value) {
        return clamp(value, 0, 255);
    }
}
