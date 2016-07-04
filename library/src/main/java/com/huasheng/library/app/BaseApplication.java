package com.huasheng.library.app;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.DisplayMetrics;

import com.huasheng.library.base.ActivityManager;
import com.huasheng.library.utils.ImageLoadProxyUtils;

import java.util.Locale;

/**
 * @author pengyuming
 * @version 1.0
 * @Package com.huasheng.library.base
 * @Description: TODO (用一句话描述该文件做什么)
 * Date: 2016-06-27  09:28
 */
public class BaseApplication extends Application {

    /**
     * 屏幕宽度
     */
    public static int screenWidth;
    /**
     * 屏幕高度
     */
    public static int screenHeight;
    /**
     * 屏幕密度
     */
    public static float screenDensity;

    private static BaseApplication mInstance;


    private static Context mApplicationContext;

    /**
     * 应用程序Activity管理类
     */
    private static ActivityManager mActivityManagerInstance;


    /**
     * 获取ApplicationContext
     */
    public static Context getContext() {
        return mApplicationContext;
    }


    public static BaseApplication getInstance() {
        return mInstance;
    }


    public static ActivityManager getActivityManager() {
        return mActivityManagerInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mApplicationContext = this;
        mActivityManagerInstance = ActivityManager.getActivityManager();
        initImageLoader(this);
        initScreenSize();
    }

    /**
     * 初始化imageloader
     */
    private void initImageLoader(Context mContext) {
        ImageLoadProxyUtils.initImageLoader(mContext);
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static String getVersion() {
        try {
            PackageManager manager = mInstance.getPackageManager();
            PackageInfo info = manager.getPackageInfo(mInstance.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取当前系统语言
     *
     * @return 当前系统语言
     */
    public static String getLanguage() {
        Locale locale = mInstance.getResources().getConfiguration().locale;
        String language = locale.getDefault().toString();
        return language;
    }

    /**
     * 初始化当前设备屏幕宽高
     */
    private void initScreenSize() {
        DisplayMetrics curMetrics = getApplicationContext().getResources().getDisplayMetrics();
        screenWidth = curMetrics.widthPixels;
        screenHeight = curMetrics.heightPixels;
        screenDensity = curMetrics.density;
    }
}
