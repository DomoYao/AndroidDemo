package com.Business;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.view.Window;
import android.view.WindowManager;
/**
 * Activity工具类。
 * 
 * @author Geek_Soledad (66704238@51uc.com)
 */
public class ActivityUtil {

	/**
	 * 返回当前屏幕是否为竖屏。
	 * 
	 * @param context
	 * @return 当且仅当当前屏幕为竖屏时返回true,否则返回false。
	 */
	public static boolean isOriatationPortrait(Context context) {
		return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
	}

	/**
	 * 隐藏输入法。
	 * 
	 * @param activity
	 */
	public static void hideInputMethod(Activity activity) {
		activity.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	}

	/**
	 * 设置全屏
	 * 
	 * @param activity
	 *            要设置全屏的activity
	 */
	public static void setFullScreen(Activity activity) {
		activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
		Window window = activity.getWindow();
		WindowManager.LayoutParams params = window.getAttributes();
		params.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
		window.setAttributes(params);
		window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
	}

	/**
	 * 隐藏标题
	 * 
	 * @param activity
	 *            要隐藏标题的activity
	 */
	public static void hideTitleBar(Activity activity) {
		activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
	}

	/**
	 * 设置activity屏幕为垂直方向。
	 * 
	 * @param activity
	 *            Activity对象。
	 */
	public static void setScreenPortrait(Activity activity) {
		activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}

	/**
	 * 设置activity屏幕为水平方向
	 * 
	 * @param activity
	 *            Activity对象。
	 */
	public static void setScreenLandscape(Activity activity) {
		activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	}
}