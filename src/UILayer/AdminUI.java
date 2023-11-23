package UILayer;

import BusinessLayer.Admin;
import BusinessLayer.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class AdminUI extends javax.swing.JFrame {
    Admin adminInstance;
    private DefaultTableModel tableModel;
    private List<User> displayedUsers;
    private List<Integer> displayedIndexes;


    public AdminUI() {
        initComponents();
        adminInstance = new Admin("Admin", "1234");
        tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"Name", "Username", "Role"});
        displayedUsers = new ArrayList<>();
        displayedIndexes = new ArrayList<>();
        updateTableFromDatabase();

    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        usernameTextField = new javax.swing.JTextField();
        passwordTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox = new javax.swing.JComboBox<>();
        addButton1 = new javax.swing.JButton();
        logoutButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        deleteButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel6.setText("Users");

        usernameTextField.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        usernameTextField.setToolTipText("");
        usernameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameTextFieldActionPerformed(evt);
            }
        });

        passwordTextField.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        passwordTextField.setToolTipText("");
        passwordTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordTextFieldActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel3.setText("Username: ");

        jLabel4.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel4.setText("Password: ");

        nameTextField.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        nameTextField.setToolTipText("");
        nameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextFieldActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel7.setText("Role:");

        jLabel8.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel8.setText("Name:");

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel5.setText("Add User");

        jComboBox.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        jComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Manager", "Sales Assistant" }));
        jComboBox.setToolTipText("");
        jComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxActionPerformed(evt);
            }
        });

        addButton1.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        addButton1.setText("Add");
        addButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameTextField)
                                .addGap(23, 23, 23)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(usernameTextField)
                                .addGap(186, 186, 186))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                                .addGap(699, 699, 699)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(192, 192, 192))))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(700, Short.MAX_VALUE)
                    .addComponent(addButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel8))
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(104, Short.MAX_VALUE)
                    .addComponent(addButton1)
                    .addGap(5, 5, 5)))
        );

        logoutButton1.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        logoutButton1.setText("Logout");
        
        logoutButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Name", "Username", "Password"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        deleteButton.setBackground(new java.awt.Color(255, 0, 51));
        deleteButton.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logoutButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(625, 625, 625)
                                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13))
                            .addComponent(jSeparator2))))
                .addGap(60, 60, 60))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(logoutButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>    
    
    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = jTable1.getSelectedRow();
    if (selectedRow != -1) {
        User selectedUser = displayedUsers.get(selectedRow);
        String roleType = (String) jTable1.getValueAt(selectedRow, 2);

        displayedUsers.remove(selectedRow);
        tableModel.removeRow(selectedRow);

        adminInstance.deleteUser(selectedUser.getUsername(), roleType);
    } else {
        JOptionPane.showMessageDialog(this, "Please select a row to delete");
    }

}
    
    
    private void usernameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        // TODO add your handling code here:
    }                                                 

    private void passwordTextFieldActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        // TODO add your handling code here:
    }                                                 

    private void jComboBoxActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    }                                         

    private void nameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    private void logoutButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                              
        int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Confirmation", JOptionPane.YES_NO_OPTION);
        
        if (dialogResult == JOptionPane.YES_OPTION) {
            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            this.dispose();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new LoginUI().setVisible(true);
                }
            });

        }
    }                                      

    private void addButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                           
       try{
           String username = usernameTextField.getText();
           String name = nameTextField.getText();
           String password = passwordTextField.getText();
           String userType = jComboBox.getSelectedItem().toString();
           if(username.isEmpty()||name.isEmpty()||password.isEmpty()){
               JOptionPane.showMessageDialog(null, "Please enter all values","ERROR",JOptionPane.ERROR_MESSAGE);

               return ;

           }

           adminInstance.addUser(name, username, password, userType);
           updateTableFromDatabase();

           clearFields();
           jComboBox.setSelectedIndex(0);
       }
       catch (SQLIntegrityConstraintViolationException e){
           JOptionPane.showMessageDialog(null, "Duplicate entry found","ERROR",JOptionPane.ERROR_MESSAGE);
            clearFields();
       }

       
    }
    void clearFields(){
        usernameTextField.setText("");
        nameTextField.setText("");
        passwordTextField.setText("");
    }
   
    public void updateTableFromDatabase() {

        List<User> users = adminInstance.getUserManagersFromDB();
        List<User> users2 = adminInstance.getUserSalesAssistantFromDB();

        jTable1.setModel(tableModel);
        tableModel.setRowCount(0);

        displayedUsers.clear(); 
        displayedIndexes.clear();

    for (int i = 0; i < users.size(); i++) {
        User user = users.get(i);
        //System.out.println("Adding user to table: " + user.getName());
        displayedUsers.add(user);
        displayedIndexes.add(i);

        tableModel.addRow(new Object[]{user.getName(), user.getUsername(), user.getRole().getRoleType()});
    }

    for (int i = 0; i < users2.size(); i++) {
        User user = users2.get(i);
        //System.out.println("Adding user to table: " + user.getName());
        displayedUsers.add(user);
        displayedIndexes.add(i);

        tableModel.addRow(new Object[]{user.getName(), user.getUsername(), user.getRole().getRoleType()});
    }


    tableModel.fireTableDataChanged();
}

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AdminUI loginUI = new AdminUI();
                loginUI.setVisible(true); 
            }
        });
    }


    // Variables declaration - do not modify                     
    private javax.swing.JButton addButton1;
    private javax.swing.JButton deleteButton;
    private javax.swing.JComboBox<String> jComboBox;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton logoutButton1;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JTextField passwordTextField;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration                   
}
