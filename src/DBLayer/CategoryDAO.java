package DBLayer;

import BusinessLayer.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * The CategoryDAO class provides data access methods for interacting with the database
 * related to category operations.
 */
public class CategoryDAO {

    private Connection connection;
    /**
     * Constructs a CategoryDAO object and establishes a database connection.
     */
    public CategoryDAO() {
        try {
            connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            System.out.println("CONNECTION FAILED");
            throw new RuntimeException(e);
        }
    }

    /**
     * Constructs a CategoryDAO object with a specified database connection.
     *
     * @param connection The database connection to be used by the CategoryDAO.
     */
    public CategoryDAO(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Retrieves a list of product names within a specified category.
     *
     * @param categoryId The ID of the category.
     * @return List of product names in the specified category.
     */
    public List<String> getProductsInCategory(int categoryId) {
        List<String> productList = new ArrayList<>();
        try {
            String query = "SELECT id,name FROM Product WHERE category_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, categoryId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        productList.add(resultSet.getInt(1)+"|"+resultSet.getString(2));
                        System.out.println(productList);

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
    
    /**
     * Retrieves a list of product names within a specified category.
     *
     * @param categoryId The ID of the category.
     * @return List of product names in the specified category and its leaf categories.
     */
    public List<String> getProductsInCategorytillleaf(int categoryId) {
        List<String> productList = new ArrayList<>();
        try {
            String query = "CALL GetProductsForCategoryConcat(?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, categoryId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        productList.add(resultSet.getString(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
     /**
     * Inserts a new category into the database.
     *
     * @param categoryId         The ID of the category.
     * @param categoryName       The name of the category.
     * @param parentCategoryId   The ID of the parent category (null for root category).
     * @param Description        The description of the category.
     */
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
    
    /**
     * Inserts a new parent category into the database.
     *
     * @param categoryId    The ID of the category.
     * @param categoryName  The name of the category.
     * @param description   The description of the category.
     */
    public void insertParentCategory(int categoryId, String categoryName,String description) {
        insertCategory(categoryId, categoryName, null,description);
    }
    
    /**
     * Checks if a category with the specified ID exists in the database.
     *
     * @param categoryId The ID of the category to check.
     * @return true if the category exists, false otherwise.
     */
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
    
    /**
     * Checks if a category with the specified name exists in the database.
     *
     * @param categoryId The name of the category to check.
     * @return true if the category exists, false otherwise.
     */
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
    
    /**
     * Deletes a category from the database based on the category ID.
     *
     * @param categoryId The ID of the category to be deleted.
     */
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
     /**
     * Updates the information of a category in the database.
     *
     * @param categoryId           The ID of the category to be updated.
     * @param newName              The new name of the category.
     * @param newParentCategoryId  The new parent category ID (null for root category).
     * @param description          The new description of the category.
     */
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
                preparedStatement.setString(3,description);
                preparedStatement.setInt(4, categoryId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Retrieves a list of all categories from the database.
     *
     * @return List of Category objects representing all categories.
     */
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
     /**
     * Retrieves a specific category by its ID from the database.
     *
     * @param categoryId The ID of the category to retrieve.
     * @return The Category object representing the specified category.
     */
    
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
     /**
     * Retrieves a list of names of all categories from the database.
     *
     * @return List of category names.
     */
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
    
     /**
     * Retrieves the category ID based on the category name.
     *
     * @param categoryName The name of the category.
     * @return The ID of the specified category.
     */
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
    /**
     * Retrieves the name of a category based on its ID.
     *
     * @param category The ID of the category.
     * @return The name of the specified category.
     */
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
