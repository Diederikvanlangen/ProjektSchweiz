package com.yadeses.duitsKruisWordPuzzel.main;

import java.awt.Toolkit;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class GameField extends JTable {

	private static final long serialVersionUID = 1L;
	static Object[][] cells = new Object[20][20];
	static Object[] junk = new Object[20];

	public GameField() {		
		super(initHelp(), junk);
		setTableHeader(null);
		
	}

	private static Object[][] initHelp() {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				cells[i][j] = new JTextArea();
				((AbstractDocument) ((JTextArea) cells[i][j]).getDocument())
						.setDocumentFilter(new SizeDocumentFilter());
			}
			junk[i] = "";
		}
		return cells;
	}
}
