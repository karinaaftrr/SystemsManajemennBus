package Customers;

public class CustomerSessions {

    private static String username;
    private static String fullName;

    public static void setUser(String user, String name) {
        username = user;
        fullName = name;
    }

    public static String getUsername() {
        return username;
    }

    public static String getFullName() {
        return fullName;
    }

    public static void clear() {
        username = null;
        fullName = null;
    }
}
