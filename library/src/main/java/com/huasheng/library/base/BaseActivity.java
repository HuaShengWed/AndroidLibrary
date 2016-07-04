package com.huasheng.library.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.huasheng.library.utils.ToastUtils;
import com.huasheng.library.view.dialog.CustomConfirmDialog;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import butterknife.ButterKnife;


/**
 * @author (YD)
 * @version (1.0)
 * @ProjectName WeddingPlanner
 * @Title: BaseActivity
 * @Package com.huasheng.library.base
 * @Description: TODO(BaseActivity.....)
 * @date 16/7/4
 * @time 下午3:52
 */
public abstract class BaseActivity extends FragmentActivity implements ISuperBaseView {

    private ProgressDialog mProgressDialog;

    private FragmentManager fragmentManager;

    private static final String LAYOUT_LINEARLAYOUT = "LinearLayout";
    private static final String LAYOUT_FRAMELAYOUT = "FrameLayout";
    private static final String LAYOUT_RELATIVELAYOUT = "RelativeLayout";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initContentView(savedInstanceState);
        // 初始化View注入
        ButterKnife.bind(this);
        initView();
        initPresenter();
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = null;
        if (name.equals(LAYOUT_FRAMELAYOUT)) {
            view = new AutoFrameLayout(context, attrs);
        }

        if (name.equals(LAYOUT_LINEARLAYOUT)) {
            view = new AutoLinearLayout(context, attrs);
        }

        if (name.equals(LAYOUT_RELATIVELAYOUT)) {
            view = new AutoRelativeLayout(context, attrs);
        }

        if (view != null) return view;

        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    /**
     * 初始化布局
     */
    public abstract void initContentView(Bundle savedInstanceState);

    /**
     * 初始化控件
     */
    public abstract void initView();

    /**
     * 初始化控制中心
     */
    public abstract void initPresenter();

    @Override
    public void finish() {
        super.finish();
    }

    /**
     * 显示单选对话框
     *
     * @param title           标题
     * @param message         提示信息
     * @param strings         选项数组
     * @param checkedItem     默认选中
     * @param onClickListener 点击事件的监听
     */
    public void showRadioButtonDialog(String title, String message, String[] strings, int checkedItem, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        if (!TextUtils.isEmpty(message)) {
            builder.setMessage(message);
        }
        builder.setSingleChoiceItems(strings, checkedItem, onClickListener);
        builder.create();
        builder.show();
    }

    /**
     * 显示单选对话框
     *
     * @param title           标题
     * @param strings         选项数组
     * @param onClickListener 点击事件的监听
     */
    public void showRadioButtonDialog(String title, String[] strings, DialogInterface.OnClickListener onClickListener) {
        showRadioButtonDialog(title, null, strings, 0, onClickListener);
    }

    /**
     * 弹出自定义对话框
     */
    public void showConfirmDialog(String title, View.OnClickListener positiveListener) {
        CustomConfirmDialog confirmDialog = new CustomConfirmDialog(this, title, positiveListener);
        confirmDialog.show();
    }

    @Override
    public void showProgress(boolean flag, String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(flag);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setMessage(message);
        }

        mProgressDialog.show();
    }

    @Override
    public void showProgress(String message) {
        showProgress(true, message);
    }

    @Override
    public void showProgress() {
        showProgress(true);
    }

    @Override
    public void showProgress(boolean flag) {
        showProgress(flag, "");
    }

    @Override
    public void hideProgress() {
        if (mProgressDialog == null)
            return;

        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showToast(int resId) {
        showToast(getString(resId));
    }

    @Override
    public void showToast(String msg) {
        if (!isFinishing()) {
            ToastUtils.show(msg, Toast.LENGTH_SHORT);
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void close() {
        finish();
    }

    //--------------------------Fragment相关--------------------------//

    /**
     * 获取Fragment管理器
     *
     * @return
     */
    public FragmentManager getBaseFragmentManager() {
        if (fragmentManager == null) {
            fragmentManager = getSupportFragmentManager();
        }
        return fragmentManager;
    }

    /**
     * 获取Fragment事物管理
     *
     * @return
     */
    public FragmentTransaction getFragmentTransaction() {
        return getBaseFragmentManager().beginTransaction();
    }

    /**
     * 替换一个Fragment
     *
     * @param res
     * @param fragment
     */
    public void replaceFragment(int res, BaseFragment fragment) {
        replaceFragment(res, fragment, false);
    }

    /**
     * 替换一个Fragment并设置是否加入回退栈
     *
     * @param res
     * @param fragment
     * @param isAddToBackStack
     */
    public void replaceFragment(int res, BaseFragment fragment, boolean isAddToBackStack) {
        FragmentTransaction fragmentTransaction = getFragmentTransaction();
        fragmentTransaction.replace(res, fragment);
        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();

    }

    /**
     * 添加一个Fragment
     *
     * @param res
     * @param fragment
     */
    public void addFragment(int res, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentTransaction();
        fragmentTransaction.add(res, fragment);
        fragmentTransaction.commit();
    }

    /**
     * 移除一个Fragment
     *
     * @param fragment
     */
    public void removeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentTransaction();
        fragmentTransaction.remove(fragment);
        fragmentTransaction.commit();
    }

    /**
     * 显示一个Fragment
     *
     * @param fragment
     */
    public void showFragment(Fragment fragment) {
        if (fragment.isHidden()) {
            FragmentTransaction fragmentTransaction = getFragmentTransaction();
            fragmentTransaction.show(fragment);
            fragmentTransaction.commit();
        }
    }

    /**
     * 隐藏一个Fragment
     *
     * @param fragment
     */
    public void hideFragment(Fragment fragment) {
        if (!fragment.isHidden()) {
            FragmentTransaction fragmentTransaction = getFragmentTransaction();
            fragmentTransaction.hide(fragment);
            fragmentTransaction.commit();
        }
    }

    //--------------------------Fragment相关end--------------------------//

}
