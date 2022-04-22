import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class driverLogin {
	//Object Variables
	driverView dv = new driverView();
	
	public JPanel driverLoginPanel(Container mainContainer) {
		//Creating a new Panel for the login page
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(new FlowLayout());
		loginPanel.setBorder(new EmptyBorder(150, 50, 150, 50));
		
		//Creating the components for the panel
		JLabel loginLabel = new JLabel("Enter Driver ID: ");
		JTextField IDField = new JTextField("", 20);
		JButton loginButton = new JButton("Login");
		
		//Adding the components to the panel
		loginPanel.add(loginLabel);
		loginPanel.add(IDField);
		loginPanel.add(loginButton);
		
		//On login press
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//If the text field is empty
				if(IDField.getText().equals("")) {
					JOptionPane.showMessageDialog(loginPanel, "Please enter ID");
				}
				else {
					mainContainer.removeAll();
					mainContainer.add(dv.driverPanel());
					mainContainer.validate();
				}				
			}
			
		});
		
		return loginPanel;
	}
}
