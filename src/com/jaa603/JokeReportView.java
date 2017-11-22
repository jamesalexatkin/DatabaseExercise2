package com.jaa603;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class JokeReportView extends JFrame {

	private JScrollPane scrollPane;
	private ResultSet queryResults;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public JokeReportView(ResultSet queryResults) {
		setTitle("Report for Joke");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 418, 130);
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		ScrollPaneLayout spl_scrollPane = new ScrollPaneLayout();
		scrollPane.setLayout(spl_scrollPane);
		
		this.queryResults = queryResults;
		
		try {
			table = unpackResultSet();
			table.setFont(new Font("Dialog", Font.PLAIN, 12));
			table.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			table.setFillsViewportHeight(true);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		} catch (SQLException e) {
			e.printStackTrace();
			table = new JTable();
			JOptionPane.showMessageDialog(null, "Error retrieving data. Check to ensure that you've entered an ID between 0 and 99.", "Message from Database", 0);
		}
		
		scrollPane = new JScrollPane(table);
		setContentPane(scrollPane);
		
		setVisible(true);
	}

	private JTable unpackResultSet() throws SQLException {
		queryResults.next();
		int jid = queryResults.getInt(1);
		String joke = queryResults.getString(2);
		float royalty = queryResults.getFloat(3);
		int numberofuses = queryResults.getInt(4);
		float totalroyalty = queryResults.getFloat(5);
		
		ResultSetMetaData metaData = queryResults.getMetaData();		
		Vector<String> fieldNames = new Vector<String>();
		for (int i = 1; i <= metaData.getColumnCount(); i++) {
			fieldNames.add(metaData.getColumnLabel(i));
		}
		
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		Vector<Object> firstRow = new Vector<Object>();
		for(int i = 1; i <= metaData.getColumnCount(); i++) {
			firstRow.add(queryResults.getObject(i));
		}
		data.add(firstRow);
		
		
		return new JTable(new DefaultTableModel(data, fieldNames));		
	}
	
}
