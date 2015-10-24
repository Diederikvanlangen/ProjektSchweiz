package com.yadeses.duitsKruisWordPuzzel.main;

import java.awt.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;
import javax.swing.text.AbstractDocument;
import javax.swing.text.Document;

public class Frame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public Frame() {
		super("Puzzle");
		XMLFormatReader xml = new XMLFormatReader();
		List<Map<String, String>> Words = new ArrayList<Map<String, String>>(5);
		Words = xml
				.readXML("C:\\Users\\johan\\git\\ProjektSchweiz\\src\\com\\yadeses\\duitsKruisWordPuzzel\\main\\test.xml");

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JMenuBar bar = new JMenuBar();

		JButton but1 = new JButton("Fang das Spiel an");
		JButton but2 = new JButton("Kontrollieren");
		JTextField text = new JTextField("Umschreibungen");
		text.setEditable(false);
		Document doc = text.getDocument();
		((AbstractDocument) doc).setDocumentFilter(new SizeDocumentFilter());
		but1.addActionListener(this);
		bar.add(but1);
		bar.add(but2);
		bar.add(text);
		c.ipadx = 30;
		c.ipady = 12;
		JTextField cells[][] = new JTextField[20][20];
		for (int i = 0; i < 20; i++) {
			c.gridx = i;
			for (int j = 0; j < 20; j++) {
				c.gridy = j;

				cells[i][j] = new JTextField();
				add(cells[i][j], c);
				((AbstractDocument) ((JTextField) cells[i][j]).getDocument())
						.setDocumentFilter(new SizeDocumentFilter());
			}
		}

		setSize(800, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setJMenuBar(bar);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Fang das spiel an")) {
			Game.startGame();
		}

	}
}
