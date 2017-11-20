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
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JPanel;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JInternalFrame;

public class UserInterface {

	private JFrame frmBbcDatabase;
	private JTextField txtCrackerId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface window = new UserInterface();
					window.frmBbcDatabase.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserInterface() {
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
		panelReportWest.add(btnCrackerReport, "cell 0 6");
		
		JLabel label = new JLabel("Cracker ID:");
		panelReport.add(label, BorderLayout.SOUTH);
		
		JPanel panelReportEast = new JPanel();
		panelReport.add(panelReportEast, BorderLayout.EAST);
		panelReportEast.setLayout(new MigLayout("", "[114px]", "[15px][][][][][][161px]"));
		
		JPanel panelInsert = new JPanel();
		tabbedPane.addTab("Add a New Cracker", null, panelInsert, null);
		
		JTextArea txtMessageBox = new JTextArea();
		txtMessageBox.setText("Message Box:\n----------------");
		txtMessageBox.setLineWrap(true);
		GridBagConstraints gbc_txtMessageBox = new GridBagConstraints();
		gbc_txtMessageBox.gridheight = 2;
		gbc_txtMessageBox.anchor = GridBagConstraints.NORTH;
		gbc_txtMessageBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMessageBox.gridx = 0;
		gbc_txtMessageBox.gridy = 7;
		frmBbcDatabase.getContentPane().add(txtMessageBox, gbc_txtMessageBox);
	}

}
