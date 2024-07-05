
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AppointmentGui extends JFrame {
    private JPanel panel = new JPanel();
    private JButton backButton = new JButton("Back");
    private AppointmentManager appointmentManager = AppointmentManager.getInstance();
    private JLabel messageLabel = new JLabel("Press the hour you want to make an appointment.", SwingConstants.CENTER);

    public AppointmentGui(ArrayList<Teacher> teachers, String teachersName, String selectedDay) {
        int[][] teachersHours = {
                {4, 2, 1, 3, 0},
                {3, 5, 2, 2, 1},
                {2, 3, 0, 3, 0},
                {0, 0, 1, 1, 2},
                {3, 2, 0, 0, 0}
        };

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        int dayIndex = getDayIndex(selectedDay);
        gbc.insets = new Insets(5, 5, 5, 5);

        boolean hasAvailableHours = false; // Flag to check if there are available hours

        int[] availableHours = teachersHours[0]; // Assume we are using the first teacher's hours
        for (int hour = 8; hour < 20; hour++) {
            if (availableHours[dayIndex] > hour - 8) {
                hasAvailableHours = true; // At least one available hour found

                String hourText = hour + ":00";
                JButton hourButton = new JButton(hourText);

                // Set button color based on appointment status
                if (appointmentManager.isSlotBooked(hourText)) {
                    hourButton.setBackground(Color.ORANGE);
                }

                hourButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (appointmentManager.isSlotBooked(hourText)) {
                            int response = JOptionPane.showConfirmDialog(panel, "An appointment already exists for " + hourText + ". Do you want to cancel it?", "Appointment Exists", JOptionPane.YES_NO_OPTION);
                            if (response == JOptionPane.YES_OPTION) {
                                appointmentManager.cancelSlot(hourText);
                                hourButton.setBackground(null); // Reset to default color
                                JOptionPane.showMessageDialog(panel, "Appointment for " + hourText + " has been canceled.", "Appointment Canceled", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            appointmentManager.bookSlot(hourText);
                            hourButton.setBackground(Color.GREEN);

                            // Create a non-blocking message dialog
                            JOptionPane optionPane = new JOptionPane("Appointment successfully booked for " + hourText, JOptionPane.INFORMATION_MESSAGE);
                            JDialog dialog = optionPane.createDialog(panel, "Success");
                            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

                            // Position the dialog to the right of the main frame
                            dialog.setLocation(AppointmentGui.this.getX() + AppointmentGui.this.getWidth(), AppointmentGui.this.getY());
                            dialog.setVisible(true);

                            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                                @Override
                                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                                    hourButton.setBackground(Color.ORANGE); // Change to a different color
                                }
                            });
                        }
                    }
                });

                // Add button to panel
                gbc.gridx = hour - 8; // Position buttons horizontally
                gbc.gridy = 1; // Position buttons below the message label
                gbc.anchor = GridBagConstraints.CENTER;
                panel.add(hourButton, gbc);
            }
        }

        // Check if there are no available hours for the day
        if (!hasAvailableHours) {
            JLabel noHoursLabel = new JLabel("There are no available hours this day.", SwingConstants.CENTER);
            noHoursLabel.setBackground(Color.WHITE);
            noHoursLabel.setForeground(Color.BLACK);
            noHoursLabel.setOpaque(true);
            gbc.gridx = 0;
            gbc.gridy = 2; // Position the label below the buttons
            gbc.gridwidth = 12;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            panel.add(noHoursLabel, gbc);

            // Hide the message label
            messageLabel.setVisible(false);
        } else {
            // Show the message label
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 12;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            messageLabel.setOpaque(true);
            messageLabel.setBackground(Color.WHITE); // Set background color to white
            messageLabel.setForeground(Color.BLACK); // Set text color to black
            panel.add(messageLabel, gbc);
        }

        // Add back button
        gbc.gridx = 0;
        gbc.gridy = 3; // Position the back button below the buttons or the message label
        gbc.gridwidth = 12;
        panel.add(backButton, gbc);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new OpenTeacherHoursCalendar(teachers, teachersName);
            }
        });

        panel.setBackground(Color.YELLOW); // Change panel background color to yellow
        this.setContentPane(panel);
        this.setSize(800, 200); // Adjust the frame size as needed
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private int getDayIndex(String day) {
        switch (day) {
            case "Monday":
                return 0;
            case "Tuesday":
                return 1;
            case "Wednesday":
                return 2;
            case "Thursday":
                return 3;
            case "Friday":
                return 4;
            default:
                return -1;
        }
    }
}
