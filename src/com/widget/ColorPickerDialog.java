package com.widget;


import com.Business.ActivityUtil;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;



/**
 * ��ɫѡ����������Ĵ���������ϴ��룬���ı�ʹ�������ں�������¡�
 * 
 * @author Geek_Soledad (66704238@51uc.com)
 */
public class ColorPickerDialog extends Dialog {
	private Context mContext;
	/**
	 * ���⡣
	 */
	private String mTitle;
	/**
	 * ��ʼ��ɫ��
	 */
	private int mInitialColor;
	/**
	 * ��ɫѡ���Ļص��ӿڡ�
	 */
	private OnColorChangedListener mListener;

	/**
	 * ���췽����
	 * 
	 * @param context
	 * @param title
	 *            �Ի�����⡣
	 * @param l
	 *            ��ɫѡ���Ļص��ӿڡ�
	 */
	public ColorPickerDialog(Context context, String title,
			OnColorChangedListener l) {
		this(context, title, Color.GRAY, l);

	}

	/**
	 * ���췽����
	 * 
	 * @param context
	 * @param title
	 *            �Ի���ı��⡣
	 * @param initalColor
	 *            ��ʼ����ɫ��
	 * @param l
	 *            ��ɫѡ���Ļص��ӿڡ�
	 */
	public ColorPickerDialog(Context context, String title, int initalColor,
			OnColorChangedListener l) {
		super(context);
		mContext = context;
		mTitle = title;
		mListener = l;
		mInitialColor = initalColor;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		boolean isPortrait = ActivityUtil.isOriatationPortrait(mContext);
		if (isPortrait) {
			setContentView(new PortraitColorPickerView(mContext, mListener));
		} else {
			setContentView(new LandscapeColorPickerView(mContext, mListener));
		}
		setTitle(mTitle);
	}

	public interface OnColorChangedListener {
		void colorChanged(int color);
	}

	protected abstract class ColorPickerView extends View {
		protected OnColorChangedListener mListener;
		protected Paint mCirclePaint;// ����ɫ������
		protected Paint mCenterPaint;// �м�Բ����
		protected Paint mLinePaint;// �ָ��߻���
		protected Paint mRectPaint;// ���䷽�黭��

		protected Shader mRectShader;// ���䷽�齥��ͼ��
		protected float mRectLeft;// ���䷽���󶥵�x����
		protected float mRectTop;// ���䷽���󶥵�y����
		protected float mRectRight;// ���䷽���ҵ׵�x����
		protected float mRectBottom;// ���䷽���ҵ׵�y����

		protected int mHeight;// View��
		protected int mWidth;// View��
		protected float mCircleRadius;// ɫ���뾶(paint�в�)
		protected float mCenterRadius;// ����Բ�뾶

		protected boolean mDownInCircle = true;// ���ڽ��价��
		protected boolean mDownInRect;// ���ڽ��䷽����
		protected boolean mHighlightCenter;// ����
		protected boolean mlittleLightCenter;// ΢��

		protected final int[] mCircleColors;// ����ɫ����ɫ
		protected final int[] mRectColors;// ���䷽����ɫ

		public ColorPickerView(Context context, OnColorChangedListener l) {
			super(context);
			this.mListener = l;
			mCircleColors = new int[] { 0xFFFF0000, 0xFFFF00FF, 0xFF0000FF,
					0xFF00FFFF, 0xFF00FF00, 0xFFFFFF00, 0xFFFF0000 };
			mRectColors = new int[] { 0xFF000000, mInitialColor, 0xFFFFFFFF };
		}

		protected int ave(int s, int d, float p) {
			return s + Math.round(p * (d - s));
		}

		/**
		 * �����Ƿ���ɫ����
		 * 
		 * @param x
		 *            ����
		 * @param y
		 *            ����
		 * @param outRadius
		 *            ɫ����뾶
		 * @param inRadius
		 *            ɫ���ڰ뾶
		 * @return
		 */
		protected boolean inColorCircle(float x, float y, float outRadius,
				float inRadius) {
			double outCircle = outRadius * outRadius;
			double inCircle = inRadius * inRadius;
			double fingerCircle = x * x + y * y;
			return (fingerCircle < outCircle && fingerCircle > inCircle);
		}

		/**
		 * �����Ƿ�������Բ��
		 * 
		 * @param x
		 *            ����
		 * @param y
		 *            ����
		 * @param centerRadius
		 *            Բ�뾶
		 * @return
		 */
		protected boolean inCenter(float x, float y, float centerRadius) {
			double centerCircle = centerRadius * centerRadius;
			double fingerCircle = x * x + y * y;
			return (fingerCircle < centerCircle);
		}

		/**
		 * �����Ƿ��ڽ���ɫ��
		 * 
		 * @param x
		 * @param y
		 * @return
		 */
		protected boolean inRect(float x, float y) {
			return (x <= mRectRight && x >= mRectLeft && y <= mRectBottom && y >= mRectTop);
		}

		/**
		 * ��ȡԲ������ɫ
		 * 
		 * @param colors
		 * @param unit
		 * @return
		 */
		protected int interpCircleColor(int colors[], float unit) {
			if (unit <= 0) {
				return colors[0];
			}
			if (unit >= 1) {
				return colors[colors.length - 1];
			}

			float p = unit * (colors.length - 1);
			int i = (int) p;
			p -= i;

			// now p is just the fractional part [0...1) and i is the index
			int c0 = colors[i];
			int c1 = colors[i + 1];
			int a = ave(Color.alpha(c0), Color.alpha(c1), p);
			int r = ave(Color.red(c0), Color.red(c1), p);
			int g = ave(Color.green(c0), Color.green(c1), p);
			int b = ave(Color.blue(c0), Color.blue(c1), p);

			return Color.argb(a, r, g, b);
		}

		@Override
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			super.onMeasure(mWidth, mHeight);
		}

		/**
		 * ����ָ����ʱ��
		 * 
		 * @param inCircle
		 *            �Ƿ���ɫ���ϡ�
		 * @param inCenter
		 *            �Ƿ���ԲȦ�����ϡ�
		 * @param inRect
		 *            �Ƿ��ڽ�������ϡ�
		 */
		protected void onActionDown(boolean inCircle, boolean inCenter,
				boolean inRect) {
			mDownInCircle = inCircle;
			mDownInRect = inRect;
			mHighlightCenter = inCenter;
		}

		/**
		 * ��ȡ���������ɫ
		 * 
		 * @param colors
		 * @param x
		 *            ��ָ�����������x����
		 * @param y
		 *            ��ָ�����������y����
		 * @return
		 */
		abstract int interpRectColor(int[] colors, float x, float y);

		/**
		 * ����ָ�ƶ�ʱ��
		 * 
		 * @param x
		 *            ��ָλ����Բ�ĺ��������Ծ��롣
		 * @param y
		 *            ��ָλ����Բ�����������Ծ��롣
		 * @param inCircle
		 *            �Ƿ���ɫ���С�
		 * @param inCenter
		 *            �Ƿ���ԲȦ�С�
		 * @param inRect
		 *            �Ƿ��ڽ�������С�
		 */
		protected void onActionMove(float x, float y, boolean inCircle,
				boolean inCenter, boolean inRect) {
			if (mDownInCircle && inCircle) {// down���ڽ���ɫ����, ��moveҲ�ڽ���ɫ����
				final float angle = (float) Math.atan2(y, x);
				float unit = (float) (angle / (2 * Math.PI));
				if (unit < 0) {
					unit += 1;
				}
				mCenterPaint.setColor(interpCircleColor(mCircleColors, unit));
			} else if (mDownInRect && inRect) {// down�ڽ��䷽����, ��moveҲ�ڽ��䷽����
				mCenterPaint.setColor(interpRectColor(mRectColors, x, y));
			}
			if ((mHighlightCenter && inCenter)
					|| (mlittleLightCenter && inCenter)) {// �������Բ,
															// ��ǰ�ƶ�������Բ
				mHighlightCenter = true;
				mlittleLightCenter = false;
			} else if (mHighlightCenter || mlittleLightCenter) {// ���������Բ,
																// ��ǰ�Ƴ�����Բ
				mHighlightCenter = false;
				mlittleLightCenter = true;
			} else {
				mHighlightCenter = false;
				mlittleLightCenter = false;
			}
			invalidate();
		}

		/**
		 * ����ָ�ɿ�ʱ��
		 * 
		 * @param inCenter
		 *            �Ƿ���ԲȦ�С�
		 */
		protected void onActionUp(boolean inCenter) {
			if (mHighlightCenter && inCenter) {// ���������Բ, �ҵ�ǰ����������Բ
				if (this.mListener != null) {
					this.mListener.colorChanged(mCenterPaint.getColor());
					ColorPickerDialog.this.dismiss();
				}
			}
			if (mDownInCircle) {
				mDownInCircle = false;
			}
			if (mDownInRect) {
				mDownInRect = false;
			}
			if (mHighlightCenter) {
				mHighlightCenter = false;
			}
			if (mlittleLightCenter) {
				mlittleLightCenter = false;
			}
			invalidate();
		}
	}

	/**
	 * ����ʱ����ɫѡ������view.
	 * 
	 * @author msdx
	 * 
	 */
	private class PortraitColorPickerView extends ColorPickerView {

		public PortraitColorPickerView(Context context, OnColorChangedListener l) {
			super(context, l);
			Display display = ColorPickerDialog.this.getWindow()
					.getWindowManager().getDefaultDisplay();
			int height = (int) (display.getHeight() * 0.5f) - 36;
			int width = (int) (display.getWidth() * 0.7f);
			this.mHeight = height;
			this.mWidth = width;
			setMinimumHeight(height);
			setMinimumWidth(width);

			// ����ɫ������

			Shader s = new SweepGradient(0, 0, mCircleColors, null);
			mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			mCirclePaint.setShader(s);
			mCirclePaint.setStyle(Paint.Style.STROKE);
			mCirclePaint.setStrokeWidth(50);
			mCircleRadius = width / 2 * 0.7f - mCirclePaint.getStrokeWidth()
					* 0.5f;

			// ����Բ����
			mCenterPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			mCenterPaint.setColor(mInitialColor);
			mCenterPaint.setStrokeWidth(5);
			mCenterRadius = (mCircleRadius - mCirclePaint.getStrokeWidth() / 2) * 0.7f;

			// �߿����
			mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			mLinePaint.setColor(Color.parseColor("#72A1D1"));
			mLinePaint.setStrokeWidth(4);

			// �ڰ׽������

			mRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			mRectPaint.setStrokeWidth(5);
			mRectLeft = -mCircleRadius - mCirclePaint.getStrokeWidth() * 0.5f;
			mRectTop = mCircleRadius + mCirclePaint.getStrokeWidth() * 0.5f
					+ mLinePaint.getStrokeMiter() * 0.5f + 15;
			mRectRight = mCircleRadius + mCirclePaint.getStrokeWidth() * 0.5f;
			mRectBottom = mRectTop + 50;
		}

		@Override
		protected void onDraw(Canvas canvas) {
			// �ƶ�����
			canvas.translate(mWidth / 2, mHeight / 2 - 50);
			// ������Բ
			canvas.drawCircle(0, 0, mCenterRadius, mCenterPaint);
			// �Ƿ���ʾ����Բ���СԲ��
			if (mHighlightCenter || mlittleLightCenter) {
				int c = mCenterPaint.getColor();
				mCenterPaint.setStyle(Paint.Style.STROKE);
				if (mHighlightCenter) {
					mCenterPaint.setAlpha(0xFF);
				} else if (mlittleLightCenter) {
					mCenterPaint.setAlpha(0x90);
				}
				canvas.drawCircle(0, 0,
						mCenterRadius + mCenterPaint.getStrokeWidth(),
						mCenterPaint);

				mCenterPaint.setStyle(Paint.Style.FILL);
				mCenterPaint.setColor(c);
			}
			// ��ɫ��
			canvas.drawOval(new RectF(-mCircleRadius, -mCircleRadius,
					mCircleRadius, mCircleRadius), mCirclePaint);
			// ���ڰ׽����
			if (mDownInCircle) {
				mRectColors[1] = mCenterPaint.getColor();
			}
			mRectShader = new LinearGradient(mRectLeft, 0, mRectRight, 0,
					mRectColors, null, Shader.TileMode.MIRROR);
			mRectPaint.setShader(mRectShader);
			canvas.drawRect(mRectLeft, mRectTop, mRectRight, mRectBottom,
					mRectPaint);
			float offset = mLinePaint.getStrokeWidth() / 2;
			canvas.drawLine(mRectLeft - offset, mRectTop - offset * 2,
					mRectLeft - offset, mRectBottom + offset * 2, mLinePaint);// ��
			canvas.drawLine(mRectLeft - offset * 2, mRectTop - offset,
					mRectRight + offset * 2, mRectTop - offset, mLinePaint);// ��
			canvas.drawLine(mRectRight + offset, mRectTop - offset * 2,
					mRectRight + offset, mRectBottom + offset * 2, mLinePaint);// ��
			canvas.drawLine(mRectLeft - offset * 2, mRectBottom + offset,
					mRectRight + offset * 2, mRectBottom + offset, mLinePaint);// ��
			super.onDraw(canvas);
		}

		@Override
		public boolean onTouchEvent(MotionEvent event) {
			float x = event.getX() - mWidth / 2;
			float y = event.getY() - mHeight / 2 + 50;
			boolean inCircle = inColorCircle(x, y,
					mCircleRadius + mCirclePaint.getStrokeWidth() / 2,
					mCircleRadius - mCirclePaint.getStrokeWidth() / 2);
			boolean inCenter = inCenter(x, y, mCenterRadius);
			boolean inRect = inRect(x, y);
			System.out.println(x + "..." + y);
			System.out.println(mRectLeft + "..." + mRectRight + "..."
					+ mRectTop + "..." + mRectBottom);
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				onActionDown(inCircle, inCenter, inRect);
			case MotionEvent.ACTION_MOVE:
				onActionMove(x, y, inCircle, inCenter, inRect);
				break;
			case MotionEvent.ACTION_UP:
				onActionUp(inCenter);
				break;
			}
			return true;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.sinaapp.msdxblog.androidkit.graphics.ColorPickerDialog.
		 * ColorPickerView#interpRectColor(int[], float, float)
		 */
		protected int interpRectColor(int colors[], float x, float y) {
			int a, r, g, b, c0, c1;
			float p;
			if (x < 0) {
				c0 = colors[0];
				c1 = colors[1];
				p = (x + mRectRight) / mRectRight;
			} else {
				c0 = colors[1];
				c1 = colors[2];
				p = x / mRectRight;
			}
			a = ave(Color.alpha(c0), Color.alpha(c1), p);
			r = ave(Color.red(c0), Color.red(c1), p);
			g = ave(Color.green(c0), Color.green(c1), p);
			b = ave(Color.blue(c0), Color.blue(c1), p);
			return Color.argb(a, r, g, b);
		}
	}

	/**
	 * ����ʱ����ɫѡ������view.
	 * 
	 * @author msdx
	 * 
	 */
	private class LandscapeColorPickerView extends ColorPickerView {

		public LandscapeColorPickerView(Context context,
				OnColorChangedListener l) {
			super(context, l);
			Display display = ColorPickerDialog.this.getWindow()
					.getWindowManager().getDefaultDisplay();
			int height = (int) (display.getHeight() * 0.8f) - 36;
			int width = (int) (display.getWidth() * 0.5f);
			this.mHeight = height;
			this.mWidth = width;
			setMinimumHeight(height);
			setMinimumWidth(width);

			// ����ɫ������
			Shader s = new SweepGradient(0, 0, mCircleColors, null);
			mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			mCirclePaint.setShader(s);
			mCirclePaint.setStyle(Paint.Style.STROKE);
			mCirclePaint.setStrokeWidth(50);
			mCircleRadius = mHeight / 2 * 0.7f - mCirclePaint.getStrokeWidth()
					* 0.5f;

			// ����Բ����
			mCenterPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			mCenterPaint.setColor(mInitialColor);
			mCenterPaint.setStrokeWidth(5);
			mCenterRadius = (mCircleRadius - mCirclePaint.getStrokeWidth() / 2) * 0.7f;

			// �߿����
			mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			mLinePaint.setColor(Color.parseColor("#72A1D1"));
			mLinePaint.setStrokeWidth(4);

			// �ڰ׽������
			mRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			mRectPaint.setStrokeWidth(5);
			mRectLeft = mCircleRadius + mCirclePaint.getStrokeWidth() * 0.5f
					+ mLinePaint.getStrokeMiter() * 0.5f + 15;
			mRectTop = -mCircleRadius - mCirclePaint.getStrokeWidth() * 0.5f;
			mRectRight = mRectLeft + 50;
			mRectBottom = mCircleRadius + mCirclePaint.getStrokeWidth() * 0.5f;
		}

		@Override
		protected void onDraw(Canvas canvas) {
			// �ƶ�����
			canvas.translate(mWidth / 2 - 50, mHeight / 2);
			// ������Բ
			canvas.drawCircle(0, 0, mCenterRadius, mCenterPaint);
			// �Ƿ���ʾ����Բ���СԲ��
			if (mHighlightCenter || mlittleLightCenter) {
				int c = mCenterPaint.getColor();
				mCenterPaint.setStyle(Paint.Style.STROKE);
				if (mHighlightCenter) {
					mCenterPaint.setAlpha(0xFF);
				} else if (mlittleLightCenter) {
					mCenterPaint.setAlpha(0x90);
				}
				canvas.drawCircle(0, 0,
						mCenterRadius + mCenterPaint.getStrokeWidth(),
						mCenterPaint);

				mCenterPaint.setStyle(Paint.Style.FILL);
				mCenterPaint.setColor(c);
			}
			// ��ɫ��
			canvas.drawOval(new RectF(-mCircleRadius, -mCircleRadius,
					mCircleRadius, mCircleRadius), mCirclePaint);
			// ���ڰ׽����
			if (mDownInCircle) {
				mRectColors[1] = mCenterPaint.getColor();
			}
			mRectShader = new LinearGradient(0, mRectTop, 0, mRectBottom,
					mRectColors, null, Shader.TileMode.MIRROR);
			mRectPaint.setShader(mRectShader);
			canvas.drawRect(mRectLeft, mRectTop, mRectRight, mRectBottom,
					mRectPaint);
			float offset = mLinePaint.getStrokeWidth() / 2;
			canvas.drawLine(mRectLeft - offset, mRectTop - offset * 2,
					mRectLeft - offset, mRectBottom + offset * 2, mLinePaint);// ��
			canvas.drawLine(mRectLeft - offset * 2, mRectTop - offset,
					mRectRight + offset * 2, mRectTop - offset, mLinePaint);// ��
			canvas.drawLine(mRectRight + offset, mRectTop - offset * 2,
					mRectRight + offset, mRectBottom + offset * 2, mLinePaint);// ��
			canvas.drawLine(mRectLeft - offset * 2, mRectBottom + offset,
					mRectRight + offset * 2, mRectBottom + offset, mLinePaint);// ��
			super.onDraw(canvas);
		}

		@Override
		public boolean onTouchEvent(MotionEvent event) {
			float x = event.getX() - mWidth / 2 + 50;
			float y = event.getY() - mHeight / 2;
			boolean inCircle = inColorCircle(x, y,
					mCircleRadius + mCirclePaint.getStrokeWidth() / 2,
					mCircleRadius - mCirclePaint.getStrokeWidth() / 2);
			boolean inCenter = inCenter(x, y, mCenterRadius);
			boolean inRect = inRect(x, y);
			System.out.println(x + "..." + y);
			System.out.println(mRectLeft + "..." + mRectRight + "..."
					+ mRectTop + "..." + mRectBottom);
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				onActionDown(inCircle, inCenter, inRect);
			case MotionEvent.ACTION_MOVE:
				onActionMove(x, y, inCircle, inCenter, inRect);
				break;
			case MotionEvent.ACTION_UP:
				onActionUp(inCenter);
				break;
			}
			return true;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.sinaapp.msdxblog.androidkit.graphics.ColorPickerDialog.
		 * ColorPickerView#interpRectColor(int[], float, float)
		 */
		protected int interpRectColor(int colors[], float x, float y) {
			int a, r, g, b, c0, c1;
			float p;
			float referLine = mRectBottom;
			if (y < 0) {
				c0 = colors[0];
				c1 = colors[1];
				p = (y + referLine) / referLine;
			} else {
				c0 = colors[1];
				c1 = colors[2];
				p = y / referLine;
			}
			a = ave(Color.alpha(c0), Color.alpha(c1), p);
			r = ave(Color.red(c0), Color.red(c1), p);
			g = ave(Color.green(c0), Color.green(c1), p);
			b = ave(Color.blue(c0), Color.blue(c1), p);
			return Color.argb(a, r, g, b);
		}
	}
}