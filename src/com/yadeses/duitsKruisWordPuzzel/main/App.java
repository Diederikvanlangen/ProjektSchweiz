package com.yadeses.duitsKruisWordPuzzel.main;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class App {

	public static void main(String[] args) {
		
		Frame mainFrame = new Frame();
		
	}

	public static ArrayList<HashMap<String, String>> readXML(String fileName) {

		ArrayList<HashMap<String, String>> words = new ArrayList<HashMap<String, String>>();

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
					words.get(Integer.parseInt(word.getAttribute("id")) - 1).put(word.getAttribute("naam"),
							word.getTextContent());
					System.out.println(word.getAttribute("naam"));
				}
			}

		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return words;
	}

	@SuppressWarnings("unchecked")
	private static ArrayList<Object> genGame(List<HashMap<String, String>> data, int depth) {
		depth += 1;
		LinkedList<HashMap<String, String>> data2 = (LinkedList<HashMap<String, String>>) ((LinkedList<HashMap<String, String>>) data).clone();
		Random random = new Random();
		ArrayList<Object> game = new ArrayList<Object>();

		List<String> keys = new ArrayList<String>(data2.get(4).keySet());
		String randomKey = keys.get(random.nextInt(keys.size()));
		data2.get(4).remove(randomKey);
		game.add(randomKey);

		char[] keyChars = randomKey.toCharArray();

		for (char c : keyChars) {
			ArrayList<Integer> num = new ArrayList<Integer>();
			for (int i = 0; i < 4; i++) {
				num.add(i);
			}
			loop: while (num.size() != 0) {
				HashMap<String, String> niveau = data2.get(num.remove(random.nextInt(num.size())));
				for (String s : niveau.keySet()) {
					if (s.indexOf(c) != -1) {
						game.add(s.toCharArray());
						niveau.remove(s);
						break loop;
					}
				}
			}
		}
		if (game.size() < randomKey.length()) {
			if (depth > 3) {
				System.err.print("Error!");
			} else {
				return genGame(data, depth);
			}
		}
		while (game.size() < 13) {
			HashMap<String, String> n = data2.get(random.nextInt(4));
			List<String> keys2 = new ArrayList<String>(n.keySet());
			String randomKey2 = keys2.get(random.nextInt(keys2.size()));
			n.remove(randomKey2);
			game.add(randomKey2.toCharArray());
		}

		return game;
	}

	public static ArrayList<Object> genGame(List<HashMap<String, String>> data) {
		LinkedList<HashMap<String, String>> data2 = (LinkedList<HashMap<String, String>>) ((LinkedList<HashMap<String, String>>) data).clone();
		Random random = new Random();
		ArrayList<Object> game = new ArrayList<Object>();

		List<String> keys = new ArrayList<String>(data2.get(4).keySet());
		String randomKey = keys.get(random.nextInt(keys.size()));
		data2.get(4).remove(randomKey);
		game.add(randomKey);

		char[] keyChars = randomKey.toCharArray();

		for (char c : keyChars) {
			ArrayList<Integer> num = new ArrayList<Integer>();
			for (int i = 0; i < 4; i++) {
				num.add(i);
			}
			loop: while (num.size() != 0) {
				HashMap<String, String> niveau = data2.get(num.remove(random.nextInt(num.size())));
				for (String s : niveau.keySet()) {
					if (s.indexOf(c) != -1) {
						game.add(s.toCharArray());
						niveau.remove(s);
						break loop;
					}
				}
			}
		}
		if (game.size() < randomKey.length()) {
			return (ArrayList<Object>) genGame(data, 0);
		}
		while (game.size() < 13) {
			HashMap<String, String> n = data2.get(random.nextInt(4));
			List<String> keys2 = new ArrayList<String>(n.keySet());
			String randomKey2 = keys2.get(random.nextInt(keys2.size()));
			n.remove(randomKey2);
			game.add(randomKey2);
		}
		return game;
	}

}
