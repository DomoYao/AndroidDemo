package com.example.mydemo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class RefreshDemo extends Activity {

	private TextView tvResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.refresh_demo);

		tvResult = (TextView) findViewById(R.id.tvResult);

		TextView tvopennew = (TextView) findViewById(R.id.tvopennew);
		tvopennew.setOnClickListener(clickevent);
		
		// 注册广播
		IntentFilter filter = new IntentFilter(RefreshDemo2.action);
		registerReceiver(broadcastReceiver, filter);
	}

	private OnClickListener clickevent = new OnClickListener() {

		@Override
		public void onClick(View v) {
			int id = v.getId();
			switch (id) {
			case R.id.tvopennew: {
				Intent _intent = new Intent();
				_intent.setClass(RefreshDemo.this, RefreshDemo2.class);
				startActivityForResult(_intent, 1);
				break;
			}

			}
		}
	};
	
	
	
	 @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
         switch(resultCode){  
             case 1:  
                 // ResultActivity的返回数据  
             case 2:  
             {
            	String jsonString = data.getExtras().getString("data");
     			tvResult.setText("onActivityResult："+jsonString);
             }
          }  
     }  
	

	BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// 请求刷新页面
			String jsonString = intent.getExtras().getString("data");
			tvResult.setText("广播："+jsonString);
		}
	};
	
	// 注意释放
	@Override
	protected void onDestroy() {
		unregisterReceiver(broadcastReceiver);
		super.onDestroy();
	};
}
