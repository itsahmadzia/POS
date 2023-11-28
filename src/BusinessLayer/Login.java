package BusinessLayer;

import java.util.ArrayList;

public class Login {
    private ArrayList<User> users;

    public Login(ArrayList<User> users) {
        this.users = users;
    }
   public boolean authenticateUser(String username, String password) {
    for (User user : users) {
        if (user.login(username, password) && !user.isLoggedIn()) {
            user.setLoggedIn(true);
            return true;
        }
    }
    return false;
}

}
