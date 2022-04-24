//import required classes and packages  
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
  
//create CreateLoginForm class to create login form  
//class extends JFrame to create a window where our component add  
//class implements ActionListener to perform an action on button click  
public class CustomerLogin extends JFrame 
{  
    //initialize button, panel, label, and text field  
    JButton b1;  
    JPanel newPanel;  
    JLabel userLabel;  
    JTextField  textField1;  
    
    CustomerView cv = new CustomerView();
    DatabaseConnection dc = new DatabaseConnection();
      
    //calling constructor  
    public JPanel customerLoginForm(Container mainContainer)  
    {     
          
        //create label for username   
        userLabel = new JLabel("Please enter User ID: ", SwingConstants.CENTER);  
        userLabel.setBorder(new EmptyBorder(50, 50, 50, 50));
          
        //create text field to get username from the user  
        textField1 = new JTextField(20);    //set length of the text  
          
          
        //create submit button  
        b1 = new JButton("Login"); //set label to button  
          
        //create panel to put form elements  
        newPanel = new JPanel(new FlowLayout());  
        newPanel.add(userLabel);    //set username label to panel  
        newPanel.add(textField1);   //set text field to panel  
        newPanel.add(b1);           //set button to panel  
          
        //set border to panel   
        add(newPanel, BorderLayout.CENTER);  
          
        //perform action on button click   
        b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String userValue = textField1.getText();        //get user entered username from the textField1   
		          
		        //check whether the credentials are authentic or not 
				if(userValue.equals("")) {
					JOptionPane.showMessageDialog(newPanel, "Please enter ID");
				}
				else if(!dc.getCustomerID().contains(textField1.getText())) {
		        	JOptionPane.showMessageDialog(newPanel, "Customer not found");
		        }
		        else{  
		        	mainContainer.removeAll();
					mainContainer.add(cv.customerPanel(mainContainer));
					mainContainer.validate();
		        }  
			}
        	
        });     //add action listener to button  
        //setTitle("CustomerLogin");         //set title to the login form  
        
        return newPanel;
    }  
}
      

