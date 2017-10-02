package com.fileprocess.dbvector;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ApplicationContext {
		public static Map<String, String> map=new HashMap<String,String>();
	static public void xmlParser(){
	    try {

	    	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder db = dbf.newDocumentBuilder();
	    	Document document = db.parse(new File("D:/workspace/FileTransferProject/src/com/resource.xml"));
	    	NodeList nodeList = document.getElementsByTagName("input-file");
	    	for(int x=0,size= nodeList.getLength(); x<size; x++) {
	    	    map.put(nodeList.item(x).getAttributes().getNamedItem("name").getNodeValue(),nodeList.item(x).getAttributes().getNamedItem("time").getNodeValue());
	    	    Iterator it = map.entrySet().iterator();
/*	    	    while (it.hasNext()) {
	    	        Map.Entry pair = (Map.Entry)it.next();
	    	        System.out.println(pair.getKey() + " = " + pair.getValue());
	    	    }*/
	    	}
	        } catch (Exception e) {
	    	e.printStackTrace();
	        }
	}
}
