package UILayer;

import BusinessLayer.Category;
import DBLayer.CategoryDAO;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class categoryTree {

    private static JTree categoryTree;
    private static JCheckBox showProductsCheckBox;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Category Tree Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            categoryTree = createCategoryTree();
            JScrollPane scrollPane = new JScrollPane(categoryTree);

            showProductsCheckBox = new JCheckBox("Show at Leaf Node");
            JButton showProductsButton = new JButton("Show Products");
            showProductsButton.addActionListener(UILayer.categoryTree::showProductsButtonClicked);

            JPanel controlPanel = new JPanel();
            controlPanel.add(showProductsCheckBox);
            controlPanel.add(showProductsButton);

            frame.add(controlPanel, BorderLayout.NORTH);
            frame.add(scrollPane, BorderLayout.CENTER);

            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private static JTree createCategoryTree() {
        DefaultMutableTreeNode root = buildCategoryTreeFromDatabase();

        return new JTree(root);
    }

    private static DefaultMutableTreeNode buildCategoryTreeFromDatabase() {
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

    private static void showProductsButtonClicked(ActionEvent e) {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) categoryTree.getLastSelectedPathComponent();

        if (selectedNode != null) {
            System.out.println("Selected Node: " + selectedNode.getUserObject());
        } else {
            System.out.println("Please select a node.");
        }
    }
}
