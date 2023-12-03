package UILayer;

import BusinessLayer.Product;
import DBLayer.CategoryDAO;
import DBLayer.ProductDAO;

import javax.swing.*;
import java.sql.SQLDataException;
import java.util.List;

/**
 * The updateProduct class represents a user interface for updating product information.
 */
public class updateProduct extends javax.swing.JFrame {

    /**
     * Creates a new instance of the updateProduct class.
     */
    private int id;

    public updateProduct() {
        initComponents();
    }
    
    /**
     * Updates the category combo box with available categories.
     */
    void updateCombo(){
        combCat.removeAllItems();
        List<String> categories = new CategoryDAO().getAllCategoriesName(); // Assuming you have a method in CategoryDAO to get category names
        for (String category : categories) {
            combCat.addItem(category);
        }
    }
    addProduct previous_screen;
    
    /**
     * Creates a new instance of the `updateProduct` class with a reference to the previous screen.
     *
     * @param p The reference to the previous screen.
     */
    public updateProduct(addProduct p){
        initComponents();
        updateCombo();
        previous_screen =p;
        String selectedrow = p.getDataatrow();
        String []sp=selectedrow.split("\n");
        for(String s : sp){
            System.out.println(s);
        }
        int idx =0 ;
        for(int L=0;L< combCat.getItemCount();L++){
            if(combCat.getItemAt(L).equals(sp[7])){
                idx=L;
                break ;

            }
        }
        combCat.setSelectedIndex(idx);

        id= Integer.parseInt(sp[0]);
            String name = sp[1];
            String description = sp[2];
            String price = sp[3];
            String quantity = sp[4];

        String stock = sp[6];


          double parsedPrice = Double.parseDouble(price);
            int parsedQuantity = Integer.parseInt(quantity);
            int parsedStock = Integer.parseInt(stock);
        tfName.setText(name);
        tfDesc.setText(description);
        tfPrice.setText(price);
        tfQuantity.setText(quantity);
        tfStock.setText(stock);







    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        tfName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfStock = new javax.swing.JTextField();
        tfDesc = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfPrice = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfQuantity = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        combCat = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jLabel6.setText("Category");

        tfStock.setColumns(5);

        jLabel5.setText("Description");

        tfPrice.setColumns(5);
        tfPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPriceActionPerformed(evt);
            }
        });

        jLabel4.setText("Name");

        tfQuantity.setColumns(5);
        tfQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfQuantityActionPerformed(evt);
            }
        });

        jLabel2.setText("Stock");

        jLabel3.setText("Quantity");

        jLabel9.setText("Price");

        btnUpdate.setForeground(new java.awt.Color(0, 153, 0));
        btnUpdate.setText("Save");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnCancel.setForeground(new java.awt.Color(204, 0, 0));
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        combCat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<null>" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(97, 97, 97)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel5)
                                                        .addComponent(jLabel4)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel6)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jLabel9))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(84, 84, 84)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tfDesc)
                                        .addComponent(tfName)
                                        .addComponent(tfStock)
                                        .addComponent(tfQuantity)
                                        .addComponent(tfPrice)
                                        .addComponent(combCat, javax.swing.GroupLayout.Alignment.TRAILING, 0, 96, Short.MAX_VALUE)
                                        .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(157, 157, 157))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(tfDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(combCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(tfStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tfQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tfPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel9))
                                .addGap(57, 57, 57)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnUpdate)
                                        .addComponent(btnCancel))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    private void tfPriceActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void tfQuantityActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
    
     /**
     * Handles the update button action performed event.
     *
     * @param evt The action event.
     */
    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        try {
            ProductDAO productDAO = new ProductDAO();
            List <Product> all = productDAO.getAllProducts();

            Product n=productDAO.getProductByID(id);
                if(n==null){
                    return ;
                }

            n.setCategory_code(new CategoryDAO().getCategoryCodebyName((String) combCat.getSelectedItem()));
            n.setName(tfName.getText());
            n.setDescription(tfDesc.getText());
            n.setQuantity_per_pack(Integer.parseInt(tfQuantity.getText()));
            n.setStock_quantity(Integer.parseInt(tfStock.getText()));
            n.setPrice(Double.parseDouble(tfPrice.getText()));
            //checking if the name is changed
            String row =previous_screen.getDataatrow();
            String []p=row.split("\n");
            if(!(p[1].equals(n.getName()))) {
               int id = productDAO.getIDbyName(n.getName());
               if(id!=-1){
                   throw new SQLDataException();
               }
            }
            productDAO.updateProduct(n, id);
            previous_screen.loadProductsIntoTable();
            this.dispose();



        }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Please enter valid values ","ERROR",JOptionPane.ERROR_MESSAGE);
            e.getCause();
        } catch (SQLDataException e) {
            JOptionPane.showMessageDialog(null,"Product already exist ","ERROR",JOptionPane.ERROR_MESSAGE);
            e.getCause();
            throw new RuntimeException(e);
        }


    }
    
    /**
     * Handles the cancel button action performed event.
     *
     * @param evt The action event.
     */
    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        //go back to previous screen
        this.dispose();
    }

     /**
     * The main method to launch the updateProduct window.
     *
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
            java.util.logging.Logger.getLogger(updateProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(updateProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(updateProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(updateProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new updateProduct().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> combCat;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField tfDesc;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfPrice;
    private javax.swing.JTextField tfQuantity;
    private javax.swing.JTextField tfStock;
    // End of variables declaration
}
