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
    private Reader Cname;
    private String UserID = "";
    private DatabaseConnection dc = new DatabaseConnection();
    
    private String customerName;
    private int customerID;
    private String customerAddress;
    private int orderCount;
    private String orders;
	
	public CustomerProfile(String cid){
		super();
		this.setSize(350, 800);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		JPanel customerPanel = new JPanel();
		customerPanel.setLayout(new GridLayout(4, 1, 10, 10));
		customerPanel.setBorder(new EmptyBorder(150, 20, 300, 20));
		
		try {			
			UserID = cid;
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
	
		this.add(customerPanel);
	} 	
	
	public void customerInfo() throws IOException{
		Statement stmt = null;
		ResultSet result = null;
		
		String url = dc.getConnectionURL();	//****IMPORTANT: Change this*****
	    String user = dc.getUser();
	    String password = dc.getPass();	//password is specific to the user -- make sure to change 
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

        } catch (SQLException ex) {

        	 System.out.println("SQLException: " + ex.getMessage());
        	 System.out.println("SQLState: " + ex.getSQLState());
        	 System.out.println("VendorError: " + ex.getErrorCode());
       
        }
	}
	
	public void orderInfo() {
		Statement stmt = null;
		ResultSet result = null;
		
		String url = dc.getConnectionURL();	//****IMPORTANT: Change this*****
	    String user = dc.getUser();
	    String password = dc.getPass();	//password is specific to the user -- make sure to change 
	   
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
	
	public void profileView() {
		setVisible(true);
	}
}