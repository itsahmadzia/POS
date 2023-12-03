package DBTest;

import BusinessLayer.*;
import BusinessTest.*;
import BusinessTest.DatabaseConnectionTest;
import DBLayer.*;
import java.sql.*; 

import java.util.Date;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import org.junit.After;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

public class OrderDAOTest {
    private Connection connection;

    public static final String TESTING_JDBC_URL = "jdbc:mysql://localhost:3306/test_randomn";
    public static final String TESTING_USER = "root";
    public static final String TESTING_PASSWORD = "r13Bne3@7";
    private ProductDAO productDAO;
    private OrderDAO orderDAO;
    private AdminDAO adminDAO;


    @Before
    public void setUp() {
        DatabaseConnectionTest.init(TESTING_JDBC_URL, TESTING_USER, TESTING_PASSWORD);

        try {
            connection = DBTest.DatabaseConnection.getConnectionToTest();  
            productDAO = new ProductDAO(connection);
            orderDAO = new OrderDAO(connection);
            adminDAO = new AdminDAO(connection);

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
           // e.printStackTrace();
            System.err.println("Error during testInsertTestData");
        }
    }

    private void clearTestData() {
        try {
            String deleteProductsQuery = "DELETE FROM Product";
            try (PreparedStatement deleteProductsStatement = connection.prepareStatement(deleteProductsQuery)) {
                deleteProductsStatement.executeUpdate();
            }

        } catch (SQLException e) {
           // e.printStackTrace();
           System.err.println("Error during clear:");
        }
               
          try {
              String deleteCategoryQuery = "DELETE FROM Category";
              try (PreparedStatement deleteCategoryStatement = connection.prepareStatement(deleteCategoryQuery)) {
                  deleteCategoryStatement.executeUpdate();
              }
          } catch (SQLException e) {
              e.printStackTrace();
          }
          
            try {
              String deleteorderItemQuery = "DELETE FROM order_t_Item";
              try (PreparedStatement deleteorderItemStatement = connection.prepareStatement(deleteorderItemQuery)) {
                  deleteorderItemStatement.executeUpdate();
              }
          } catch (SQLException e) {
              e.printStackTrace();
          }
            
              try {
              String deleteorderQuery = "DELETE FROM order_t";
              try (PreparedStatement deleteOrderStatement = connection.prepareStatement(deleteorderQuery)) {
                  deleteOrderStatement.executeUpdate();
              }
          } catch (SQLException e) {
              e.printStackTrace();
          }
              try {
              String deleteOperatorQuery = "DELETE FROM Operator";
              try (PreparedStatement deleteOpStatement = connection.prepareStatement(deleteOperatorQuery)) {
                  deleteOpStatement.executeUpdate();
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
    public void testOrder() {
        
        //product
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
            
        //order
        String customerName="TestCusotmer";
        User user = new User("TestOperator1","TestOperatorone","pass1");
        try {
            adminDAO.insertSalesAssistant(user);
        }
        catch (SQLException e){
        }
        
        Cart c = new Cart();
        Order order = new Order();
        Item current = new Item();
        current.setProduct(testProduct);
        current.setQuantityorder((20));
        current.setPack(false);
        current.setPrice(testProduct.getPrice()*20);
        current.total(testProduct);
        
        c.add(current);
        order = c.generateOrder(customerName, 19.99, 20, user.getUsername());
   
        OrderDAO orderDAO = new OrderDAO();
        int orderId = orderDAO.saveOrder(order);


        assertNotEquals(-1, orderId);

       /*
        List<Item> items = new ArrayList<>();
        items.add(current);
        
        assertEquals(1, items.size());
        assertEquals("TestProductAspirin", items.get(0).getProduct().getName());
        assertEquals(20, items.get(0).getQuantityorder());
        */
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
    
    
}
