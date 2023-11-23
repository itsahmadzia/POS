package DBLayer;

import BusinessLayer.Category;

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

    public void insertCategory(int categoryId, String categoryName, Integer parentCategoryId,String Description) {
        try {
            String query = "INSERT INTO Category (id, name, parent_category_id,Description) VALUES (?, ?, ?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, categoryId);
                preparedStatement.setString(2, categoryName);
                if (parentCategoryId == null) {
                    preparedStatement.setNull(3, Types.INTEGER);
                } else {
                    preparedStatement.setInt(3, parentCategoryId);
                }
                preparedStatement.setString(4,Description);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertParentCategory(int categoryId, String categoryName,String description) {
        insertCategory(categoryId, categoryName, null,description);
    }
    public boolean categoryExists(int categoryId) {
        try {
            String query = "SELECT COUNT(*) FROM Category WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, categoryId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        return count > 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean categoryNameExists(String  categoryId) {
        try {
            String query = "SELECT COUNT(*) FROM Category WHERE name=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, categoryId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        return count > 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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

    public void updateCategory(int categoryId, String newName, Integer newParentCategoryId,String description) {
        try {
            String query = "UPDATE Category SET name=?, parent_category_id=?,Description=? WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, newName);
                if (newParentCategoryId == null) {
                    preparedStatement.setNull(2, Types.INTEGER);
                } else {
                    preparedStatement.setInt(2, newParentCategoryId);
                }
                preparedStatement.setString(2,description);
                preparedStatement.setInt(4, categoryId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        try {
            String query = "SELECT * FROM Category";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    Category category = new Category();
                    category.setCode(resultSet.getInt("id"));
                    category.setName(resultSet.getString("name"));
                    category.setDescription(resultSet.getString("Description"));



                    int parentCategoryId = resultSet.getInt("parent_category_id");
                    if (!resultSet.wasNull()) {
                       Category parentCategoryName =getCategoryById(parentCategoryId);
                        category.setParentCategoryName(parentCategoryName.getName());
                    }

                    categoryList.add(category);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    public Category getCategoryById(int categoryId) {
        Category category = null;
        try {
            String query = "SELECT * FROM Category WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, categoryId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        category = new Category();
                        category.setCode(resultSet.getInt("id"));
                        category.setName(resultSet.getString("name"));
                        category.setDescription(resultSet.getString("Description"));
                        category.setParentCategoryName((String) getNamebyID(resultSet.getInt("parent_category_id")));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    public List<String> getAllCategoriesName() {
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

    public Integer getCategoryCodebyName(String categoryName) {
        try {
            String query = "SELECT id FROM Category WHERE name=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, categoryName);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("id");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object getNamebyID(int category) {
        try {
            String query = "SELECT name FROM Category WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, category);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("name");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
