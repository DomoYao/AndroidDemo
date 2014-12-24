package com.Business;

import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ˫�������ࡣ
 * 
 * @author Geek_Soledad (66704238@51uc.com)
 */
public abstract class DoubleClick {
	protected Context mContext;
	/**
	 * ��ʼ�����ʱ�䡣
	 */
	private long mStartTime;

	private DoubleClickListener doubleClickListener;
	private Toast mToast;

	/**
	 * ���췽������ʼ��Context���󼰿�ʼ������ʱ�䡣
	 * 
	 * @param context
	 */
	public DoubleClick(Context context) {
		mContext = context;
		mToast = new Toast(context); 

		LinearLayout layout = new LinearLayout(context);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		layout.setBackgroundResource(android.R.drawable.toast_frame);
		layout.setLayoutParams(layoutParams);
        TextView tv = new TextView(context);
        tv.setId(android.R.id.message);
        tv.setTextColor(Color.WHITE);
        LayoutParams tvParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        tvParams.weight = 1;
        tv.setShadowLayer(2.75f, 1.3f, 1.3f, 0xBB000000);
        layout.addView(tv);
		mToast.setView(layout);
		
		mStartTime = -1;
	}

	protected void resetStartTime() {
		mStartTime = -1;
	}

	/**
	 * ��ĳ������Ҫ˫����ִ��ʱ�����ô˷�����
	 * 
	 * @param delayTime
	 *            �ж�˫����ʱ�䡣
	 * @param msg
	 *            ����һ�ε��ʱ����������ʾ��Ϣ�����Ϊnull��������ʾ��
	 */
	public void doDoubleClick(int delayTime, String msg) {
		if (!doInDelayTime(delayTime)) {
			mToast.setDuration(delayTime);
			mToast.setText(msg);
			mToast.show();
		}
	}

	/**
	 * �������ָ����ʱ������ִ��doOnDoubleClick�����򷵻�false��
	 * 
	 * @param delayTime
	 *            ָ�����ӳ�ʱ�䡣
	 * @return ���ҽ�����ָ����ʱ����ʱ����true,���򷵻�false��
	 */
	protected boolean doInDelayTime(int delayTime) {
		long nowTime = System.currentTimeMillis();
		if (nowTime - mStartTime <= delayTime) {
			if (doubleClickListener != null) {
				doubleClickListener.afteDoubleClick();
			}
			mStartTime = -1;
			return true;
		}
		mStartTime = nowTime;
		return false;
	}

	/**
	 * ��ĳ������Ҫ˫����ִ��ʱ�����ô˷�����
	 * 
	 * @param delayTime
	 *            �ж�˫����ʱ�䡣
	 * @param msgResid
	 *            ����һ�ε��ʱ����������ʾ��Ϣ����ԴID��
	 */
	public void doDoubleClick(int delayTime, int msgResid) {
		if (!doInDelayTime(delayTime)) {
			mToast.setDuration(delayTime);
            mToast.setText(msgResid);
			mToast.show();
		}
	}

	public interface DoubleClickListener {
		public void afteDoubleClick();
	}

	public DoubleClickListener getDoubleClickListener() {
		return doubleClickListener;
	}

	public void setDoubleClickListener(DoubleClickListener doubleClickListener) {
		this.doubleClickListener = doubleClickListener;
	}
}
