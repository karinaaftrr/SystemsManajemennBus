package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {

    private static final String URL = "jdbc:mysql://localhost:3306/sistembus?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = ""; // kosong kalau pakai XAMPP

    public static Connection getConnection() {
        try {
            // Wajib load driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("✅ Koneksi ke database BERHASIL");
            return conn;

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("❌ Koneksi ke database GAGAL!");
            return null;
        }
    }
}
