package GUIS;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.util.Vector;

public class SupplierGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel Current_units;
    private JTextField price;
    private JCheckBox available;
    private String units;

    public SupplierGUI() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setTitle("Supplier");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        initializeComponents();
        loadData();
    }

    private void initializeComponents() {
        JLabel t = new JLabel("Supplier GUI");
        t.setBounds(174, 6, 133, 16);
        contentPane.add(t);

        JLabel u = new JLabel("Current Units:");
        u.setBounds(35, 58, 100, 16);
        contentPane.add(u);

        Current_units = new JLabel("0");
        Current_units.setBounds(140, 58, 100, 16);
        contentPane.add(Current_units);

        JLabel p = new JLabel("Price per Unit:");
        p.setBounds(35, 136, 100, 16);
        contentPane.add(p);

        price = new JTextField();
        price.setBounds(140, 131, 100, 26);
        contentPane.add(price);

        JLabel av = new JLabel("Available:");
        av.setBounds(263, 58, 100, 16);
        contentPane.add(av);

        available = new JCheckBox();
        available.setBounds(367, 54, 34, 23);
        contentPane.add(available);

        JButton s = new JButton("Save");
        s.setBounds(263, 131, 117, 29);
        s.addActionListener(e -> saveChanges());
        contentPane.add(s);

        JButton b = new JButton("Back");
        b.setBounds(263, 180, 117, 29);
        b.addActionListener(e -> dispose());
        contentPane.add(b);
    }

    private void loadData() {
    	FileManager fm = new FileManager("Data.txt");
        Vector<String> data = fm.read();
        
        if (data.size() >= 3) {
            units = data.get(0);
            Current_units.setText(units);
            price.setText(data.get(1));
            available.setSelected(data.get(2).equals("1"));
        } 
    }

    private void saveChanges() {
        String p = price.getText().trim();
        try {
            double priceValue = Double.parseDouble(p);
            if (priceValue <= 0) {
                JOptionPane.showMessageDialog(this,
                    "Price must be positive",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Please enter a valid price",
                "Invalid Input",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        Vector<String> temp = new Vector<>();
        temp.add(units);
        temp.add(p);
        temp.add(available.isSelected() ? "1" : "0");

        FileManager fm = new FileManager("Data.txt");
        fm.save(temp);
        JOptionPane.showMessageDialog(this, 
            "Changes successfully!",
            "Success",
            JOptionPane.INFORMATION_MESSAGE);
    }
}