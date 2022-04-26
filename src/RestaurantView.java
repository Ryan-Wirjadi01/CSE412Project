
//import required classes and packages  
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

public class RestaurantView extends JDialog{
	
	//global Variables
	private DatabaseConnection dc = new DatabaseConnection();
	private CustomerLogin cl = new CustomerLogin();
	private ArrayList<String> fList = new ArrayList<>();
	private ArrayList<String> checkOutList = new ArrayList<>();
	private ArrayList<JCheckBox> checkboxes = new ArrayList<>();
	private Float totalPrice = (float)0.00;
    private String UserID = "";

	
	public RestaurantView(String name) {
		//Set the layout of the dialog
		super();
		this.setSize(350, 800);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		//Create the panels
		JPanel titlePanel = new JPanel();
		JPanel foodPanel = new JPanel();
		foodPanel.setLayout(new BoxLayout(foodPanel, BoxLayout.Y_AXIS));
		
		//Create the components
		JLabel restLabel = new JLabel(name);
		restLabel.setFont(new Font("Arial", Font.BOLD, 26));
		JButton checkOutButton = new JButton("Check Out");
		
		//Add components to panel
		titlePanel.add(restLabel);

		//Adding food items to panel
		fList = dc.getFoodList(name); 
		for(String str : fList) {
			String price = String.valueOf(dc.getFoodPrice(name, str));
			JCheckBox box = new JCheckBox(str);
			JLabel priceLabel = new JLabel("$" + price);
			checkboxes.add(box);
			foodPanel.add(box); 
			foodPanel.add(priceLabel);
			foodPanel.revalidate(); 
		}
		
		//Add components to panel
		foodPanel.add(checkOutButton);
		
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(foodPanel, BorderLayout.CENTER);
		
		//If the checkOutButton is pressed
		checkOutButton.addActionListener(new ActionListener( ) {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(JCheckBox box : checkboxes) {
					if(box.isSelected()) {
						checkOutList.add(box.getText());
						totalPrice += dc.getFoodPrice(name, box.getText());
					}
				}
				//need orderID< time stamp, customerID
				// inserting orders 
				int cID = Integer.parseInt(UserID);
				int oID = dc.getorderID();
				dc.insertOrder("not delivered", 4, 3,oID, 4 , dc.timeStamp(),cID, 1, totalPrice);
				dispose();
				
				orderPage op = new orderPage(checkOutList, totalPrice, name, oID);
				op.orderView();
				System.out.println(totalPrice);
			}
		});
	}

	
	public void setCID(String ID) {
		UserID = ID;
	}
	
	public void foodview() {
		setVisible(true);
	}
}
