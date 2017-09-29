package com.fileprocess.helper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser {
	private static XMLParser xmlParser = null;
	private Document doc = null;
	private Map<String, String> filePropertyMap = null;
	
	private XMLParser() throws SAXException, IOException, ParserConfigurationException {
		super();
		File xmlPropFile = new File("resources/instruct.xml");
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		builder = builderFactory.newDocumentBuilder();
		doc = builder.parse(xmlPropFile);
		
		NodeList inFileNodeList = doc.getElementsByTagName("inputFile");
		for(int i=0; i<inFileNodeList.getLength();i++){
			String name = null;
			String time = null;
			
			Node node = inFileNodeList.item(i);

			if(node != null){
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element nElement = (Element) node;
					name = nElement.getAttribute("name");
					time = nElement.getAttribute("time");
				}
			}
			
			if(filePropertyMap == null){
				filePropertyMap = new HashMap<String, String>();
			}
			
			if(!filePropertyMap.containsKey(name) && name != null && time != null){
				filePropertyMap.put(name, time);
			}
		}
	}
	
	public static XMLParser getInstance() throws SAXException, IOException, ParserConfigurationException{
		if(xmlParser == null){
			xmlParser = new XMLParser();
		}
		return xmlParser;
	}

	public Map<String, String> getFilePropertyMap() {
		return filePropertyMap;
	}

}
