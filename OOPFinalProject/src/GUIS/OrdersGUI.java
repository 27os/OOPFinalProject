package GUIS;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class OrdersGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrOrders;
	private JButton btnClose;
	private Vector<String> orders;
	private JList orderList;

	
	public OrdersGUI() {
		FileManager fm = new FileManager("Orders.txt");
		orders = fm.read();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrOrders = new JScrollPane();
		scrOrders.setBounds(6, 6, 438, 195);
		contentPane.add(scrOrders);
		
		orderList = new JList<>(orders);
		scrOrders.setViewportView(orderList);
		
		btnClose = new JButton("Exit");
		btnClose.setBounds(166, 213, 117, 29);
		contentPane.add(btnClose);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backtoManager();
			}
		});
	}
	
	
	private void backtoManager() {
		ManagerGUI g = new ManagerGUI();
		g.show();
		dispose();
	}

}
