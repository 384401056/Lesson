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
		NodeList nodes = root.getElementsByTagName("person"); //用给定的标签名返回所有子级元素。这里返回所有person元素.
		
		for(int i=0;i<nodes.getLength();i++){

			Element personElement = (Element)nodes.item(i);
			Person person = new Person();
			person.setId(Integer.valueOf(personElement.getAttribute("id"))); //获取元素属性 id,并赋值给preson对象.
			NodeList childNodes = personElement.getChildNodes();//获取person元素下的所有子元素.
			
			for(int j=0;j<childNodes.getLength();j++){
				//如果子节点的类型等于Element,就将childNodes.item(i)转为Element类型。
				if(childNodes.item(j).getNodeType()==Node.ELEMENT_NODE){
					Element childElement = (Element)childNodes.item(j);
					//如果节点名称为name,则取节点的Value赋值给person的name成员变量。
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
