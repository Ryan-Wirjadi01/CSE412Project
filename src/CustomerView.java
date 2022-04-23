//import required classes and packages  
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;  
  
//create NewPage class to create a new page on which user will navigate  
class CustomerView extends JFrame  
{  
    //constructor  
    public void customerPanel(Container mainContainer)  
    {  
    	JPanel newPanel = new JPanel();
    	newPanel.setLayout(new GridLayout(3, 1, 10, 5));
    	newPanel.setBorder(new EmptyBorder(20, 50, 20, 50));
    	
    	JLabel welcomeLabel = new JLabel("Welcome back to HubGrub!");
    	JLabel locationLabel = new JLabel("Please enter your zip code: ");
    	JLabel restaurantLabel = new JLabel("Restaurants near you: ");
    	
    	newPanel.add(welcomeLabel);
    	newPanel.add(locationLabel);
    	newPanel.add(restaurantLabel);
    	
    	JScrollPane scrollPane = new JScrollPane(new JPanel());
    	//scrollPane.setLayout(new FlowLayout());
    	
    	mainContainer.add(newPanel, BorderLayout.NORTH);
    	mainContainer.add(scrollPane, BorderLayout.CENTER);
        
    }  
    
}  
