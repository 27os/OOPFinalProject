package GUIS;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class ManagerGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea txtBudget;
	private JLabel lblTitle;
	private JLabel lblPurchase;
	private JLabel lblViewOrders;
	private JButton btnViewMenu;
	private JButton btnPurchase;
	private JButton btnViewOrders;
	private JLabel lblViewMenu;
	private Vector<String> menu;
	

	/**
	 * Create the frame.
	 */
	public ManagerGUI() {
		FileManager fm = new FileManager("Menu.txt");
		menu = fm.read();
		MainGUI.budget = Double.parseDouble(menu.get(0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitle = new JLabel("Manager GUI");
		lblTitle.setBounds(176, 17, 104, 16);
		contentPane.add(lblTitle);
		
		lblPurchase = new JLabel("Purchase Ingredients");
		lblPurchase.setBounds(66, 127, 152, 16);
		contentPane.add(lblPurchase);
		
		lblViewOrders = new JLabel("View Orders");
		lblViewOrders.setBounds(64, 180, 96, 16);
		contentPane.add(lblViewOrders);
		
		txtBudget = new JTextArea();
		txtBudget.setBounds(291, 17, 122, 16);
		txtBudget.setEditable(false);
		txtBudget.setText("$"+String.valueOf(MainGUI.budget));
		contentPane.add(txtBudget);
		
		btnViewMenu = new JButton("Enter");
		btnViewMenu.setBounds(273, 71, 117, 29);
		btnViewMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewMenu();
			}
		});
		contentPane.add(btnViewMenu);
		
		btnPurchase = new JButton("Enter");
		btnPurchase.setBounds(273, 122, 117, 29);
		btnPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				purchaseMaterial();
			}
		});
		contentPane.add(btnPurchase);
		
		btnViewOrders = new JButton("Enter");
		btnViewOrders.setBounds(273, 180, 117, 29);
		btnViewOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewOrder();
			}
		});
		contentPane.add(btnViewOrders);
		
		lblViewMenu = new JLabel("View menu");
		lblViewMenu.setBounds(66, 76, 81, 16);
		contentPane.add(lblViewMenu);
	}
	
	public void purchaseMaterial() {
		MaterialGUI g = new MaterialGUI();
		g.show();
		dispose();
	}
	
	public void viewMenu() {
		ManagerMenuGUI g = new ManagerMenuGUI();
		g.show();
		dispose();
	}
	
	public void viewOrder() {
		OrdersGUI g = new OrdersGUI();
		g.show();
		dispose();
	}
	

}
