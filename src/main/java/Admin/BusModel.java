package Admin;

import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BusModel {

    private final IntegerProperty busID;
    private final StringProperty from;
    private final StringProperty destination;
    private final StringProperty status;
    private final StringProperty price;
    private final StringProperty date;
    private final IntegerProperty capacity;

    public BusModel(int busID, String from, String destination,
                    String status, String price,
                    LocalDate date, int capacity) {

        this.busID = new SimpleIntegerProperty(busID);
        this.from = new SimpleStringProperty(from);
        this.destination = new SimpleStringProperty(destination);
        this.status = new SimpleStringProperty(status);
        this.price = new SimpleStringProperty(price);
        this.date = new SimpleStringProperty(date.toString());
        this.capacity = new SimpleIntegerProperty(capacity);
    }

    public IntegerProperty busIDProperty() { return busID; }
    public StringProperty fromProperty() { return from; }
    public StringProperty destinationProperty() { return destination; }
    public StringProperty statusProperty() { return status; }
    public StringProperty priceProperty() { return price; }
    public StringProperty dateProperty() { return date; }
    public IntegerProperty capacityProperty() { return capacity; }

    public int getBusID() { return busID.get(); }
    public String getFrom() { return from.get(); }
    public String getDestination() { return destination.get(); }
    public String getStatus() { return status.get(); }
    public String getPrice() { return price.get(); }
    public LocalDate getDateAsLocalDate() { return LocalDate.parse(date.get()); }
    public int getCapacity() { return capacity.get(); }

    public void setBusID(int busID) { this.busID.set(busID); }
    public void setFrom(String from) { this.from.set(from); }
    public void setDestination(String destination) { this.destination.set(destination); }
    public void setStatus(String status) { this.status.set(status); }
    public void setPrice(String price) { this.price.set(price); }
    public void setDate(LocalDate date) { this.date.set(date.toString()); }
    public void setCapacity(int capacity) { this.capacity.set(capacity); }
}
