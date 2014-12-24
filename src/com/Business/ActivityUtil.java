package com.Business;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.view.Window;
import android.view.WindowManager;
/**
 * Activity�����ࡣ
 * 
 * @author Geek_Soledad (66704238@51uc.com)
 */
public class ActivityUtil {

	/**
	 * ���ص�ǰ��Ļ�Ƿ�Ϊ������
	 * 
	 * @param context
	 * @return ���ҽ�����ǰ��ĻΪ����ʱ����true,���򷵻�false��
	 */
	public static boolean isOriatationPortrait(Context context) {
		return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
	}

	/**
	 * �������뷨��
	 * 
	 * @param activity
	 */
	public static void hideInputMethod(Activity activity) {
		activity.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	}

	/**
	 * ����ȫ��
	 * 
	 * @param activity
	 *            Ҫ����ȫ����activity
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
	 * ���ر���
	 * 
	 * @param activity
	 *            Ҫ���ر����activity
	 */
	public static void hideTitleBar(Activity activity) {
		activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
	}

	/**
	 * ����activity��ĻΪ��ֱ����
	 * 
	 * @param activity
	 *            Activity����
	 */
	public static void setScreenPortrait(Activity activity) {
		activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}

	/**
	 * ����activity��ĻΪˮƽ����
	 * 
	 * @param activity
	 *            Activity����
	 */
	public static void setScreenLandscape(Activity activity) {
		activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	}
}