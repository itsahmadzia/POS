/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package DBTest;

import BusinessLayer.Product;
import BusinessTest.*;
import BusinessTest.DatabaseConnectionTest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import DBLayer.ProductDAO;
import java.util.Date;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import org.junit.After;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class ProductDAOTest {
    private Connection connection;

    public static final String TESTING_JDBC_URL = "jdbc:mysql://localhost:3306/test_randomn";
    public static final String TESTING_USER = "ostechnix";
    public static final String TESTING_PASSWORD = "Password123#@!";
    private ProductDAO productDAO;

    @Before
    public void setUp() {
        DatabaseConnectionTest.init(TESTING_JDBC_URL, TESTING_USER, TESTING_PASSWORD);

        try {
            connection = DatabaseConnectionTest.getConnection();
            productDAO = new ProductDAO(connection);

            clearTestData();
            insertTestData();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get a connection to the testing database", e);
        }
    }
    
    private void insertTestData() {
        try {
            //category for test product
            String insertCategoryQuery = "INSERT INTO Category (id, name) VALUES (1, 'TestCategory')";
            try (PreparedStatement insertCategoryStatement = connection.prepareStatement(insertCategoryQuery)) {
                insertCategoryStatement.executeUpdate();
            }

            String insertProductQuery1 = "INSERT INTO Product (id, name, price, stock_quantity, quantity_per_pack, description, category_id, expiryDate) " +
                    "VALUES (1, 'TestProduct1', 10.99, 100, 20, 'Description1', 1, '2023-12-15 00:00:00')";
            try (PreparedStatement insertProductStatement1 = connection.prepareStatement(insertProductQuery1)) {
                insertProductStatement1.executeUpdate();
            }

            String insertProductQuery2 = "INSERT INTO Product (id, name, price, stock_quantity, quantity_per_pack, description, category_id, expiryDate) " +
                    "VALUES (2, 'TestProduct2', 19.99, 150, 30, 'Description2', 1, '2023-12-14 00:00:00')";
            try (PreparedStatement insertProductStatement2 = connection.prepareStatement(insertProductQuery2)) {
                insertProductStatement2.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearTestData() {
        try {
            String deleteProductsQuery = "DELETE FROM Product";
            try (PreparedStatement deleteProductsStatement = connection.prepareStatement(deleteProductsQuery)) {
                deleteProductsStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
               
          try {
              String deleteCategoryQuery = "DELETE FROM Category";
              try (PreparedStatement deleteCategoryStatement = connection.prepareStatement(deleteCategoryQuery)) {
                  deleteCategoryStatement.executeUpdate();
              }
          } catch (SQLException e) {
              e.printStackTrace();
          }
    }
           
    @After
    public void tearDown() {
        clearTestData();
    }



    @Test
    public void testInsertProduct() {
            Product testProduct = new Product();
            testProduct.setName("TestProductAspirin");
            testProduct.setPrice(19.99);
            testProduct.setStock_quantity(100);
            testProduct.setQuantity_per_pack(10);
            testProduct.setDescription("TestProductDescription");
            testProduct.setCategory_code(1); 
            testProduct.setExp(java.sql.Date.valueOf("2023-12-31"));

            productDAO.insertProduct(testProduct);

            int insertedProductId = testProduct.getId(); 
            Product insertedProduct = productDAO.getProductByID(insertedProductId);

            assertNotNull(insertedProduct);
            assertEquals("TestProductAspirin", insertedProduct.getName()); 
    }

    
    @Test
    public void testGetProductByID() {
         int existingProductId = 1; 
        Product existingProduct = productDAO.getProductByID(existingProductId);
        assertEquals("TestProduct1", existingProduct.getName()); // Update the expected name

       
        int nonExistingProductId = -1;
        Product nonExistingProduct = productDAO.getProductByID(nonExistingProductId);
        assertNull(nonExistingProduct); 
    }
    @Test
    public void testGetIDbyName() {
        String existingProductName = "TestProduct1"; 
        int existingProductId = productDAO.getIDbyName(existingProductName);
        assertNotEquals(-1, existingProductId);

        // non existing product
        String nonExistingProductName = "NonExistentProduct"; 
        int nonExistingProductId = productDAO.getIDbyName(nonExistingProductName);
        assertEquals(-1, nonExistingProductId);
    }
    
    @Test
    public void testGetAllProducts() {
       // insertTestData();

       List<Product> productList = productDAO.getAllProducts();
        System.out.println("Product List from Database:");
        for (Product product : productList) {
            System.out.println(product);
        }
        assertEquals(2, productList.size());

        Product retrievedProduct1 = productList.get(0);
        assertEquals("TestProduct1", retrievedProduct1.getName());

        Product retrievedProduct2 = productList.get(1);
        assertEquals("TestProduct2", retrievedProduct2.getName());
    }
    
    @Test
    public void testUpdateProduct() {
        //from insertTestData
        Product existingProduct = productDAO.getProductByID(1);
        assertNotNull(existingProduct);

        existingProduct.setName("UpdatedProduct");
        existingProduct.setPrice(15.99);
        existingProduct.setStock_quantity(150);
        existingProduct.setQuantity_per_pack(25);
        existingProduct.setDescription("UpdatedDescription");
        existingProduct.setCategory_code(1);

        productDAO.updateProduct(existingProduct, 1);
        System.out.println("Updated Product from Database:");
     
        Product updatedProduct = productDAO.getProductByID(1);
            System.out.println("Updated Product from Database:");
             System.out.println(updatedProduct);

        assertNotNull(updatedProduct);
        assertEquals("UpdatedProduct", updatedProduct.getName());
        assertEquals(15.99, updatedProduct.getPrice(), 0.01);
        assertEquals(150, updatedProduct.getStock_quantity());
        assertEquals(25, updatedProduct.getQuantity_per_pack());
        assertEquals("UpdatedDescription", updatedProduct.getDescription());
        assertEquals(1, updatedProduct.getCategory_code());
    }


    @Test
    public void testDeleteProduct() {
        Product testProduct = new Product();
        testProduct.setName("TestProductToDelete");
        testProduct.setPrice(29.99);
        testProduct.setStock_quantity(50);
        testProduct.setQuantity_per_pack(5);
        testProduct.setDescription("TestProductToDeleteDescription");
        testProduct.setCategory_code(1); 
        testProduct.setExp(java.sql.Date.valueOf("2023-12-31"));

        productDAO.insertProduct(testProduct);
        int insertedProductId = testProduct.getId();

        try {
            assertNotNull(productDAO.getProductByID(insertedProductId));
            productDAO.deleteProduct(insertedProductId);

            assertNull(productDAO.getProductByID(insertedProductId));
        } catch (Exception e) {
            fail("Exception thrown while testing deleteProduct: " + e.getMessage());
        }
    }


    @Test
    public void testProductExists() {
        Product testProduct = new Product();
        testProduct.setName("TestProductToCheckExistence");
        testProduct.setPrice(39.99);
        testProduct.setStock_quantity(75);
        testProduct.setQuantity_per_pack(8);
        testProduct.setDescription("TestProductToCheckExistenceDescription");
        testProduct.setCategory_code(1);
        testProduct.setExp(java.sql.Date.valueOf("2023-12-31"));

        productDAO.insertProduct(testProduct);

        int insertedProductId = testProduct.getId();
        assertTrue(productDAO.productExists(insertedProductId));
        productDAO.deleteProduct(insertedProductId);

        assertFalse(productDAO.productExists(insertedProductId));
    }
    @Test
    public void testSearchProductsById() {
        Product testProduct1 = new Product(11, 1, java.sql.Date.valueOf("2023-12-15"), 10.99, "TestProduct1", 100, 20, "Description1");
        Product testProduct2 = new Product(22, 1, java.sql.Date.valueOf("2023-12-14"), 19.99, "TestProduct2", 150, 30, "Description2");

        productDAO.insertProduct(testProduct1);
        productDAO.insertProduct(testProduct2);

   
        int searchId = 11; 
        List<Product> matchingProducts = productDAO.searchProductsById(searchId);

        System.out.println("matchingProducts  :");
        for (Product product : matchingProducts) {
            System.out.println(product);
        }

        assertEquals(1, matchingProducts.size());

        Product retrievedProduct = matchingProducts.get(0);
        System.out.println("Retrieved Product :");
        System.out.println(retrievedProduct);

        assertEquals(testProduct1.getId(), retrievedProduct.getId());
        assertEquals(testProduct1.getName(), retrievedProduct.getName());
        assertEquals(testProduct1.getPrice(), retrievedProduct.getPrice(), 0.01);
        assertEquals(testProduct1.getStock_quantity(), retrievedProduct.getStock_quantity());
        assertEquals(testProduct1.getQuantity_per_pack(), retrievedProduct.getQuantity_per_pack());
        assertEquals(testProduct1.getDescription(), retrievedProduct.getDescription());
        assertEquals(testProduct1.getCategory_code(), retrievedProduct.getCategory_code());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String expectedDateStr = dateFormat.format(testProduct1.getExp());
        String retrievedDateStr = dateFormat.format(retrievedProduct.getExp());
        assertEquals(expectedDateStr, retrievedDateStr);
    }
    

    @Test
    public void testSearchProductsByName() {
        String searchName = "TestProduct1"; //from insertTestData();
        List<Product> matchingProducts = productDAO.searchProductsByName(searchName);

        assertNotNull(matchingProducts);
        assertEquals(1, matchingProducts.size());

        Product retrievedProduct1 = matchingProducts.get(0);
        assertEquals(1, retrievedProduct1.getId());
        assertEquals("TestProduct1", retrievedProduct1.getName());
        assertEquals(10.99, retrievedProduct1.getPrice(), 0.01); 
        assertEquals(100, retrievedProduct1.getStock_quantity());
        assertEquals(20, retrievedProduct1.getQuantity_per_pack());
        assertEquals("Description1", retrievedProduct1.getDescription());
        assertEquals(1, retrievedProduct1.getCategory_code());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String expectedDateStr = "2023-12-15";
        String retrievedDateStr = dateFormat.format(retrievedProduct1.getExp());
        assertEquals(expectedDateStr, retrievedDateStr);
    }

}
