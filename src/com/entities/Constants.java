package com.entities;

import java.util.ArrayList;
import java.util.List;


public class Constants {
	public static ArrayList<NewsClassify> getData() {
		ArrayList<NewsClassify> newsClassify = new ArrayList<NewsClassify>();
		NewsClassify classify = new NewsClassify();
		classify.setId(0);
		classify.setTitle("�Ƽ�");
		newsClassify.add(classify);
		classify = new NewsClassify();
		classify.setId(1);
		classify.setTitle("�ȵ�");
		newsClassify.add(classify);
		classify = new NewsClassify();
		classify.setId(2);
		classify.setTitle("����");
		newsClassify.add(classify);
		classify = new NewsClassify();
		classify.setId(3);
		classify.setTitle("����");
		newsClassify.add(classify);
		classify = new NewsClassify();
		classify.setId(4);
		classify.setTitle("���");
		newsClassify.add(classify);
		classify = new NewsClassify();
		classify.setId(5);
		classify.setTitle("����");
		newsClassify.add(classify);
		classify = new NewsClassify();
		classify.setId(6);
		classify.setTitle("�Ƽ�");
		newsClassify.add(classify);
		classify = new NewsClassify();
		classify.setId(7);
		classify.setTitle("����");
		newsClassify.add(classify);
		return newsClassify;
	}
	
	public static ArrayList<NewsEntity> getNewsList() {
		ArrayList<NewsEntity> newsList = new ArrayList<NewsEntity>();
		for(int i =0 ; i < 10 ; i++){
			NewsEntity news = new NewsEntity();
			news.setId(i);
			news.setNewsId(i);
			news.setCollectStatus(false);
			news.setCommentNum(i + 10);
			news.setInterestedStatus(true);
			news.setLikeStatus(true);
			news.setReadStatus(false);
			news.setNewsCategory("�Ƽ�");
			news.setNewsCategoryId(1);
			news.setTitle("�����ùȸ��۾�����10�����£�����������Ϸ");
			List<String> url_list = new ArrayList<String>();
			if(i%2 == 1){
				String url1 = "http://infopic.gtimg.com/qq_news/digi/pics/102/102066/102066094_400_640.jpg";
				String url2 = "http://infopic.gtimg.com/qq_news/digi/pics/102/102066/102066096_400_640.jpg";
				String url3 = "http://infopic.gtimg.com/qq_news/digi/pics/102/102066/102066099_400_640.jpg";
				news.setPicOne(url1);
				news.setPicTwo(url2);
				news.setPicThr(url3);
				url_list.add(url1);
				url_list.add(url2);
				url_list.add(url3);
			}else{
				news.setTitle("AA�ó�:���ܶ����⳵ƽ̨");
				String url = "http://r3.sinaimg.cn/2/2014/0417/a7/6/92478595/580x1000x75x0.jpg";
				news.setPicOne(url);
				url_list.add(url);
			}
			news.setPicList(url_list);
			news.setPublishTime(Long.valueOf(i));
			news.setReadStatus(false);
			news.setSource("�ֻ���Ѷ��");
			news.setSummary("��Ѷ����Ѷ�����룺Gin���ȸ��۾�������Ŀǰ���Ŀɴ��������豸������Դ�����ȥ�κεط���ֻҪ���ɷ����������û��������ŭ������Ϊ�ֻ��ĵڶ��顰��ǿ��ʵ��ʾ������ʹ�á����⣬��Ȼ����δ��ʽ���ۣ����ȸ�����������г������˽���һ��Ŀ��Ź������۸���Ϊ1500��Ԫ��Լ�������9330Ԫ������Ȼ��ʮ�ְ��󣬵����ٿ�������һЩ�����ߵ�����ҲԤʾ�Źȸ��۾��Ĺ������ģ����������Խ��Խ���ˡ�");
			news.setMark(i);
			if(i == 4){
				news.setTitle("����ս��ǿ�ƻع�");
				news.setLocal("�ƹ�");
				news.setIsLarge(true);
				String url = "http://imgt2.bdstatic.com/it/u=3269155243,2604389213&fm=21&gp=0.jpg";
				news.setPicOne(url);
				url_list.clear();
				url_list.add(url);
			}else{
				news.setIsLarge(false);
			}
			if(i == 2){
				news.setComment("���۲��֣�˵�ķǳ��á�");
			}
			newsList.add(news);
		}
		return newsList;
	}
	
	/** mark=0 ���Ƽ� */
	public final static int mark_recom = 0;
	/** mark=1 ������ */
	public final static int mark_hot = 1;
	/** mark=2 ���׷� */
	public final static int mark_frist = 2;
	/** mark=3 ������ */
	public final static int mark_exclusive = 3;
	/** mark=4 ���ղ� */
	public final static int mark_favor = 4;
}
