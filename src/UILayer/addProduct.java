/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package UILayer;

import BusinessLayer.Product;
import DBLayer.CategoryDAO;
import DBLayer.ProductDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLDataException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author malik
 */
public class addProduct extends javax.swing.JFrame {

    /**
     * Creates new form addProduct
     */
    public addProduct() {



        initComponents();
        jtableproducts.setModel(defaultTableModel);
        loadProductsIntoTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    void updateCombo(){
        tfCategory.removeAllItems();
        List<String> categories = new CategoryDAO().getAllCategoriesName(); // Assuming you have a method in CategoryDAO to get category names
        for (String category : categories) {
            tfCategory.addItem(category);
        }
    }
    private void initComponents() {


        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfID = new javax.swing.JTextField();
        tfStock = new javax.swing.JTextField();
        tfQuantity = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        tfDesc = new javax.swing.JTextField();
        tfCategory = new JComboBox<>();
        jBADD = new javax.swing.JButton();
        jbUpdate = new javax.swing.JButton();
        jbDelete = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtableproducts = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        expDate = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tfPrice = new javax.swing.JTextField();
        updateCombo();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1000, 1000));

        jLabel1.setText("ID");

        jLabel2.setText("Stock");

        jLabel3.setText("Quantity");

        tfID.setColumns(5);
        tfID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfIDActionPerformed(evt);
            }
        });

        tfStock.setColumns(5);

        tfQuantity.setColumns(5);
        tfQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfQuantityActionPerformed(evt);
            }
        });

        jLabel4.setText("Name");

        jLabel5.setText("Description");

        jLabel6.setText("Category");

        jBADD.setFont(new java.awt.Font("Andale Mono", 1, 18)); // NOI18N
        jBADD.setText("ADD");
        jBADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBADDActionPerformed(evt);
            }
        });

        jbUpdate.setFont(new java.awt.Font("Andale Mono", 0, 14)); // NOI18N
        jbUpdate.setText("Update");
        jbUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbUpdateActionPerformed(evt);
            }
        });

        jbDelete.setBackground(new java.awt.Color(204, 0, 0));
        jbDelete.setFont(new java.awt.Font("Andale Mono", 1, 13)); // NOI18N
        jbDelete.setForeground(new java.awt.Color(255, 255, 255));
        jbDelete.setText("Delete");
        jbDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDeleteActionPerformed(evt);
            }
        });

        jScrollPane2.setBorder(null);
        jScrollPane2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jScrollPane2FocusGained(evt);
            }
        });

        jtableproducts.setModel(defaultTableModel=new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "ID", "Name", "Description", "Price", "Quantity/pack", "Exp", "Stock", "Category"
                }
        ) {
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jtableproducts);
        if (jtableproducts.getColumnModel().getColumnCount() > 0) {
            jtableproducts.getColumnModel().getColumn(0).setPreferredWidth(3);
            jtableproducts.getColumnModel().getColumn(3).setPreferredWidth(3);
            jtableproducts.getColumnModel().getColumn(4).setPreferredWidth(5);
            jtableproducts.getColumnModel().getColumn(6).setPreferredWidth(3);
        }

        jLabel7.setBackground(new java.awt.Color(204, 255, 204));
        jLabel7.setFont(new java.awt.Font("DejaVu Sans Mono", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 102));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Product");

        jLabel8.setText("ExpiryDate");

        jLabel9.setText("Price");

        tfPrice.setColumns(5);
        tfPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPriceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel1)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jLabel9))
                                                .addGap(49, 49, 49)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(tfPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(tfID, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                                                                .addComponent(tfStock)
                                                                .addComponent(tfQuantity)))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(68, 68, 68)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel4)
                                                                        .addComponent(jLabel5)
                                                                        .addComponent(jLabel6)
                                                                        .addComponent(jLabel8))
                                                                .addGap(45, 45, 45)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(tfCategory)
                                                                        .addComponent(tfDesc)
                                                                        .addComponent(tfName)
                                                                        .addComponent(expDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(155, 155, 155)
                                                                .addComponent(jBADD, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jbUpdate)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jbDelete)))
                                                .addGap(6, 6, 6)))
                                .addGap(33, 33, 33))
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel1)
                                                .addComponent(tfID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel4)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(tfStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel5)
                                                .addComponent(tfDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel3)
                                                .addComponent(tfQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel6)
                                        .addComponent(tfCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(expDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel8)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel9)
                                                .addComponent(tfPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jBADD)
                                        .addComponent(jbUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jbDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>

    private void tfIDActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void tfQuantityActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jScrollPane2FocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void jBADDActionPerformed(java.awt.event.ActionEvent evt) {
//add button code
//
        if(tfCategory.getItemCount()==0){
            JOptionPane.showMessageDialog(null,"No categories exists to add product Add category first","ERROR",JOptionPane.ERROR_MESSAGE);
            return;
        }

if(addDatainDB()) {
    addDatainGUI();
    clearallfields();
}


        // TODO add your handling code here:
    }

    public void addDatainGUI() {
        try {

            int id = Integer.parseInt(tfID.getText());
            String name = tfName.getText();
            String description = tfDesc.getText();
            String price = tfPrice.getText();
            String quantity = tfQuantity.getText();
            String stock = tfStock.getText();
           String category = (String) tfCategory.getSelectedItem();
            java.util.Date expirationDate = expDate.getDate();

            double parsedPrice = Double.parseDouble(price);
            int parsedQuantity = Integer.parseInt(quantity);
            int parsedStock = Integer.parseInt(stock);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedExpDate = dateFormat.format(expirationDate);
               defaultTableModel.addRow(new Object[]{id, name, description, parsedPrice, parsedQuantity, formattedExpDate, parsedStock, category});

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter valid numeric values for ID, Price, Quantity, Stock, and Category.");
        }

    }

private void clearallfields(){
    tfID.setText("");
    tfName.setText("");
    tfDesc.setText("");
    tfPrice.setText("");
    tfQuantity.setText("");
    tfStock.setText("");

    expDate.setDate(null);

}
    public void loadProductsIntoTable() {
         List<Product> productList = new ProductDAO().getAllProducts();
   defaultTableModel.setRowCount(0);
        for (Product product : productList) {
            Object[] rowData = {
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getQuantity_per_pack(),
                    product.getExp(),
                    product.getStock_quantity(),
                    new CategoryDAO().getNamebyID(product.getCategory_code())
            };
            defaultTableModel.addRow(rowData);
        }
    }

    private Boolean addDatainDB() {
        try {

            int id = Integer.parseInt(tfID.getText());
            ProductDAO d = new ProductDAO();

            String name = tfName.getText();
            if(d.productExists(id)||d.getIDbyName(name)!=-1){
throw new SQLDataException();
            }
            String description = tfDesc.getText();
            String price = tfPrice.getText();
            String quantity = tfQuantity.getText();
            String stock = tfStock.getText();
            String  categoryname = ((String) tfCategory.getSelectedItem());
            int category = new CategoryDAO().getCategoryCodebyName(categoryname);
            java.util.Date expirationDate = expDate.getDate();

            double parsedPrice = Double.parseDouble(price);
            int parsedQuantity = Integer.parseInt(quantity);
            int parsedStock = Integer.parseInt(stock);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedExpDate = dateFormat.format(expirationDate);

            if(!(new ProductDAO().productExists(id))){
                Product p = new Product();
                p.setExp(expirationDate);
                p.setId(id);
                p.setDescription(description);
                p.setName(name);
                p.setPrice(parsedPrice);
                p.setCategory_code(category);
                p.setQuantity_per_pack(parsedQuantity);
                p.setStock_quantity(parsedStock);
                new ProductDAO().insertProduct(p);

            }
            else {
                throw new SQLDataException();
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter valid numeric values for ID, Price, Quantity, Stock, and Category.");
            return false;
        }
        catch (SQLDataException e) {
            JOptionPane.showMessageDialog(null, "Product already exists ");
            return false;
        }
return true;
    }

    private void removeSelectedRow() {
        int selectedRowIndex = jtableproducts.getSelectedRow();

        if (selectedRowIndex != -1) {
            String productId = jtableproducts.getValueAt(selectedRowIndex, 0).toString();
   defaultTableModel.removeRow(selectedRowIndex);
            new ProductDAO().deleteProduct(Integer.parseInt(productId));
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to delete.");
        }
    }

    private void jbUpdateActionPerformed(java.awt.event.ActionEvent evt) {
//update button event
        if(jtableproducts.getSelectedRow()!=-1) {
            updateProduct up = new updateProduct(this);
            up.setVisible(true);

        }
        else{
            JOptionPane.showMessageDialog(null,"PLEASE SELECT A PRODUCT TO EDIT");
        }
        // TODO add your handling code here:
    }

    private void jbDeleteActionPerformed(java.awt.event.ActionEvent evt) {
///delete button event
        removeSelectedRow();
        // TODO add your handling code here:
    }

    private void tfPriceActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    /**
     * @param args the command line arguments
     *
     *
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
            java.util.logging.Logger.getLogger(addProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new addProduct().setVisible(true);
            }
        });
    }

  public String getDataatrow(){
        int selected=jtableproducts.getSelectedRow();
        StringBuilder s = new StringBuilder();
        for(int i = 0 ; i < jtableproducts.getColumnCount();i++){
            s.append(defaultTableModel.getValueAt(selected,i));
            s.append("\n");
        }

        return s.toString();
  }


    // Variables declaration - do not modify
    private com.toedter.calendar.JDateChooser expDate;
    private javax.swing.JButton jBADD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbDelete;
    private javax.swing.JButton jbUpdate;
    private javax.swing.JTable jtableproducts;
    private JComboBox<String> tfCategory;
    private javax.swing.JTextField tfDesc;
    private javax.swing.JTextField tfID;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfPrice;
    private javax.swing.JTextField tfQuantity;
    private javax.swing.JTextField tfStock;
    private DefaultTableModel defaultTableModel=new DefaultTableModel();

    // End of variables declaration


}
