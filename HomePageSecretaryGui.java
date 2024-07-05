import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class HomePageSecretaryGui extends JFrame{

	//JPANEL
	private JPanel panel = new JPanel();
		
	//JLABELS
	private JLabel messageForUserField = new JLabel("Choose one of the below actions");
	
	//JBUTTONS
	private JButton gradesButton = new JButton("Grades");
	private JButton logOutButton = new JButton("Log out");
	
	public HomePageSecretaryGui(String userId,String studentId)
	{
		//Add background color
		panel.setBackground(Color.black);
			
		//Add label color
		messageForUserField.setForeground(Color.white);
			
		//Add message
		panel.add(messageForUserField);
		
		//Add buttons
		panel.add(gradesButton);
		gradesButton.setFocusable(false);
		panel.add(logOutButton);
		logOutButton.setFocusable(false);
				
		logOutButton.setBackground(Color.red);
		
		
		logOutButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new GradingSystemGui();
			}
		});
		
		
		gradesButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Student(userId,studentId);
				
			}
		});
		
		this.setContentPane(panel);
		
		this.setVisible(true);
		this.setSize(270,250);
		this.setTitle("Secretary Page");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
