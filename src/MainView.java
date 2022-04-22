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
	
	//Object Variables
	driverLogin dl = new driverLogin();
	
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
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.setBorder(new EmptyBorder(300, 50, 300, 50));
		
		buttonPanel.add(customerButton);
		buttonPanel.add(deliveryButton);
		
		//Create Label for MainView
		JLabel chooseLabel = new JLabel("Select User: ");
		
		JPanel labelPanel = new JPanel();
//		labelPanel.setBorder(new EmptyBorder(75, 50, 200, 50));
		labelPanel.add(chooseLabel);
		
		//Add panels to container
		mainContainer.add(labelPanel, BorderLayout.NORTH);
		mainContainer.add(buttonPanel, BorderLayout.CENTER);
		
		//On Delivery button press
		deliveryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainContainer.removeAll();
				mainContainer.add(dl.driverLoginPanel(mainContainer));
				validate();				
			}
			
		});
		
		//On Customer button Press
		customerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			
			}
			
		});
	}
	
	//Runs the program with main
	public static void main(String[] args) {
		MainView mv = new MainView("HubGrub");
		mv.setVisible(true);
	}
}
