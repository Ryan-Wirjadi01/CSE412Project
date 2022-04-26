import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerProfile extends JDialog{
	//sql info
    Reader Cname;
    String UserID = "";
    
	String customerName;
	int customerID;
	String customerAddress;
	int orderCount;
	String orders;
	
	public JPanel CustomerProfile(){
		JPanel customerPanel = new JPanel();
		customerPanel.setLayout(new GridLayout(4, 1, 10, 10));
		customerPanel.setBorder(new EmptyBorder(150, 50, 300, 50));
		
		try {			
			customerInfo();
			orderInfo();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JLabel welcomeLabel = new JLabel("Hello " + customerName);
		JLabel IDLabel = new JLabel("Customer ID: " + customerID);
		JLabel addressLabel = new JLabel("Address: " + customerAddress);
		JLabel orderLabel = new JLabel("Current Orders: " + orderCount);
		welcomeLabel.setFont(new Font("Ariel", Font.BOLD, 20));
		
		customerPanel.add(welcomeLabel);
		customerPanel.add(IDLabel);
		customerPanel.add(addressLabel);
		customerPanel.add(orderLabel);
		return customerPanel;
	}
	
	public void customerInfo() throws IOException{
		Statement stmt = null;
		ResultSet result = null;
		
		String url = "jdbc:postgresql://localhost:5432/foodApp";	//****IMPORTANT: Change this*****
	    String user = "postgres";
	    String password = "bnwong2001";	//password is specific to the user -- make sure to change 
		String query = "SELECT * FROM customer WHERE cid = '" + UserID + "'";
		 
        try (Connection con = DriverManager.getConnection(url, user, password); PreparedStatement pst = con.prepareStatement(query)) {
        	stmt = con.createStatement();
		    result = stmt.executeQuery(query);

		    while(result.next()) {
		    	customerID = result.getInt("cid");
		    	customerAddress = result.getString("address");
		    	
			    Cname = result.getCharacterStream("name");
			    
			    //used to convert reader to string
			    StringBuilder builder = new StringBuilder();
		        int numChars;
		        char[] buffer = new char[4096];
	
		        while ((numChars = Cname.read(buffer)) >= 0) {
		            builder.append(buffer, 0, numChars);
		        }
		        
		        customerName = builder.toString();
			 
		    }
		    //System.out.println("ID: "+ driverID + "rating" + rating + "name" + driverName);

        } catch (SQLException ex) {

        	 System.out.println("SQLException: " + ex.getMessage());
        	 System.out.println("SQLState: " + ex.getSQLState());
        	 System.out.println("VendorError: " + ex.getErrorCode());
       
        }
	}
	
	public void orderInfo() {
		Statement stmt = null;
		ResultSet result = null;
		
		String url = "jdbc:postgresql://localhost:5432/foodApp";	//****IMPORTANT: Change this*****
	    String user = "postgres";
	    String password = "bnwong2001";	//password is specific to the user -- make sure to change 
	   
	    //driverLogin dl = new driverLogin();

	    String query = "SELECT order_id from delivers where did = " + UserID + ";";
		 
        try (Connection con = DriverManager.getConnection(url, user, password); PreparedStatement pst = con.prepareStatement(query)) {
        	stmt = con.createStatement();
		    result = stmt.executeQuery(query);

		    while(result.next()) {
		    	orderCount++;			    
		    }
		    System.out.println("orders: "+ orders);

        } catch (SQLException ex) {

        	 System.out.println("SQLException: " + ex.getMessage());
        	 System.out.println("SQLState: " + ex.getSQLState());
        	 System.out.println("VendorError: " + ex.getErrorCode());
       
        }
	}
	
	public void setCID(String ID) {
		UserID = ID;
	}
}