
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Lesson extends JFrame {

    // JPANEL
    private JPanel panel = new JPanel();

    // JLABELS
    private JLabel messageForUserField = new JLabel("Choose one of the courses to check your grade.");

    // JLIST
    private JList<String> lessonList = new JList<>();
    
    // BACK BUTTON
    private JButton backButton = new JButton("Back");
    
    private int studentIndex;

    public Lesson() {
       
        panel.setBackground(Color.black);

        messageForUserField.setForeground(Color.white);

        panel.add(messageForUserField);

        DefaultListModel<String> model = new DefaultListModel<>();

        model.addElement("Τεχνολογία Λογισμικού");
        model.addElement("Αλληλεπίδραση Ανθρώπου - Υπολογιστή");
        model.addElement("Ασφάλεια Πληροφοριών Και Συστημάτων");
        model.addElement("Ψηφιακή Οικονομική");
        model.addElement("Ανάλυση Αλγορίθμων");

        lessonList.setModel(model);
        HoverListCellRenderer renderer = new HoverListCellRenderer();
        lessonList.setCellRenderer(renderer);

        lessonList.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int index = lessonList.locationToIndex(e.getPoint());
                if (index != renderer.hoveredIndex) {
                    renderer.hoveredIndex = index;
                    lessonList.repaint();
                }
            }
        });
        
        lessonList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {  
                    int index = lessonList.locationToIndex(e.getPoint());
                    if (index != -1) {
                    	studentIndex = index;
                    	dispose();
                        SharedData.addLesson(lessonList.getModel().getElementAt(index));
                    	new CoursesGrade(lessonList.getModel().getElementAt(index));
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(lessonList);
        scrollPane.setPreferredSize(new Dimension(250, 150));
        panel.add(scrollPane);
        
        backButton.addActionListener(e -> {
            dispose();
            new HomePageStudentGui(lessonList.getModel().getElementAt(studentIndex));
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
                component.setBackground(Color.CYAN); 
                component.setForeground(Color.BLACK); 
            } else {
                component.setFont(defaultFont);
                if(isSelected) {
                component.setBackground( list.getSelectionBackground());
                component.setForeground( list.getSelectionForeground());
                }
                else {
                	component.setBackground(getBackground());
                	component.setForeground(getForeground());
				}
             }
            return component;
        }
    }
}