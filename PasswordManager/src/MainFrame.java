import java.awt.BorderLayout;
import java.util.Scanner;
import org.sqlite.SQLiteDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField userTextField;
	private JTextField passTextField;
	private String user = null;
	private String pass = null;
	
	static MainData myMain = new MainData();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane =  new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel userLabel = new JLabel("Username:");
		userLabel.setBounds(6, 24, 390, 16);
		contentPane.add(userLabel);
		
		userTextField = new JTextField();
		userTextField.setBounds(6, 45, 363, 26);
		userTextField.addActionListener(new GetUserText());
		contentPane.add(userTextField);
		userTextField.setColumns(10);
		
		
		JLabel passLabel = new JLabel("Password:");
		passLabel.setBounds(5, 109, 218, 16);
		contentPane.add(passLabel);
		
		passTextField = new JTextField();
		passTextField.setBounds(5, 130, 364, 26);
		passTextField.addActionListener(new GetPassText());
		contentPane.add(passTextField);
		passTextField.setColumns(10);
		
		
		
		JButton enterButton = new JButton("Enter");
		enterButton.setBounds(6, 218, 117, 29);
		enterButton.addActionListener(new Popup());
		contentPane.add(enterButton);
		
		
		JButton exitButton = new JButton("Exit");
		exitButton.setBounds(252, 218, 117, 29);
		exitButton.addActionListener(new CloseButton());
		contentPane.add(exitButton);
		
		
		
	}
	
	private class CloseButton implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
			//Closes Frame
		}
	}
	
	private class Popup implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Data Entered!");
			SQLiteDataSource ds = MainData.openDataBase("jdbc:sqlite:firstDB.db");
			MainData.dataEnter(ds,user,pass);
			System.out.println("Username: " + user + " Password: " + pass);
		}
	}
	
	private class GetUserText implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			user = userTextField.getText();
		}
	}
	
	private class GetPassText implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			pass = passTextField.getText();
			pass = MainData.passwordCheck(pass);
		}
	}
	
}
