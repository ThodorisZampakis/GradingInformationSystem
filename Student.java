import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Student extends JFrame {

    // JPANEL
    private JPanel panel = new JPanel();

    // JLABELS
    private JLabel messageForUserField = new JLabel("Choose one of the below students to put a grade.");

    // JLIST
    private JList<String> studentsList = new JList<>();
    
    // BACK BUTTON
    private JButton backButton = new JButton("Back");
   
    private int studentIndex = 0 ;
    public Student(String userId,String studentId) {
        
        // Προσθήκη χρώματος φόντου
        panel.setBackground(Color.black);

        // Προσθήκη χρώματος στο μήνυμα
        messageForUserField.setForeground(Color.white);

        // Προσθήκη μηνύματος στο panel
        panel.add(messageForUserField);
        
        

        DefaultListModel<String> model = new DefaultListModel<>();

        for (String id : CentralRegistry.getStudentsIds()) {
            if (!id.equals("secretary")) {
                model.addElement(id);
            }
        }

        studentsList.setModel(model);
        HoverListCellRenderer renderer = new HoverListCellRenderer();
        studentsList.setCellRenderer(renderer);

        studentsList.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int index = studentsList.locationToIndex(e.getPoint());
                if (index != renderer.hoveredIndex) {
                    renderer.hoveredIndex = index;
                    studentsList.repaint();
                }
            }
        });
        
        studentsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {  // διπλό κλικ για να ανοίξει το παράθυρο
                    int index = studentsList.locationToIndex(e.getPoint());
                    if(index == -1)
                    	studentIndex = 0;
                    if (index != -1) {
                        dispose();
                        new Courses(studentsList.getModel().getElementAt(index));
                        	studentIndex = index;
						
                        
                        	
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(studentsList);
        scrollPane.setPreferredSize(new Dimension(250, 150));
        panel.add(scrollPane);
        
        backButton.addActionListener(e -> {
            dispose();
             boolean getIndsideSecreary = false;
             
             if(userId.equals("secretary")) {
            	 new HomePageSecretaryGui(userId,studentsList.getModel().getElementAt(studentIndex));
            	 getIndsideSecreary = true;
             }
           
			if(studentId.equals(studentsList.getModel().getElementAt(studentIndex))&&!getIndsideSecreary)	
		      new HomePageStudentGui(studentId);
           
        });
        
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
