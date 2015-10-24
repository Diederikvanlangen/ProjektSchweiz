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
import javax.swing.JLabel;
public class Frame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public Frame() {
		super("Puzzle");

		List<Map<String, String>> Words = new ArrayList<Map<String, String>>(5);
		Words = App.readXML("test.xml");

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JMenuBar bar = new JMenuBar();

		
		JButton but2 = new JButton("Kontrollieren");
		JTextField text = new JTextField("Umschreibungen");
		text.setEditable(false);
		Document doc = text.getDocument();
		((AbstractDocument) doc).setDocumentFilter(new SizeDocumentFilter());
		
		bar.add(but2);		
		c.ipadx = 30;
		c.ipady = 12;
		JTextField cells[][] = new JTextField[20][20];
		for (int i = 0; i < 12; i++) {
			c.gridx = 0;
			c.gridy = 20+i;
			String getal = "" + (i+1);
			JLabel o = new JLabel(getal);			
			add(o,c);
			for (int j = 0; j < 20; j++) {
				c.gridx = j+1;
				cells[i][j] = new JTextField();
				add(cells[i][j], c);
				((AbstractDocument) ((JTextField) cells[i][j]).getDocument())
						.setDocumentFilter(new SizeDocumentFilter());
			}
		}
		JLabel Umschreibungen = new JLabel("Umschreibungen");
		c.gridy=0;
		c.gridx=10;
		c.gridwidth=4;			
		add(Umschreibungen,c);
		
		for(int i=0;i<12;i++){
			String getal = "" + (i+1);
			JLabel omschrijving = new JLabel(getal + ". ");
			c.gridwidth=1;
			c.gridx=0;
			c.gridy=i+1;
			c.ipady = 12;
			add(omschrijving,c);
		}
		setSize(800, 850);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setJMenuBar(bar);
		setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		

	}
}
