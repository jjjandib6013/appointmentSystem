import javax.swing.*;               // Importing Swing classes for GUI components
import java.awt.*;                  // Importing AWT classes for layout management and event handling
import java.awt.event.*;            // Importing classes for event listeners
import java.io.*;                   // Importing IO classes for reading and writing files
import java.util.HashMap;           // Importing HashMap for storing accounts

// OOP: The AppointmentSystem class is extending JFrame, meaning it is a GUI component.
public class AppointmentSystem extends JFrame {
    
    private AppointmentManager appointmentManager; // OOP: AppointmentManager is used to handle appointments.
    private JTextArea displayArea;  // GUI: JTextArea to display appointments.
    JTextField userLogin;          // GUI: JTextField for user to input username.
    JPasswordField passLogin;      // GUI: JPasswordField for user to input password.
    private JPanel inputPanel;     // GUI: JPanel for layout of input fields.
    private JPanel bottomPanel;    // GUI: JPanel for the bottom layout with buttons.

    private HashMap<String, String> accounts = new HashMap<>(); // OOP: HashMap to store account username and password pairs.
    private final String accountsFile = "accounts.txt"; // IO: File where account data is stored.

    // This method sets up the appointment system GUI.
    public void setupAppointmentSystem() {
        appointmentManager = new AppointmentManager(userLogin.getText().trim()); // OOP: Create an AppointmentManager instance for user.
        
        setTitle("Appointment System"); // GUI: Set window title.
        setSize(800, 800); // GUI: Set window size.
        setDefaultCloseOperation(EXIT_ON_CLOSE); // GUI: Close application on window close.
        setLayout(new BorderLayout()); // GUI: Set layout manager to BorderLayout.
        setLocationRelativeTo(null); // GUI: Center the window on the screen.

        // Creating a panel for the header (system title).
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.getHSBColor(195 / 360f, 25 / 100f, 90 / 100f)); // Set header background color.
        JLabel systemNameLabel = new JLabel("JP Appointment Management System"); // GUI: Create label with system name.
        systemNameLabel.setFont(new Font(Font.SERIF, Font.BOLD, 30)); // GUI: Set label font.
        systemNameLabel.setHorizontalAlignment(JLabel.CENTER); // GUI: Center the text.
        headerPanel.add(systemNameLabel); // GUI: Add label to header panel.
        add(headerPanel, BorderLayout.NORTH); // GUI: Add header panel to the top of the window.

        // Create and configure the text area to display appointments.
        displayArea = new JTextArea();
        displayArea.setEditable(false); // GUI: Set text area as non-editable.
        displayArea.setBorder(BorderFactory.createEmptyBorder(30, 35, 0, 0)); // GUI: Add padding around text.
        displayArea.setFont(new Font(Font.SERIF, Font.BOLD, 20)); // GUI: Set font for the text area.
        displayArea.setBackground(Color.getHSBColor(199 / 360f, 43 / 100f, 94 / 100f)); // GUI: Set background color.
        displayArea.setOpaque(true); // GUI: Ensure the background color is opaque.
        JScrollPane scrollPane = new JScrollPane(displayArea); // GUI: Add scroll functionality to the display area.
        add(scrollPane, BorderLayout.CENTER); // GUI: Add the scrollable display area to the center.

        // Create the input panel for appointment details.
        inputPanel = new JPanel(new GridLayout(8, 2, 20, 20)); // GUI: Grid layout for the input fields.
        inputPanel.setBackground(Color.getHSBColor(57 / 360f, 62 / 100f, 99 / 100f)); // Set input panel background color.

        // Create labels and text fields for appointment details (Title, Name, Date, etc.)
        JLabel titleLabel = new JLabel("Title:"); // GUI: Label for title field.
        titleLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20)); // Set label font.
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 0)); // Add padding.

        // Similar code for other labels (Name, Date, etc.)
        // Set up text fields for each label.
        
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
    
        JLabel dateLabel = new JLabel("Date (MM-DD-YY):");
        dateLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        dateLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
    
        JLabel timeLabel = new JLabel("Time (HH:MM):");
        timeLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        timeLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
    
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        descriptionLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
    
        JLabel locationLabel = new JLabel("Location:");
        locationLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        locationLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        // OOP: Encapsulation of text fields for user inputs: titleField, nameField, etc.
        JTextField titleField = new JTextField(); // Text field for appointment title.
        titleField.setFont(new Font(Font.SERIF, Font.PLAIN, 20)); // Set font for input fields.
        
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

        // Create buttons for adding, saving, logging out, etc.
        JButton addButton = new JButton("Add Appointment"); // GUI: Button to add an appointment.
        addButton.setFont(new Font(Font.SERIF, Font.BOLD, 20)); // Set font for the button.
        addButton.setFocusable(false); // Disable button focus outline.

        // Create a Save button to save appointments to file.
        JButton saveButton = new JButton("Save to File");
        saveButton.setFont(new Font(Font.SERIF, Font.BOLD, 20)); // Set button font.
        saveButton.setFocusable(false); // Disable button focus outline.

        // Add listener for logout button (action handler).
        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font(Font.SERIF, Font.BOLD, 20)); // Set button font.
        logoutButton.setFocusable(false); // Disable button focus outline.
        logoutButton.addActionListener(new LogoutButtonClickListener()); // OOP: Registering an event listener for logout.

        // Done button to return to bottom panel view.
        JButton doneButton = new JButton("Done");
        doneButton.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        doneButton.setFocusable(false); // Disable button focus outline.
        doneButton.addActionListener(new DoneButtonClickListener()); // OOP: Action listener for done button.

        // Add all components to inputPanel.
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
        inputPanel.add(logoutButton);
        inputPanel.add(doneButton);

        // Bottom panel with button to create an appointment.
        bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.getHSBColor(195 / 360f, 0.25f, 0.90f)); // Set background color.

        // Button to trigger the creation of an appointment form.
        JButton createAppointmentButton = new JButton("Create Appointment");
        createAppointmentButton.setFont(new Font(Font.SERIF, Font.BOLD, 20)); // Set button font.
        createAppointmentButton.setFocusable(false); // Disable button focus outline.
        createAppointmentButton.addActionListener(e -> { // OOP: Lambda expression for event handling.
            remove(bottomPanel); // GUI: Remove bottom panel.
            add(inputPanel, BorderLayout.SOUTH); // GUI: Add input panel.
            revalidate(); // GUI: Revalidate the layout.
            repaint(); // GUI: Repaint the window.
        });

        bottomPanel.add(createAppointmentButton); // Add button to bottom panel.
        add(bottomPanel, BorderLayout.SOUTH); // Add bottom panel to the window.

        // Event handling: Adding action listeners to buttons.
        addButton.addActionListener(e -> handleAddAppointment(titleField, nameField, dateField, timeField, descriptionField, locationField));
        saveButton.addActionListener(e -> appointmentManager.saveAppointments()); // OOP: Call method to save appointments.

        displayAppointments(); // Display the list of appointments.
    
        setVisible(true); // Make the frame visible.
    }

    // Method to handle adding an appointment (including validation).
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

        try {
            if (!date.matches("\\d{2}-\\d{2}-\\d{2}")) {
                throw new IllegalArgumentException("Date must be in the format MM-DD-YY (e.g., 12-25-24).");
            }

            String[] dateParts = date.split("-");
            int month = Integer.parseInt(dateParts[0]);
            int day = Integer.parseInt(dateParts[1]);
            int year = Integer.parseInt(dateParts[2]);

            if (month < 1 || month > 12 || day < 1 || day > 31) {
                throw new IllegalArgumentException("Month must be between 1-12 and day between 1-31.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Invalid Date", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            if (!time.matches("\\d{2}:\\d{2}")) {
                throw new IllegalArgumentException("Time must be in the format HH:MM (e.g., 08:30).");
            }

            String[] timeParts = time.split(":");
            int hours = Integer.parseInt(timeParts[0]);
            int minutes = Integer.parseInt(timeParts[1]);

            if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
                throw new IllegalArgumentException("Hours must be between 00-23 and minutes between 00-59.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Invalid Time", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Appointment appointment = new Appointment(title, name, date, time, description, location);
        appointmentManager.addAppointment(appointment);
        displayAppointments();
        titleField.setText(" ");
        nameField.setText(" ");
        dateField.setText(" ");
        timeField.setText(" ");
        descriptionField.setText(" ");
        locationField.setText(" ");
    }

    // Method to display all appointments in the display area.
    private void displayAppointments() {
        displayArea.setText(""); // Clear previous appointments.
    
        // Check if there are any appointments to display.
        if (appointmentManager.getAppointments().isEmpty()) {
            displayArea.append("No appointments yet.\n");
        } else {
            // Iterate through all appointments and display them.
            for (int i = 0; i < appointmentManager.getAppointments().size(); i++) {
                Appointment appt = appointmentManager.getAppointments().get(i); // OOP: Accessing Appointment objects.
                displayArea.append("Appointment " + (i + 1) + "\n" + appt.toString() + "\n");
            }
        }
    }

    // Method to display the login screen.
    public void introAppointment() {

        loadAccounts();

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

        JLabel createAccountLabel = new JLabel("Don't have an account?");
        createAccountLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));


        JButton createAccountButton = new JButton("Create Account");
        createAccountButton.setFocusable(false);
        createAccountButton.setFont(new Font(Font.SERIF, Font.BOLD, 20));

        LoginButtonClickListener loginListener = new LoginButtonClickListener();
        createAccountButton.addActionListener(new CreateAccountButtonListener());
        
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
        gbc.gridx = 0;
        gbc.gridwidth = 2; 
        gbc.insets = new Insets(20, 0, 0, 0); 
        introPanel.add(loginButton, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 0, 0);
        introPanel.add(createAccountLabel, gbc);

        gbc.gridy = 5; 
        gbc.insets = new Insets(20, 0, 0, 0); 
        introPanel.add(createAccountButton, gbc);

        add(introPanel, BorderLayout.NORTH);

        setVisible(true);

    }

    // Method to load accounts from file into the HashMap.
    private void loadAccounts() {
        File file = new File(accountsFile); // IO: File object to read from "accounts.txt".
        if (!file.exists()) return; // Return if file does not exist.

        // Read from the file and store accounts in HashMap.
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) { // IO: Reading file with BufferedReader.
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":"); // Split the line into username and password.
                if (parts.length == 2) {
                    accounts.put(parts[0], parts[1]); // OOP: Add username and password to HashMap.
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading accounts file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to save an account to the file.
    private void saveAccount(String username, String password) {
        accounts.put(username, password); // Add account to HashMap.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(accountsFile, true))) { // IO: Writing to file.
            writer.write(username + ":" + password); // Write username and password.
            writer.newLine(); // Add new line.
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving account to file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Listener class for login button.
    private class LoginButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String username = userLogin.getText(); // Get the username input.
            String password = new String(passLogin.getPassword()); // Get the password input.
    
            if (accounts.containsKey(username) && accounts.get(username).equals(password)) { // OOP: Check username and password in HashMap.
                JOptionPane.showMessageDialog(AppointmentSystem.this, "Login successful!", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                getContentPane().removeAll();
                appointmentManager = new AppointmentManager(username); // OOP: Initialize AppointmentManager for user.
                setupAppointmentSystem(); // Call method to set up appointment system GUI.
                revalidate();
                repaint();
            } else {
                JOptionPane.showMessageDialog(AppointmentSystem.this, "Invalid username or password!", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Listener class for creating a new account.
    private class CreateAccountButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	String username = userLogin.getText().trim();
            String password = new String(passLogin.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(AppointmentSystem.this, "Username and password cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (accounts.containsKey(username)) {
                JOptionPane.showMessageDialog(AppointmentSystem.this, "Account already exists!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                saveAccount(username, password);
                JOptionPane.showMessageDialog(AppointmentSystem.this, "Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    // Listener class for logging out.
    private class LogoutButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(AppointmentSystem.this, "Logout successful!", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
            getContentPane().removeAll();
            introAppointment(); // Show intro screen again.
            revalidate();
            repaint();
        }
    }

    // Listener class for the done button (return to bottom panel).
    private class DoneButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            getContentPane().remove(inputPanel); // Remove input panel.
            add(bottomPanel, BorderLayout.SOUTH); // Add bottom panel back.
            revalidate();
            repaint();
        }
    }

    // Main method to run the application.
    public static void main(String[] args) {
            AppointmentSystem app = new AppointmentSystem();
            app.introAppointment(); // OOP: Create an AppointmentSystem
    }
}
