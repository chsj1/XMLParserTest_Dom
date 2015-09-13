package com.example.xmlparsertest;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomParserHelper {

	public static List<Singer> getSingers(InputStream inputStream)
			throws Exception {
		List<Singer> list = new ArrayList<Singer>();
		// 创建一个document解析的工厂
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(inputStream); // 表示整个文档对象
		Element element = document.getDocumentElement();
		
		// 遍历所有元素
		NodeList singerNodes = element.getElementsByTagName("singer");
		for (int i = 0; i < singerNodes.getLength(); i++) {
			Element singerElement = (Element) singerNodes.item(i);
			Singer singer = new Singer();
			singer.setId(Integer.parseInt(singerElement.getAttribute("id")));//获取并设置当前元素的 id
			NodeList childNodes = singerElement.getChildNodes(); // 获取根节点下的子节点
			
			for (int j = 0; j < childNodes.getLength(); j++) {
				if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
					//childNodes.item(j).getNodeName(): 获取该节点的名字
					if ("name".equals(childNodes.item(j).getNodeName())) {
						/**
						 * childNodes.item(j).getFirstChild()
								.getNodeValue(): 获取该节点的值
						 */
						singer.setName(childNodes.item(j).getFirstChild()
								.getNodeValue());
					} else if ("salary"
							.equals(childNodes.item(j).getNodeName())) {
						singer.setSalary(childNodes.item(j).getFirstChild()
								.getNodeValue());
					}
				}
			}
			
			list.add(singer);
		}
		return list;
	}
}


