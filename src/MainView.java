import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class MainView extends JFrame{

	//Setting the size of the window
	private static int WINDOW_WIDTH = 350;
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
		JButton customerButton = new JButton("Customer");
		JButton deliveryButton = new JButton("Delivery");
		
		//Panels need to be contained in a container
		Container mainContainer = this.getContentPane();
		mainContainer.setLayout(new BorderLayout(10,10));
		
		//Created a panel for buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 2, 10, 10));
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.setBorder(new EmptyBorder(325, 50, 325, 50));
		
		buttonPanel.add(customerButton);
		buttonPanel.add(deliveryButton);
		
		//Create Label for MainView
		JLabel chooseLabel = new JLabel("Select User: ");
		
		JPanel labelPanel = new JPanel();
		
		mainContainer.add(buttonPanel, BorderLayout.CENTER);
	}
	
	//Runs the program with main
	public static void main(String[] args) {
		MainView mv = new MainView("HubGrub");
		mv.setVisible(true);
	}
}
