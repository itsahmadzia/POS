/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package test.DBTest;

import BusinessLayer.Category;
import DBLayer.CategoryDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import test.BusinessTest.DatabaseConnectionTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class CategoryDAOTest {
    private Connection connection;

    public static final String TESTING_JDBC_URL = "jdbc:mysql://localhost:3306/test_randomn";
    public static final String TESTING_USER = "ostechnix";
    public static final String TESTING_PASSWORD = "Password123#@!";
    private CategoryDAO categoryDAO;

    @Before
    public void setUp() {
        DatabaseConnectionTest.init(TESTING_JDBC_URL, TESTING_USER, TESTING_PASSWORD);

        try {
            connection = DatabaseConnectionTest.getConnection();
            categoryDAO = new CategoryDAO(connection);

             clearTestData();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get a connection to the testing database", e);
        }
    }
    
    @After
    public void tearDown() {
        clearTestData(); 
    }

     @Test
    public void testGetProductsInCategory() {
        int categoryId = 1;
        String categoryName = "TestCategory";
        String product1Name = "Product1";
        String product2Name = "Product2";

        categoryDAO.insertParentCategory(categoryId, categoryName, "Category Description");
        insertTestProduct(1, product1Name, categoryId);
        insertTestProduct(2, product2Name, categoryId);

        List<String> productList = categoryDAO.getProductsInCategory(categoryId);

        assertNotNull(productList);
        assertEquals(2, productList.size());
        assertTrue(productList.contains(product1Name));
        assertTrue(productList.contains(product2Name));

        System.out.println("Products in category:");
        for (String product : productList) {
            System.out.println(product);
        }
    }
    
    
    private void insertTestProduct(int productId, String productName, int categoryId) {
        try {
            String insertProductQuery = "INSERT INTO Product (id, name, category_id, price, stock_quantity, quantity_per_pack) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertProductStatement = connection.prepareStatement(insertProductQuery)) {
                insertProductStatement.setInt(1, productId);
                insertProductStatement.setString(2, productName);
                insertProductStatement.setInt(3, categoryId);
                insertProductStatement.setDouble(4, 22);
                insertProductStatement.setInt(5, 20);
                insertProductStatement.setInt(6, 1); 
                insertProductStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
     @Test
    public void testGetProductsInCategoryTillLeaf() {
        int categoryId = 2;
        
        String categoryName = "TestCategory 2";
        String product1Name = "Product3";
        String product2Name = "Product4";

        categoryDAO.insertParentCategory(categoryId, categoryName, "Category Description2");
        insertTestProduct(3, product1Name, categoryId);
        insertTestProduct(4, product2Name, categoryId);

        System.out.println("testGetProductsInCategoryTillLeaf");

        List<String> productList = categoryDAO.getProductsInCategorytillleaf(categoryId);

        assertNotNull(productList);
        assertEquals(2, productList.size());
        assertTrue(productList.contains(product1Name));
        assertTrue(productList.contains(product2Name));

        System.out.println("Products in category till leaf:");
        for (String product : productList) {
            System.out.println(product);
        }
    }
    
    /*
      @Test
    public void testGetProductsInCategoryTillLeaf() {
        CategoryDAO categoryDAO = new CategoryDAO();

        int rootCategoryId = 24;
        String rootCategoryName = "RootCategory2";
        int subCategoryId = 25;
        String subCategoryName = "SubCategory2";
        String product1Name = "Product1";
        String product2Name = "Product2";

        // Insert test data into the database
        categoryDAO.insertParentCategory(rootCategoryId, rootCategoryName, "Root Category Description");
        categoryDAO.insertCategory(subCategoryId, subCategoryName, rootCategoryId, "Sub Category Description");
        insertTestProduct(1, product1Name, subCategoryId);
        insertTestProduct(2, product2Name, subCategoryId);

        System.out.println("Inserted test data successfully.");

        // Call the method being tested
        List<String> productList = categoryDAO.getProductsInCategorytillleaf(rootCategoryId);

        // Perform assertions to verify that the list contains the expected product names
        assertNotNull(productList);
        assertEquals(2, productList.size());
        assertTrue(productList.contains(product1Name));
        assertTrue(productList.contains(product2Name));

        System.out.println("Products in category till leaf:");
        for (String product : productList) {
            System.out.println(product);
        }
    }

*/
    @Test
    public void testInsertCategory() {
               int categoryId = 3;
                  if (!categoryDAO.categoryExists(categoryId)) {
                String categoryName = "TestCategory3";
                String categoryDescription = "Category Description3";

                categoryDAO.insertCategory(categoryId, categoryName, null, categoryDescription);

                Category retrievedCategory = categoryDAO.getCategoryById(categoryId);

                assertNotNull(retrievedCategory);
                assertEquals(categoryId, retrievedCategory.getCode());
                assertEquals(categoryName, retrievedCategory.getName());
                assertNull(retrievedCategory.getParentCategoryName()); // root
                assertEquals(categoryDescription, retrievedCategory.getDescription());
         } else {
            System.out.println("Category with ID " + categoryId + " already exists.");
        }
         
   }

    @Test
    public void testInsertParentCategory() {
        int categoryId = 4;
        String categoryName = "TestParentCategory";
        String categoryDescription = "Parent Category Description";

        categoryDAO.insertParentCategory(categoryId, categoryName, categoryDescription);

        Category retrievedCategory = categoryDAO.getCategoryById(categoryId);

        assertNotNull(retrievedCategory);
        assertEquals(categoryId, retrievedCategory.getCode());
        assertEquals(categoryName, retrievedCategory.getName());
        assertNull(retrievedCategory.getParentCategoryName()); //no parent 
        assertEquals(categoryDescription, retrievedCategory.getDescription());
    }

    @Test
    public void testCategoryExists() {
        int categoryId = 5;
        String categoryName = "TestCategoryExists";
        String categoryDescription = "TCategoryExists Description";

        categoryDAO.insertCategory(categoryId, categoryName, null, categoryDescription);

        boolean exists = categoryDAO.categoryExists(categoryId);
        assertTrue(exists);
    }

    @Test
    public void testCategoryNameExists() {
        int categoryId = 6;
        String categoryName = "TestCategory6";
        String categoryDescription = "Category Description6";

        categoryDAO.insertCategory(categoryId, categoryName, null, categoryDescription);

        boolean exists = categoryDAO.categoryNameExists(categoryName);
        assertTrue(exists);
    }

    @Test
    public void testDeleteCategory() {
        int categoryId = 7;
        String categoryName = "TestCategory7";
        String categoryDescription = "Category Description7";

        categoryDAO.insertCategory(categoryId, categoryName, null, categoryDescription);
        categoryDAO.deleteCategory(categoryId);

        boolean exists = categoryDAO.categoryExists(categoryId);
        assertFalse(exists);
    }

    @Test
    public void testUpdateCategory() {
        int categoryId = 8;
        String categoryName = "TestCategory8";
        String categoryDescription = "Category Description8";
        categoryDAO.insertCategory(categoryId, categoryName, null, categoryDescription);

        String newCategoryName = "UpdatedCategory";
        String newCategoryDescription = "Updated Category Description";
        categoryDAO.updateCategory(categoryId, newCategoryName, null, newCategoryDescription);

        Category updatedCategory = categoryDAO.getCategoryById(categoryId);

        assertNotNull(updatedCategory);
        assertEquals(categoryId, updatedCategory.getCode());
        assertEquals(newCategoryName, updatedCategory.getName());
        assertNull(updatedCategory.getParentCategoryName());
        assertEquals(newCategoryDescription, updatedCategory.getDescription());
    }

    @Test
    public void testGetAllCategories() {

        int categoryId1 = 9;
        String categoryName1 = "TestCategory9";
        String categoryDescription1 = "Category Description9";
        categoryDAO.insertCategory(categoryId1, categoryName1, null, categoryDescription1);

        int categoryId2 = 10;
        String categoryName2 = "TestCategory10";
        String categoryDescription2 = "Category Description 10";
        categoryDAO.insertCategory(categoryId2, categoryName2, categoryId1, categoryDescription2);

        List<Category> categoryList = categoryDAO.getAllCategories();

        assertNotNull(categoryList);
        assertEquals(2, categoryList.size());

        Category retrievedCategory1 = categoryList.get(0);
        assertEquals(categoryId1, retrievedCategory1.getCode());
        assertEquals(categoryName1, retrievedCategory1.getName());
        assertNull(retrievedCategory1.getParentCategoryName());
        assertEquals(categoryDescription1, retrievedCategory1.getDescription());


        Category retrievedCategory2 = categoryList.get(1);
        assertEquals(categoryId2, retrievedCategory2.getCode());
        assertEquals(categoryName2, retrievedCategory2.getName());
        assertEquals(categoryName1, retrievedCategory2.getParentCategoryName());
        assertEquals(categoryDescription2, retrievedCategory2.getDescription());
    }

    @Test
    public void testGetCategoryById() {
        int categoryId = 11;
        String categoryName = "TestCategory11";
        String categoryDescription = "Category Description11";
        categoryDAO.insertCategory(categoryId, categoryName, null, categoryDescription);

        Category retrievedCategory = categoryDAO.getCategoryById(categoryId);

        assertNotNull(retrievedCategory);
        assertEquals(categoryId, retrievedCategory.getCode());
        assertEquals(categoryName, retrievedCategory.getName());
        assertNull(retrievedCategory.getParentCategoryName());
        assertEquals(categoryDescription, retrievedCategory.getDescription());
    }

    @Test
    public void testGetAllCategoriesName() {
        int categoryId1 = 12;
        String categoryName1 = "Parent Category12";
        categoryDAO.insertCategory(categoryId1, categoryName1, null, "Category Description 1");

        int categoryId2 = 13;
        String categoryName2 = "Test Category12";
        categoryDAO.insertCategory(categoryId2, categoryName2, categoryId1, "Category Description 2");

        List<String> categoryNameList = categoryDAO.getAllCategoriesName();

        assertNotNull(categoryNameList);
        assertEquals(2, categoryNameList.size());
        assertTrue(categoryNameList.contains(categoryName1));
        assertTrue(categoryNameList.contains(categoryName2));
    }

    @Test
    public void testGetCategoryCodeByName() {
        int categoryId = 14;
        String categoryName = "TestCategory14";
        categoryDAO.insertCategory(categoryId, categoryName, null, "Category Description14");

        Integer retrievedCategoryId = categoryDAO.getCategoryCodebyName(categoryName);

        assertNotNull(retrievedCategoryId);
        assertEquals(categoryId, retrievedCategoryId.intValue());
    }

   
    @Test
    public void testGetNamebyID() {
        int categoryId = 1;
        String categoryName = "TestCategory15";
        categoryDAO.insertCategory(categoryId, categoryName, null, "Category Description15");

        String retrievedName = (String) categoryDAO.getNamebyID(categoryId);

        assertNotNull(retrievedName);
        assertEquals(categoryName, retrievedName);
    }
    
    private void clearTestData() {
        try {
            String deleteProductsQuery = "DELETE FROM Product";
            try (PreparedStatement deleteProductsStatement = connection.prepareStatement(deleteProductsQuery)) {
                deleteProductsStatement.executeUpdate();
            }
            
            String deleteCategoriesQuery = "DELETE FROM Category";
            try (PreparedStatement deleteCategoriesStatement = connection.prepareStatement(deleteCategoriesQuery)) {
                deleteCategoriesStatement.executeUpdate();
            }

        

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
