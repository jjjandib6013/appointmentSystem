import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class AppointmentManager {
    private final String fileName = "appointments.dat";
    private ArrayList<Appointment> appointments;

    public AppointmentManager() {
        appointments = new ArrayList<>();
        loadAppointments();
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public void saveAppointments() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(appointments);
            JOptionPane.showMessageDialog(null, "Appointments saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error saving appointments!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    private void loadAppointments() {
        File file = new File(fileName);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                appointments = (ArrayList<Appointment>) ois.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Error loading appointments!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
