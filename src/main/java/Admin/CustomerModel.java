package Admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CustomerModel {

    private final StringProperty id;
    private final StringProperty seat;
    private final StringProperty name;
    private final StringProperty gender;
    private final StringProperty phone;
    private final StringProperty busId;
    private final StringProperty location;
    private final StringProperty date;

    public CustomerModel(String id, String seat, String name, String gender,
                         String phone, String busId, String location, String date) {

        this.id = new SimpleStringProperty(id);
        this.seat = new SimpleStringProperty(seat);
        this.name = new SimpleStringProperty(name);
        this.gender = new SimpleStringProperty(gender);
        this.phone = new SimpleStringProperty(phone);
        this.busId = new SimpleStringProperty(busId);
        this.location = new SimpleStringProperty(location);
        this.date = new SimpleStringProperty(date);
    }

    // ===== PROPERTY =====
    public StringProperty idProperty() { return id; }
    public StringProperty seatProperty() { return seat; }
    public StringProperty nameProperty() { return name; }
    public StringProperty genderProperty() { return gender; }
    public StringProperty phoneProperty() { return phone; }
    public StringProperty busIdProperty() { return busId; }
    public StringProperty locationProperty() { return location; }
    public StringProperty dateProperty() { return date; }

    // ===== GETTER =====
    public String getId() { return id.get(); }
    public String getSeat() { return seat.get(); }
    public String getName() { return name.get(); }
    public String getGender() { return gender.get(); }
    public String getPhone() { return phone.get(); }
    public String getBusId() { return busId.get(); }
    public String getLocation() { return location.get(); }
    public String getDate() { return date.get(); }
}
