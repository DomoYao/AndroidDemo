package com.example.mydemo;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import com.entities.NewsEntity;
import com.entities.Options;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import android.app.Activity;
import android.graphics.Bitmap;

import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NewsAdapter extends BaseAdapter {
	ArrayList<NewsEntity> newsList;
	Activity activity;
	LayoutInflater inflater = null;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	DisplayImageOptions options;
	/** �����ĸ���ѡ���  */
	private PopupWindow popupWindow;
	public NewsAdapter(Activity activity, ArrayList<NewsEntity> newsList) {
		this.activity = activity;
		this.newsList = newsList;
		inflater = LayoutInflater.from(activity);
		options = Options.getListOptions();
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return newsList == null ? 0 : newsList.size();
	}

	@Override
	public NewsEntity getItem(int position) {
		// TODO Auto-generated method stub
		if (newsList != null && newsList.size() != 0) {
			return newsList.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder mHolder;
		View view = convertView;
		if (view == null) {
			view = inflater.inflate(R.layout.list_item, null);
			mHolder = new ViewHolder();
			mHolder.item_layout = (RelativeLayout)view.findViewById(R.id.item_layout);
			mHolder.item_title = (TextView)view.findViewById(R.id.item_title);
			mHolder.right_image = (ImageView)view.findViewById(R.id.right_image);
			view.setTag(mHolder);
		} else {
			mHolder = (ViewHolder) view.getTag();
		}
		//��ȡposition��Ӧ������
		NewsEntity news = getItem(position);
		mHolder.item_title.setText(news.getTitle());
		mHolder.item_source.setText(news.getSource());
		mHolder.comment_count.setText("����" + news.getCommentNum());
		mHolder.publish_time.setText(news.getPublishTime() + "Сʱǰ");
		List<String> imgUrlList = news.getPicList();
		mHolder.popicon.setVisibility(View.VISIBLE);
		mHolder.comment_count.setVisibility(View.VISIBLE);
		mHolder.right_padding_view.setVisibility(View.VISIBLE);
		if(imgUrlList !=null && imgUrlList.size() !=0){
			if(imgUrlList.size() == 1){
				mHolder.item_image_layout.setVisibility(View.GONE);
				//�Ƿ��Ǵ�ͼ
				if(news.getIsLarge()){
					mHolder.large_image.setVisibility(View.VISIBLE);
					mHolder.right_image.setVisibility(View.GONE);
					imageLoader.displayImage(imgUrlList.get(0), mHolder.large_image, options);
					mHolder.popicon.setVisibility(View.GONE);
					mHolder.comment_count.setVisibility(View.GONE);
					mHolder.right_padding_view.setVisibility(View.GONE);
				}else{
					mHolder.large_image.setVisibility(View.GONE);
					mHolder.right_image.setVisibility(View.VISIBLE);
					imageLoader.displayImage(imgUrlList.get(0), mHolder.right_image, options);
				}
			}else{
				mHolder.large_image.setVisibility(View.GONE);
				mHolder.right_image.setVisibility(View.GONE);
				mHolder.item_image_layout.setVisibility(View.VISIBLE);
				imageLoader.displayImage(imgUrlList.get(0), mHolder.item_image_0, options);
				imageLoader.displayImage(imgUrlList.get(1), mHolder.item_image_1, options);
				imageLoader.displayImage(imgUrlList.get(2), mHolder.item_image_2, options);
			}
		}else{
			mHolder.right_image.setVisibility(View.GONE);
			mHolder.item_image_layout.setVisibility(View.GONE);
		}
		int markResID = getAltMarkResID(news.getMark(),news.getCollectStatus());
		if(markResID != -1){
			mHolder.alt_mark.setVisibility(View.VISIBLE);
			mHolder.alt_mark.setImageResource(markResID);
		}else{
			mHolder.alt_mark.setVisibility(View.GONE);
		}
		//�жϸ����Ÿ����Ƿ�Ϊ��
		if (!TextUtils.isEmpty(news.getNewsAbstract())) {
			mHolder.item_abstract.setVisibility(View.VISIBLE);
			mHolder.item_abstract.setText(news.getNewsAbstract());
		} else {
			mHolder.item_abstract.setVisibility(View.GONE);
		}
		//�жϸ������Ƿ��������ǵģ��ƹ�ȣ�Ϊ�վ�������
		if(!TextUtils.isEmpty(news.getLocal())){
			mHolder.list_item_local.setVisibility(View.VISIBLE);
			mHolder.list_item_local.setText(news.getLocal());
		}else{
			mHolder.list_item_local.setVisibility(View.GONE);
		}
		//�ж������ֶ��Ƿ�Ϊ�գ���Ϊ����ʾ��Ӧ����
		if(!TextUtils.isEmpty(news.getComment())){
			//news.getLocal() != null && 
			mHolder.comment_layout.setVisibility(View.VISIBLE);
			mHolder.comment_content.setText(news.getComment());
		}else{
			mHolder.comment_layout.setVisibility(View.GONE);
		}
		//�жϸ������Ƿ��Ѷ�
		if(!news.getReadStatus()){
			mHolder.item_layout.setSelected(true);
		}else{
			mHolder.item_layout.setSelected(false);
		}
		//����+��ť���Ч��
		mHolder.popicon.setOnClickListener(new popAction(position));
		return view;
	}

	static class ViewHolder {
		RelativeLayout item_layout;
		//title
		TextView item_title;
		//ͼƬԴ
		TextView item_source;
		//�����ƹ�֮��ı�ǩ
		TextView list_item_local;
		//��������
		TextView comment_count;
		//����ʱ��
		TextView publish_time;
		//����ժҪ
		TextView item_abstract;
		//���Ϸ�TAG���ͼƬ
		ImageView alt_mark;
		//�ұ�ͼƬ
		ImageView right_image;
		//3��ͼƬ����
		LinearLayout item_image_layout; //3��ͼƬʱ��Ĳ���
		ImageView item_image_0;
		ImageView item_image_1;
		ImageView item_image_2;
		//��ͼ��ͼƬ�Ļ�����
		ImageView large_image;
		//pop��ť
		ImageView popicon;
		//���۲���
		RelativeLayout comment_layout;
		TextView comment_content;
		//paddingview
		View right_padding_view;
	}
	/** �������Ի�ȡ��Ӧ����ԴID  */
	public int getAltMarkResID(int mark,boolean isfavor){
		
		return -1;
	}
	
	/** popWindow �رհ�ť */
	private ImageView btn_pop_close;
	
	
	
	/** 
	 * ��ʾpopWindow
	 * */
	public void showPop(View parent, int x, int y,int postion) {
		//����popwindow��ʾλ��
		popupWindow.showAtLocation(parent, 0, x, y);
		//��ȡpopwindow����
		popupWindow.setFocusable(true);
		//����popwindow�������������򣬱�رա�
		popupWindow.setOutsideTouchable(true);
		popupWindow.update();
		if (popupWindow.isShowing()) {
			
		}
		btn_pop_close.setOnClickListener(new OnClickListener() {
			public void onClick(View paramView) {
				popupWindow.dismiss();
			}
		});
	}
	
	/** 
	 * ÿ��ITEM��more��ť��Ӧ�ĵ������
	 * */
	public class popAction implements OnClickListener{
		int position;
		public popAction(int position){
			this.position = position;
		}
		@Override
		public void onClick(View v) {
			int[] arrayOfInt = new int[2];
			//��ȡ�����ť������
			v.getLocationOnScreen(arrayOfInt);
	        int x = arrayOfInt[0];
	        int y = arrayOfInt[1];
	        showPop(v, x , y, position);
		}
	}
}
