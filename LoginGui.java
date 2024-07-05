import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LoginGui extends JFrame{
	
	//JPanel
	private JPanel panel= new JPanel();
	
	//JLabel
	private JLabel messageForUserField = new JLabel("Login to your academic account");
	private JLabel idLabel = new JLabel("Enter your id");
	private JLabel passwordLabel = new JLabel("Enter your password");
	
	//JTextFields
	private JTextField idField = new JTextField("");
	private JPasswordField passwordField = new JPasswordField("");
	
	//JButton
	private JButton enterButton = new JButton("Enter");
	private JButton backButton = new JButton("Back");
	
	
	//Integers
	private int finalp = 0;
	private int finaln = 0;
	private int indexn=0;
	private int indexp=0;
	

	
	public LoginGui()
	{
		
		//Add background color
		panel.setBackground(Color.black);
		
		//Add label color
		messageForUserField.setForeground(Color.white);
		
		//Add message
		panel.add(messageForUserField);
		
		//AddLabels
		idLabel.setForeground(Color.blue);
		passwordLabel.setForeground(Color.blue);
		panel.add(idLabel);
		panel.add(passwordLabel);
		
		//Add Text fields
		panel.add(idField);
		idField.setPreferredSize(new Dimension(100,25));
		panel.add(passwordField);
		passwordField.setPreferredSize(new Dimension(100,25));
				
		//Add button
		panel.add(enterButton);
		enterButton.setFocusable(false);
		panel.add(backButton);
		backButton.setFocusable(false);
		

		//Adding bold font in messageForUserField
		Font boldFont = messageForUserField.getFont().deriveFont(Font.BOLD);
		messageForUserField.setFont(boldFont);
				
		ButtonListener loginListener = new ButtonListener();
		enterButton.addActionListener(loginListener);
		
		ButtonListener backListener = new ButtonListener();
		backButton.addActionListener(backListener);
		
		this.setContentPane(panel);
		
		this.setVisible(true);
		this.setSize(270,250);
		this.setTitle("Login Page");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

	
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			if (e.getSource() == enterButton)
			{
				String userId = idField.getText();
				indexn = 0;
				for (String id: CentralRegistry.getStudentsIds())
				{
					indexn++;
					if (userId.equals(id.toString()))
					{
						finaln = indexn;
						char userPwd[] = passwordField.getPassword();
						String passwordString = new String(userPwd);
						indexp = 0;
						for (String p: CentralRegistry.getStudentsPwds())
						{
							indexp++;
							if (passwordString.equals(p.toString()))
							{
								finalp = indexp;
							}
						}
					}
				}
				if ( finalp == finaln )
				{
					
					if (finalp==0 || finaln==0)
					{
						JOptionPane.showMessageDialog(panel, "Invalid details,please try again");
					}
					else
					{
						JOptionPane.showMessageDialog(panel, "Welcome," + userId + " !");
						dispose();
						if (userId.equals("secretary"))
							new HomePageSecretaryGui(userId,null);
						else
							new HomePageStudentGui(userId);
					}

				}
				else
				{
					JOptionPane.showMessageDialog(panel, "Invalid details,please try again");
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
