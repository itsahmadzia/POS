package UILayer;

import BusinessLayer.Category;
import DBLayer.CategoryDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLDataException;
import java.util.List;
import java.util.Objects;
/**
 * Represents the user interface for adding, updating, and deleting categories.
 */
public class addCategory extends javax.swing.JFrame {

    /**
     * Creates new form addCategory.
     */
    public addCategory() {

        initComponents();

        List<String> allcats= new CategoryDAO().getAllCategoriesName();
        for(String p:allcats){
            comboCategories.addItem(p);
        }
        loadCategoriesIntoTable();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents(){

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfID = new javax.swing.JTextField();
        tfCatName = new javax.swing.JTextField();
        comboCategories = new javax.swing.JComboBox<>();
        btnBack = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtableCategories = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        tfDescription = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setText("ID");

        jLabel2.setText("Name");

        jLabel3.setText("Parent Category:");

        tfID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfIDActionPerformed(evt);
            }
        });

        comboCategories.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Null>" }));

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnUpdate.setForeground(new java.awt.Color(153, 102, 0));
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setForeground(new java.awt.Color(204, 0, 0));
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnAdd.setForeground(new java.awt.Color(0, 153, 0));
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jtableCategories.setModel(defaultTableModel=new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "ID", "Name", "Description", "Parent Name"
                }
        ) {
            boolean[] canEdit = new boolean [] {
                    false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jtableCategories);

        jLabel4.setText("Description");

        jLabel7.setBackground(new java.awt.Color(204, 255, 204));
        jLabel7.setFont(new java.awt.Font("DejaVu Sans Mono", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 102));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Add Category");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel1)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(tfID)
                                                                .addGap(66, 66, 66)
                                                                .addComponent(jLabel2)
                                                                .addGap(32, 32, 32))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel3)
                                                                        .addComponent(comboCategories, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(36, 36, 36)))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(123, 123, 123)
                                                                .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addComponent(tfCatName)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addGap(89, 89, 89)
                                                .addComponent(tfDescription))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnBack)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(27, 27, 27))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(btnBack)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(tfID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2)
                                        .addComponent(tfCatName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(120, 120, 120)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(btnUpdate)
                                                        .addComponent(btnDelete)
                                                        .addComponent(btnAdd)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(29, 29, 29)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel4)
                                                        .addComponent(tfDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(47, 47, 47)
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(comboCategories, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 225, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29))
        );
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        pack();
    }// </editor-fold>

    /**
     * Action performed when the ID text field is activated.
     * @param evt The ActionEvent triggered.
     */
    private void tfIDActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

    }
    
   /**
     * Action performed when the "Back" button is clicked.
     * @param evt The ActionEvent triggered.
     */
    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
                 SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Managermenu().setVisible(true);
            }

        });
        this.dispose();
    }
    
    /**
     * Retrieves data from the selected row in the table.
     * @return A string representation of the data in the selected row.
     */
    public String getDataatrow(){
        int selected=jtableCategories.getSelectedRow();

        StringBuilder s = new StringBuilder();
        for(int i = 0 ; i < jtableCategories.getColumnCount();i++){
            s.append(defaultTableModel.getValueAt(selected,i));
            s.append("\n");
        }

        return s.toString();
    }
    
    /**
     * Action performed when the "Add" button is clicked.
     * @param evt The ActionEvent triggered.
     */
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:


        String name = null;
        try {
            Boolean isroot=false;
            int id = Integer.parseInt(tfID.getText());
            name = tfCatName.getText();
            if (new CategoryDAO().categoryExists(id) || new CategoryDAO().categoryNameExists(name)) {
                throw new SQLDataException();
            }
            String description = tfDescription.getText();
            if (Objects.equals(comboCategories.getSelectedItem(), name)) {

                throw new SQLDataException();
            }
            Category a = new Category();
            a.setName(name);
            a.setCode(id);
            a.setDescription(description);
            if(comboCategories.getSelectedItem().equals("<Null>")){
                isroot=true;
            }
            else{
                a.setParentCategoryName((String) comboCategories.getSelectedItem());
            }
            if(isroot){
                new CategoryDAO().insertParentCategory(a.getCode(),a.getName(),a.getDescription());
            }
            else{
                new CategoryDAO().insertCategory(a.getCode(),a.getName(),new CategoryDAO().getCategoryCodebyName(a.getParentCategoryName()),a.getDescription());
        }

            updatecomboBox();
            loadCategoriesIntoTable();
            clearfields();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "PLEASE ENTER A NUMBER IN ID", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (SQLDataException r) {
            JOptionPane.showMessageDialog(null, "CATEGORY ALREADY EXISTS", "ERROR", JOptionPane.ERROR_MESSAGE);
        }


    }

   /**
     * Action performed when the "Update" button is clicked.
     * @param evt The ActionEvent triggered.
     */
    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {
    //Update btn here

       if(jtableCategories.getSelectedRow()!=-1){
           String []s=getDataatrow().split("\n");

     if(s.length<=4) {
         updateCategory ui = new updateCategory(getDataatrow(),this);
         ui.setVisible(true);
     }

       }
       else{
           JOptionPane.showMessageDialog(null,"Please select a row to edit","ERROR",JOptionPane.ERROR_MESSAGE);
       }
    }

    /**
     * Action performed when the "Delete" button is clicked.
     * @param evt The ActionEvent triggered.
     */
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int selectedRowIndex = jtableCategories.getSelectedRow();

        if (selectedRowIndex != -1) {
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this category?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (response == JOptionPane.YES_OPTION) {
                String productId = jtableCategories.getValueAt(selectedRowIndex, 0).toString();
                defaultTableModel.removeRow(selectedRowIndex);
                new CategoryDAO().deleteCategory(Integer.parseInt(productId));
                loadCategoriesIntoTable();
                updatecomboBox();
                clearfields();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to delete.");
        }
    }
   
    /**
     * Clears the text fields.
     */
    void clearfields(){
        tfCatName.setText("");
        tfID.setText("");
        tfDescription.setText("");
    }
    
    /**
     * Loads categories into the table.
     */
    public void loadCategoriesIntoTable() {
       List<Category> catlist = new CategoryDAO().getAllCategories();


        defaultTableModel.setRowCount(0);

       for (Category p : catlist) {
            Object[] rowData = {
                    p.getCode(),
                    p.getName(),
                    p.getDescription(),
                  p.getParentCategoryName()
            };
            defaultTableModel.addRow(rowData);
        }
    }
    
     /**
     * Updates the comboCategories dropdown with the latest category names.
     */
    void updatecomboBox(){
        List<String> allcat= new CategoryDAO().getAllCategoriesName();
       String f= comboCategories.getItemAt(0);
        comboCategories.removeAllItems();;
        comboCategories.addItem(f);
        for(String t : allcat){
            comboCategories.addItem(t);
        }
    }

    /**
     * The main method to run the application.
     * @param args The command line arguments.
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(addCategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addCategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addCategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addCategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new addCategory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> comboCategories;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jtableCategories;
    private javax.swing.JTextField tfCatName;
    private javax.swing.JTextField tfDescription;
    private javax.swing.JTextField tfID;
    DefaultTableModel defaultTableModel=new DefaultTableModel();
    // End of variables declaration
}
