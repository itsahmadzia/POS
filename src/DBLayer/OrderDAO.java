package DBLayer;

import BusinessLayer.*;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;


public class OrderDAO {
    private Connection connection; 
    
    public OrderDAO() {
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int saveOrder(Order order) {
        int orderId = 0;
        try (Connection conn = DatabaseConnection.getConnection();  
             PreparedStatement statement = conn.prepareStatement(
                     "INSERT INTO order_t (id, customer_name, total, order_date, operator_username) VALUES (?, ?, ?, ?, ?)")) {

            orderId = generateRandomOrderId();

            statement.setInt(1, orderId);
            statement.setString(2, order.getCustomerName());
            statement.setDouble(3, order.getTotal());
            statement.setDate(4, new java.sql.Date(order.getTimestamp().getTime()));
            statement.setString(5, order.getOperatorName());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating order failed, no rows affected.");
            }
            saveOrderItems(orderId, order.items);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderId;
    }

    public void saveOrderItems(int orderId, List<Item> items) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(
                     "INSERT INTO order_t_Item (order_id, product_name, quantity_ordered, price, item_price) VALUES (?, ?, ?, ?,?)")) {

            for (Item item : items) {
                double productPrice = getProductPrice(item.getProduct().getName());
                statement.setInt(1, orderId);
                statement.setString(2, item.getProduct().getName());
                statement.setInt(3, item.getQuantityorder());
                statement.setDouble(4, productPrice);  
                 statement.setDouble(5, item.getPrice());  
                statement.addBatch();
            }
            statement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
    private double getProductPrice(String productName) {
        double productPrice = 0.0; 

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT price FROM Product WHERE name = ?")) {
             statement.setString(1, productName);

             try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    productPrice = resultSet.getDouble("price");
                }
             }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productPrice;
    }

    private static int generateRandomOrderId() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            int randomOrderId;
            int maxAttempts = 20;
            Random random = new Random(); 

            for (int attempt = 1; attempt <= maxAttempts; attempt++) {
                randomOrderId = random.nextInt(1000) + 1;
                //intln("Generated Order ID: " + randomOrderId);

                ResultSet resultSet = statement.executeQuery("SELECT 1 FROM order_t WHERE id = " + randomOrderId);

                if (!resultSet.next()) {
                    return randomOrderId;
                }
            }
            showErrorDialog("Failed to generate a unique order ID after " + maxAttempts + " attempts.");

        } catch (SQLException e) {
            showErrorDialog("Error generating order ID: " + e.getMessage());
        }
        return -1;
    }



    private static void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }


    public void updateStock(int orderId, String productName, int quantityOrdered) {
        try {
            String updateStockQuery = "UPDATE Product SET stock_quantity = stock_quantity - ? WHERE name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateStockQuery)) {
                preparedStatement.setInt(1, quantityOrdered);
                preparedStatement.setString(2, productName);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
}
