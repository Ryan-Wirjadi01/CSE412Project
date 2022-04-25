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
	private ButtonGroup checkBoxGroup;
	private boolean hasFound = false;
    private ArrayList<String> rList;
    
    public JPanel customerPanel(Container mainContainer)  
    {  
    	JPanel newPanel = new JPanel();
    	newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
    	newPanel.setBorder(new EmptyBorder(20, 20, 20, 50));
    	
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
    	
    	newPanel.add(welcomeLabel);
    	newPanel.add(emptyLabel);
    	newPanel.add(locationLabel);
    	newPanel.add(cityField);
    	newPanel.add(emptyLabel);
    	newPanel.add(button);
    	newPanel.add(emptyLabel);
    	newPanel.add(restaurantLabel);
    	
//    	ArrayList<String> loc = dc.getLocation();
//        for(String i : loc) {
//        	System.out.println(i);
//        }
    	
    	checkBoxGroup = new ButtonGroup();
    	
    	button.addActionListener(new ActionListener() {
    		@Override
			public void actionPerformed(ActionEvent e) {
    			if(cityField.getText().equals("")) {
    				JOptionPane.showMessageDialog(newPanel, "Please enter ID");
    			}
//    			else if(!dc.getLocation().contains(cityField.getText())) {
//    				JOptionPane.showMessageDialog(newPanel, "Please enter valid city");
//    				System.out.println(cityField.getText());
//    			}
    			else {
    				rList = dc.getRestaurants(cityField.getText());
        	    	//ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
        	    	for(String str : rList) {
        	    		//System.out.println(str);
        	    		JCheckBox box = new JCheckBox(str);
        	    		box.setPreferredSize(box.getPreferredSize());
//        	    		checkBoxes.add(box);
        	    		checkBoxGroup.add(box);
        	    		newPanel.add(box);
        	    		newPanel.revalidate();
        	    		newPanel.repaint();
        	    		//hasFound = true;
        	    	}
        	    	newPanel.add(findButton);
        	    	newPanel.revalidate();
    			}
    		}
    	});
    	
    	profileButton.addActionListener(new ActionListener() {
    		@Override
			public void actionPerformed(ActionEvent e) {
    			
    		}
    	});
    	
    	findButton.addActionListener(new ActionListener() {
    		@Override
			public void actionPerformed(ActionEvent e) {
    			if(checkBoxGroup.isSelected(null)) {
    				JOptionPane.showMessageDialog(newPanel, "Please select a restaurant");
    			}
    			else {
    				System.out.println(checkBoxGroup.getSelection());
    				checkBoxGroup.getSelection().addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							
						}
    					
    				});
    			}
    		}
    	});
    	
    	
    	return newPanel;
        
    }  
    
}