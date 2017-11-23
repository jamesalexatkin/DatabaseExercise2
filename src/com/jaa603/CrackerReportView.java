package com.jaa603;

import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.postgresql.util.PSQLException;

import javax.swing.JOptionPane;

import javax.swing.JTable;
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
		setBounds(100, 100, 680, 130);
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		ScrollPaneLayout spl_scrollPane = new ScrollPaneLayout();
		scrollPane.setLayout(spl_scrollPane);
		
		this.queryResults = queryResults;
		
		try {
			// Generate table from query results
			table = unpackResultSet();
			table.setFont(new Font("Dialog", Font.PLAIN, 12));
			table.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			table.setFillsViewportHeight(true);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		} catch (SQLException e) {
			e.printStackTrace();
			table = new JTable();
			// Error in unpacking ResultSet results from it being empty as a non-existent ID was specified
			JOptionPane.showMessageDialog(null, "Error retrieving data. Check to ensure that you've entered an ID between 0 and 999.", "Message from Database", 0);
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
		
		scrollPane = new JScrollPane(table);
		setContentPane(scrollPane);
		
		setVisible(true);		
	}

	/**
	 * Unpacks a ResultSet into a JTable.
	 * @return the JTable
	 * @throws SQLException
	 * @throws PSQLException
	 */
	private JTable unpackResultSet() throws SQLException, PSQLException {
		queryResults.next();
		
		// Get field names
		ResultSetMetaData metaData = queryResults.getMetaData();		
		Vector<String> fieldNames = new Vector<String>();
		for (int i = 1; i <= metaData.getColumnCount(); i++) {
			fieldNames.add(metaData.getColumnLabel(i));
		}
		// Get field data
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		Vector<Object> firstRow = new Vector<Object>();
		for(int i = 1; i <= metaData.getColumnCount(); i++) {
			firstRow.add(queryResults.getObject(i));
		}
		data.add(firstRow);
		
		return new JTable(new DefaultTableModel(data, fieldNames));
	}
}
