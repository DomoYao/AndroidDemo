package com.Business;
import android.util.Log;
public class LogAuto {
	public static final int ASSERT = Log.ASSERT;
	public static final int DEBUG = Log.DEBUG;
	public static final int ERROR = Log.ERROR;
	public static final int INFO = Log.INFO;
	public static final int VERBOSE = Log.VERBOSE;
	public static final int WARN = Log.WARN;
	protected final String TAG;

	protected LogAuto(Class<?> clazz) {
		TAG = initTag(clazz);
	}

	protected String initTag(Class<?> clazz) {
		return String.format("%s: %s", Version.ANDROIDKIT_NAME, clazz.getSimpleName());
	}

	public static LogAuto getLog(Class<?> clazz) {
		return new LogAuto(clazz);
	}

	public void d(String msg) {
		Log.d(TAG, msg);
	}

	public void d(String msg, Throwable tr) {
		Log.d(TAG, msg, tr);
	}

	public void e(String msg) {
		Log.e(TAG, msg);
	}

	public void e(String msg, Throwable tr) {
		Log.e(TAG, msg, tr);
	}

	public void i(String msg) {
		Log.i(TAG, msg);
	}

	public void i(String msg, Throwable tr) {
		Log.i(TAG, msg, tr);
	}

	public void v(String msg) {
		Log.v(TAG, msg);
	}

	public void v(String msg, Throwable tr) {
		Log.v(TAG, msg, tr);
	}

	public void w(String msg) {
		Log.w(TAG, msg);
	}

	public void w(Throwable tr) {
		Log.w(TAG, tr);
	}

	public void w(String msg, Throwable tr) {
		Log.w(TAG, msg, tr);
	}

	public void getStackTraceString(Throwable tr) {
		Log.getStackTraceString(tr);
	}

	public void isLoggable(int level) {
		Log.isLoggable(TAG, level);
	}

	public void println(int priority, String msg) {
		Log.println(priority, TAG, msg);
	}

}
