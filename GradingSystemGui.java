import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class GradingSystemGui extends JFrame{

	//Panel
	private JPanel panel = new JPanel();
	
	//JTextField
	private JLabel welcomeMessageField = new JLabel("Welcome to the university grading system.");
	
	//Buttons
	private JButton loginButton = new JButton("Login");
	private JButton registerButton = new JButton("Register");
	
	//Images
	private ImageIcon icon;
	private JLabel label;
	
	public GradingSystemGui()
	{
		//Add Image
		icon = new ImageIcon("logo.png");
		label = new JLabel(icon);
		
		//Add background color
		panel.setBackground(Color.black);
		
		//Add TextField
		panel.add(welcomeMessageField);
		Font boldFont = welcomeMessageField.getFont().deriveFont(Font.BOLD);
		welcomeMessageField.setFont(boldFont);
		welcomeMessageField.setForeground(Color.white);
		
		//Add Buttons
		panel.add(loginButton);
		panel.add(registerButton);
		
		//Add image
		panel.add(label);
		
		
		/*Add Buttons functions --> register button first and login
		button second*/
		ButtonListener listener = new ButtonListener();
		registerButton.setFocusable(false);
		registerButton.addActionListener(listener);
		
		ButtonListener loginlistener = new ButtonListener();
		loginButton.setFocusable(false);
		loginButton.addActionListener(listener);
		
		
		this.setContentPane(panel);
		
		this.setVisible(true);
		this.setSize(300,300);
		this.setTitle("Login Page");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

	//Functions for register button
	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == registerButton)
			{
				dispose();
				new RegisterGui();
			}
			else if (e.getSource() == loginButton)
			{
				dispose();
				new LoginGui();
			}
		}
		
	}

}
