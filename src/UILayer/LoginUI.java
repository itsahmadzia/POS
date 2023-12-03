package UILayer;

import BusinessLayer.*;
import DBLayer.ProductDAO;

import javax.swing.*;
/**
 * The LoginUI class represents the user interface for user authentication.
 * It provides a login form with fields for username, password, and user type.
 * Users can log in as Admin, Manager, or Sales Assistant based on their selected role.
 */
public class LoginUI extends javax.swing.JFrame {
    
    /**
     * Default constructor for LoginUI.
     * Initializes the components of the UI.
     */
    public LoginUI() {
        initComponents();
        setLocationRelativeTo(null);
    }
    Admin adminInstance; 
    User userInstance;
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        typeComboBox = new javax.swing.JComboBox<>();
        loginButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 20)); // NOI18N
        jLabel1.setText("Username");

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 20)); // NOI18N
        jLabel2.setText("Password");

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 1, 20)); // NOI18N
        jLabel3.setText("Type");

        usernameTextField.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        usernameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameTextFieldActionPerformed(evt);
            }
        });

        passwordField.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N

        typeComboBox.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        typeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Manager", "Sales Assistant" }));
        typeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeComboBoxActionPerformed(evt);
            }
        });

        loginButton.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1))
                    .addComponent(jLabel3))
                .addGap(95, 95, 95)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(typeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                    .addComponent(usernameTextField)
                    .addComponent(loginButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(139, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(typeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void usernameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        // TODO add your handling code here:
    }                                                 

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
                performLogin();
    }                                           

    private void typeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {                                             

    }
    
    /**
     * Performs the login action based on the entered username, password, and selected role.
     * Opens the corresponding UI for the authenticated user.
     */
    private void performLogin() {
        String enteredUsername = usernameTextField.getText();
        char[] enteredPasswordChars = passwordField.getPassword();

        String enteredPassword = new String(enteredPasswordChars);
        if(enteredPassword.isEmpty()||enteredUsername.isEmpty()){
            showError("Please enter username or password.");
            return ;
        }
        String selectedRole = (String) typeComboBox.getSelectedItem();

        if ("Admin".equals(selectedRole)) {
    adminInstance = new Admin(enteredUsername, enteredPassword);

    if (adminInstance.authenticateFromDB(enteredUsername, enteredPassword)) {
        adminInstance.setLoggedIn(true);
        usernameTextField.setText("");
        passwordField.setText("");
        openAdminUI(adminInstance);
        this.dispose();
    } else {
        showError("Invalid credentials. Please try again.");
    }
}
 else {
            Role roleInstance = null;

            if ("Manager".equals(selectedRole)) {
                roleInstance = new Manager();
            } else if ("Sales Assistant".equals(selectedRole)) {
                roleInstance = new SalesAssistant();
            }

            if (roleInstance != null) {
                User currentUser = new User(enteredUsername, enteredPassword, roleInstance);
                boolean isAuthenticated = currentUser.authenticate(enteredUsername, enteredPassword, roleInstance.getRoleType());
                currentUser.setName(currentUser.getName(enteredUsername,roleInstance.getRoleType()) );

                if (isAuthenticated) {
                    if (roleInstance instanceof Manager) {
                         
                         usernameTextField.setText("");
                         passwordField.setText("");
                         userInstance = currentUser;
                         currentUser.setLoggedIn(isAuthenticated);
                        openManagerUI();
                        this.dispose();
                        
                    } else if (roleInstance instanceof SalesAssistant) {
                         usernameTextField.setText("");
                         passwordField.setText("");
                         //openSalesAssistantUI();
                         userInstance = currentUser;
                         currentUser.setLoggedIn(isAuthenticated);
                          //System.out.println("currentUser "+currentUser.getName()+" \n");
                        new ProductDAO().deleteExpiredProducts();
                           openSalesAssistantUI(userInstance);
                        this.dispose();
                        
                    } else {
                        showError("Unknown role: " + roleInstance.getRoleType());
                    }
                } else {
                    showError("Invalid credentials. Please try again.");
                }
            } else {
                showError("Unknown role: " + selectedRole);
            }
        }
    }
     /**
     * Displays an error message in a dialog box.
     *
     * @param message The error message to be displayed.
     */
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Login Failed", JOptionPane.ERROR_MESSAGE);

    }
    
    /**
     * Retrieves the logged-in Admin instance, if any.
     *
     * @return The logged-in Admin instance or null if no Admin is logged in.
     */
    public Admin getLoggedInAdmin() {
    return adminInstance != null && adminInstance.isLoggedIn() ? adminInstance : null;
}

    /**
     * Opens the AdminUI with the provided Admin instance.
     *
     * @param admin The logged-in Admin instance.
     */
    private void openAdminUI(Admin admin) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AdminUI(adminInstance).setVisible(true);
            }
        });
    }
    /**
     * Opens the Manager UI.
     */
    private void openManagerUI() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() { 
                new Managermenu().setVisible(true);

            }
        });
    }
     /**
     * Opens the Sales Assistant UI with the provided User instance.
     *
     * @param user The logged-in User instance.
     */
    private void openSalesAssistantUI(User user) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               // new SalesAssistantUI().setVisible(true);
                new SalesAssistantUI(userInstance).setVisible(true);
            }
        });
    }
    /**
     * The main method to launch the LoginUI.
     *
     * @param args The command-line arguments.
     */
    public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginUI loginUI = new LoginUI();
                loginUI.setLocationRelativeTo(null); 
                loginUI.setVisible(true);
            }
        });
    }                                            

    /**
     * Retrieves the User instance.
     *
     * @param user The User instance to be retrieved.
     * @return The User instance.
     */    
    public User getUserInstance(User user){
        return userInstance;
    }
    
    /**
     * Retrieves the Admin instance.
     *
     * @param admin The Admin instance to be retrieved.
     * @return The Admin instance.
     */
     public Admin getAdminInstance(Admin admin){
        return adminInstance;
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton loginButton;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JComboBox<String> typeComboBox;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration     
    
   
}
