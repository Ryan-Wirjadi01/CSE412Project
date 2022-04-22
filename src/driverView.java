import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class driverView {
	//Labels for information
	JLabel welcomeLabel = new JLabel("Hello ");
	JLabel IDLabel = new JLabel("Driver ID: ");
	JLabel ratingLabel = new JLabel("Your Rating: ");
	JLabel orderLabel = new JLabel("Current Orders: ");
	
	public JPanel driverPanel() {
		//Create the GUI
		JPanel driverPanel = new JPanel();
		driverPanel.setLayout(new GridLayout(4, 1, 10, 10));
		driverPanel.setBorder(new EmptyBorder(150, 50, 300, 50));
		
		welcomeLabel.setFont(new Font("Ariel", Font.BOLD, 26));
		driverPanel.add(welcomeLabel);
		driverPanel.add(IDLabel);
		driverPanel.add(ratingLabel);
		driverPanel.add(orderLabel);
		
		return driverPanel;
	}
}
