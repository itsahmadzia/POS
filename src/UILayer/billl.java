package UILayer;

import BusinessLayer.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;


public class billl extends javax.swing.JFrame {
    private static Cart cart;
    private Order order;
    private User user;
    Product product;
    double overallTotal;

    public billl(User currentUser) {
        initComponents();
        user = currentUser;
        if (cart == null) {
            cart = new Cart();
        }
        if (order == null) {
            order = new Order();
        }
        if (product == null) {
            product = new Product();
        }
        //double overallTotal = 0.0;

        jTable2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int row = jTable2.getSelectedRow();
                    if (row != -1) {
                        String productName = jTable2.getValueAt(row, 1).toString();
                        String productId = jTable2.getValueAt(row, 0).toString();

                        nameTextField.setText(productName);
                        idTextField.setText(productId);
                    }
                }
            }
        });

    }
    public billl() {
        initComponents();
        if (cart == null) {
            cart = new Cart();
        }
        if (order == null) {
            order = new Order();
        }
        if (product == null) {
            product = new Product();
        }
        double overallTotal = 0.0;
        nameTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
////here herehere

                try {
                    String enteredProductName = nameTextField.getText().trim();
                    List<Product> matchingProducts = product.searchProductsByNameFromDB(enteredProductName);

                    if (matchingProducts != null && !matchingProducts.isEmpty()) {
                        DefaultTableModel model = new DefaultTableModel();
                        model.addColumn("ID");
                        model.addColumn("Name");
                        model.addColumn("Price");
                        model.addColumn("Stock Quantity");
                        model.addColumn("Quantity per pack");

                        for (Product product : matchingProducts) {
                            model.addRow(new Object[]{
                                    product.getId(),
                                    product.getName(),
                                    product.getPrice(),
                                    product.getStock_quantity(),
                                    product.getQuantity_per_pack()
                            });
                        }

                        jTable2.setModel(model);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error retrieving products: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }


            }
        });

    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        logoutButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        addToCartButton1 = new javax.swing.JButton();
        checkoutButton1 = new javax.swing.JButton();
        cancelButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        totalTextField = new javax.swing.JTextField();
        updateButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        idTextField = new javax.swing.JTextField();
        nameTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        packCheckBox = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(150, 584));

        logoutButton.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        logoutButton.setText("Logout");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        removeButton.setBackground(new java.awt.Color(255, 51, 51));
        removeButton.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        removeButton.setForeground(new java.awt.Color(255, 255, 255));
        removeButton.setText("Remove");
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        addToCartButton1.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        addToCartButton1.setText("Add To Cart");
        addToCartButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartButton1ActionPerformed(evt);
            }
        });
        nameTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
updateTable();
            }
        });

        checkoutButton1.setBackground(new java.awt.Color(0, 204, 102));
        checkoutButton1.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        checkoutButton1.setText("Checkout");
        checkoutButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkoutButton1ActionPerformed(evt);
            }
        });

        cancelButton1.setBackground(new java.awt.Color(255, 0, 0));
        cancelButton1.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        cancelButton1.setForeground(new java.awt.Color(255, 255, 255));
        cancelButton1.setText("Cancel Order");
        cancelButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButton1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 1, 20)); // NOI18N
        jLabel5.setText("Total Rs.");

        totalTextField.setFont(new java.awt.Font("Helvetica Neue", 0, 20)); // NOI18N
        totalTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalTextFieldActionPerformed(evt);
            }
        });

        updateButton.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        updateButton.setText("Update Quantity");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(checkoutButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(totalTextField))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addGap(27, 27, 27)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(addToCartButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(cancelButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(removeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(updateButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))))
                                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(addToCartButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(removeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(cancelButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(totalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(54, 54, 54)
                                .addComponent(checkoutButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 22)); // NOI18N
        jLabel1.setText("Cart");

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel2.setText("ID");

        idTextField.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        idTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idTextFieldActionPerformed(evt);
            }
        });

        nameTextField.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        nameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextFieldActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel3.setText("Name");

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel4.setText("Quantity");

        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });

        packCheckBox.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        packCheckBox.setText("Pack");
        packCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                packCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(idTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(packCheckBox)
                                .addGap(53, 53, 53))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(packCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(idTextField)
                                                                .addComponent(jLabel3)
                                                                .addComponent(nameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                                                                .addComponent(jLabel4)
                                                                .addComponent(jLabel2))
                                                        .addComponent(jSpinner1))))
                                .addContainerGap())
        );

        jTable1.setModel(cartTablemodel=new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "ID", "Name", "Quantity", "Price", "Total"
                }
        ) {
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(2).setHeaderValue("Quantity");
            jTable1.getColumnModel().getColumn(4).setHeaderValue("Total");
        }

        jTable2.setModel(productTablemodel=new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "ID", "Name", "Price", "Stock Quantity", "Quantity per pack"
                }
        ) {
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 1, 16)); // NOI18N
        jLabel6.setText("Search products by entering product name or id");

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 1, 22)); // NOI18N
        jLabel7.setText("Order");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel1)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(jScrollPane1)
                                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
                                                                .addComponent(jLabel6)
                                                                .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(32, 32, 32)
                                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addComponent(jLabel7)
                                        .addContainerGap(882, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addComponent(jLabel7)
                                        .addContainerGap(565, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>                        

    private void totalTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
        totalTextField.setEditable(false);
        totalTextField.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {
                roundTotalTextField();
            }
        });
    }

    private void roundTotalTextField() {
        try {
            double value = Double.parseDouble(totalTextField.getText());
            String formattedValue = String.format("%.2f", value);
            totalTextField.setText(formattedValue);
        } catch (NumberFormatException ex) {
            totalTextField.setText("0.00");
            JOptionPane.showMessageDialog(this, "Invalid value in total TextField.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void idTextFieldActionPerformed(java.awt.event.ActionEvent evt) {}

    private void addToCartButton1ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private Item findItemInCart(int productId) {
        for (Item item : cart.getItems()) {
            if (item.getProduct().getId() == productId) {
                return item;
            }
        }
        return null;
    }


    private void nameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String enteredProductName = nameTextField.getText().trim();
            List<Product> matchingProducts = product.searchProductsByNameFromDB(enteredProductName);

            if (matchingProducts != null && !matchingProducts.isEmpty()) {
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("ID");
                model.addColumn("Name");
                model.addColumn("Price");
                model.addColumn("Stock Quantity");
                model.addColumn("Quantity per pack");

                for (Product product : matchingProducts) {
                    model.addRow(new Object[]{
                            product.getId(),
                            product.getName(),
                            product.getPrice(),
                            product.getStock_quantity(),
                            product.getQuantity_per_pack()
                    });
                }

                jTable2.setModel(model);
            } else {
                JOptionPane.showMessageDialog(null, "No matching products found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error retrieving products: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void checkoutButton1ActionPerformed(java.awt.event.ActionEvent evt) {

        if (cart.isEmpty()) {
            JOptionPane.showMessageDialog(this, "The cart is empty. Add items before checkout.", "Empty Cart", JOptionPane.WARNING_MESSAGE);
            return;
        }

        //Customer Name
        String customerName = JOptionPane.showInputDialog(this, "Enter Customer Name:");

        if (customerName != null && !customerName.trim().isEmpty()) {
            double totalAmountDue = 0.0;

            if (totalTextField != null) {
                String totalAmountDueStr = totalTextField.getText();
                try {
                    totalAmountDue = Double.parseDouble(totalAmountDueStr);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Invalid value at totalTextField.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Total amount field is null.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double amountPaid = 0.0;
            boolean validInput = false;

            //Customer Payment Amount
            while (!validInput) {
                String amountPaidStr = JOptionPane.showInputDialog(this, "Enter Amount Paid:");
                try {
                    amountPaid = Double.parseDouble(amountPaidStr);

                    if (amountPaid >= totalAmountDue) {
                        validInput = true;
                    } else {
                        JOptionPane.showMessageDialog(this, "Amount paid cannot be less than total amount due.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid amount paid.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                }
            }

            order = cart.generateOrder(customerName, totalAmountDue, amountPaid, user.getUsername());
            order.saveOrder(order);

            for (Item item : order.items) {
                order.updateStockInDB(order.getOrder_id(), item.getProduct().getName(), item.getQuantityorder());
            }

            //Invoice
            generateInvoicePDF();

            order = null;
            overallTotal = 0.0;
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            DefaultTableModel model2 = (DefaultTableModel) jTable2.getModel();
            model2.setRowCount(0);

            idTextField.setText("");
            nameTextField.setText("");
            jSpinner1.setValue(1);
            totalTextField.setText("");

        } else {
            JOptionPane.showMessageDialog(this, "Please enter a valid customer name.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void generateInvoicePDF() {
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);

            document.addPage(page);
            String fileName = "invoice_" + new Date().getTime() + ".pdf";

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            PDType0Font font = PDType0Font.load(document, new FileInputStream(new File("Monospace_Medium.ttf")));
            PDType0Font boldFont = PDType0Font.load(document, new FileInputStream(new File("Monospace_Bold.ttf")));

            contentStream.setFont(boldFont, 20);
            float yStart = page.getMediaBox().getHeight() - 50;
            float margin = 50;
            float width = page.getMediaBox().getWidth() - 2 * margin;
            float yPosition = yStart;

            contentStream.beginText();
            contentStream.newLineAtOffset((width - boldFont.getStringWidth("Pharmacy") / 1000f) / 2f, yPosition);
            contentStream.showText("Pharmacy");
            contentStream.endText();
            yPosition -= 20;

            contentStream.setFont(boldFont, 16);
            contentStream.beginText();
            contentStream.newLineAtOffset((width - boldFont.getStringWidth("Invoice") / 1000f) / 2f, yPosition);
            contentStream.showText("Invoice");
            contentStream.endText();
            yPosition -= 20;

            contentStream.setFont(font, 14);
            float fixedXPosition = 60;

            writeText(contentStream, "Order Id           :" + order.getOrder_id(), fixedXPosition, yPosition, width);
            writeText(contentStream, "Time               :" + order.getTimestamp(), fixedXPosition, yPosition -= 15, width);
            writeText(contentStream, "Operator           :" + user.getName(), fixedXPosition, yPosition -= 15, width);
            writeText(contentStream, "Customer Name      :" + order.getCustomerName(), fixedXPosition, yPosition -= 15, width);

            yPosition -= 40;
            contentStream.setFont(boldFont, 14);

            writeText(contentStream, "Item", margin, yPosition, 100);
            writeText(contentStream, "Qty", margin + 150, yPosition, 200);
            writeText(contentStream, "Price", margin + 290, yPosition, 100);
            writeText(contentStream, "Total", margin + 450, yPosition, 100);

            yPosition -= 15;
            float lineYPosition = yPosition + 5;
            float lineWidth = width - 2 * margin;
            contentStream.setLineWidth(1f);
            contentStream.moveTo(margin, lineYPosition);
            contentStream.lineTo(margin + lineWidth+100, lineYPosition);
            contentStream.stroke();
            yPosition -= 20;
            contentStream.setFont(font, 14);

            /*for (Item item : order.items) {
                writeText(contentStream, "Name: " + item.getItemname()+
                        "  Quantity: " + item.getQuantityorder() +
                        "  Price: " + item.getProduct().getPrice() + "  Total: " + item.total(item.getProduct()), margin, yPosition -= 15, width);
            }*/
            float gapSize = 15;

            writeTableToPDF(jTable1, contentStream, margin, yPosition -= gapSize, width);
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

            float totalTableHeight = (model.getRowCount() + 1) * 15;
            yPosition -= totalTableHeight;
            float tableLineYPosition = yPosition + 5;
            contentStream.moveTo(margin, tableLineYPosition);
            contentStream.lineTo(margin + lineWidth+100, tableLineYPosition);
            contentStream.stroke();

            contentStream.setFont(boldFont, 14);
            writeText(contentStream, "Amount Due : Rs." + formatDecimal(order.getTotal()), margin + 300, yPosition -= gapSize, width);
            writeText(contentStream, "Amount Paid: Rs." + formatDecimal(order.getAmountPaid()), margin + 300, yPosition -= gapSize, width);
            double remainingBalance = order.getAmountPaid() - order.getTotal();

            if (remainingBalance > 0) {
                writeText(contentStream, "Returned: Rs." + formatDecimal(remainingBalance), margin + 325, yPosition -= gapSize, width);
            } else {
                writeText(contentStream, "Returned: Rs. 0", margin + 320, yPosition -= gapSize, width);
            }

            contentStream.close();
            document.save(fileName);
            document.close();

            JOptionPane.showMessageDialog(this, "Invoice generated successfully: " + fileName,
                    "Invoice Generated", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error generating invoice",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String formatDecimal(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(value);
    }

    private void writeTableToPDF(JTable table, PDPageContentStream contentStream, float margin, float yPosition, float width) throws IOException {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        int colCount = model.getColumnCount();
        float cellWidth = 150;

        for (int row = 0; row < rowCount; row++) {
            for (int col = 1; col < colCount; col++) {
                Object value = model.getValueAt(row, col);
                float cellXPosition = margin + (col - 1) * cellWidth;
                writeText(contentStream, value.toString(), cellXPosition, yPosition - row * 15, cellWidth);
            }
        }
    }

    private void writeText(PDPageContentStream contentStream, String text, float x, float y, float width) throws IOException {
        contentStream.beginText();
        contentStream.newLineAtOffset(x, y);
        contentStream.showText(text);
        contentStream.endText();
    }

    private void cancelButton1ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {
     }

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {


    }

    private void updateTable() {

    }

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {
        int currentValue = (int) jSpinner1.getValue();
        if (currentValue <= 0) {
            jSpinner1.setValue(1);
        }
    }

    private void packCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {


    }

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
            java.util.logging.Logger.getLogger(billl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(billl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(billl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(billl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new billl().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton addToCartButton1;
    private javax.swing.JButton cancelButton1;
    private javax.swing.JButton checkoutButton1;
    private javax.swing.JTextField idTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JButton logoutButton;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JCheckBox packCheckBox;
    private javax.swing.JButton removeButton;
    private javax.swing.JTextField totalTextField;
    private javax.swing.JButton updateButton;
    private DefaultTableModel productTablemodel;
    private DefaultTableModel cartTablemodel;

    // End of variables declaration                   
}