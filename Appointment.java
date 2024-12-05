// The Appointment class implements the AppointmentType interface
public class Appointment implements AppointmentType {

    // Instance variables to store the details of an appointment (Encapsulation)
    private String title;        // Stores the title of the appointment
    private String name;         // Stores the name associated with the appointment
    private String date;         // Stores the date of the appointment
    private String time;         // Stores the time of the appointment
    private String description;  // Stores the description of the appointment
    private String location;     // Stores the location of the appointment

    // Constructor to initialize an appointment with specific details (Encapsulation and Constructor)
    public Appointment(String title, String name, String date, String time, String description, String location) {
        this.title = title;        // Set the appointment title
        this.name = name;          // Set the name associated with the appointment
        this.date = date;          // Set the date of the appointment
        this.time = time;          // Set the time of the appointment
        this.description = description;  // Set the description of the appointment
        this.location = location; // Set the location of the appointment
    }

    // Getter method for the title of the appointment (Encapsulation)
    public String getTitle() { 
        return title; 
    }

    // Getter method for the name associated with the appointment (Encapsulation)
    public String getName() { 
        return name; 
    }

    // Getter method for the date of the appointment (Encapsulation)
    public String getDate() { 
        return date; 
    }

    // Getter method for the time of the appointment (Encapsulation)
    public String getTime() { 
        return time; 
    }

    // Getter method for the description of the appointment (Encapsulation)
    public String getDescription() { 
        return description; 
    }

    // Getter method for the location of the appointment (Encapsulation)
    public String getLocation() {
        return location; 
    }

    // Method to format the appointment details as a string (Polymorphism and Interface Implementation)
    @Override
    public String formatDetails() {
        // Return the formatted details of the appointment
        return "Title: " + title + "\n" + 
               "Name: " + name + "\n" + 
               "Date: " + date + "\n" + 
               "Time: " + time + "\n" + 
               "Description: " + description + "\n" + 
               "Location: " + location + "\n";
    }

    // Override the toString method to return the formatted appointment details (Polymorphism)
    @Override
    public String toString() {
        return formatDetails();  // Return the result of the formatDetails method
    }
}
