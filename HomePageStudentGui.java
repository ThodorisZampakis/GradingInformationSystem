import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class HomePageStudentGui extends JFrame{

	//JPANEL
	private JPanel panel = new JPanel();
	
	//JBUTTONS
	private JButton gradesButton = new JButton("Grades");
	private JButton evaluateProfButton = new JButton("Evaluate professor");
	private JButton addAppointmentButton = new JButton("Add Appointment");
	private JButton logOutButton = new JButton("Log out");
	
	//JLABELS
	private JLabel messageForUserField = new JLabel("Choose one of the below actions");
	
	
	public HomePageStudentGui(String userId)
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
		panel.add(evaluateProfButton);
		evaluateProfButton.setFocusable(false);
		panel.add(addAppointmentButton);
		addAppointmentButton.setFocusable(false);
		panel.add(logOutButton);
		logOutButton.setFocusable(false);

		
		logOutButton.setBackground(Color.red);
		
		
		/*
		 * ButtonListener gradesListener = new ButtonListener();
		 * gradesButton.addActionListener(gradesListener);
		 */
		 
		 
		
		 gradesButton.addActionListener(new ActionListener() {
		 
		 @Override public void actionPerformed(ActionEvent e) { 
		// TODO Auto-generated
			 dispose();
			 new Lesson();
		 	}
		  });
		 
		 logOutButton.addActionListener(new ActionListener() {
			 
			 @Override public void actionPerformed(ActionEvent e) { 
			// TODO Auto-generated
					dispose();
					new GradingSystemGui();
			 	}
			  });
		 
		 evaluateProfButton.addActionListener(new ActionListener() {
			 
			 @Override public void actionPerformed(ActionEvent e) { 
			// TODO Auto-generated
				 SwingUtilities.invokeLater(() -> new ReviewsGui());
			 	}
			  });
		 
		 addAppointmentButton.addActionListener(new ActionListener() {
			 
			 @Override public void actionPerformed(ActionEvent e) { 
			// TODO Auto-generated
				 	dispose();
					Main2.main(null);
			 	}
			  });
		 
		
			/*
			 * ButtonListener logOutListener = new ButtonListener();
			 * logOutButton.addActionListener(logOutListener);
			 * 
			 * ButtonListener evaluateProfessorListener = new ButtonListener();
			 * evaluateProfButton.addActionListener(evaluateProfessorListener);
			 * 
			 * ButtonListener addAppointmentListener = new ButtonListener();
			 * addAppointmentButton.addActionListener(addAppointmentListener);
			 */
		
		this.setContentPane(panel);
		
		this.setVisible(true);
		this.setSize(270,250);
		this.setTitle("Home Page");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/*
	 * class ButtonListener implements ActionListener{
	 * 
	 * @Override public void actionPerformed(ActionEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * 
	 * 
	 * if (e.getSource() == logOutButton) { dispose(); new GradingSystemGui(); }
	 * else if(e.getSource() == evaluateProfButton) { SwingUtilities.invokeLater(()
	 * -> new ReviewsGui()); } else if (e.getSource() == addAppointmentButton) {
	 * dispose(); Main2.main(null); } } }
	 */
}
