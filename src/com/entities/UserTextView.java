package com.entities;

import android.R.color;
import android.R.string;
import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UserTextView extends LinearLayout{

	private UserEntity userentiy=null;
	private Context currcontext = null;
	private String titleString="";
	public UserTextView(Context context,UserEntity userentity,String Title) {
		super(context);
		// TODO Auto-generated constructor stub
		this.setHorizontalGravity(HORIZONTAL);
		currcontext = context;
		userentiy=userentity;
		titleString=Title;
		createSubject();
	}

	// 创建主题并呈现
		private void createSubject() {
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
			params.setMargins(12, 8, 12, 0);
			TextView view = new TextView(currcontext);
			view.setText("zhongguo"+userentiy.UserName);
			view.setTextColor(Color.RED);
			view.setTextSize(16);
			
			this.addView(view,params);
			
			TextView view2 = new TextView(currcontext);
			view2.setText(titleString);
			
			view2.setTextColor(Color.RED);
			view2.setTextSize(16);
			
			this.addView(view2,params);
		}

		

}
