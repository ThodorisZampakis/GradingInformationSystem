import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;


public class RegisterGui extends JFrame{
	
	//Panel
	private JPanel panel= new JPanel();
	
	//JLabel
	private JLabel messageForUserField = new JLabel("Create account in the Grading System");
	
	//JTextFields
	private JTextField NameField = new JTextField("Enter your name");
	private JTextField lastNameField = new JTextField("Enter your last name");
	private JTextField idField = new JTextField("Enter your id");
	private JTextField passwordField = new JTextField("Enter your password");
	private JTextField repeatPasswordField = new JTextField("Re-enter your password");
	
	//JButton
	private JButton enterButton = new JButton("Register");
	private JButton backButton = new JButton("Back");
	
	//ArrayLists
	private ArrayList<String> names = new ArrayList<>();
	private ArrayList<String> lastNames = new ArrayList<>();
	private ArrayList<String> passwords = new ArrayList<>();
	private ArrayList<String> ids = new ArrayList<>();


	public RegisterGui()
	{
		//Add background color
		panel.setBackground(Color.black);
		
		//Add label color
		messageForUserField.setForeground(Color.white);
		
		//Add message
		panel.add(messageForUserField);
		
		//Add Text fields
		panel.add(NameField);
		panel.add(lastNameField);
		panel.add(passwordField);
		panel.add(repeatPasswordField);
		panel.add(idField);
		
		//Add button
		panel.add(enterButton);
		enterButton.setFocusable(false);
		panel.add(backButton);
		backButton.setFocusable(false);
	

		//Adding bold font in messageForUserField
		Font boldFont = messageForUserField.getFont().deriveFont(Font.BOLD);
		messageForUserField.setFont(boldFont);
		
		ButtonListener registerListener = new ButtonListener();
		enterButton.addActionListener(registerListener);
		
		ButtonListener backListener = new ButtonListener();
		backButton.addActionListener(backListener);
		
		this.setContentPane(panel);
		
		this.setVisible(true);
		this.setSize(300,250);
		this.setTitle("Register Page");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			if (e.getSource() == enterButton)
			{
				/*Elegxei an o kwdikos tou xristi einai o idios me ton
				deutero kwdiko tou xristi k antisoixa emfanizei minima*/
				if (!(passwordField.getText().equals(repeatPasswordField.getText())))
				{
					JOptionPane.showMessageDialog(panel, " Password have to be the same in all fields");
				}
				else
				{
						
						String name = NameField.getText();
						String lastName = lastNameField.getText();
						String password = passwordField.getText();
						String id = idField.getText();
					
					
						//Adding students to the system by putting their details in ArrayLists
						names.add(name);
						lastNames.add(lastName);
						passwords.add(password);
						ids.add(id);
						
						
						JOptionPane.showMessageDialog(panel, "Registration was sucessfully completed!");
						CentralRegistry.showStudents(ids);
						CentralRegistry.showStudentsPwds(passwords);
						new LoginGui();
						dispose();

				}
			}
			else if (e.getSource() == backButton)
			{
				dispose();
				new GradingSystemGui();
			}
		}
		
	}
}
