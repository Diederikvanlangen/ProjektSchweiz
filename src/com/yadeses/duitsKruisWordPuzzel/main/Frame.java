package com.yadeses.duitsKruisWordPuzzel.main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;

public class Frame extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	
	public Frame(){
		super("Puzzle");
		JMenuBar bar = new JMenuBar();
		JButton but1 = new JButton("Fang das spiel an");
		//JScrollPane pane = new JScrollPane(new GameField());
		but1.addActionListener(this);
		bar.add(but1);
		//add(pane);
		add(new GameField());
		setSize(screenSize);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setJMenuBar(bar);
		setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Fang das spiel an")){
			Game.startGame();
		}
		
	}
}
