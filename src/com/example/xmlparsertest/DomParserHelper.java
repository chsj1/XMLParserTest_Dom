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
		// ����һ��document�����Ĺ���
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(inputStream); // ��ʾ�����ĵ�����
		Element element = document.getDocumentElement();
		
		// ��������Ԫ��
		NodeList singerNodes = element.getElementsByTagName("singer");
		for (int i = 0; i < singerNodes.getLength(); i++) {
			Element singerElement = (Element) singerNodes.item(i);
			Singer singer = new Singer();
			singer.setId(Integer.parseInt(singerElement.getAttribute("id")));//��ȡ�����õ�ǰԪ�ص� id
			NodeList childNodes = singerElement.getChildNodes(); // ��ȡ���ڵ��µ��ӽڵ�
			
			for (int j = 0; j < childNodes.getLength(); j++) {
				if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
					//childNodes.item(j).getNodeName(): ��ȡ�ýڵ������
					if ("name".equals(childNodes.item(j).getNodeName())) {
						/**
						 * childNodes.item(j).getFirstChild()
								.getNodeValue(): ��ȡ�ýڵ��ֵ
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


