package com.huasheng.library.utils;

import android.widget.Toast;

import com.huasheng.library.base.BaseApplication;


/**
 * ToastUtils
 * 
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2013-12-9
 */
public class ToastUtils {

    public static void show(int resId) {
        show(BaseApplication.getContext().getResources().getText(resId), Toast.LENGTH_LONG);
    }

    public static void show(int resId, int duration) {
        show(BaseApplication.getContext().getResources().getText(resId), duration);
    }

    public static void show(CharSequence text) {
        show(text, Toast.LENGTH_LONG);
    }

    public static void show(CharSequence text, int duration) {
        Toast.makeText(BaseApplication.getContext(), text, duration).show();
    }

    public static void show(int resId, Object... args) {
        show(String.format(BaseApplication.getContext().getResources().getString(resId), args), Toast.LENGTH_LONG);
    }

    public static void show(String format, Object... args) {
        show(String.format(format, args), Toast.LENGTH_LONG);
    }

    public static void show(int resId, int duration, Object... args) {
        show(String.format(BaseApplication.getContext().getResources().getString(resId), args), duration);
    }

    public static void show(String format, int duration, Object... args) {
        show(String.format(format, args), duration);
    }
}
