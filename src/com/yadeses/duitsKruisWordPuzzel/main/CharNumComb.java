package com.yadeses.duitsKruisWordPuzzel.main;

import java.util.ArrayList;
import java.util.Random;

public class CharNumComb {

	public ArrayList<Character> combination;

	public CharNumComb() {
		combination = new ArrayList();
		char[] temp = "abcdefghijklmnopqrstuvwxyzäöüß".toCharArray();
		ArrayList<Character> alphabet = new ArrayList<Character>();
		for(char l: temp) {
			alphabet.add(l);
		}
		Random random = new Random();
		while (alphabet.size() > 0) {
			combination.add(alphabet.remove(random.nextInt(alphabet.size())));
		}

	}
}
