//import required classes and packages  
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;  
  
//create NewPage class to create a new page on which user will navigate  
class CustomerView extends JFrame  
{  
	private DatabaseConnection dc = new DatabaseConnection();
    //constructor  
    public JPanel customerPanel(Container mainContainer)  
    {  
    	JPanel newPanel = new JPanel();
    	newPanel.setLayout(new FlowLayout());
    	newPanel.setBorder(new EmptyBorder(20, 50, 20, 50));
    	
    	JLabel welcomeLabel = new JLabel("Welcome back to HubGrub!");
    	JLabel locationLabel = new JLabel("Please enter your city: ");
    	JTextField cityField = new JTextField("", 20);
    	JLabel restaurantLabel = new JLabel("Restaurants near you: ");
    	JButton button = new JButton("Go!");
    	
    	newPanel.add(welcomeLabel);
    	newPanel.add(locationLabel);
    	newPanel.add(cityField);
    	newPanel.add(button);
    	newPanel.add(restaurantLabel);
    	
    	button.addActionListener(new ActionListener() {
    		@Override
			public void actionPerformed(ActionEvent e) {
    			ArrayList<String> rList = dc.getRestaurants(cityField.getText());
    	    	ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
    	    	
    	    	for(String str : rList) {
//    	    		JCheckBox box = new JCheckBox(str);
//    	    		checkBoxes.add(box);
//    	    		newPanel.add(box);
    	    		JLabel label = new JLabel(str);
    	    		newPanel.add(welcomeLabel);
    	    		newPanel.revalidate();
    	    		newPanel.repaint();
    	    	}
    		}
    	});
    	
    	//mainContainer.add(newPanel, BorderLayout.NORTH);
    	
    	return newPanel;
        
    }  
    
}  
