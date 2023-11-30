package BusinessLayer;

/**
 * The Logout class provides methods for logging out users and administrator.
 */
public class Logout {
/**
     * Logs out a user by setting their logged-in status to false.
     *
     * @param user The user to be logged out.
     */
    public static void logOut(User user) {
        user.setLoggedIn(false);
    }
    /**
     * Logs out an administrator by setting their logged-in status to false.
     *
     * @param admin The administrator to be logged out.
     */
    public static void logOutAdmin(Admin admin) {
        admin.setLoggedIn(false);
    }
}
