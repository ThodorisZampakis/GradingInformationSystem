
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class CoursesGrade extends JFrame {

    private static final HashMap String = null;

	// JPANEL
    private JPanel panel = new JPanel();

    // JLABELS
    private JLabel messageForUserField = new JLabel("The grade is:");

    // JLIST
    private JList<String> coursesList1 = new JList<>();

    // BACK BUTTON
    private JButton backButton = new JButton("Back");

    private List<Integer> grades = SharedData.getGrades();
    private List<String> lesson = SharedData.getLesson();
    private List<String> lesson2 = SharedData.getLesson2();
    
    private HashMap<String,Integer> indexLessons = new HashMap<String,Integer>();

    public CoursesGrade(String thisLesson) {
        

        panel.setBackground(Color.black);

        messageForUserField.setForeground(Color.white);

        panel.add(messageForUserField);

        DefaultListModel<String> model1 = new DefaultListModel<>();

        boolean hasGrades = false;
        for (int i = 0; i < lesson.size(); i++) {
            if (i < grades.size() && lesson.get(i).equals(lesson2.get(i))) {
                model1.addElement(lesson.get(i) + ":" + grades.get(i));
                hasGrades = true;
            }
        }

        if (!hasGrades) {
            model1.addElement("No grades yet");
        }
        
        /*
		 * int i = 0; int indexLesson = 0; boolean foundLesson = false; while( i<
		 * grades.size()&&!foundLesson) { if (lesson.get(i).equals(thisLesson)) {
		 * foundLesson = true; indexLesson = i; //indexLessons.put(thisLesson,
		 * grades.get(indexLesson)); model1.addElement(lesson.get(indexLesson) + ":" +
		 * grades.get(indexLesson));
		 * 
		 * } i++; } if(!foundLesson && indexLesson == 0)
		 * model1.addElement("No grades yet.");
		 */
        	
        

        coursesList1.setModel(model1);
        HoverListCellRenderer renderer1 = new HoverListCellRenderer();
        coursesList1.setCellRenderer(renderer1);

        coursesList1.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int index = coursesList1.locationToIndex(e.getPoint());
                if (index != renderer1.hoveredIndex) {
                    renderer1.hoveredIndex = index;
                    coursesList1.repaint();
                }
            }
        });

        JScrollPane scrollPane1 = new JScrollPane(coursesList1);
        scrollPane1.setPreferredSize(new Dimension(250, 150));
        panel.add(scrollPane1);

        backButton.addActionListener(e -> {
            dispose();
            new Lesson();
        });

        panel.add(backButton);

        this.setContentPane(panel);
        this.setVisible(true);
        this.setSize(300, 250);
        this.setTitle("Courses Page");
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
                component.setBackground(Color.CYAN);
                component.setForeground(Color.BLACK);
            } else {
                component.setFont(defaultFont);
                if (isSelected) {
                    component.setBackground(list.getSelectionBackground());
                    component.setForeground(list.getSelectionForeground());
                } else {
                    component.setBackground(getBackground());
                    component.setForeground(getForeground());
                }
            }

            return component;
        }
    }
}
