package com.example.lesson_xml_parser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXforHandler extends DefaultHandler {
	private static final String TAG="SAXforHandler";
	private List<Person> persons;
	private String perTag;
	
	Person person;

	public List<Person> getPersons(){
		return persons;
	}
	

	/*
	 * 开始解析XML文档
	 * 此事件只在开始解析文档时执行一次。比较适合处理一些初始化行为.
	 */
	@Override
	public void startDocument() throws SAXException {
		persons = new ArrayList<Person>();
		System.out.println(TAG + "***startDocument***");
	}

	/*
	 * 开始解析XML元素
	 * 参数说明：
	 * 1.命名空间。
	 * 2.标签名称。
	 * 3.带命名空间的标签名(即全名)。
	 * 4.存放在该标签的所有属性。
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		System.out.println("startElement"+" "+localName);
		if("person".equals(qName)){
			//attributes 是此XML元素的属性。
			for(int i=0;i<attributes.getLength();i++){
				//输出属性的名称和值.
				System.out.println(TAG + " attributeName:"+attributes.getLocalName(i)+" attributeValue:"+attributes.getValue(i));
				
				person = new Person();
				person.setId(Integer.valueOf(attributes.getValue(i))); //设置人员ID信息。
			}
		}
		perTag = localName;
		System.out.println("startElement"+" "+qName+" ***startElement***");
	}


	/*
	 *开始解析XML成员。
	 *参数说明：
	 *1.当前读取到的TextNode字节数组。
	 *2.字节开始的位置，如果要读取全部，那就是从0开始。
	 *3.当前TextNode的长度。
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		
		//获取当前TextNode(节点).
		String data = new String(ch,start,length).trim();
		
		System.out.println("characters ch="+String.valueOf(ch)
				+" start=" + String.valueOf(start)
				+" length="+String.valueOf(length));
		
		if(!"".equals(data.trim())){
			System.out.println("characters "+"content:"+data.trim());
		}
		
		if("name".equals(perTag)){
			person.setName(data);  //设置人员名字信息。
		}
		else if("age".equals(perTag)){
			person.setAge(new Short(data)); //设置人员年龄信息。
		}	
	}
	
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		
		System.out.println("endElement" + " "+qName+"***endElement***");
		
		//当解析到person元素结束时，对persons进行添加.
		if("person".equals(qName) && person!=null){
			persons.add(person);
			person = null;
		}
		perTag = null;
	}
	

	@Override
	public void endDocument() throws SAXException {
		System.out.println("endDocument " + "***endDocument***");
	}
	


}



























