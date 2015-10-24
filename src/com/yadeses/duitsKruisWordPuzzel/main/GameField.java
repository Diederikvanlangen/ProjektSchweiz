package com.yadeses.duitsKruisWordPuzzel.main;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.AbstractDocument;

public class GameField extends JTable {

	private static final long serialVersionUID = 1L;
	static Object[][] cells = new Object[20][20];
	static Object[] junk = new Object[20];

	public GameField() {
		super(new MyTableModel(initHelp()));
		setTableHeader(null);
	}

	private static Object[][] initHelp() {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				cells[i][j] = new JTextArea();
				((AbstractDocument) ((JTextArea) cells[i][j]).getDocument())
						.setDocumentFilter(new SizeDocumentFilter());
			}
		}
		return cells;
	}
	
}

class MyTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	
	String[] colNames;
	Object[][] data;
	
	public MyTableModel(Object[][] obj){
		data = obj;
		for(int i = 0; i < obj[0].length; i++) {
			colNames[i] = "";
		}
	}
	
	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public int getColumnCount() {
		return colNames.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
	
}
