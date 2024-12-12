package GUIS;
import java.io.*;
import java.util.*;

public class Customer {
    private String firstName;
    private String lastName;
    private double MoneySpent;


    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.MoneySpent = 0.0;
    }
    
    public void placeOrder(Map<String, Integer> orderItems) {
    	FileManager fm = new FileManager("Menu.txt");
        Vector<String> menuData = fm.read();
        double b = Double.parseDouble(menuData.get(0));
        List<String> items = new ArrayList<>();
        List<Double> prices = new ArrayList<>();
        
        for (int i = 1;i< menuData.size();i++) {
            if (i % 2== 1) {
                items.add(menuData.get(i));
            } else {
                prices.add(Double.parseDouble(menuData.get(i)));
            }
        }
        FileManager fm2 = new FileManager("Data.txt");

        Vector<String> dataContent= fm2.read();
        int temp =Integer.parseInt(dataContent.get(0));
        String p =dataContent.get(1);
        String ava = dataContent.get(2);
        int total = 0;
        for (int quantity :orderItems.values()) {
        	total +=quantity;
        }
        if (temp< total) {
            throw new IllegalStateException("Not enough raw materials. Available: " + temp);
        }
        double total2= 0;
        StringBuilder orderDetails= new StringBuilder();
        
        for (Map.Entry<String, Integer> entry : orderItems.entrySet()) {
            String item =entry.getKey();
            int q= entry.getValue();
            int index= items.indexOf(item);
            
            if (index !=-1) {
            	total2 +=prices.get(index)*q;
                orderDetails.append(item).append(q);
            }
        }
        Vector<String> newMenuContent = new Vector<>();
        newMenuContent.add(String.valueOf(b + total2));
        for (int i = 1; i < menuData.size(); i++) {
            newMenuContent.add(menuData.get(i));
        }
        fm.save(newMenuContent);

        Vector<String> newDataContent =new Vector<>();
        newDataContent.add(String.valueOf(temp- total2));
        newDataContent.add(p);
        newDataContent.add(ava);
        fm2.save(newDataContent);

        FileManager fm3 = new FileManager("Orders.txt");
        
        Vector<String> orders= fm3.read();
        orders.add(orderDetails.toString());
        fm3.save(orders);

        this.MoneySpent+= total2;
    }

    public void reserveSeat() {
    	FileManager fm3 = new FileManager("Orders.txt");
        Vector<String> orders= fm3.read();
        orders.add(firstName +" " +lastName+" reserved a seat");
        fm3.save(orders);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getMoneySpent() {
        return MoneySpent;
    }
}
