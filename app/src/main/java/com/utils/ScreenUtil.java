package com.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 屏幕分辨率工具类
 * ScreenUtil
 */
public class ScreenUtil {
    private static int screenW;
    private static int screenH;
    private static float screenDensity;

    /**
     * 初始化屏幕分辨率工具
     * @param context
     */
    public static void initScreen(Context context) {
        DisplayMetrics metric = context.getResources().getDisplayMetrics();
        screenW = metric.widthPixels;
        screenH = metric.heightPixels;
        screenDensity = metric.density;
    }

    /**
     * 获取屏幕宽度
     * @return
     */
    public static int getScreenW() {
        return screenW;
    }

    /**
     * 获取屏幕宽度
     * @return
     */
    public static int getScreenH() {
        return screenH;
    }

    /**
     * 获取屏幕密度
     * @return
     */
    public static float getScreenDensity() {
        return screenDensity;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(float dpValue) {
        return (int) (dpValue * getScreenDensity() + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp(float pxValue) {
        return (int) (pxValue / getScreenDensity() + 0.5f);
    }
}
