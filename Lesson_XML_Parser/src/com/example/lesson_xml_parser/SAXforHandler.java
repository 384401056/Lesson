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
	 * ��ʼ����XML�ĵ�
	 * ���¼�ֻ�ڿ�ʼ�����ĵ�ʱִ��һ�Ρ��Ƚ��ʺϴ���һЩ��ʼ����Ϊ.
	 */
	@Override
	public void startDocument() throws SAXException {
		persons = new ArrayList<Person>();
		System.out.println(TAG + "***startDocument***");
	}

	/*
	 * ��ʼ����XMLԪ��
	 * ����˵����
	 * 1.�����ռ䡣
	 * 2.��ǩ���ơ�
	 * 3.�������ռ�ı�ǩ��(��ȫ��)��
	 * 4.����ڸñ�ǩ���������ԡ�
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		System.out.println("startElement"+" "+localName);
		if("person".equals(qName)){
			//attributes �Ǵ�XMLԪ�ص����ԡ�
			for(int i=0;i<attributes.getLength();i++){
				//������Ե����ƺ�ֵ.
				System.out.println(TAG + " attributeName:"+attributes.getLocalName(i)+" attributeValue:"+attributes.getValue(i));
				
				person = new Person();
				person.setId(Integer.valueOf(attributes.getValue(i))); //������ԱID��Ϣ��
			}
		}
		perTag = localName;
		System.out.println("startElement"+" "+qName+" ***startElement***");
	}


	/*
	 *��ʼ����XML��Ա��
	 *����˵����
	 *1.��ǰ��ȡ����TextNode�ֽ����顣
	 *2.�ֽڿ�ʼ��λ�ã����Ҫ��ȡȫ�����Ǿ��Ǵ�0��ʼ��
	 *3.��ǰTextNode�ĳ��ȡ�
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		
		//��ȡ��ǰTextNode(�ڵ�).
		String data = new String(ch,start,length).trim();
		
		System.out.println("characters ch="+String.valueOf(ch)
				+" start=" + String.valueOf(start)
				+" length="+String.valueOf(length));
		
		if(!"".equals(data.trim())){
			System.out.println("characters "+"content:"+data.trim());
		}
		
		if("name".equals(perTag)){
			person.setName(data);  //������Ա������Ϣ��
		}
		else if("age".equals(perTag)){
			person.setAge(new Short(data)); //������Ա������Ϣ��
		}	
	}
	
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		
		System.out.println("endElement" + " "+qName+"***endElement***");
		
		//��������personԪ�ؽ���ʱ����persons�������.
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



























