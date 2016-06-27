package com.huasheng.library.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.LinkedList;

/**
 * @author pengyuming
 * @version 1.0
 * @Package com.huasheng.library.base
 * @Description: TODO (用一句话描述该文件做什么)
 * Date: 2016-06-27  09:28
 */
public class BaseApplication extends Application {

    private static BaseApplication instance;

    private static Context mApplicationContext;

    private LinkedList<BaseActivity> mBaseActivityList = new LinkedList<BaseActivity>();

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationContext = this;
        instance = this;

    }

    // 获取ApplicationContext
    public static Context getContext() {
        return mApplicationContext;
    }

    public void addActivity(BaseActivity activity) {
        mBaseActivityList.add(activity);
    }

    /*
    * 销毁所有activity
    */
    public void allFinish() {
        for (Activity activity : mBaseActivityList) {
            if (activity != null) {
                activity.finish();
            }
        }
    }
}
