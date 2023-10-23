public class Logout {
    public static void logOut(User user) {
        user.setLoggedIn(false);
    }
    // logging out Admin
    public static void logOutAdmin(Admin admin) {
        admin.setLoggedIn(false);
        System.out.println("Admin logged out");
    }
}
