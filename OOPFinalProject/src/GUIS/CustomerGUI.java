package GUIS;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.util.*;

public class CustomerGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Map<String, Integer> currentOrder;
    private Vector<String> menuItems;
    private Vector<String> menuPrices;
    private String budget;

    public CustomerGUI() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setTitle("Customer Interface");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        currentOrder = new HashMap<>();
        menuItems = new Vector<>();
        menuPrices = new Vector<>();
        
        initializeComponents();
        loadMenu();
    }

    private void initializeComponents() {
        JLabel t = new JLabel("Customer Menu");
        t.setBounds(174, 6, 133, 16);
        contentPane.add(t);
        
        JButton o = new JButton("Place Order");
        o.setBounds(79, 58, 117, 29);
        o.addActionListener(e -> showOrderDialog());
        contentPane.add(o);
        
        JButton m = new JButton("View Menu");
        m.setBounds(79, 131, 117, 29);
        m.addActionListener(e -> showMenuDialog());
        contentPane.add(m);

        JButton r = new JButton("Reserve Seat");
        r.setBounds(263, 58, 117, 29);
        r.addActionListener(e -> showReservationDialog());
        contentPane.add(r);

        JButton back = new JButton("Back");
        back.setBounds(263, 131, 117, 29);
        back.addActionListener(e -> dispose());
        contentPane.add(back);
    }

    private void loadMenu() {
    	FileManager fm = new FileManager("Menu.txt");
        Vector<String> menuData = fm.read();
        if (!menuData.isEmpty()) {
            budget =menuData.get(0);
            for (int i= 1; i<menuData.size(); i++) {
                if (i%2== 1) {
                    menuItems.add(menuData.get(i));
                } else {
                    menuPrices.add(menuData.get(i));
                }
            }
        }
    }

    private void showMenuDialog() {
        if (menuItems.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No menu items available");
            return;
        }

        StringBuilder menuText = new StringBuilder("Menu Items:\n\n");
        for (int i = 0; i < menuItems.size(); i++) {
            menuText.append(String.format("%-20s $%s\n", menuItems.get(i), menuPrices.get(i)));
        }

        JTextArea textArea = new JTextArea(menuText.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN,12));
        JScrollPane scrollPane =new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(300,Math.min(400,menuItems.size()*25+50)));

        JOptionPane.showMessageDialog(this, scrollPane, "Menu", JOptionPane.PLAIN_MESSAGE);
    }

    private void showOrderDialog() {
        if (menuItems.isEmpty()) {
            JOptionPane.showMessageDialog(this,"No items available");
            return;
        }
        JPanel p= new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        Map<String, JSpinner> s =new HashMap<>();

        for (int i =0; i <menuItems.size(); i++) {
            JPanel temp = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel temp2= new JLabel(String.format("%-20s $%s",menuItems.get(i),menuPrices.get(i)));
            temp2.setFont(new Font(Font.MONOSPACED,Font.PLAIN,12));
            JSpinner spinner = new JSpinner(new SpinnerNumberModel(0, 0, 100,1));
            spinner.setPreferredSize(new Dimension(60,25));
            s.put(menuItems.get(i), spinner);
            
            temp.add(temp2);
            temp.add(spinner);
            p.add(temp);
        }

        JScrollPane scrollPane= new JScrollPane(p);
        scrollPane.setPreferredSize(new Dimension(350,Math.min(400, menuItems.size()*35+50)));

        int result = JOptionPane.showConfirmDialog(this, scrollPane, 
            "Place Order", JOptionPane.OK_CANCEL_OPTION);

        if (result==JOptionPane.OK_OPTION) {
            processOrder(s);
        }
    }

    private void processOrder(Map<String, JSpinner> spinners) {
        FileManager fm = new FileManager("Menu.txt");
        FileManager fm2 = new FileManager("Orders.txt");
        FileManager fm3 = new FileManager("Data.txt");
        currentOrder.clear();
        for (String item : menuItems) {
            int quantity = (Integer) spinners.get(item).getValue();
            if (quantity > 0) {
                currentOrder.put(item, quantity);
            }
        }

        if (currentOrder.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select at least one item");
            return;
        }

        Vector<String> temp1 = fm3.read();
        Vector<String> menuContent = fm.read();
        Vector<String> orders = fm2.read();
        
        int num_order = 0;
        double num_cost =0.0;
        StringBuilder orderDetails = new StringBuilder();
        
        for (Map.Entry<String, Integer> entry : currentOrder.entrySet()) {
            String item = entry.getKey();
            int quantity = entry.getValue();
            int index = menuItems.indexOf(item);
            
            num_order += quantity;
            num_cost += Double.parseDouble(menuPrices.get(index)) * quantity;
            orderDetails.append(item).append(quantity);
        }
        double raw_mat = Double.parseDouble(temp1.get(0));
        if (num_order > raw_mat) {
            JOptionPane.showMessageDialog(this, "Not enough raw materials");
            return;
        }
        temp1.set(0, String.valueOf(raw_mat - num_order));
        fm3.save(temp1);
        double currentBudget= Double.parseDouble(menuContent.get(0));
        double newBudget =currentBudget + num_cost;
        menuContent.set(0, String.valueOf(newBudget));
        fm.save(menuContent);

        orders.add(orderDetails.toString());
        fm2.save(orders);

        JOptionPane.showMessageDialog(this, 
            "Order placed!\nTotal amount: $" + num_cost);
    }

    private void showReservationDialog() {
    	FileManager fm= new FileManager("Orders.txt");
        JPanel p =new JPanel(new GridLayout(2, 2, 5, 5));
        JTextField fn = new JTextField();
        JTextField ln = new JTextField();
        p.add(new JLabel("First Name:"));
        p.add(fn);
        p.add(new JLabel("Last Name:"));
        p.add(ln);

        int result = JOptionPane.showConfirmDialog(this,p,"Reserve Seat", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String fn1=fn.getText().trim();
            String ln1=ln.getText().trim();

            if (fn1.isEmpty() || ln1.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both first and last name");
                return;
            }

            Vector<String> orders = fm.read();
            orders.add(fn1 + " " + ln1 + " reserved a seat");
            fm.save(orders);
            JOptionPane.showMessageDialog(this, "Seat reserved!");
        }
    }
}