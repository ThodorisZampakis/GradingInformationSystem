
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class OpenTeacherHoursCalendar extends JFrame {
    private JPanel panel = new JPanel();
    private JButton mondayButton = new JButton("Monday");
    private JButton tuesdayButton = new JButton("Tuesday");
    private JButton wednesdayButton = new JButton("Wednesday");
    private JButton thursdayButton = new JButton("Thursday");
    private JButton fridayButton = new JButton("Friday");
    private JButton retuButton = new JButton("Back");
    private JLabel teacherNameLabel = new JLabel();
    private JList<JLabel> list = new JList<>();
    private int hoveredIndex = -1;
    private String teacherName; 

    public OpenTeacherHoursCalendar(ArrayList<Teacher> teachers, String teachersName) {
        this.teacherName = teachersName; 
        
        DefaultListModel<JLabel> model = new DefaultListModel<>();

        JLabel messageLabel = new JLabel("Select a day to see available hours.");
        model.addElement(messageLabel);

        ArrayList<JButton> buttons = new ArrayList<>();
        buttons.add(mondayButton);
        buttons.add(tuesdayButton);
        buttons.add(wednesdayButton);
        buttons.add(thursdayButton);
        buttons.add(fridayButton);

        for (JButton button : buttons) {
            JLabel label = new JLabel(button.getText());
            model.addElement(label);
        }

        list.setModel(model);
        list.setCellRenderer(new LabelListRenderer());
        list.setForeground(Color.BLACK);
        list.setBackground(Color.YELLOW);
        
        list.setSelectionModel(new DefaultListSelectionModel() {
            @Override
            public void setSelectionInterval(int index0, int index1) {
                if (index0 != 0 && index1 != 0) {
                    super.setSelectionInterval(index0, index1);
                }
            }
        });

        teacherNameLabel.setText(teachersName + "'s available hours depending on the day.");
        teacherNameLabel.setForeground(Color.WHITE);
        teacherNameLabel.setOpaque(true); 
        teacherNameLabel.setBackground(Color.BLACK);

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10); 
        panel.add(teacherNameLabel, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(new JScrollPane(list), gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        panel.add(retuButton, gbc);

        panel.setBackground(Color.BLACK);

        this.setContentPane(panel);
        this.setSize(400, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedIndex = list.getSelectedIndex();
                    if (selectedIndex > 0) { 
                        JLabel selectedLabel = list.getModel().getElementAt(selectedIndex);
                        dispose();
                        new AppointmentGui(teachers, teacherName, selectedLabel.getText()); 
                    }
                }
            }
        });

        retuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new FindOfficeHoursGUI(teachers);
            }
        });

        
        list.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int index = list.locationToIndex(e.getPoint());
                if (index != hoveredIndex) {
                    hoveredIndex = index;
                    list.repaint();
                }
            }
        });

        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                hoveredIndex = -1;
                list.repaint();
            }
        });
    }

    class LabelListRenderer extends JLabel implements ListCellRenderer<JLabel> {
        public LabelListRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends JLabel> list, JLabel value, int index, boolean isSelected, boolean cellHasFocus) {
            setText(value.getText());
            setIcon(value.getIcon());

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            if (index == 0) {
                setBackground(Color.WHITE);
                setFont(getFont().deriveFont(Font.PLAIN, 12f));
            } else {
                if (index == hoveredIndex) {
                    setFont(getFont().deriveFont(Font.BOLD, 16f));
                    Dimension size = getPreferredSize();
                    size.width = list.getWidth(); 
                    setPreferredSize(size);
                } else {
                    setFont(getFont().deriveFont(Font.PLAIN, 12f));
                }
            }

            return this;
        }
    }
}
