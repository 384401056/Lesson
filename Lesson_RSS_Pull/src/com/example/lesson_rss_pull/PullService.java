package com.example.lesson_rss_pull;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import android.util.Xml;


public class PullService {
	//读取XML文档。
		public static List<News> readXML(InputStream is) throws Throwable{
			
			System.out.println("PullService------------->start");
			
			//获取一个Pull解析对象实例。
			XmlPullParser parser = Xml.newPullParser();
			parser.setInput(is, "UTF-8");
			
			boolean flog = false;
			List<News> news_list = null;
			News news = null;
			/*
			 * 获取当前解析事件的数字代码.此处触发了 Pull的第一个事件.也就是开始解析文档.
			 * Pull解析器的事件不是一个个的方法，而是对应一个个的数字。Pull一共只有5个方法。
			 */
			int eventCode = parser.getEventType();

			//只要没有解析到文档的结束部分就一直循环。
			while(eventCode != XmlPullParser.END_DOCUMENT){
				switch (eventCode) {
				//文件开始事件
				case XmlPullParser.START_DOCUMENT: 
					news_list = new ArrayList<News>();
					break;
				//开始解析元素事件。
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
				//解析元素结束事件。	
				case XmlPullParser.END_TAG:
					//如果解析到person的结束，而且person对象不为空，就将person加入到persons.并清空person.
					if("item".equals(parser.getName())){
						flog = false;
						news_list.add(news);
						news=null;
					}
					break;
				default:
					break;
				}
				eventCode = parser.next(); //触发一下个事件，并返回事件数字代码。
			}
			
			System.out.println("PullService------------->news_list return:"+news_list.size());
			return news_list;
		}
}
