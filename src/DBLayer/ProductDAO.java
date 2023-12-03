package DBLayer;


import BusinessLayer.Product;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * The ProductDAO class provides data access methods for handling products and related operations in the database.
 */
public class ProductDAO {

    private Connection connection;
/**
     * Default constructor for creating a ProductDAO instance with its own database connection.
     */
    public ProductDAO() {
        try {
            connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            System.out.println("CONNECTION FAILED");
            throw new RuntimeException(e);
        }
    }
    /**
     * Constructor for creating a ProductDAO instance with a provided database connection.
     *
     * @param connection The database connection to be used by the ProductDAO instance.
     */
    public ProductDAO(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Retrieves the ID of a product by its name from the database.
     *
     * @param name The name of the product.
     * @return The ID of the product.
     */
    public int getIDbyName(String name) {
        try {
            String query = "SELECT id FROM Product WHERE name=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("id");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
         return -1;
    }
     /**
     * Retrieves a product by its ID from the database.
     *
     * @param productId The ID of the product.
     * @return The Product object.
     */
    public Product getProductByID(int productId) {
        try {
            String query = "SELECT * FROM Product WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, productId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        Product product = new Product();
                        product.setId(resultSet.getInt("id"));
                        product.setName(resultSet.getString("name"));
                        product.setPrice(resultSet.getDouble("price"));
                        product.setStock_quantity(resultSet.getInt("stock_quantity"));
                        product.setQuantity_per_pack(resultSet.getInt("quantity_per_pack"));
                        product.setDescription(resultSet.getString("description"));
                        product.setCategory_code(resultSet.getInt("category_id"));
                        product.setExp(resultSet.getDate("expiryDate"));
                        return product;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Inserts a new product into the database.
     *
     * @param product The Product object to be inserted.
     */
    public void insertProduct(Product product) {
        try {
            String query = "INSERT INTO Product (id, name, price, stock_quantity, quantity_per_pack, description, category_id, expiryDate) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, product.getId());
                preparedStatement.setString(2, product.getName());
                preparedStatement.setDouble(3, product.getPrice());
                preparedStatement.setInt(4, product.getStock_quantity());
                preparedStatement.setInt(5, product.getQuantity_per_pack());
                preparedStatement.setString(6, product.getDescription());
                preparedStatement.setInt(7, product.getCategory_code());
                preparedStatement.setDate(8, new java.sql.Date(product.getExp().getTime()));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Retrieves all products from the database.
     *
     * @return A list of all products.
     */
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        try {
            String query = "SELECT * FROM Product order by name asc";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setId(resultSet.getInt("id"));
                    product.setName(resultSet.getString("name"));
                    product.setPrice(resultSet.getDouble("price"));
                    product.setStock_quantity(resultSet.getInt("stock_quantity"));
                    product.setQuantity_per_pack(resultSet.getInt("quantity_per_pack"));
                    product.setDescription(resultSet.getString("description"));
                    product.setCategory_code(resultSet.getInt("category_id"));
                    product.setExp(resultSet.getDate("expiryDate"));
                    productList.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

      /**
     * Updates an existing product in the database.
     *
     * @param product The updated Product object.
     * @param iid     The ID of the product to be updated.
     */
    public void updateProduct(Product product,int iid ) {
        try {
            String query = "UPDATE Product SET name=?, price=?, stock_quantity=?, " +
                    "quantity_per_pack=?, description=?, category_id=? WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, product.getName());
                preparedStatement.setDouble(2, product.getPrice());
                preparedStatement.setInt(3, product.getStock_quantity());
                preparedStatement.setInt(4, product.getQuantity_per_pack());
                preparedStatement.setString(5, product.getDescription());
                preparedStatement.setInt(6, product.getCategory_code());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                 preparedStatement.setInt(7,iid);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Deletes a product from the database.
     *
     * @param productId The ID of the product to be deleted.
     */
    public void deleteProduct(int productId) {
        try {
            String query = "DELETE FROM Product WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, productId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Checks if a product with the given ID exists in the database.
     *
     * @param productId The ID of the product.
     * @return True if the product exists, false otherwise.
     */
    public boolean productExists(int productId) {
        try {
            String query = "SELECT COUNT(*) FROM Product WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, productId);
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
     * Retrieves the price of a product from the database based on its ID.
     *
     * @param productId The ID of the product.
     * @return The price of the product.
     */
    public double getProductPriceFromDB(int productId) {
        double price = 0.0;
        try {
            String sql = "SELECT price FROM Product WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, productId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        price = resultSet.getDouble("price");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return price;
    }
    
    /**
     * Retrieves a Product object from the database based on its ID.
     *
     * @param productId The ID of the product.
     * @return The Product object.
     */
    public Product getProductFromDatabase(int productId) {
       try {
           String query = "SELECT * FROM Product WHERE id = ?";
           try (PreparedStatement statement = connection.prepareStatement(query)) {
               statement.setInt(1, productId);
               try (ResultSet resultSet = statement.executeQuery()) {
                   if (resultSet.next()) {
                       int id = resultSet.getInt("id");
                       int categoryCode = resultSet.getInt("category_id");
                       Date expiryDate = resultSet.getDate("expiryDate");
                       double price = resultSet.getDouble("price");
                       String name = resultSet.getString("name");
                       int stockQuantity = resultSet.getInt("stock_quantity");
                       int quantityPerPack = resultSet.getInt("quantity_per_pack");
                       String description = resultSet.getString("description");

                       return new Product(id, categoryCode, expiryDate, price, name, stockQuantity, quantityPerPack, description);
                   } else {
                       return null; 
                   }
               }
           }
       } catch (SQLException e) {

           return null;
       }
   }
    /**
     * Searches for products in the database based on their ID.
     *
     * @param searchId The ID to search for.
     * @return A list of matching products.
     */
    public List<Product> searchProductsById(int searchId) {
        List<Product> matchingProducts = new ArrayList<>();
        try {
            String query = "SELECT * FROM Product WHERE id LIKE ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, searchId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Product product = new Product();
                        product.setId(resultSet.getInt("id"));
                        product.setName(resultSet.getString("name"));
                        product.setPrice(resultSet.getDouble("price"));
                        product.setStock_quantity(resultSet.getInt("stock_quantity"));
                        product.setQuantity_per_pack(resultSet.getInt("quantity_per_pack"));
                        product.setDescription(resultSet.getString("description"));
                        product.setCategory_code(resultSet.getInt("category_id"));
                        product.setExp(resultSet.getDate("expiryDate"));
                        matchingProducts.add(product);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matchingProducts;
    }
    
     /**
     * Searches for products in the database based on their name.
     *
     * @param searchName The name to search for.
     * @return A list of matching products.
     */
    public List<Product> searchProductsByName(String searchName) {
        List<Product> matchingProducts = new ArrayList<>();
        try {
            if(Objects.equals(searchName, "")){
                return null;
            }
            String query = "SELECT * FROM Product WHERE name LIKE ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, "%"+searchName+"%");
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Product product = new Product();
                        product.setId(resultSet.getInt("id"));
                        product.setName(resultSet.getString("name"));
                        product.setPrice(resultSet.getDouble("price"));
                        product.setStock_quantity(resultSet.getInt("stock_quantity"));
                        product.setQuantity_per_pack(resultSet.getInt("quantity_per_pack"));
                        product.setDescription(resultSet.getString("description"));
                        product.setCategory_code(resultSet.getInt("category_id"));
                        product.setExp(resultSet.getDate("expiryDate"));
                        matchingProducts.add(product);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matchingProducts;
    }
    
    /**
    * Loads products into a JTable by retrieving all products from the database.
    * This method is not currently in use.
    */
    /***
     *
     *     public void loadProductsIntoTable() {
     *         List<Product> productList = new ProductDAO().getAllProducts();
     *         defaultTableModel.setRowCount(0);
     *         for (Product product : productList) {
     *             Object[] rowData = {
     *                     product.getId(),
     *                     product.getName(),
     *                     product.getDescription(),
     *                     product.getPrice(),
     *                     product.getQuantity_per_pack(),
     *                     product.getExp(),
     *                     product.getStock_quantity(),
     *                     new CategoryDAO().getNamebyID(product.getCategory_code())
     *             };
     *             defaultTableModel.addRow(rowData);
     *         }
     *     }
      */
    
    /**
    * Retrieves the maximum product ID from the Product table.
    *
    * @return The maximum product ID, or 0 if there is no maximum ID.
    */

    public int getMaxProductId() {
        try {
            String query = "SELECT MAX(id) FROM Product";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                if (resultSet.next()) {
                    int maxId = resultSet.getInt(1);
                    return maxId;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Return 0 if there is no maximum ID
        return 0;
    }
}
