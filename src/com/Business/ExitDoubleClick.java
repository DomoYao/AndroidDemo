package com.Business;

import android.app.Activity;
import android.content.Context;

/**
 * ˫���˳�Activity���ࡣ
 * 
 * @author Geek_Soledad (66704238@51uc.com)
 */
public class ExitDoubleClick extends DoubleClick {

	private static ExitDoubleClick exit;

	private ExitDoubleClick(Context context) {
		super(context);
		setDoubleClickListener(new DoubleClickListener() {
			@Override
			public void afteDoubleClick() {
				((Activity) mContext).finish();
				destroy();
			}
		});
	}

	/**
	 * ����һ��˫���˳���ʵ����
	 * 
	 * @param context
	 * @return ExitDoubleClick
	 */
	public static synchronized ExitDoubleClick getInstance(Context context) {
		if (exit == null) {
			exit = new ExitDoubleClick(context);
		}
		return exit;
	}

	/**
	 * ˫���˳�Activity�����msgΪnull����Ĭ����ʾ����ʾ��Ϊ"�ٰ�һ���˳�"��
	 */
	@Override
	public void doDoubleClick(int delayTime, String msg) {
		if (msg == null || msg.equals("")) {
			msg = "�ٰ�һ���˳�";
		}
		super.doDoubleClick(delayTime, msg);
	}

	private static void destroy() {
		exit = null;
	}
}
