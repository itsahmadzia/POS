package UILayer;

import BusinessLayer.Logout;
import BusinessLayer.User;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Manager menu class represents the  user interface for the manager's menu.
 * It provides options for managing products, categories, operators, and generating reports.
 */
public class Managermenu extends javax.swing.JFrame {

    /**
     * Default constructor.
     * Initializes the components of the UI and sets it to the maximized state.
     */
    public Managermenu() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        username = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        totalCategories = new javax.swing.JLabel();
        expireSummary = new javax.swing.JLabel();
        labelTotalproducts = new javax.swing.JLabel();
        btnProduct = new javax.swing.JButton();
        btnCategory = new javax.swing.JButton();
        btnCattree = new javax.swing.JButton();
        btnOperators = new javax.swing.JButton();
        btnReports = new javax.swing.JButton();
        btnExpiring = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();

        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        username.setFont(new java.awt.Font("Michroma", 0, 24)); // NOI18N
        username.setForeground(new java.awt.Color(255, 255, 255));
        username.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        username.setText("MED ++");

        jLabel2.setFont(new java.awt.Font("Lato Black", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Welcome Back to");

        totalCategories.setFont(new java.awt.Font("Lato Medium", 0, 18)); // NOI18N
        totalCategories.setForeground(new java.awt.Color(255, 255, 255));
        totalCategories.setText("Total 23 categories are stored");

        expireSummary.setFont(new java.awt.Font("Lato Medium", 0, 18)); // NOI18N
        expireSummary.setForeground(new java.awt.Color(255, 255, 255));
        expireSummary.setText("A total of 3 products will be expired in 63 days ");

        labelTotalproducts.setFont(new java.awt.Font("Lato Medium", 0, 18)); // NOI18N
        labelTotalproducts.setForeground(new java.awt.Color(255, 255, 255));
        labelTotalproducts.setText("Currently 49 products are in the inventory");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(expireSummary)
                                        .addComponent(labelTotalproducts)
                                        .addComponent(totalCategories))
                                .addContainerGap(278, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(username, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(145, 145, 145)
                                .addComponent(labelTotalproducts)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(totalCategories)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(expireSummary)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnProduct.setBackground(new java.awt.Color(153, 153, 153));
        btnProduct.setFont(new java.awt.Font("TlwgTypewriter", 1, 18)); // NOI18N
        btnProduct.setForeground(new java.awt.Color(255, 255, 255));
        btnProduct.setText("Manage Product");
        btnProduct.setToolTipText("Add, Update or delete products");
        btnProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductActionPerformed(evt);
            }
        });

        btnCategory.setBackground(new java.awt.Color(153, 153, 153));
        btnCategory.setFont(new java.awt.Font("TlwgTypewriter", 1, 18)); // NOI18N
        btnCategory.setForeground(new java.awt.Color(255, 255, 255));
        btnCategory.setText("Manage Category");
        btnCategory.setToolTipText("Add, Update or delete categories");
        btnCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategoryActionPerformed(evt);
            }
        });

        btnCattree.setBackground(new java.awt.Color(153, 153, 153));
        btnCattree.setFont(new java.awt.Font("TlwgTypewriter", 1, 18)); // NOI18N
        btnCattree.setForeground(new java.awt.Color(255, 255, 255));
        btnCattree.setText("Show products by Cateory");
        btnCattree.setToolTipText("Overall structure of categories");
        btnCattree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCattreeActionPerformed(evt);
            }
        });

        btnOperators.setBackground(new java.awt.Color(153, 153, 153));
        btnOperators.setFont(new java.awt.Font("TlwgTypewriter", 1, 18)); // NOI18N
        btnOperators.setForeground(new java.awt.Color(255, 255, 255));
        btnOperators.setText("Manage Operators");
        btnOperators.setToolTipText("Add or delete operators");
        btnOperators.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOperatorsActionPerformed(evt);
            }
        });

        btnReports.setBackground(new java.awt.Color(153, 153, 153));
        btnReports.setFont(new java.awt.Font("TlwgTypewriter", 1, 18)); // NOI18N
        btnReports.setForeground(new java.awt.Color(255, 255, 255));
        btnReports.setText("Generate Reports");
        btnReports.setToolTipText("Generate Inventory or sales reports");
        btnReports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportsActionPerformed(evt);
            }
        });
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              btnLogoutActionPerformed(e);

            }
        });

        btnExpiring.setBackground(new java.awt.Color(153, 153, 153));
        btnExpiring.setFont(new java.awt.Font("TlwgTypewriter", 1, 18)); // NOI18N
        btnExpiring.setForeground(new java.awt.Color(255, 255, 255));
        btnExpiring.setText("Freshness Countdown");
        btnExpiring.setToolTipText("Show products which will soon expire");
        btnExpiring.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExpiringActionPerformed(evt);
            }
        });

        btnLogout.setBackground(new java.awt.Color(153, 153, 153));
        btnLogout.setFont(new java.awt.Font("Verdana", 1, 13)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setText("Log Out");
        btnLogout.setToolTipText("Log Out ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(83, 83, 83)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btnProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnOperators, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnCattree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnExpiring, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnReports, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(61, 61, 61))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnLogout)
                                                .addContainerGap())))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(99, 99, 99)
                                .addComponent(btnProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(btnCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(btnOperators, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(btnCattree, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(btnExpiring, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(btnReports, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
    * Handles the action performed when the logout button is clicked.
    * Displays a confirmation dialog and logs the user out if confirmed.
    *
    * @param e The ActionEvent triggered by the button click.
    */
    private void btnLogoutActionPerformed(ActionEvent e) {        
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
    
    
     /**
     * Opens the Add Product UI when the "Manage Product" button is clicked.
     *
     * @param evt The ActionEvent representing the button click.
     */
    private void btnProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductActionPerformed
        // TODO add your handling code here:

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new addProduct().setVisible(true);
            }

        });
        this.dispose();
    }//GEN-LAST:event_btnProductActionPerformed
    
    /**
     * Opens the Add Category UI when the "Manage Category" button is clicked.
     *
     * @param evt The ActionEvent representing the button click.
     */
    private void btnCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoryActionPerformed
        // TODO add your handling code here:
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new addCategory().setVisible(true);
            }

        });
        this.dispose();
    }//GEN-LAST:event_btnCategoryActionPerformed
    
    /**
     * Opens the CategoryTreeFrame UI when the "Show products by Category" button is clicked.
     *
     * @param evt The ActionEvent representing the button click.
     */
    private void btnCattreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCattreeActionPerformed
        // TODO add your handling code here:


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CategoryTreeFrame().setVisible(true);
            }

        });
        this.dispose();
    }//GEN-LAST:event_btnCattreeActionPerformed
    
    /**
     * Opens the AdminUI when the "Manage Operators" button is clicked.
     *
     * @param evt The ActionEvent representing the button click.
     */
    private void btnOperatorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOperatorsActionPerformed
        // TODO add your handling code here:
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AdminUI(true).setVisible(true);
            }

        });
        this.dispose();

    }//GEN-LAST:event_btnOperatorsActionPerformed
    
    /**
     * Opens the GraphMenu UI when the "Generate Reports" button is clicked.
     *
     * @param evt The ActionEvent representing the button click.
     */
    private void btnReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportsActionPerformed
        // TODO add your handling code here:
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new graphMenu().setVisible(true);
            }

        });
        this.dispose();
    }//GEN-LAST:event_btnReportsActionPerformed
    
     /**
     * Opens the ProductReport UI when the "Freshness Countdown" button is clicked.
     *
     * @param evt The ActionEvent representing the button click.
     */
    private void btnExpiringActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExpiringActionPerformed
        // TODO add your handling code here:
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new productReport().setVisible(true);
            }

        });
        this.dispose();
    }//GEN-LAST:event_btnExpiringActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Managermenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Managermenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Managermenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Managermenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Managermenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCategory;
    private javax.swing.JButton btnCattree;
    private javax.swing.JButton btnExpiring;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnOperators;
    private javax.swing.JButton btnProduct;
    private javax.swing.JButton btnReports;
    private javax.swing.JLabel expireSummary;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelTotalproducts;
    private javax.swing.JLabel totalCategories;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
