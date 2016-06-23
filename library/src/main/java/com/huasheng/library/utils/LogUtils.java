package com.huasheng.library.utils;

import android.util.Log;

/**
 * @author Chad.Cym
 * @ClassName: LogUtils
 * @Description: TODO Log管理类
 * @date 2014年7月8日 上午10:59:36
 */
public class LogUtils {

    private static final String TAG = "HuaSheng";

    /**
     * 是否显示日志
     */
    public static boolean mIsShowLog = true;

    /**
     * @param isShowLog 是否打印出Log
     */
    public static void initLogManager(boolean isShowLog) {
        mIsShowLog = isShowLog;

    }

    public static void e(String msg) {
        if (mIsShowLog) {

            Log.e(TAG, msg);

        }

    }

    public static void w(String msg) {
        if (mIsShowLog) {

            Log.w(TAG, msg);

        }
    }

    public static void d(String msg) {

        if (mIsShowLog) {

            Log.d(TAG, msg);

        }
    }

    public static void v(String msg) {
        if (mIsShowLog) {

            Log.v(TAG, msg);

        }
    }

    public static void i(String msg) {
        if (mIsShowLog) {

            Log.i(TAG, msg);

        }
    }
}
