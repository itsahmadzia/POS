package BusinessLayer;

public class Logout {
    public static void logOut(User user) {
        user.setLoggedIn(false);
    }
    public static void logOutAdmin(Admin admin) {
        admin.setLoggedIn(false);
        System.out.println("Admin logged out");
    }
}