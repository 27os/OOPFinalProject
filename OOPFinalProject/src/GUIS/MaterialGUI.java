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
import javax.swing.JButton;

public class MaterialGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtQuantity;
	private Vector<String> material;
	private Vector<String> menu;
	private JTextField txtBudget;
	private JLabel lblMessage;
	private JLabel lblTitle;
	private JLabel lblAvailability;
	private JLabel lblPrice;
	private JLabel lblPriceNum;
	private JButton btnBuy;
	
	

	public MaterialGUI() {
		FileManager fm = new FileManager("Data.txt");
		material = fm.read();
		FileManager bm = new FileManager("Menu.txt");
		menu = bm.read();
		double budget = Double.parseDouble(menu.get(0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitle = new JLabel("Material");
		lblTitle.setBounds(36, 40, 61, 16);
		contentPane.add(lblTitle);
		
		lblAvailability = new JLabel(" ");
		lblAvailability.setBounds(324, 40, 61, 16);
		if(Double.parseDouble(material.get(2)) ==1) {
			lblAvailability.setText("Available!");
		}else {
			lblAvailability.setText("Unvailable!");
		}
		contentPane.add(lblAvailability);
		
		lblPrice = new JLabel("Price");
		lblPrice.setBounds(36, 98, 61, 16);
		
		contentPane.add(lblPrice);
		
		lblPriceNum = new JLabel("");
		lblPriceNum.setBounds(324, 98, 61, 16);
		lblPriceNum.setText(material.get(1));
		contentPane.add(lblPriceNum);
		
		txtQuantity = new JTextField();
		txtQuantity.setBounds(36, 157, 130, 26);
		contentPane.add(txtQuantity);
		txtQuantity.setColumns(10);
		
		btnBuy = new JButton("Buy");
		btnBuy.setBounds(299, 157, 117, 29);
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double quantity = Double.parseDouble(txtQuantity.getText());
				double cost = (Double.parseDouble(material.get(1)))*quantity;
				if(budget>=cost && Double.parseDouble(material.get(2))==1) {
					buyMaterial(cost, quantity);
					backtoManager();
				}else {
					lblMessage.setText("Insufficient Budget or Material Unavailable!");
				}
				
			}
		});
		contentPane.add(btnBuy);
		
		lblMessage = new JLabel("");
		lblMessage.setBounds(23, 221, 421, 16);
		contentPane.add(lblMessage);
		
		txtBudget = new JTextField();
		txtBudget.setBounds(286, 6, 130, 26);
		contentPane.add(txtBudget);
		txtBudget.setEditable(false);
		txtBudget.setText("$"+String.valueOf(budget));
		txtBudget.setColumns(10);
	}
	
	
	public void backtoManager() {
		ManagerGUI g = new ManagerGUI();
		g.show();
		dispose();
	}
	
	public void buyMaterial(double cost, double quantity) {
		FileManager fm = new FileManager("Data.txt");
		FileManager bm = new FileManager("Menu.txt");
		menu.set(0, String.valueOf(Double.parseDouble(menu.get(0))-cost));
		bm.save(menu);
		double num = Double.parseDouble(material.get(0));
		material.set(0,Double.toString(num+quantity));
		fm.save(material);
	}

}
