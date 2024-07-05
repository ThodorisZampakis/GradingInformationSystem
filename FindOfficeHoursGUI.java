

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FindOfficeHoursGUI extends JFrame{
    private JPanel panel = new JPanel();
    private JTextField searchTeacher = new JTextField("Search selected teacher's available office hours");
    private JButton selectedTeacherOfficeHoursButton = new JButton("Search Available Office Hours & Days");
    private JButton backButton = new JButton("Back");
    private ImageIcon icon;
    private JLabel label;
    
    public FindOfficeHoursGUI(ArrayList<Teacher> teachers) {
    	icon = new ImageIcon("images.png");
        label = new JLabel(icon);
    	panel.add(searchTeacher);
    	panel.add(selectedTeacherOfficeHoursButton);
    	panel.setBackground(Color.black);
    	panel.add(label);
       	this.setContentPane(panel);
    	this.setSize(350,300);
    	this.setVisible(true);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    	selectedTeacherOfficeHoursButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
                //Anazhthsh onomatos ka8hghth. 
				
				String teacherName = null;
				int indexTeacher = 0;
				while(indexTeacher < teachers.size() && teacherName == null){
					if(searchTeacher.getText().equals(teachers.get(indexTeacher).getName())) {
						teacherName = teachers.get(indexTeacher).getName();
					}
					indexTeacher++;
				}
				
				//Telos anazhths ka8hghth.
				
				if(teacherName!=null) {
					dispose();
					new OpenTeacherHoursCalendar(teachers,teacherName);
				}
				else {
					JOptionPane.showMessageDialog(panel, "You have to type a teacher's name to see available days & hours");
				}
			}
		});
    backButton.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			new HomePageStudentGui(null);
		}
	});
    panel.add(backButton);
    
    
    }	
  }

