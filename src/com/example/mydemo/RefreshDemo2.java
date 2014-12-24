package com.example.mydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RefreshDemo2 extends Activity {

	public static final String action = "jason.broadcast.action";
	public EditText eText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.refresh_demo2);
		
		eText=(EditText)findViewById(R.id.et1);
		
		// 通过广播更新前一个页面
		Button btsureButton = (Button) findViewById(R.id.bt1);
		btsureButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String result=eText.getText().toString();
				Intent intent = new Intent(action); 
                intent.putExtra("data", result); 
                sendBroadcast(intent); 
				finish();
			}
		});
		
		
		// 通过onActivityResult更新前一个页面
		Button btButton = (Button) findViewById(R.id.bt2);
		btButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String result=eText.getText().toString();
				Intent intent = new Intent(action); 
                intent.putExtra("data", result); 
                RefreshDemo2.this.setResult(2,intent);
				finish();
			}
		});
	}
}
