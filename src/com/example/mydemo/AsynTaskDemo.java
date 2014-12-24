package com.example.mydemo;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AsynTaskDemo extends Activity{
	 private MyDemoAPP app=null; 
	 private ImageView mImageAndTextView=null;
	 private Button btn_Asc=null;
	 
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	        
	        setContentView(R.layout.asyntaskdemo);
	        app= ((MyDemoAPP)getApplicationContext()); 
	        TextView tv_glb=(TextView)findViewById(R.id.tv_glb);
	        tv_glb.setText(app.UserName);
	        
	        mImageAndTextView=(ImageView)findViewById(R.id.imageView1);
			 btn_Asc=(Button)findViewById(R.id.bt_ok);
			 btn_Asc.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						GetImageTask task=new GetImageTask();
						task.execute("http://t11.baidu.com/it/u=3466552225,3938862908&fm=59"); // 复制网络图片地址
					}
				});
			 
	        super.onCreate(savedInstanceState);
	    }
	 
	 
	 
	 
	 /**
	  * AsyncTask定义了三种泛型类型 Params，Progress和Result.
	  *   Params启动任务执行的输入参数，比如HTTP请求的URL；
	  *   Progress后台任务执行的百分比；
	  *   Result后台执行任务最终返回的结果，比如String；
	  *
	  */
	private class GetImageTask extends AsyncTask<String, Void, Bitmap> {
	 	InputStream is = null;
	 	int mScreenWidth=0;
 		int mImageHeight=0;
 		
	 	@Override
	 	protected Bitmap doInBackground(String... params) {
	 		URL myFileUrl = null;
	 		Bitmap bitmap = null;
	 		InputStream is = null;
	 		
	 		HttpURLConnection conn = null;
	 		try {
	 			myFileUrl = new URL(params[0]);
	 		} catch (MalformedURLException e) {
	 			e.printStackTrace();
	 		}
	 		
	 		try {
	 			conn = (HttpURLConnection) myFileUrl.openConnection();
	 			conn.setDoInput(true);
	 			conn.connect();
	 			is = conn.getInputStream();
	 			bitmap = BitmapFactory.decodeStream(is);
	 			mScreenWidth=bitmap.getWidth();
	 			mImageHeight=bitmap.getHeight();
	 			is.close();
	 		} catch (IOException e) {
	 			e.printStackTrace();
	 		} finally {
	 			try {
	 				if (is != null) {
	 					is.close();
	 				}
	 				if (conn != null) {
	 					conn.disconnect();
	 				}
	 			} catch (IOException e) {
	 				e.printStackTrace();
	 			}
	 		}
	 		return bitmap;
	 	}

	 	@Override
	 	protected void onCancelled() {
	 		super.onCancelled();
	 	}

	 	@Override
	 	protected void onPostExecute(Bitmap result) {
	 		mImageAndTextView.setImageBitmap(result);
	 		mImageAndTextView.postInvalidate(0, 0, mScreenWidth, mImageHeight + 30); // 只更新稍比图片大一些的区域
	 		super.onPostExecute(result);
	 	}

	 }
}
