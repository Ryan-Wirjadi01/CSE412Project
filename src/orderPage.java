import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class orderPage extends JDialog{
	private static int WINDOW_WIDTH = 350;
    private static int WINDOW_HEIGHT = 800;
	private DatabaseConnection dc = new DatabaseConnection();
	
	private String status= ""; 
	private Float price = (float)0.00;
	private String driver="";
	
	public orderPage(ArrayList<String> foodList, Float totalPrice, String restName, int oID) {
        super();
        this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        this.setLocationRelativeTo(null); 
        this.setResizable(false);
        
        price = totalPrice;
        
		JPanel confirmationPanel = new JPanel();
		confirmationPanel.setLayout(new GridLayout(10, 1, 10, 10));
		confirmationPanel.setBorder(new EmptyBorder(150, 50, 300, 50));
		
		orderConfirmation(oID);

		
		JLabel orderLabel = new JLabel("Order Confirmation: ");	
		orderLabel.setFont(new Font("Arial", Font.BOLD, 20));
		
		JLabel idLabel = new JLabel("Order ID: " + oID);	
		JLabel statusLabel = new JLabel("Order Status: " + status);	
		JLabel restaurantLabel = new JLabel("Restaurant: "+ restName);	
		JLabel priceLabel = new JLabel("Order Total: " + price);
		JLabel driverLabel = new JLabel("Delivery Driver: " + driver);

		
		//label set text 
		this.add(confirmationPanel);
		
		confirmationPanel.add(orderLabel);
		confirmationPanel.add(idLabel);
		confirmationPanel.add(statusLabel);
		confirmationPanel.add(restaurantLabel);
		confirmationPanel.add(priceLabel);
		confirmationPanel.add(driverLabel);

		Timer time = new Timer();
		time.schedule(new TimerTask(){

			@Override
			public void run() {
				if(status.contentEquals("delivered")) {
					time.cancel();
				}else if(status.contentEquals("not delivered")) {
					status = "in progress";
					statusLabel.setText("Order Status: " + status);
					dc.updateOrderStatus(oID, status);
					confirmationPanel.validate();
				}else {
					status = "delivered";
					statusLabel.setText("Order Status: " + status);
					dc.updateOrderStatus(oID, status);
					confirmationPanel.validate();
				}
			}
			
		}, 10000, 10000);
	}

	public void orderView() {
		setVisible(true);
	}
	
	public void orderConfirmation(int orderID) {
		Connection c;
	    Statement stmt;
	    Reader Dname;
	    
		String url = dc.getConnectionURL();	//****IMPORTANT: Change this*****
	    String user = dc.getUser();
	    String password = dc.getPass();	//password is specific to the user -- make sure to change 
	    
	     //ArrayList<String> foodItems = new ArrayList<>();
	    try {
	   	 c = DriverManager.getConnection(url, user, password);
         c.setAutoCommit(false);	
	     stmt = c.createStatement();
	     ResultSet rs = stmt.executeQuery("SELECT * FROM order_place WHERE order_id = " + orderID + ";");
	     while(rs.next()) {
        	 Dname = rs.getCharacterStream("status");
		    
		    //used to convert reader to string
		    StringBuilder builder = new StringBuilder();
	        int numChars;
	        char[] buffer = new char[4096];

	        while ((numChars = Dname.read(buffer)) >= 0) {
	            builder.append(buffer, 0, numChars);
	        }
	        
	        status = builder.toString();

         }
	     //get driver information
	     rs = stmt.executeQuery("SELECT name FROM deliverydriver, delivers WHERE deliverydriver.did = delivers.did AND delivers.order_id = "+ orderID +";");
	     
	     while(rs.next()) {
        	 Dname = rs.getCharacterStream("name");
		    
		    //used to convert reader to string
		    StringBuilder builder = new StringBuilder();
	        int numChars;
	        char[] buffer = new char[4096];

	        while ((numChars = Dname.read(buffer)) >= 0) {
	            builder.append(buffer, 0, numChars);
	        }
	        
	        driver = builder.toString();

         }
	     
	     rs.close();
         stmt.close(); 
         c.close();
         
	    }catch(Exception e) {
         System.err.println(e.getClass().getName() + ": " + e.getMessage());
         System.exit(0);	 
	    }	
	}
	


}