//import required classes and packages  
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Enumeration;  
  
//create NewPage class to create a new page on which user will navigate  
class CustomerView  
{  
	//global variables
	private DatabaseConnection dc = new DatabaseConnection();
	private boolean hasFound = false;
    private ArrayList<String> rList;
    private String UserID = "";
    
    public JPanel customerPanel(Container mainContainer)  
    {  
    	//Create the panel and set the layout
    	JPanel newPanel = new JPanel();
    	newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
    	newPanel.setBorder(new EmptyBorder(20, 20, 20, 50));
    	
    	//Create the components
    	JLabel welcomeLabel = new JLabel("Welcome back to HubGrub!");
    	welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
    	JLabel locationLabel = new JLabel("Please enter your city: ");
    	JTextField cityField = new JTextField("", 20);
    	cityField.setMaximumSize(cityField.getPreferredSize());
    	JLabel restaurantLabel = new JLabel("Restaurants near you: ");
    	JLabel emptyLabel = new JLabel(" ");
    	JButton button = new JButton("Go!");
    	JButton profileButton = new JButton("Profile");
    	JButton findButton = new JButton("Go!");
    	JTextField answerField = new JTextField();
    	
    	//Add the components to the panel
    	newPanel.add(welcomeLabel);
    	newPanel.add(profileButton);
    	newPanel.add(emptyLabel);
    	newPanel.add(locationLabel);
    	newPanel.add(cityField);
    	newPanel.add(emptyLabel);
    	newPanel.add(button);
    	newPanel.add(emptyLabel);
    	newPanel.add(restaurantLabel);
    	
    	//Button for searching for restaurants
    	button.addActionListener(new ActionListener() {
    		@Override
			public void actionPerformed(ActionEvent e) {
    			//If the field is empty
    			if(cityField.getText().equals("")) {
    				JOptionPane.showMessageDialog(newPanel, "Please enter a city");
    			}
//    			else if(!dc.getLocation().contains(cityField.getText())) {
//    				JOptionPane.showMessageDialog(newPanel, "Please enter valid city");
//    				System.out.println(cityField.getText());
//    			}
    			else {
    				//hasFound = true;
//    				if(hasFound) {
//    					Enumeration elements = checkBoxGroup.getElements();
//        				while(elements.hasMoreElements()) {
//        					JCheckBox box = (JCheckBox)elements.nextElement();
//        					newPanel.remove(box);
//        					checkBoxGroup.remove(box);
//        					newPanel.revalidate();
//        				}
//        			}
    				//Adding the restaurants to the panel
    				rList = dc.getRestaurants(cityField.getText());
        	    	for(String str : rList) {
        	    		JLabel restLabel = new JLabel(str);
        	    		newPanel.add(restLabel);
        	    		newPanel.revalidate();
        	    	}
        	    	newPanel.add(new JLabel("Please select a restaurant:"));
        	    	answerField.setMaximumSize(cityField.getPreferredSize());
        	    	newPanel.add(answerField);
        	    	newPanel.add(findButton);
        	    	newPanel.revalidate();
        	    	newPanel.repaint();
    			}
    		}
    	});
    	
    	//If the profileButton is pressed
    	profileButton.addActionListener(new ActionListener() {
    		@Override
			public void actionPerformed(ActionEvent e) {
    			CustomerProfile cp = new CustomerProfile(UserID); 
    			cp.profileView();
    		}
    	});
    	
    	//If the findButton is pressed
    	findButton.addActionListener(new ActionListener() {
    		@Override
			public void actionPerformed(ActionEvent e) {
    			//If field is empty
    			if(answerField.getText().equals("")) {
    				JOptionPane.showMessageDialog(newPanel, "Please select a restaurant");
    			}
//    			else if(!dc.getRestaurants(cityField.getText()).contains(answerField.getText())) {
//    				JOptionPane.showMessageDialog(newPanel, "Please enter valid restaurant");
//    				System.out.println(rList.get(4));
//    				System.out.println(answerField.getText().compareTo(rList.get(4)));
//    			}
    			else {
    				RestaurantView rv = new RestaurantView(answerField.getText());
    				rv.setCID(UserID);
    				rv.foodview();
    			}
    		}
    	});

    	return newPanel;
        
    }  
    
	public void setCID(String ID) {
		UserID = ID;
	}
    
}