package GUIS;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollBar;

public class ManagerMenuGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtPrice;
	private JList List;
	private Vector<String>menu;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnEdit;
	private JLabel lblName;
	private JLabel lblPrice;
	private JScrollPane scrollPane;
	
	
	
	/**
	 * Create the frame.
	 */
	public ManagerMenuGUI() {
		FileManager fm = new FileManager("Menu.txt");
		menu = fm.read();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtName = new JTextField();
		txtName.setBounds(16, 211, 130, 26);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(158, 211, 130, 26);
		contentPane.add(txtPrice);
		txtPrice.setColumns(10);
		
		btnAdd = new JButton("Add");
		btnAdd.setBounds(327, 17, 117, 29);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				Double price =  Double.parseDouble(txtPrice.getText());
				addProduct(name, price);
			}
		});
		contentPane.add(btnAdd);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(327, 72, 117, 29);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				deleteProduct(name);
			}
		});
		contentPane.add(btnDelete);
		
		btnEdit = new JButton("Edit");
		btnEdit.setBounds(327, 130, 117, 29);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				Double price =  Double.parseDouble(txtPrice.getText());
				editProduct(name, price);
			}
		});
		contentPane.add(btnEdit);
		
		lblName = new JLabel("Name");
		lblName.setBounds(26, 183, 61, 16);
		contentPane.add(lblName);
		
		lblPrice = new JLabel("Price");
		lblPrice.setBounds(171, 183, 61, 16);
		contentPane.add(lblPrice);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 6, 267, 153);
		contentPane.add(scrollPane);
		
		List = new JList<>(menu);
		scrollPane.setViewportView(List);
		

	}
	public void deleteProduct(String s) {
		int x = 0;
		for (Integer i = 0; i < menu.size(); i++){
            if(menu.get(i).equals(s)) {
            	x=i;
            	menu.remove(i+1);    	
            }
        }
		menu.remove(x);
		FileManager fm = new FileManager("Menu.txt");
		fm.save(menu);
		backtoManager();
	}
	
	public void editProduct(String s, Double d) {
		for (Integer i = 0; i < menu.size(); i++){
            if(menu.get(i).equals(s)) {
            	menu.set(i, s);
            	menu.set(i+1, Double.toString(d));
            	FileManager fm = new FileManager("Menu.txt");
        		fm.save(menu);
            }
        }
		
		backtoManager();
	}
	
	public void addProduct(String s, Double d) {
		int index = menu.size();
		for (Integer i = 0; i < menu.size(); i++){
            if(menu.get(i).equals(s)) {
            	return;
            }
        }
		menu.add(s);
		menu.add(Double.toString(d));
		FileManager fm = new FileManager("Menu.txt");
		fm.save(menu);
		backtoManager();
	}
	
	public void backtoManager() {
		ManagerGUI g = new ManagerGUI();
		g.show();
		dispose();
	}

}
