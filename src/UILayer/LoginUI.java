package UILayer;
import BusinessLayer.*;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class LoginUI extends javax.swing.JFrame {
    
    public LoginUI() {
        initComponents();
    }
    Admin adminInstance; 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        passwordField = new javax.swing.JTextField();
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
        private void performLogin() {
            String enteredUsername = usernameTextField.getText();
            String enteredPassword = passwordField.getText();
            String selectedRole = (String) typeComboBox.getSelectedItem();
            
                if ("Admin".equals(selectedRole)) {
                Admin adminInstance = new Admin(1, enteredUsername, enteredPassword);
                if (adminInstance.authenticateFromDB(enteredUsername, enteredPassword)) {
                    openAdminUI();
                    }
                else {
                    JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
                }else if ("Manager".equals(selectedRole)) {
                     Manager managerInstance = new Manager(enteredUsername, enteredPassword);
                     if (managerInstance.authenticateFromDB(enteredUsername, enteredPassword)) {
                            openManagerUI();
                     }
                     else {
                    JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    }
                   
                } else if ("Sales Assistant".equals(selectedRole)) {
                    SalesAssistant salesAssistantInstance = new SalesAssistant(enteredUsername, enteredPassword);
                    if (salesAssistantInstance.authenticateFromDB(enteredUsername, enteredPassword)){
                        openSalesAssistantUI();
                    }
                    else {
                    JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
                }
    }
   
    public Admin getLoggedInAdmin() {
        return adminInstance;
    }

    private void openAdminUI() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AdminUI().setVisible(true);
            }
        });
    }
    private void openManagerUI() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new addProduct().setVisible(true);
            }
        });
    }
    private void openSalesAssistantUI() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SalesAssistantUI().setVisible(true);
            }
        });
    }

    public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginUI().setVisible(true);
            }
        });
    }                                            



    // Variables declaration - do not modify                     
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton loginButton;
    private javax.swing.JTextField passwordField;
    private javax.swing.JComboBox<String> typeComboBox;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration                   
}
