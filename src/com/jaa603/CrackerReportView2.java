package com.jaa603;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import javax.swing.SpringLayout;

public class CrackerReportView2 extends JFrame {

	private JScrollPane contentPane;
	private ResultSet queryResults;
	private JTable table;

	/**
	 * Create the frame.
	 * @param queryResults 
	 */
	public CrackerReportView2(ResultSet queryResults) {
		setTitle("Report for Cracker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 300);
		contentPane = new JScrollPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		//SpringLayout sl_contentPane = new SpringLayout();
		ScrollPaneLayout sc_layout = new ScrollPaneLayout();
		contentPane.setLayout(sc_layout);
		
		
		table = new JTable();
		
//		sl_contentPane.putConstraint(SpringLayout.NORTH, table_1, -92, SpringLayout.SOUTH, contentPane);
//		sl_contentPane.putConstraint(SpringLayout.WEST, table_1, 186, SpringLayout.WEST, contentPane);
//		sl_contentPane.putConstraint(SpringLayout.SOUTH, table_1, -116, SpringLayout.SOUTH, contentPane);
//		sl_contentPane.putConstraint(SpringLayout.EAST, table_1, -385, SpringLayout.EAST, contentPane);
		//contentPane.add(table_1);
		
		//JScrollPane scrollPane = new JScrollPane(table_1);
		contentPane.add(table);
		
		setVisible(true);
		
		this.queryResults = queryResults;
		
		try {
			unpackResultSet(table);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void unpackResultSet(JTable table) throws SQLException {
		queryResults.next();
		int cid = queryResults.getInt(1);
		String crackerName = queryResults.getString(2);
		String giftDescription = queryResults.getString(3);
		String joke = queryResults.getString(4);
		String hatDescription = queryResults.getString(5);
		float saleprice = queryResults.getFloat(6);
		float costprice = queryResults.getFloat(7);
		int quantity = queryResults.getInt(8);
		float netprofit = queryResults.getFloat(9);
		
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
		
		table = new JTable(new DefaultTableModel(data, fieldNames));
		//contentPane.add(table/*, BorderLayout.CENTER*/);
		contentPane = new JScrollPane(table);
		
		
	}
}
