package DBLayer;

import BusinessLayer.Item;
import BusinessLayer.Order;

import javax.swing.*;
import java.sql.*;
import java.util.List;
import java.util.Random;


public class OrderDAO {
    private Connection connection; 
    
    public OrderDAO() {
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private int getLastInsertedOrderId(Connection conn) throws SQLException {
        try (Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id FROM order_t ORDER BY id DESC LIMIT 1");

            if (resultSet.next()) {
                return resultSet.getInt("id");
            } else {
                throw new SQLException("Failed to retrieve the last order ID.");
            }
        }
    }


    public int saveOrder(Order order) {
       int orderId=-1;
        try (Connection conn = DatabaseConnection.getConnection();  
             PreparedStatement statement = conn.prepareStatement(
                     "INSERT INTO order_t ( customer_name, total, order_date, operator_username) VALUES ( ?, ?, ?, ?)")) {


           statement.setString(1, order.getCustomerName());
            statement.setDouble(2, order.getTotal());
            statement.setDate(3, new java.sql.Date(order.getTimestamp().getTime()));
            statement.setString(4, order.getOperatorName());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating order failed, no rows affected.");
            }
            System.out.println(orderId);
        orderId=getLastInsertedOrderId(DatabaseConnection.getConnection());
            System.out.println(orderId);
            saveOrderItems(orderId, order.items);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderId;
    }

    public void saveOrderItems(int orderId, List<Item> items) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(
                     "INSERT INTO order_t_Item (order_id, product_id, product_name, quantity_ordered, price, item_price) VALUES (?, ?, ?, ?, ?, ?)")) {
    
            for (Item item : items) {
                double productPrice = getProductPrice(item.getProduct().getName());
                statement.setInt(1, orderId);
                statement.setInt(2, item.getProduct().getId());
                statement.setString(3, item.getProduct().getName());
                statement.setInt(4, item.getQuantityorder());
                statement.setDouble(5, productPrice);
                statement.setDouble(6, item.getPrice());
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
