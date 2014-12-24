package com.example.mydemo;

import com.widget.PagingBaseAdapter;

import android.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyPagingAdaper extends PagingBaseAdapter<String> {

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public String getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
//		View view;
//		TextView textView;
//		
//		String text = getItem(position);
//
//		if(convertView != null) {
//			view = (TextView)convertView;
//		}else {
//			view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pagingitem, null);
//		}
//		
//		textView= (TextView) view.findViewById(R.id.text1);
//		textView.setText(text);
//		return textView;
		
		TextView textView;
		String text = getItem(position);

		if(convertView != null) {
			textView = (TextView)convertView;
		}else {
			textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_list_item_1, null);
		}
		textView.setText(text);
		return textView;
	}
}
