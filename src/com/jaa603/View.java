package com.jaa603;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import net.miginfocom.swing.MigLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JPanel;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JInternalFrame;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class View {

	private JFrame frmBbcDatabase;
	private JTextField txtCrackerId;
	private JTextField txtJokeId;

	protected Adapter adapter;

	private final String MESSAGE_FROM_DB = "Message from Database";
	
	/**
	 * Create the application.
	 */
	public View(Adapter adapter) {
		this.adapter = adapter;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBbcDatabase = new JFrame();
		frmBbcDatabase.setTitle("BBC Database");
		frmBbcDatabase.setBounds(100, 100, 450, 300);
		frmBbcDatabase.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{438, 0};
		gridBagLayout.rowHeights = new int[]{15, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmBbcDatabase.getContentPane().setLayout(gridBagLayout);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.gridheight = 7;
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 0);
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		frmBbcDatabase.getContentPane().add(tabbedPane, gbc_tabbedPane);
		
		JPanel panelReport = new JPanel();
		tabbedPane.addTab("Produce a Report", null, panelReport, null);
		panelReport.setLayout(new BorderLayout(0, 0));
		
		JPanel panelReportWest = new JPanel();
		panelReport.add(panelReportWest, BorderLayout.WEST);
		panelReportWest.setLayout(new MigLayout("", "[114px]", "[15px][][][][][][161px]"));
		
		JLabel lblCrackerReport = new JLabel("Cracker ID:");
		panelReportWest.add(lblCrackerReport, "flowy,cell 0 5,growx,aligny top");
		
		txtCrackerId = new JTextField();
		panelReportWest.add(txtCrackerId, "cell 0 5,alignx left,aligny center");
		txtCrackerId.setColumns(10);
		
		JButton btnCrackerReport = new JButton("Produce cracker report!");
		btnCrackerReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cid = txtCrackerId.getText();
				try {
					ResultSet queryResults = adapter.getCracker(cid);
					CrackerReportView report = new CrackerReportView(queryResults);
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Error retrieving data", MESSAGE_FROM_DB, 0);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error parsing input. Please enter a valid integer greater than zero.", MESSAGE_FROM_DB, 0);
				}
			}
		});
		panelReportWest.add(btnCrackerReport, "cell 0 6");
		
		JPanel panelReportEast = new JPanel();
		panelReport.add(panelReportEast, BorderLayout.EAST);
		panelReportEast.setLayout(new MigLayout("", "[114px]", "[15px][][][][][][161px]"));
		
		JLabel lblJokeReport = new JLabel("Joke ID:");
		panelReportEast.add(lblJokeReport, "flowy,cell 0 5,growx,aligny top");
		
		txtJokeId = new JTextField();
		panelReportEast.add(txtJokeId, "cell 0 5,alignx left,aligny center");
		txtJokeId.setColumns(10);
		
		JButton btnJokeReport = new JButton("Produce joke report!");
		panelReportEast.add(btnJokeReport, "cell 0 6 1 6");
		
		JPanel panelInsert = new JPanel();
		tabbedPane.addTab("Add a New Cracker", null, panelInsert, null);
		
		frmBbcDatabase.setVisible(true);
	}

}