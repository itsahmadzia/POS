package UILayer;

import BusinessLayer.Category;
import BusinessLayer.Product;
import DBLayer.CategoryDAO;
import DBLayer.ProductDAO;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
/**
 * The CategoryTreeFrame class represents the user interface for displaying a category tree
 * and related product information. It provides functionalities such as showing products at leaf nodes,
 * displaying detailed product information, and navigating back to the manager menu.
 */
public class CategoryTreeFrame extends JFrame {
    private JTree categoryTree;
    private JCheckBox showProductsCheckBox;
    private JTable tableP=new JTable();
    private DefaultTableModel tableModel=new DefaultTableModel();

    private        List<JLabel> list = new ArrayList<>();
    
    /**
     * Constructs a CategoryTreeFrame with the necessary components and initializes the UI.
     */
    public CategoryTreeFrame() {

        super("Category Tree Example");
        tableP.setModel(tableModel);
        tableModel.addColumn("ProductID");
        tableModel.addColumn("NAME");
        add(new JScrollPane(tableP), BorderLayout.CENTER);
        tableP.setDefaultEditor(Object.class, null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        categoryTree = createCategoryTree();
        categoryTree.setPreferredSize(new Dimension(300, 600));
        JScrollPane treeScrollPane = new JScrollPane(categoryTree);

        showProductsCheckBox = new JCheckBox("Show at Leaf Node");
        JButton showProductsButton = new JButton("Show Products");
        showProductsButton.addActionListener(this::showProductsButtonClicked);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(this::goBack);

        JPanel controlPanel = new JPanel();
        controlPanel.add(backButton);
        controlPanel.add(showProductsCheckBox);
        showProductsButton.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
          JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));

        for(int i = 0 ; i <7; i++){
            list.add(new JLabel());
            list.get(i).setBorder(BorderFactory.createEmptyBorder(2,0,4,0));
            eastPanel.add(list.get(i));
        }

eastPanel.setPreferredSize(new Dimension(300,600));
        eastPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(controlPanel, BorderLayout.NORTH);
        add(treeScrollPane, BorderLayout.WEST);
        add(eastPanel,BorderLayout.EAST);


        tableP.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tableP.getSelectedRow();
                    if (selectedRow >= 0) {
                        // Retrieve the selected product from the table
                        String selectedProduct = (String) tableModel.getValueAt(selectedRow, 0);

                        // Update the JLabels in the east panel with information about the selected product
                        updateLabels(selectedProduct);
                    }
                }
            }
        });
        // Add a TreeSelectionListener to the JTree
        showProductsCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) categoryTree.getLastSelectedPathComponent();
                if(selectedNode!=null){
                    ProductDAO pd = new ProductDAO();
                    CategoryDAO c = new CategoryDAO();

                    List<String> productList;
                    if (showProductsCheckBox.isSelected()) {
                        System.out.println("Selected Node: " + selectedNode.getUserObject());
                        productList = c.getProductsInCategorytillleaf(c.getCategoryCodebyName((String) selectedNode.getUserObject()));
                    } else {
                        System.out.println("Selected Node: " + selectedNode.getUserObject());
                        productList = c.getProductsInCategory(c.getCategoryCodebyName((String) selectedNode.getUserObject()));
                    }
                    tableModel.setRowCount(0);
                    for(String P:productList){
                       String []p= P.split("\\|");
                        tableModel.addRow(new Object[]{p[0],p[1]});
                    }
                }
            }
        });

        categoryTree.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) categoryTree.getLastSelectedPathComponent();
                if(selectedNode!=null){
                    ProductDAO pd = new ProductDAO();
                    CategoryDAO c = new CategoryDAO();

                    List<String> productList;
                    if (showProductsCheckBox.isSelected()) {
                        System.out.println("Selected Node: " + selectedNode.getUserObject());
                        productList = c.getProductsInCategorytillleaf(c.getCategoryCodebyName((String) selectedNode.getUserObject()));
                    } else {
                        System.out.println("Selected Node: " + selectedNode.getUserObject());


                        productList = c.getProductsInCategory(c.getCategoryCodebyName((String) selectedNode.getUserObject()));
                    }
                    tableModel.setRowCount(0);
                    for(String P:productList){
                        String []p= P.split("\\|");
                        tableModel.addRow(new Object[]{p[0],p[1]});
                    }
                }


            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        categoryTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) categoryTree.getLastSelectedPathComponent();

                if (selectedNode != null) {
                    ProductDAO pd = new ProductDAO();
                    CategoryDAO c = new CategoryDAO();

                    List<String> productList;
                    if (showProductsCheckBox.isSelected()) {
                        System.out.println("Selected Node: " + selectedNode.getUserObject());
                        productList = c.getProductsInCategorytillleaf(c.getCategoryCodebyName((String) selectedNode.getUserObject()));
                    } else {
                        System.out.println("Selected Node: " + selectedNode.getUserObject());


                            productList = c.getProductsInCategory(c.getCategoryCodebyName((String) selectedNode.getUserObject()));
                            productList = c.getProductsInCategory(c.getCategoryCodebyName((String) selectedNode.getUserObject()));

                    }
tableModel.setRowCount(0);
                    for(String P:productList){
                        tableModel.addRow(new Object[]{P});
                    }
                } else {
                    System.out.println("Please select a node.");
                }
            }
        });

        setSize(800, 600);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }
    
    /**
     * Updates the JLabels in the east panel with information about the selected product.
     *
     * @param selectedProduct The ID of the selected product.
     */
    private void updateLabels(String selectedProduct) {
         for (int i = 0; i < 7; i++) {
            list.get(i).setText("");
        }

        ProductDAO d = new ProductDAO();
        int code = Integer.parseInt(selectedProduct);
        Product s = d.getProductByID(code);
        list.get(0).setText("ID:" + s.getId());
        list.get(1).setText("Name:" + s.getName());
        list.get(2).setText("Expiry " + s.getExp());
        list.get(3).setText("Price " + s.getPrice());
        list.get(4).setText("Stock " + s.getStock_quantity());

    list.get(5).setText("Description: "+s.getDescription());
        JLabel dateLabel = calculateDateDifference(String.valueOf(s.getExp()));
        list.get(6).setText("Remaining: "+dateLabel.getText());
        list.get(6).setForeground(dateLabel.getForeground());
    }

    
    /**
     * Creates a JTree representing the category tree based on the data retrieved from the database.
     *
     * @return The constructed JTree.
     */
    private JTree createCategoryTree() {
        DefaultMutableTreeNode root = buildCategoryTreeFromDatabase();
        return new JTree(root);
    }
    
     /**
     * Builds the category tree structure from the database and returns the root node.
     *
     * @return The root node of the constructed category tree.
     */
    private DefaultMutableTreeNode buildCategoryTreeFromDatabase() {
        DefaultMutableTreeNode commonRoot = new DefaultMutableTreeNode("Categories");

        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> categoryList = categoryDAO.getAllCategories();

        java.util.Map<Integer, DefaultMutableTreeNode> categoryMap = new java.util.HashMap<>();

        for (Category category : categoryList) {
            int categoryId = category.getCode();
            String categoryName = category.getName();
            String parentCategoryName = category.getParentCategoryName();

            DefaultMutableTreeNode categoryNode = new DefaultMutableTreeNode(categoryName);
            categoryMap.put(categoryId, categoryNode);

            if (parentCategoryName == null) {
                commonRoot.add(categoryNode);
            } else {
                DefaultMutableTreeNode parentNode = categoryMap.get(categoryDAO.getCategoryCodebyName(parentCategoryName));
                if (parentNode != null) {
                    parentNode.add(categoryNode);
                }
            }
        }

        return commonRoot;
    }

    private void showProductsButtonClicked(ActionEvent e) {
        // This method remains the same as in your original code
    }
    
    /**
     * Handles the action when the "Back" button is clicked.
     *
     * @param e The ActionEvent triggered by the button click.
     */
    private void goBack(ActionEvent e) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Managermenu().setVisible(true);
            }

        });
        this.dispose();
        // Handle the back button action if needed
        // For example, closing the current frame and returning to the previous frame
        // You may want to customize this based on your application's structure
    }
    /**
     * Calculates the date difference between the input date and the current date.
     *
     * @param date The expiration date of the product.
     * @return JLabel displaying the remaining duration with appropriate formatting.
     */
    JLabel calculateDateDifference(String date) {
        LocalDate inputDate = LocalDate.parse(date);
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between( currentDate,inputDate);

        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();

        String resultText = String.format("%d years, %d months, %d days", years, months, days);

        JLabel label = new JLabel(resultText);

       if (period.getMonths()==0&&period.getYears()==0&&period.getDays() < 15) {
            label.setForeground(Color.RED);
        }

        return label;
    }


    /**
     * Main method to launch the CategoryTreeFrame.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(CategoryTreeFrame::new);
    }
}
