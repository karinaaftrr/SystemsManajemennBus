package Customers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class Booking_confirmationController {

    @FXML private Label lblBus;
    @FXML private Label lblLocation;
    @FXML private Label lblSeat;
    @FXML private Label lblGender;
    @FXML private Label lblPhone;
    @FXML private Label lblDate;

    @FXML private Label lblTicketPrice;
    @FXML private Label lblAdminFee;
    @FXML private Label lblTotal;

    @FXML private ImageView qrImage;

    // ✅ Versi baru: cuma 6 parameter
    public void setData(String bus, String location, String seat,
                        String gender, String phone, String date) {

        lblBus.setText(bus);
        lblLocation.setText(location);
        lblSeat.setText(seat);
        lblGender.setText(gender);
        lblPhone.setText(phone);
        lblDate.setText(date);

        // Default price dulu biar gak error
        lblTicketPrice.setText("Rp 50.000");
        lblAdminFee.setText("Rp 5.000");
        lblTotal.setText("Rp 55.000");
    }
}
