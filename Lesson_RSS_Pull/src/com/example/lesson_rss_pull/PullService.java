package com.example.lesson_rss_pull;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import android.util.Xml;


public class PullService {
	//��ȡXML�ĵ���
		public static List<News> readXML(InputStream is) throws Throwable{
			
			System.out.println("PullService------------->start");
			
			//��ȡһ��Pull��������ʵ����
			XmlPullParser parser = Xml.newPullParser();
			parser.setInput(is, "UTF-8");
			
			boolean flog = false;
			List<News> news_list = null;
			News news = null;
			/*
			 * ��ȡ��ǰ�����¼������ִ���.�˴������� Pull�ĵ�һ���¼�.Ҳ���ǿ�ʼ�����ĵ�.
			 * Pull���������¼�����һ�����ķ��������Ƕ�Ӧһ���������֡�Pullһ��ֻ��5��������
			 */
			int eventCode = parser.getEventType();

			//ֻҪû�н������ĵ��Ľ������־�һֱѭ����
			while(eventCode != XmlPullParser.END_DOCUMENT){
				switch (eventCode) {
				//�ļ���ʼ�¼�
				case XmlPullParser.START_DOCUMENT: 
					news_list = new ArrayList<News>();
					break;
				//��ʼ����Ԫ���¼���
				case XmlPullParser.START_TAG:  
					if("item".equals(parser.getName())){
						flog = true;
						news = new News();
					}
					
					if(flog){
						if("title".equals(parser.getName()))
							news.setTitle(parser.nextText());
						else if("link".equals(parser.getName()))
							news.setLink(parser.nextText());
						else if("pubDate".equals(parser.getName()))
							news.setPubDate(parser.nextText());
						else if("description".equals(parser.getName()))
							news.setDescription(parser.nextText());
					}
					break;
				//����Ԫ�ؽ����¼���	
				case XmlPullParser.END_TAG:
					//���������person�Ľ���������person����Ϊ�գ��ͽ�person���뵽persons.�����person.
					if("item".equals(parser.getName())){
						flog = false;
						news_list.add(news);
						news=null;
					}
					break;
				default:
					break;
				}
				eventCode = parser.next(); //����һ�¸��¼����������¼����ִ��롣
			}
			
			System.out.println("PullService------------->news_list return:"+news_list.size());
			return news_list;
		}
}
