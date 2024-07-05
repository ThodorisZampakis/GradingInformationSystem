
import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReviewsGui extends JFrame {
	
	//JComboBox
    private JComboBox<String> roleComboBox;
    
    //JTextArea
    private JTextArea evaluationArea;
    
    //Buttons
    private JButton submitButton;
    private JButton backButton;
    
    //JTextArea
    private JTextArea displayArea;
    
    //ArrayLists
    private ArrayList<String> studentEvaluations = new ArrayList<>();
    private ArrayList<String> teacherEvaluations = new ArrayList<>();
    private ArrayList<String> teacherNames = new ArrayList<>();
    
    public ReviewsGui() {
        setTitle("Evaluation System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        
        // User selection panel
        JPanel selectionPanel = new JPanel();
        selectionPanel.add(new JLabel("Select your role:"));
        roleComboBox = new JComboBox<>(new String[]{"Student", "Teacher"});
        selectionPanel.add(roleComboBox);
        add(selectionPanel, BorderLayout.NORTH);
        
        // Evaluation area
        evaluationArea = new JTextArea(5, 30);
        add(new JScrollPane(evaluationArea), BorderLayout.CENTER);
        
        // Submit button
        submitButton = new JButton("Submit Evaluation");
        submitButton.addActionListener(new SubmitButtonListener());
        add(submitButton, BorderLayout.SOUTH);
        
        backButton = new JButton("Back");
        backButton.setBackground(Color.red);
		backButton.addActionListener(new BackButtonListener());
		add(backButton, BorderLayout.WEST);
        
        // Display area
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.EAST);
        
        setVisible(true);
    }
    
    private class SubmitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	
	            String role = (String) roleComboBox.getSelectedItem();
	            String evaluation = evaluationArea.getText();
	            
	            if (role.equals("Student")) {
	                studentEvaluations.add("Anonymous: " + evaluation);
	                displayArea.setText("Evaluations by Students:\n");
	                for (String eval : studentEvaluations) {
	                    displayArea.append(eval + "\n");
	                }
	            } else if (role.equals("Teacher")) {
	                String teacherName = JOptionPane.showInputDialog("Enter your name:");
	                teacherEvaluations.add(teacherName + ": " + evaluation);
	                teacherNames.add(teacherName);
	                displayArea.setText("Evaluations by Teachers:\n");
	                for (String eval : teacherEvaluations) {
	                    displayArea.append(eval + "\n");
	                }
	            }
	            evaluationArea.setText("");
        	}
            
        }
    
	private class BackButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			dispose();
		}
	    
	}
	
}

