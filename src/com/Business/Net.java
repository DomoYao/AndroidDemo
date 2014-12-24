package com.Business;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Net {
	
	/**
	 * 判断当前网络是否可用。
	 * 
	 * @param context
	 * @return 当且仅当当前网络可用时返回true，否则返回false。
	 */
	public static boolean isNetworkActived(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (manager == null) {
			return false;
		}
		NetworkInfo info = manager.getActiveNetworkInfo();
		return (info != null) && info.isAvailable();
	}
}
