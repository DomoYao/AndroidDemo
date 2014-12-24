package com.example.mydemo;

import java.util.ArrayList;

import com.entities.Constants;
import com.entities.NewsEntity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class NewsFragment extends Fragment {
	Activity activity;
	ArrayList<NewsEntity> newsList = new ArrayList<NewsEntity>();
	ListView mListView;
	NewsAdapter mAdapter;
	String text;
	
	public final static int SET_NEWSLIST = 0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Bundle args = getArguments();
		text = args != null ? args.getString("text") : "";
		initData();
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		this.activity = activity;
		super.onAttach(activity);
	}
	/** �˷�����˼Ϊfragment�Ƿ�ɼ� ,�ɼ�ʱ��������� */
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		if (isVisibleToUser) {
			//fragment�ɼ�ʱ��������
			if(newsList !=null && newsList.size() !=0){
				handler.obtainMessage(SET_NEWSLIST).sendToTarget();
			}else{
				new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(2);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						handler.obtainMessage(SET_NEWSLIST).sendToTarget();
					}
				}).start();
			}
		}else{
			//fragment���ɼ�ʱ��ִ�в���
		}
		super.setUserVisibleHint(isVisibleToUser);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = LayoutInflater.from(getActivity()).inflate(R.layout.news_fragment, null);
		mListView = (ListView) view.findViewById(R.id.mListView);
		return view;
	}

	private void initData() {
		newsList = Constants.getNewsList();
	}
	
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case SET_NEWSLIST:
				mAdapter = new NewsAdapter(activity, newsList);
				mListView.setAdapter(mAdapter);
				break;
			default:
				break;
			}
			super.handleMessage(msg);
		}
	};
}
