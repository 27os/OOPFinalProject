package GUIS;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;

public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitle;
	private JButton btnCustomerEntry;
	private JButton btnManagerEntry;
	private JButton btnSupplierEntry;
	private JLabel lblCustomerEntry;
	private JLabel lblManagerEntry;
	private JLabel lblSupplierEntry;
	protected static double budget;
	
	

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitle = new JLabel("Welcome to Restaurant Managing System");
		lblTitle.setBounds(92, 22, 258, 16);
		contentPane.add(lblTitle);
		
		btnCustomerEntry = new JButton("Enter");
		btnCustomerEntry.setBounds(274, 84, 117, 29);
		btnCustomerEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openCustomer();
			}
		});
		contentPane.add(btnCustomerEntry);
		
		btnManagerEntry = new JButton("Enter");
		btnManagerEntry.setBounds(274, 139, 117, 29);
		btnManagerEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openManager();
			}
		});
		contentPane.add(btnManagerEntry);
		
		btnSupplierEntry = new JButton("Enter");
		btnSupplierEntry.setBounds(274, 202, 117, 29);
		btnSupplierEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openSupplier();
			}
		});
		contentPane.add(btnSupplierEntry);
		
		lblCustomerEntry = new JLabel("Customer Entry");
		lblCustomerEntry.setBounds(79, 89, 105, 16);
		contentPane.add(lblCustomerEntry);
		
		lblManagerEntry = new JLabel("Manager Entry");
		lblManagerEntry.setBounds(79, 144, 105, 16);
		contentPane.add(lblManagerEntry);
		
		lblSupplierEntry = new JLabel("Supplier Entry");
		lblSupplierEntry.setBounds(79, 207, 105, 16);
		contentPane.add(lblSupplierEntry);
	}
	
	public void openCustomer() {
		CustomerGUI g = new CustomerGUI();
		g.show();
	}
	
	public void openManager() {
		ManagerGUI g = new ManagerGUI();
		g.show();
		dispose();
	}
	
	public void openSupplier() {
		SupplierGUI g = new SupplierGUI();
		g.show();
	}
}
