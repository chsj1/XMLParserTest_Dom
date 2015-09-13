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

public class CopyOfDomParserHelper {

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
			singer.setId(Integer.parseInt(singerElement.getAttribute("id")));
			NodeList childNodes = singerElement.getChildNodes(); // 获取根节点下的子节点
			
			for (int j = 0; j < childNodes.getLength(); j++) {
				if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
					if ("name".equals(childNodes.item(j).getNodeName())) {
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
	
	
	public static void createXMLByDom() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
            
		Document doc = db.newDocument();
        Element root = doc.createElement("singers");
        doc.appendChild(root);
        
        String names[] = new String[]{
        		"黄俊东",
        		"邓紫棋",
        		"林俊杰",
        		"周杰伦",
        		"A-lin"
        };
        float salarys[] = new float[]{
             20000,
             30000,
             40000,
             50000,
             60000
        };
        Random r = new Random();
        int len = names.length;
        
        
        int i;
        for(i = 0 ; i < 10 ; ++i){
        	Element element = doc.createElement("singer");
        	element.setAttribute("id", i + "");
        	
        	Element childElement1 = doc.createElement("name");
        	childElement1.setNodeValue(names[r.nextInt(len)]);
        	
        	Element childElement2 = doc.createElement("salary");
        	childElement2.setNodeValue(salarys[r.nextInt(len)] + "");
//        	 root.appendChild(doc.createElement(""));
        	element.appendChild(childElement1);
        	element.appendChild(childElement2);
        	
        	root.appendChild(element);
        }
        
        
        writeXML(doc, "newSingers.xml");
    }

    public static void writeXML(Document doc, String file) {
        try {
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty("indent", "yes");
            t.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(file)));
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();  
        }
    }
    
    
}


