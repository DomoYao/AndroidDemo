package com.example.mydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class DemoMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demomenu);
		
		TextView tvPagingListViewDemo=(TextView)findViewById(R.id.tvPagingListViewDemo);
		tvPagingListViewDemo.setOnClickListener(clickevent);
		
		TextView tvMsgDemo=(TextView)findViewById(R.id.tvMsgDemo);
		tvMsgDemo.setOnClickListener(clickevent);
		
		TextView tvAsyncTaskDemo=(TextView)findViewById(R.id.tvAsyncTaskDemo);
		tvAsyncTaskDemo.setOnClickListener(clickevent);
		
		TextView tvnewsDemo=(TextView)findViewById(R.id.tvnewsDemo);
		tvnewsDemo.setOnClickListener(clickevent);
		
		TextView tvZRLListViewDemo=(TextView)findViewById(R.id.tvZRLListViewDemo);
		tvZRLListViewDemo.setOnClickListener(clickevent);
		
		TextView tvRefreshDemo=(TextView)findViewById(R.id.tvRefreshDemo);
		tvRefreshDemo.setOnClickListener(clickevent);
	}
	
	
	private OnClickListener clickevent = new OnClickListener() {

		@Override
		public void onClick(View v) {
			int id = v.getId();
			switch (id) {
			case R.id.tvPagingListViewDemo: {
				Intent _intent = new Intent();
				_intent.setClass(DemoMenu.this, PagingListViewDemo.class);
				startActivity(_intent);
				break;
			}
			case R.id.tvMsgDemo: {
				Intent _intent = new Intent();
				_intent.setClass(DemoMenu.this, MsgActivity.class);
				startActivity(_intent);
				break;
			}
			case R.id.tvAsyncTaskDemo: {
				Intent _intent = new Intent();
				_intent.setClass(DemoMenu.this, AsynTaskDemo.class);
				startActivity(_intent);
				break;
			}
			case R.id.tvnewsDemo: {
				Intent _intent = new Intent();
				_intent.setClass(DemoMenu.this, NewsActivity.class);
				startActivity(_intent);
				break;
			}
			case R.id.tvZRLListViewDemo:{
				Intent _intent = new Intent();
				_intent.setClass(DemoMenu.this, ZrlListviewDemo.class);
				startActivity(_intent);
				break;
			}
			case R.id.tvRefreshDemo:{
				Intent _intent = new Intent();
				_intent.setClass(DemoMenu.this, RefreshDemo.class);
				startActivity(_intent);
				break;
			}
			
			}
		}
	};
}
