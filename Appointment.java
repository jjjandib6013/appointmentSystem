import java.io.Serializable;

public class Appointment implements Serializable {
    private String title;
    private String name;
    private String date;
    private String time;
    private String description;
    private String location;

    public Appointment(String title, String name, String date, String time, String description, String location) {
        this.title = title;
        this.name = name;
        this.date = date;
        this.time = time;
        this.description = description;
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nName: " + name + "\nDate: " + date + "\nTime: " + time + "\nDescription: " + description + "\nLocation: " + location + "\n";
    }
}
