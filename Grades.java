import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Grades extends JFrame {

    // JPANEL
    private JPanel panel = new JPanel();

    // JLABELS
    private JLabel messageForUserField = new JLabel("Choose the grade");

    // JLIST
    private JList<Integer> gradesList = new JList<>();
    
    //BACK BUTTON 
    private JButton backButton = new JButton("Back"); 

    private String studentId;

    public Grades(String userId,String studentId) {
        this.studentId = studentId;

        // Προσθήκη χρώματος φόντου
        panel.setBackground(Color.black);

        // Προσθήκη χρώματος στο μήνυμα
        messageForUserField.setForeground(Color.white);

        // Προσθήκη μηνύματος στο panel
        panel.add(messageForUserField);

        DefaultListModel<Integer> model = new DefaultListModel<>();
        for (int i = 0; i <= 10; i++) {
            model.addElement(i);
        }

        gradesList.setModel(model);
        HoverListCellRenderer renderer = new HoverListCellRenderer();
        gradesList.setCellRenderer(renderer);

        gradesList.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int index = gradesList.locationToIndex(e.getPoint());
                if (index != renderer.hoveredIndex) {
                    renderer.hoveredIndex = index;
                    gradesList.repaint();
                }
            }
        });

        gradesList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = gradesList.locationToIndex(e.getPoint());
                if (index != -1) {
                    int grade = gradesList.getModel().getElementAt(index);
                    SharedData.addGrade(grade);
                    JOptionPane.showMessageDialog(Grades.this, "The grade " + grade + " for student " + studentId + " was successfully recorded.");
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(gradesList);
        scrollPane.setPreferredSize(new Dimension(250, 150));
        
        backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Student(userId,studentId);
				
			}
		});
        panel.add(scrollPane);
        panel.add(backButton);

        this.setContentPane(panel);
        this.setVisible(true);
        this.setSize(300, 250);
        this.setTitle("Grades Page");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    static class HoverListCellRenderer extends DefaultListCellRenderer {
        private int hoveredIndex = -1;
        private final Font defaultFont = getFont();
        private final Font hoverFont = defaultFont.deriveFont(defaultFont.getSize() + 2f);

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (index == hoveredIndex) {
                component.setFont(hoverFont);
                component.setBackground(Color.CYAN); // Χρώμα φόντου κατά το hover
                component.setForeground(Color.BLACK); // Χρώμα κειμένου
            } else {
                component.setFont(defaultFont);
                component.setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
                component.setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
            }
            return component;
        }
    }
}

