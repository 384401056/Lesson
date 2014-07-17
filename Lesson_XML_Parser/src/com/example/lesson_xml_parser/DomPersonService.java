package com.example.lesson_xml_parser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomPersonService {

	public static List<Person> readXML(InputStream is) throws Exception{
		
		List<Person> persons = new ArrayList<Person>();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(is);
		
		Element root = document.getDocumentElement();
		NodeList nodes = root.getElementsByTagName("person"); //�ø����ı�ǩ�����������Ӽ�Ԫ�ء����ﷵ������personԪ��.
		
		for(int i=0;i<nodes.getLength();i++){

			Element personElement = (Element)nodes.item(i);
			Person person = new Person();
			person.setId(Integer.valueOf(personElement.getAttribute("id"))); //��ȡԪ������ id,����ֵ��preson����.
			NodeList childNodes = personElement.getChildNodes();//��ȡpersonԪ���µ�������Ԫ��.
			
			for(int j=0;j<childNodes.getLength();j++){
				//����ӽڵ�����͵���Element,�ͽ�childNodes.item(i)תΪElement���͡�
				if(childNodes.item(j).getNodeType()==Node.ELEMENT_NODE){
					Element childElement = (Element)childNodes.item(j);
					//����ڵ�����Ϊname,��ȡ�ڵ��Value��ֵ��person��name��Ա������
					if(childElement.getNodeName().equals("name")){
						person.setName(childElement.getFirstChild().getNodeValue());
					}
					else if(childElement.getNodeName().equals("age")){
						person.setAge(new Short(childElement.getFirstChild().getNodeValue()));
					}
				}
			}
			persons.add(person);
		}
		
		return persons;
	}
}
