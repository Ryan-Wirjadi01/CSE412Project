import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class MainView extends JFrame{

	//Setting the size of the window
	private static int WINDOW_WIDTH = 400;
	private static int WINDOW_HEIGHT = 800;
	
	//This is the homePage
	public MainView(String name) {
		//Sets the attributes of the window
		super(name);
		this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null); 
		this.setResizable(false);
		
		//Creating buttons and setting their names
		JButton restaurantButton = new JButton("Restaurants");
		JButton deliveryButton = new JButton("Delivery Update");
		
		//Panels need to be contained in a container
		Container mainContainer = this.getContentPane();
		mainContainer.setLayout(new BorderLayout(10,10));
		
		//Created a panel for buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.setBackground(Color.GRAY);
		
		buttonPanel.add(restaurantButton);
		buttonPanel.add(deliveryButton);
		
		mainContainer.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	//Runs the program with main
	public static void main(String[] args) {
		MainView mv = new MainView("HubGrub");
		mv.setVisible(true);
	}
}
