// Import necessary classes for file handling and displaying messages
import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

// AppointmentManager class to handle appointment operations (Encapsulation, I/O operations)
public class AppointmentManager {

    // A list to store all the appointments (Encapsulation)
    private ArrayList<Appointment> appointments;

    // File name based on the username to store the appointments (Encapsulation)
    private String currentUserFileName;

    // Constructor to initialize the AppointmentManager with a given username (Encapsulation, I/O operations)
    public AppointmentManager(String username) {
        appointments = new ArrayList<>();   // Initialize the appointments list (Encapsulation)
        currentUserFileName = username + "_appointments.txt";  // Set the filename based on the username (Encapsulation)
        loadAppointments();   // Load any previously saved appointments (I/O operation)
    }

    // Getter method to retrieve all appointments (Encapsulation)
    public ArrayList<Appointment> getAppointments() {
        return appointments;   // Return the list of appointments
    }

    // Method to add a new appointment to the list (Encapsulation)
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);  // Add the provided appointment to the list
    }

    // Method to save all appointments to a file (I/O operation)
    public void saveAppointments() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentUserFileName))) {
            // Write each appointment to the file (I/O operation)
            for (Appointment appointment : appointments) {
                writer.write(formatAppointment(appointment));  // Format and write appointment details (I/O operation)
                writer.newLine();   // Add a newline after each appointment
            }
            // Display a success message if saving is successful (GUI)
            JOptionPane.showMessageDialog(null, "Appointments saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            // Display an error message if there's an issue saving the appointments (GUI)
            JOptionPane.showMessageDialog(null, "Error saving appointments!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to load appointments from a file (I/O operation)
    private void loadAppointments() {
        File file = new File(currentUserFileName);  // Create a File object using the current user file name (I/O operation)
        if (file.exists()) {  // Check if the file exists (I/O operation)
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;  // Variable to store each line read from the file (I/O operation)
                // Read the file line by line (I/O operation)
                while ((line = reader.readLine()) != null) {
                    Appointment appointment = parseAppointment(line);  // Parse each line to an Appointment object (I/O operation)
                    if (appointment != null) {
                        appointments.add(appointment);  // Add the parsed appointment to the list (Encapsulation)
                    }
                }
            } catch (IOException ex) {
                // Display an error message if there's an issue loading the appointments (GUI)
                JOptionPane.showMessageDialog(null, "Error loading appointments!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Method to format an appointment into a string to be written to the file (I/O operation)
    private String formatAppointment(Appointment appointment) {
        // Join the appointment details with a pipe "|" separator and return as a single string (I/O operation)
        return String.join("|",
                appointment.getTitle(),  // Title of the appointment (Encapsulation)
                appointment.getName(),   // Name of the person associated with the appointment (Encapsulation)
                appointment.getDate(),   // Date of the appointment (Encapsulation)
                appointment.getTime(),   // Time of the appointment (Encapsulation)
                appointment.getDescription(),  // Description of the appointment (Encapsulation)
                appointment.getLocation() + "\n"  // Location of the appointment (with newline at the end) (Encapsulation)
        );
    }

    // Method to parse a line from the file and convert it into an Appointment object (I/O operation)
    private Appointment parseAppointment(String line) {
        String[] parts = line.split("\\|");  // Split the line by the pipe "|" separator (I/O operation)
        if (parts.length == 6) {  // Ensure there are exactly 6 parts in the line (title, name, date, time, description, location)
            // Create a new Appointment object using the parsed data (Encapsulation, OOP)
            return new Appointment(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
        }
        return null;  // Return null if the line doesn't contain the correct number of parts
    }
}
