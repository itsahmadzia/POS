/*
 * The Login class is currently not used in the project.
 * It was part of the authentication mechanism in the initial design
 * but has been replaced by a different approach.
 *
 */
// package BusinessLayer;
//
// import java.util.ArrayList;
//
// /**
//  * The Login class provides functionality for user authentication.
//  * It allows the authentication of users based on their credentials.
//  */
// public class Login {
//     private ArrayList<User> users;
//
//     /**
//      * Constructs a Login object with the provided list of users.
//      *
//      * @param users The list of users to be managed for authentication.
//      */
//     public Login(ArrayList<User> users) {
//         this.users = users;
//     }
//
//     /**
//      * Authenticates a user based on the provided username and password.
//      *
//      * @param username The username for authentication.
//      * @param password The password for authentication.
//      * @return true if authentication is successful, false otherwise.
//      */
//     public boolean authenticateUser(String username, String password) {
//         for (User user : users) {
//             if (user.login(username, password) && !user.isLoggedIn()) {
//                 user.setLoggedIn(true);
//                 return true;
//             }
//         }
//         return false;
//     }
// }
