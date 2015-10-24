package com.yadeses.duitsKruisWordPuzzel.main;

import java.util.ArrayList;
import java.util.Random;



public class CharNumComb {

	public char[] combination;

	public CharNumComb() {
		combination = new char[30];
		char[] temp = "abcdefghijklmnopqrstuvwxyzäöüß".toCharArray();
		ArrayList<Character> alphabet = new ArrayList<Character>();
		for(char l: temp) {
			alphabet.add(l);
		}
		Random random = new Random();
		while (alphabet.size() > 0) {
			combination[alphabet.size() - 1] = alphabet.remove(random.nextInt(alphabet.size()));
		}

	}
}
