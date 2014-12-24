package com.Business;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Net {
	
	/**
	 * �жϵ�ǰ�����Ƿ���á�
	 * 
	 * @param context
	 * @return ���ҽ�����ǰ�������ʱ����true�����򷵻�false��
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
