package com.yadeses.duitsKruisWordPuzzel.main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.text.AbstractDocument;
import javax.swing.text.Document;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Frame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public Frame() {
		super("Puzzle");
		String Schlüsselwort = "Schweiz";
		List<Map<String, String>> Words = new ArrayList<Map<String, String>>(5);
		Words = App.readXML("test.xml");

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JMenuBar bar = new JMenuBar();

		JButton but1 = new JButton("Erklärung");
		but1.addActionListener(this);
		JButton but2 = new JButton("Kontrollieren");
		JTextField text = new JTextField("Umschreibungen");
		text.setEditable(false);
		Document doc = text.getDocument();
		((AbstractDocument) doc).setDocumentFilter(new SizeDocumentFilter());

		bar.add(but2);
		bar.add(but1);
		c.ipadx = 20;
		c.ipady = 8;
		JTextField cells[][] = new JTextField[20][20];
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
			for (int j = 0; j < 20; j++) {
				c.gridx = j + 1;
				cells[i][j] = new JTextField();
				/*
				cells[i][j].setOpaque( true );
				JLabel label = new JLabel( new ImageIcon("1.gif") );
				label.setLayout( new BorderLayout() );
				label.add( cells[i][j]);
				
				
				add(label, c);((AbstractDocument) ((JTextField) cells[i][j]).getDocument())
				.setDocumentFilter(new SizeDocumentFilter());
				*/
				add(cells[i][j],c);
				((AbstractDocument) ((JTextField) cells[i][j]).getDocument())
				.setDocumentFilter(new SizeDocumentFilter());
				JLabel Q = new JLabel("1");
				add(Q,c);
				
				

			}
		}

		JLabel Umschreibungen = new JLabel("Umschreibungen");
		c.gridy = 0;
		c.gridx = 10;
		c.gridwidth = 8;
		add(Umschreibungen, c);

		for (int i = 0; i < 12; i++) {
			String getal = "" + (i + 1);
			JLabel omschrijving = new JLabel(getal + ". ");
			c.gridwidth = 1;
			c.gridx = 0;
			c.gridy = i + 1;
			c.ipady = 12;
			add(omschrijving, c);
		}

		setSize(900, 900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setJMenuBar(bar);
		setVisible(true);
		JOptionPane
				.showMessageDialog(
						null,
						""
								+ "In diesem Spiel sollst du probieren das Schlüsselwort \n"
								+ "von logisch Nachdenken zu raten. Dafür musst du erst \n"
								+ "schwanzig Fragen beantworten, die sich handeln um die Schweiz.\n"
								+ "Trag in jedes Feld einen Letter ein. Wenn in mehreren Felder\n"
								+ "die selbe Zahlen sind, sollen in diese Felder auch die\n"
								+ "selbe Letter gesezt werden. Wenn du alle Zahlen des \n"
								+ "Schlüsselworts weißt, kannst du es erfüllen und hast du\n"
								+ "das Spiel gewonnen!", "Spielerklärung",
						JOptionPane.PLAIN_MESSAGE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Erklärung") {
			JOptionPane
					.showMessageDialog(
							null,
							""
									+ "In diesem Spiel sollst du probieren das Schlüsselwort \n"
									+ "von logisch Nachdenken zu raten. Dafür musst du erst \n"
									+ "schwanzig Fragen beantworten, die sich handeln um die Schweiz.\n"
									+ "Trag in jedes Feld einen Letter ein. Wenn in mehreren Felder\n"
									+ "die selbe Zahlen sind, sollen in diese Felder auch die\n"
									+ "selbe Letter gesezt werden. Wenn du alle Zahlen des \n"
									+ "Schlüsselworts weißt, kannst du es erfüllen und hast du\n"
									+ "das Spiel gewonnen!", "Spielerklärung",
							JOptionPane.PLAIN_MESSAGE);
		}

	}
}
