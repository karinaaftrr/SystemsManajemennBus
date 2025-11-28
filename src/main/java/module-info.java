module com.mycompany.systemmanagemenbus {
    // JavaFX
    requires javafx.controls;
    requires javafx.fxml;

    // Ikonli
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;

    // JDBC (Wajib untuk MySQL)
    requires java.sql;

    // OPTIONAL, biasanya tidak wajib
    requires java.base;

    // FXML membutuhkan akses ke package yg memuat controller
    opens com.mycompany.systemmanagemenbus to javafx.fxml;
    opens Customers to javafx.fxml;
    opens Admin to javafx.fxml;
    
    exports com.mycompany.systemmanagemenbus;
}
