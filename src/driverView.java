import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class driverView {
	//sql info
    Reader Dname;
    String UserID = "";
    
    //variables for result display
    String driverName;
    int driverID;
    int rating;
    String orders="";
	ArrayList<String> names = new ArrayList<>();

    int orderCount=1;

	public JPanel driverPanel() {
		//Create the GUI
		JPanel driverPanel = new JPanel();
		driverPanel.setLayout(new GridLayout(10, 1, 10, 10));
		driverPanel.setBorder(new EmptyBorder(150, 50, 300, 50));
		
		//String UserID = dl.getDID();	// need to pull text from driverLogin
		
		try {			
			driverInfo();
			orderInfo();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Labels for information
		JLabel welcomeLabel = new JLabel("Hello " + driverName);
		JLabel IDLabel = new JLabel("Driver ID: " + driverID);
		JLabel ratingLabel = new JLabel("Your Rating: " + rating);
		JLabel orderLabel = new JLabel("Current Orders: " );
		orderLabel.setFont(new Font("Arial", Font.BOLD, 16));
		
		
		
		welcomeLabel.setFont(new Font("Ariel", Font.BOLD, 20));
				
		driverPanel.add(welcomeLabel);
		driverPanel.add(IDLabel);
		driverPanel.add(ratingLabel);
		driverPanel.add(orderLabel);
		
		for (String ele : names) {
			JLabel orderName = new JLabel("Order " + orderCount + ": "+ ele);
			driverPanel.add(orderName);
			driverPanel.revalidate();
			orderCount++;
        }
		
		
		return driverPanel;
	}
	
	public void driverInfo() throws IOException{
		Statement stmt = null;
		ResultSet result = null;
		
		String url = "jdbc:postgresql://localhost:5432/foodApp";	//****IMPORTANT: Change this*****
	    String user = "postgres";
	    String password = "password";	//password is specific to the user -- make sure to change 
	   
	    //driverLogin dl = new driverLogin();
	    //System.out.println(getDID);
		String query = "SELECT * FROM deliveryDriver WHERE did = '" + UserID + "'";
		 
        try (Connection con = DriverManager.getConnection(url, user, password); PreparedStatement pst = con.prepareStatement(query)) {
        	stmt = con.createStatement();
		    result = stmt.executeQuery(query);

		    while(result.next()) {
		    	driverID = result.getInt("did");
		    	rating = result.getInt("rating");
		    	
			    Dname = result.getCharacterStream("name");
			    
			    //used to convert reader to string
			    StringBuilder builder = new StringBuilder();
		        int numChars;
		        char[] buffer = new char[4096];
	
		        while ((numChars = Dname.read(buffer)) >= 0) {
		            builder.append(buffer, 0, numChars);
		        }
		        
		        driverName = builder.toString();
			 
		    }
		    //System.out.println("ID: "+ driverID + "rating" + rating + "name" + driverName);

        } catch (SQLException ex) {

        	 System.out.println("SQLException: " + ex.getMessage());
        	 System.out.println("SQLState: " + ex.getSQLState());
        	 System.out.println("VendorError: " + ex.getErrorCode());
       
        }
		
	}
	//Display the names of the orders for the driver
	public void orderInfo() throws IOException{
		Statement stmt = null;
		ResultSet rs = null;
		int count = 0;
		
		String url = "jdbc:postgresql://localhost:5432/foodApp";	//****IMPORTANT: Change this*****
	    String user = "postgres";
	    String password = "password";	//password is specific to the user -- make sure to change 
	   
	    String query ="SELECT customer.name FROM delivers, deliverydriver, customer WHERE deliverydriver.did =" + UserID + "and deliverydriver.did = delivers.did AND delivers.customer_id = customer.cid;";
		 
        try (Connection con = DriverManager.getConnection(url, user, password); PreparedStatement pst = con.prepareStatement(query)) {
        	stmt = con.createStatement();
		    rs = stmt.executeQuery(query);

		    while(rs.next()) {
		    	Dname = rs.getCharacterStream("name");
			    
			    //used to convert reader to string
			    StringBuilder builder = new StringBuilder();
		        int numChars;
		        char[] buffer = new char[4096];
	
		        while ((numChars = Dname.read(buffer)) >= 0) {
		            builder.append(buffer, 0, numChars);
		        }
		        
		    	names.add(count, builder.toString());
		    	count++;
		    }
		    
        } catch (SQLException ex) {

        	 System.out.println("SQLException: " + ex.getMessage());
        	 System.out.println("SQLState: " + ex.getSQLState());
        	 System.out.println("VendorError: " + ex.getErrorCode());
       
        }
		
	}
	
	public void setDID(String ID) {
		UserID = ID;
	}
}

