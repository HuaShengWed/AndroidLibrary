package com.huasheng.library.base;

import android.content.Context;


/**
 * @author (YD)
 * @version (1.0)
 * @ProjectName WeddingPlanner
 * @Title: ISuperBaseView
 * @Package com.huasheng.library.base
 * @Description: TODO(公共View接口.....)
 * @date 16/7/4
 * @time 下午3:47
 */
public interface ISuperBaseView {

    /**
     * 显示进度条
     *
     * @param flag    是否可取消
     * @param message 要显示的信息
     */
    void showProgress(boolean flag, String message);

    /**
     * 显示可取消的进度条
     *
     * @param message 要显示的信息
     */
    void showProgress(String message);

    /**
     * 显示可取消的无文字进度条
     */
    void showProgress();

    /**
     * 显示无文字进度条
     *
     * @param flag 是否可取消
     */
    void showProgress(boolean flag);

    /**
     * 隐藏进度条
     */
    void hideProgress();

    /**
     * 根据资源文件id弹出toast
     *
     * @param resId 资源文件id
     */
    void showToast(int resId);

    /**
     * 根据字符串弹出toast
     *
     * @param msg 提示内容
     */
    void showToast(String msg);

    /**
     * 获取当前上下文对象
     *
     * @return
     */
    Context getContext();

    /**
     * 结束当前页面
     */
    void close();

}
