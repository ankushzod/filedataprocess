package com.fileprocess.helper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class ApplicationContext {
	private XMLParser xmlParser = null;
	static ApplicationContext monitorContext = null;
	
	public static Map<String, String> filePropertyMap = null;
	public static Map<String, String> processedFileMap = null;
	
	private ApplicationContext() throws SAXException, IOException, ParserConfigurationException{
		xmlParser = XMLParser.getInstance();
		filePropertyMap = xmlParser.getFilePropertyMap();
		
		processedFileMap = new HashMap<String, String>();
		processedFileMap.put("b.csv", "28-09-2017");
	}
	
	public static ApplicationContext loadfileProcessContext() throws SAXException, IOException, ParserConfigurationException{
		if(monitorContext == null){
			monitorContext = new ApplicationContext();
		}
		return monitorContext;
	}

}
