package com.yadeses.duitsKruisWordPuzzel.main;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class App {



	public static void main(String[] args) {
		
		Frame mainFrame = new Frame();		
	}
	
	public static List<Map<String, String>> readXML(String fileName) {

		List<Map<String, String>> words = new ArrayList<Map<String, String>>(5);
		
		for (int i = 0; i < 5; i++) {
			words.add(new HashMap<String, String>());
		}

		try {

			File file = new File(fileName);
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
			NodeList nList = doc.getElementsByTagName("woord");

			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element word = (Element) nNode;
					words.get(Integer.parseInt(word.getAttribute("id"))-1).put(word.getAttribute("naam"), word.getTextContent());
					System.out.println(word.getAttribute("naam"));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return words;
	}


}
