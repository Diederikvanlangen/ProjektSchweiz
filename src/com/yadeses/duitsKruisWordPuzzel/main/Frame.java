package com.yadeses.duitsKruisWordPuzzel.main;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.Document;

public class Frame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	ArrayList<char[]> gameFormat;
	JTextField cells[][];
	int pogingen = 1;
	public static Frame mainFrame;

	public Frame(int Q) {
		super("Puzzle");
		CharNumComb comb = new CharNumComb();
		for (int i = 0; i < comb.combination.size(); i++) {
			System.out.println(i + ": " + comb.combination.get(i));
		}
		LinkedList<HashMap<String, String>> Words = App.readXML("test.xml");
		HashMap<String, String> hashmap = new HashMap<String, String>();

		for (int i = 0; i < Words.size(); i++) {
			hashmap.putAll(Words.get(i));
		}

		gameFormat = App.genGame(Words);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		JMenuBar bar = new JMenuBar();
		JButton but1 = new JButton("Erklärung");
		but1.addActionListener(this);
		JButton but4 = new JButton("Tipps");
		but4.addActionListener(this);
		JButton but2 = new JButton("Kontrollieren");
		but2.addActionListener(this);
		JButton but3 = new JButton("Neu Spiel");
		but3.addActionListener(this);

		JTextField text = new JTextField("Umschreibungen");
		text.setEditable(false);
		Document doc = text.getDocument();
		((AbstractDocument) doc).setDocumentFilter(new SizeDocumentFilter());
		cells = new JTextField[13][20];
		bar.add(but2);
		bar.add(but1);
		bar.add(but4);
		bar.add(but3);
		c.ipadx = 15;
		c.ipady = 8;
		for (int i = 0; i < 13; i++) {
			c.gridx = 0;
			c.gridy = 13 + i;
			String getal;
			if (i < 12) {

				getal = "" + (i + 1);
			} else
				getal = "Schlüsselwort";
			JLabel o = new JLabel(getal);
			add(o, c);
			System.out.println(gameFormat.get(i));
			for (int j = 0; j < ((char[]) gameFormat.get(i)).length; j++) {
				c.gridx = j + 1;
				ImageIcon imageIcon = new ImageIcon(
						"Recources/"
								+ (comb.combination
										.indexOf(((char[]) gameFormat.get(i))[j]) + 1)
								+ ".gif");
				cells[i][j] = new JTextField() {
					private static final long serialVersionUID = 1L;
					Image image = imageIcon.getImage();

					{
						setOpaque(false);
					}

					public void paintComponent(Graphics g) {
						g.drawImage(image, 0, 0, this);
						super.paintComponent(g);
					}
				};
				((AbstractDocument) ((JTextField) cells[i][j]).getDocument())
						.setDocumentFilter(new SizeDocumentFilter());
				add(cells[i][j], c);

			}
		}

		JLabel Umschreibungen = new JLabel("Umschreibungen");
		c.gridy = 0;
		c.gridx = 0;
		c.gridwidth = 6;

		add(Umschreibungen, c);

		for (int i = 0; i < 12; i++) {
			String getal = "" + (i + 1);
			JLabel omschrijvingk = new JLabel(getal + ". ");
			JLabel omschrijving = new JLabel(hashmap.get(String
					.valueOf(gameFormat.get(i))));
			c.gridwidth = 1;
			c.gridx = 0;
			c.gridy = i + 1;
			c.ipady = 12;
			add(omschrijvingk, c);
			c.gridwidth = 13;
			c.gridx = 1;
			c.gridy = i + 1;
			add(omschrijving, c);

		}
		setResizable(false);
		setSize(600, 900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setJMenuBar(bar);
		setVisible(true);
		if (Q == 0) {
			showErklärung();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Erklärung")) {
			showErklärung();
		}

		if (e.getActionCommand().equals("Kontrollieren")) {
			if (checkAnswer() == true) {
				JOptionPane.showMessageDialog(null,
						"Gratuliere, du hast das Spiel gewonnen in " + pogingen
								+ " mal!", "Gewonnen",
						JOptionPane.PLAIN_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null,
						"Leider, versuch es noch einmal.\n" + "Du hast es "
								+ pogingen + " mal probiert.",
						"Noch nicht ganz richtig", JOptionPane.PLAIN_MESSAGE);
				pogingen++;
			}
		}
		if (e.getActionCommand().equals("Neu Spiel")) {
			setVisible(false);
			dispose();
			mainFrame = new Frame(1);

		}
		if (e.getActionCommand().equals("Tipps")) {
			JOptionPane
					.showMessageDialog(
							null,"Die Letter Ä,Ü,Ö und ß werden zu eigene Letter gerechnet.\n"
								+ "Gebrauch keine Zwischenraumen wenn eine Antwort aus zwei Wörter besteht.\n"
								+ "Es ist egal ob du Majusklen gebraucht oder nicht.\n"
								+ "ß: ctrl + alt + s"
								+ "Gebrauch das Netz wenn du etwas nicht weißt"
							, "Tipps",
							JOptionPane.PLAIN_MESSAGE);
		}

	}

	private void showErklärung() {
		JOptionPane
				.showMessageDialog(
						null,
						""
								+ "In diesem Spiel sollst du probieren das Schlüsselwort \n"
								+ "durch logisch nachzudenken zu raten. Dafür sollst du erst \n"
								+ "zwölf Fragen beantworten, die sich handeln um die Schweiz.\n"
								+ "Trag in jedes Feld einen Letter ein. Wenn in mehreren Feldern\n"
								+ "die selbe Zahlen sind, sollen in diese Felder auch die\n"
								+ "selbe Letter gesetzt werden. Wenn du alle Zahlen des \n"
								+ "Schlüsselworts weißt, kannst du es erfüllen und hast du\n"
								+ "das Spiel gewonnen!", "Spielerklärung",
						JOptionPane.PLAIN_MESSAGE);
	}

	public void showError() {
		JOptionPane.showMessageDialog(null,
				"Fehler! Bitte, starte die Applikation neu.",
				"Applikationfehler", JOptionPane.ERROR_MESSAGE);
	}

	private boolean checkAnswer() {
		for (int i = 0; i < gameFormat.size(); i++) {
			for (int j = 0; j < gameFormat.get(i).length; j++) {
				if (!(String.valueOf(gameFormat.get(i)[j])
						.equalsIgnoreCase(cells[i][j].getText()))) {
					return false;
				}
			}
		}
		return true;

	}
}
