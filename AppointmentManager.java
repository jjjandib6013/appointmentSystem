import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class AppointmentManager {
    private ArrayList<Appointment> appointments;
    private String currentUserFileName;

    public AppointmentManager(String username) {
        appointments = new ArrayList<>();
        currentUserFileName = username + "_appointments.txt";
        loadAppointments();
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public void saveAppointments() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentUserFileName))) {
            for (Appointment appointment : appointments) {
                writer.write(formatAppointment(appointment));
                writer.newLine();
            }
            JOptionPane.showMessageDialog(null, "Appointments saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error saving appointments!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadAppointments() {
        File file = new File(currentUserFileName);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Appointment appointment = parseAppointment(line);
                    if (appointment != null) {
                        appointments.add(appointment);
                    }
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error loading appointments!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private String formatAppointment(Appointment appointment) {
        return String.join("|", 
            appointment.getTitle(), 
            appointment.getName(), 
            appointment.getDate(), 
            appointment.getTime(), 
            appointment.getDescription(), 
            appointment.getLocation()
        );
    }

    private Appointment parseAppointment(String line) {
        String[] parts = line.split("\\|");
        if (parts.length == 6) {
            return new Appointment(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
        }
        return null;
    }
}
