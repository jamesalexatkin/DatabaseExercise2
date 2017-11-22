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

import org.postgresql.util.PSQLException;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.border.BevelBorder;
import java.awt.Font;

public class CrackerReportView extends JFrame {

	private JScrollPane scrollPane;
	private ResultSet queryResults;
	private JTable table;

	/**
	 * Create the frame.
	 * @param queryResults 
	 */
	public CrackerReportView(ResultSet queryResults) {
		setTitle("Report for Cracker");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 703, 330);
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
			JOptionPane.showMessageDialog(null, "Error retrieving data. Check to ensure that you've entered an ID between 0 and 999.", "Message from Database", 0);
		}
		
		scrollPane = new JScrollPane(table);
		setContentPane(scrollPane);
		
		setVisible(true);		
	}

	private JTable unpackResultSet() throws SQLException, PSQLException {
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
		
		
		return new JTable(new DefaultTableModel(data, fieldNames));
		//scrollPane.add(table);
//		scrollPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(scrollPane);
//		ScrollPaneLayout spl_scrollPane = new ScrollPaneLayout();
//		scrollPane.setLayout(spl_scrollPane);
	}
}
