package com.huasheng.library.utils.ime;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;

/**
 * 软键盘处理方式（隐藏、显示）
 */
public final class ImeUtil {

	/**
	 * hide ime durationMillis
	 */
	public static long durationMillis = 250;

	/**
	 * show ime delayMillis
	 */
	public static long delayMillis = 250;

	/**
	 * hide ime
	 * @param activity
	 */
	public static void hideIme(Activity activity) {
		if (activity == null) {
			return;
		}

		hideIme(activity, durationMillis);
	}

	/**
	 * hide ime
	 * @param activity
	 * @param durationMillis
	 */
	public static void hideIme(Activity activity, long durationMillis) {
		if (activity == null) {
			return;
		}

		if (durationMillis < 0) {
			durationMillis = 0;
		}

		View view = activity.getWindow().peekDecorView();
		if (view != null && view.getWindowToken() != null) {
			InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
			TranslateAnimation ta = new TranslateAnimation(0, 0, 120, 0);
			ta.setDuration(durationMillis);
			ta.setInterpolator(new LinearInterpolator());
			ta.setFillBefore(true);
			view.setAnimation(ta);
			imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

		}
	}

	/**
	 * hide ime
	 * @param activity
	 */
	public static void hideImeThen(Activity activity) {
		if (activity == null) {
			return;
		}

		hideIme(activity, 0);
	}

	/**
	 * hide ime
	 * @param v
	 */
	public static void hideIme(View v) {
		if (v == null) return;

		Context context = v.getContext();
		if (context != null && v.getWindowToken() != null) {
			InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

			imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
		}
	}

	/**
	 * show ime
	 * @param context
	 */
	public static void showIme(Activity context) {
		if (context == null) return;

		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
		// imm.showSoftInput(context.getCurrentFocus(),
		// InputMethodManager.HIDE_NOT_ALWAYS);
	}

	/**
	 * show ime
	 * @param v
	 */
	public static void showImeThen(View v) {
		showIme(v, 0);
	}

	/**
	 * show ime
	 * @param v
	 */
	public static void showIme(View v) {
		showIme(v, delayMillis);
	}

	/**
	 * show ime
	 * @param v
	 * @param delayMillis
	 */
	public static void showIme(final View v, long delayMillis) {
		if (v == null) return;

		final long dMillis = delayMillis <= 0 ? 0 : delayMillis;
		final Context context = v.getContext();
		if (context != null && v.isFocused()) {
			v.postDelayed(new Runnable() {

				@Override
				public void run() {
					InputMethodManager imm = (InputMethodManager) context
							.getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.showSoftInput(v, InputMethodManager.HIDE_NOT_ALWAYS);
				}
			}, dMillis);
		}
	}

	/**
	 * ime is show
	 * 
	 * <pre>
	 * note:可能不准确
	 * </pre>
	 * @param context
	 * @return
	 */
	public static boolean isImeShow(Context context) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

		return imm.isActive();
	}

}
