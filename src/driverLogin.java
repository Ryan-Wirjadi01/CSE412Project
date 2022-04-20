import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class driverLogin {
	public JPanel driverLoginPanel() {
		JPanel loginPanel = new JPanel();
		loginPanel.setBackground(Color.WHITE);
		loginPanel.setLayout(new FlowLayout());
		loginPanel.setBorder(new EmptyBorder(150, 50, 150, 50));
		JLabel loginLabel = new JLabel("Enter Driver ID: ");
		JTextField IDField = new JTextField("", 20);
		JButton loginButton = new JButton("Login");
		
		loginPanel.add(loginLabel);
		loginPanel.add(IDField);
		loginPanel.add(loginButton);
		
		return loginPanel;
	}
}
