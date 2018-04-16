package com.ayhalo.ecampus.utils;

/**
 * com.lz.langfang.utils
 * Created by wuyh on 2018/3/9.
 */

public class DoubleClickExit {

    public static long mLastClick = 0L;
    private static final int THRESHOLD = 2000;

    public static boolean check() {
        long now = System.currentTimeMillis();
        boolean b = now - mLastClick < THRESHOLD;
        mLastClick = now;
        return b;
    }
}
