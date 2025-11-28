package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RegistDAO {

    // ================= INSERT REGISTER =================
    public static boolean insertRegist(String nama, String username, String password) {

        String sql = "INSERT INTO regist (nama, username, password) VALUES (?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (conn == null) {
                System.out.println("❌ Koneksi database null saat register!");
                return false;
            }

            stmt.setString(1, nama);
            stmt.setString(2, username);
            stmt.setString(3, password);

            int result = stmt.executeUpdate();

            return result > 0;

        } catch (Exception e) {
            System.out.println("❌ Error saat register:");
            e.printStackTrace();
            return false;
        }
    }

    // ================= CHECK LOGIN =================
    public static boolean checkLogin(String username, String password) {

        String sql = "SELECT * FROM regist WHERE username = ? AND password = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (conn == null) {
                System.out.println("❌ Koneksi database null saat login!");
                return false;
            }

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            return rs.next(); // TRUE kalau user ketemu

        } catch (Exception e) {
            System.out.println("❌ Error saat login:");
            e.printStackTrace();
            return false;
        }
    }

    // ================= GET DATA CUSTOMER LENGKAP =================
    public static ResultSet getCustomerData(String username, String password) {

        String sql = "SELECT * FROM regist WHERE username = ? AND password = ?";

        try {
            Connection conn = Koneksi.getConnection();

            if (conn == null) {
                System.out.println("❌ Koneksi null saat ambil data customer!");
                return null;
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            return stmt.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
