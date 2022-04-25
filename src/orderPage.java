import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class orderPage extends JFrame{
	private static int WINDOW_WIDTH = 350;
    private static int WINDOW_HEIGHT = 800;
    
	private DatabaseConnection dc = new DatabaseConnection();
	
	 String status= ""; 
	 Double price=0.00;
	 String restaurant="";

	public orderPage() {
		Container mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout(10,10));
        //super(name);
        this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); 
        this.setResizable(false);
        
		JPanel confirmationPanel = new JPanel();
		confirmationPanel.setLayout(new GridLayout(10, 1, 10, 10));
		confirmationPanel.setBorder(new EmptyBorder(150, 50, 300, 50));
		orderConfirmation(1);

		
		JLabel orderLabel = new JLabel("Order Confirmation: ");	
		orderLabel.setFont(new Font("Arial", Font.BOLD, 20));
		
		JLabel idLabel = new JLabel("Order ID: ");	
		JLabel statusLabel = new JLabel("Order Status: " + status);	
		JLabel restaurantLabel = new JLabel("Restaurant: "+ restaurant);	
		JLabel priceLabel = new JLabel("Order Total: " + price);	
		mainContainer.add(confirmationPanel);
		
		confirmationPanel.add(orderLabel);
		confirmationPanel.add(idLabel);
		confirmationPanel.add(statusLabel);
		confirmationPanel.add(restaurantLabel);
		confirmationPanel.add(priceLabel);
		
	}

	public static void main(String[] args)  {
        orderPage mv = new orderPage();
        //confirmationPanel.setVisible(true);
        mv.setVisible(true);
	}
	
	public void orderConfirmation(int orderID) {
		Connection c;
	    Statement stmt;
	    Reader Dname;
	    

		String url = "jdbc:postgresql://localhost:5432/foodApp";	//****IMPORTANT: Change this*****
	    String user = "postgres";
	    String password = "password";	//password is specific to the user -- make sure to change 
	    
	     //ArrayList<String> foodItems = new ArrayList<>();
	    try {
	   	 c = DriverManager.getConnection(url, user, password);
         c.setAutoCommit(false);	
	     stmt = c.createStatement();
	     ResultSet rs = stmt.executeQuery("SELECT * FROM order_place WHERE order_id = " + orderID + ";");
	     while(rs.next()) {
        	 Dname = rs.getCharacterStream("status");
        	 price = rs.getDouble("item_price");
		    
		    //used to convert reader to string
		    StringBuilder builder = new StringBuilder();
	        int numChars;
	        char[] buffer = new char[4096];

	        while ((numChars = Dname.read(buffer)) >= 0) {
	            builder.append(buffer, 0, numChars);
	        }
	        
	        status = builder.toString();

         }
	         
	     rs = stmt.executeQuery("SELECT restaurant.name FROM order_place, restaurant, has WHERE order_place.order_id = " + orderID+ " AND order_place.order_id = has.order_id AND has.rid = restaurant.rid;");
	
         while(rs.next()) {
        	 Dname = rs.getCharacterStream("name");
        	 StringBuilder builder = new StringBuilder();
	        int numChars;
	        char[] buffer = new char[4096];

	        while ((numChars = Dname.read(buffer)) >= 0) {
	            builder.append(buffer, 0, numChars);
	        }
	        
	        restaurant = builder.toString();
        	 
         }
	      
         //System.out.println("status: "+ status + "price: "+ price + "Restaurant: " + restaurant);
         rs.close();
         stmt.close();             
         
	    }catch(Exception e) {
         System.err.println(e.getClass().getName() + ": " + e.getMessage());
         System.exit(0);
    	 
	    }
		
	}

}
