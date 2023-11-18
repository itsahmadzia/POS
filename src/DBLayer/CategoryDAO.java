package DBLayer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    private Connection connection;

    public CategoryDAO() {
        try {
            connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            System.out.println("CONNECTION FAILED");
            throw new RuntimeException(e);
        }
    }

    public List<String> getProductsInCategory(int categoryId) {
        List<String> productList = new ArrayList<>();
        try {
            String query = "SELECT name FROM Product WHERE category_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, categoryId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        productList.add(resultSet.getString("name"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public void insertCategory(int categoryId, String categoryName, Integer parentCategoryId) {
        try {
            String query = "INSERT INTO Category (id, name, parent_category_id) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, categoryId);
                preparedStatement.setString(2, categoryName);
                if (parentCategoryId == null) {
                    preparedStatement.setNull(3, Types.INTEGER);
                } else {
                    preparedStatement.setInt(3, parentCategoryId);
                }
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertParentCategory(int categoryId, String categoryName) {
        insertCategory(categoryId, categoryName, null);
    }


    public void deleteCategory(int categoryId) {
        try {
            String query = "DELETE FROM Category WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, categoryId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCategory(int categoryId, String newName, Integer newParentCategoryId) {
        try {
            String query = "UPDATE Category SET name=?, parent_category_id=? WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, newName);
                if (newParentCategoryId == null) {
                    preparedStatement.setNull(2, Types.INTEGER);
                } else {
                    preparedStatement.setInt(2, newParentCategoryId);
                }
                preparedStatement.setInt(3, categoryId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<String> getAllCategories() {
        List<String> categoryList = new ArrayList<>();
        try {
            String query = "SELECT name FROM Category";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    categoryList.add(resultSet.getString("name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }
}
