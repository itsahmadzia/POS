package BusinessLayer;
public class Logout {
    public static void logOut(User user) {
        user.setLoggedIn(false);
    }
}
