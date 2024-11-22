import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AppointmentSystem extends JFrame {
    private AppointmentManager appointmentManager;
    private JTextArea displayArea;
    JTextField userLogin;
    JPasswordField passLogin;

    public void appointmentSystem() {
        appointmentManager = new AppointmentManager();

        setTitle("Appointment System");
        setSize(800, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 0));
        displayArea.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        displayArea.setBackground(Color.getHSBColor(199 / 360f, 43 / 100f, 94 / 100f));
        displayArea.setOpaque(true);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 0, 20));
        inputPanel.setBackground(Color.getHSBColor(57 / 360f, 62 / 100f, 99 / 100f));

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10,20,0,0));
        
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));

        JLabel dateLabel = new JLabel("Date (MM-DD-YY):");
        dateLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        dateLabel.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));

        JLabel timeLabel = new JLabel("Time (HH:MM):");
        timeLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        timeLabel.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        descriptionLabel.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));

        JLabel locationLabel = new JLabel("Location:");
        locationLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        locationLabel.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));

        JTextField titleField = new JTextField();
        titleField.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        
        JTextField nameField = new JTextField();
        nameField.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        JTextField dateField = new JTextField();
        dateField.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        JTextField timeField = new JTextField();
        timeField.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        JTextField descriptionField = new JTextField();
        descriptionField.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        JTextField locationField = new JTextField();
        locationField.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        JButton addButton = new JButton("Add Appointment");
        addButton.setFont(new Font(Font.SERIF, Font.BOLD, 20));

        JButton saveButton = new JButton("Save to File");
        saveButton.setFont(new Font(Font.SERIF, Font.BOLD, 20));

        inputPanel.add(titleLabel);
        inputPanel.add(titleField);
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(dateLabel);
        inputPanel.add(dateField);
        inputPanel.add(timeLabel);
        inputPanel.add(timeField);
        inputPanel.add(descriptionLabel);
        inputPanel.add(descriptionField);
        inputPanel.add(locationLabel);
        inputPanel.add(locationField);
        inputPanel.add(addButton);
        inputPanel.add(saveButton);

        add(inputPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> handleAddAppointment(titleField, nameField, dateField, timeField, descriptionField, locationField));
        saveButton.addActionListener(e -> appointmentManager.saveAppointments());

        displayAppointments();

        setVisible(true);
    }

    private void handleAddAppointment(JTextField titleField, JTextField nameField, JTextField dateField, JTextField timeField, JTextField descriptionField, JTextField locationField) {
        String title = titleField.getText().trim();
        String name = nameField.getText().trim();
        String date = dateField.getText().trim();
        String time = timeField.getText().trim();
        String description = descriptionField.getText().trim();
        String location = locationField.getText().trim();

        if (title.isEmpty() || name.isEmpty() || date.isEmpty() || time.isEmpty() || description.isEmpty() || location.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled out!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Appointment appointment = new Appointment(title, name, date, time, description, location);
        appointmentManager.addAppointment(appointment);
        displayAppointments();
        titleField.setText("");
        nameField.setText("");
        dateField.setText("");
        timeField.setText("");
        descriptionField.setText("");
        locationField.setText("");
    }

    private void displayAppointments() {
        displayArea.setText("");
        for (int i = 0; i < appointmentManager.getAppointments().size(); i++) {
            Appointment appt = appointmentManager.getAppointments().get(i);
            displayArea.append("Appointment " + (i+1) + "\n" + appt.toString() + "\n");
        }
    }

    public void introAppointment() {
        setTitle("Welcome to JP Appointment Management System!");
        setSize(800,800);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);

        getContentPane().setBackground(Color.getHSBColor(195 / 360f, 25 / 100f, 90 / 100f));

        JPanel introPanel = new JPanel();
        introPanel.setLayout(new GridBagLayout());
        introPanel.setBackground(Color.getHSBColor(195 / 360f, 25 / 100f, 90 / 100f));

        GridBagConstraints gbc = new GridBagConstraints();

        JLabel titleLabel = new JLabel("JP Appointment Management System");
        titleLabel.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(150,0,100,0));

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        userLogin = new JTextField(20);
        userLogin.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        passLogin = new JPasswordField(20);
        passLogin.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        loginButton.setFocusable(false);

        LoginButtonClickListener loginListener = new LoginButtonClickListener();
        
        loginButton.addActionListener(loginListener);

        userLogin.addActionListener(loginListener);
        passLogin.addActionListener(loginListener);
    
        getRootPane().setDefaultButton(loginButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        introPanel.add(titleLabel, gbc);
        
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        introPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        introPanel.add(userLogin, gbc);

        gbc.gridy = 1;  
        gbc.fill = GridBagConstraints.NONE;  
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 20, 0);
        introPanel.add(userLogin, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        introPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        introPanel.add(passLogin, gbc);

        gbc.gridy = 3;
        introPanel.add(loginButton, gbc); 

        add(introPanel, BorderLayout.NORTH);

        setVisible(true);

    }
    
    private class LoginButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String username = userLogin.getText();
            String password = new String(passLogin.getPassword());
    
            if ("joshuamanila".equals(username) && "joshuamanila".equals(password)) {
                getContentPane().removeAll();
                appointmentSystem();
                revalidate();
                repaint();
            } 
            
            else {
                JOptionPane.showMessageDialog(AppointmentSystem.this, "Invalid username or password!", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
            AppointmentSystem app = new AppointmentSystem();
            app.introAppointment();
    }

    
}


