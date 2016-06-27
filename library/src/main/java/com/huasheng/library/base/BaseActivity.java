package com.huasheng.library.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Observable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.huasheng.library.utils.ToastUtils;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;


/**
 * ClassName: BaseActivity<p>
 * Author:tb<p>
 * Fuction: Activity的基类<p>
 * CreateDate:2016/3/14 18:42<p>
 * UpdateUser:<p>
 * UpdateDate:<p>
 */
public abstract class BaseActivity extends FragmentActivity
        implements View.OnClickListener, BaseView {

    /* 上下文 */
    protected Context mContext = this;


    private static final String LAYOUT_LINEARLAYOUT = "LinearLayout";
    private static final String LAYOUT_FRAMELAYOUT = "FrameLayout";
    private static final String LAYOUT_RELATIVELAYOUT = "RelativeLayout";

    /**
     * 动画枚举
     */
    public enum ActivityAnimation {
        FADE_HOLD, SCALE_ALPHA, SCALE_ROTATE_ALPHA, SCALE_TRANSLATE_ROTATE_ALPHA, SCALE_TRANSLATE_ALPHA, HYPERSPACE, PUSH_LEFT, PUSH_RIGHT, PUSH_UP, SLIDE_LEFT, WAVE_SCALE_ALPHA, ZOOM_ENTER, SLIDE_UP
    }

    /**
     * rxbus对象
     * 结束Activity的可观测对象
     */
    private Observable<Boolean> mFinishObservable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        }
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                initView();
                initData();
                initAction();
            }
        });
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

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    protected View getDecorView() {
        return getWindow().getDecorView();
    }


    /**
     * 初始化View
     */
    protected void initView(){

    }

    /**
     * 初始数据
     */
    protected  void initData(){

    }

    /**
     * 设置监听
     */
    protected void initAction() {

    }

    /**
     * 返回Bundle
     *
     * @return
     */
    public Bundle getExtra() {
        Intent intent = getIntent();
        if (null == intent) {
            return null;
        }
        return intent.getExtras();
    }

    /**
     * 默认的跳转动画
     *
     * @param intent
     */
    public void startActivityes(final Intent intent, View v) {
//        RxView.clicks(v)
//                .throttleFirst(1, TimeUnit.SECONDS)
//                .subscribe(new Observer<Object>() {
//                    @Override
//                    public void onCompleted() {
//                        LogUtils.e("onCompleted");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        LogUtils.e("error");
//                    }
//
//                    @Override
//                    public void onNext(Object o) {
//                        LogUtils.d("button clicked");
//                        startActivity(intent, ActivityAnimation.PUSH_LEFT);
//                    }
//                });

    }

    /**
     * 跳转加动画
     *
     * @param intent
     * @param animationId
     */
    public void startActivity(Intent intent, ActivityAnimation animationId) {
        startActivity(intent);
//		initAnimation(animationId);
    }

    public void showActivity(Activity aty, Intent it) {
        aty.startActivity(it);
    }


    @Override
    public void onClick(View v) {

    }

    /**
     * 继承BaseView抽出显示信息通用行为
     *
     * @param msg
     */
    @Override
    public void toast(String msg) {
//        showSnackbar(msg);
        ToastUtils.show(msg);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0];
            int top = l[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     *
     * @param token
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
