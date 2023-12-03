package test.BusinessTest;

import BusinessLayer.Category;
import BusinessLayer.Composite;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CategoryTest {

    private Category category; 

    @Before
    public void setUp() {
        category = new Category(1, "C1",  "Description for C1");
    }

    @Test
    public void testAddComponent() {
        Category childCategory = new Category(2, "Child Category", "Child Description");

        category.addComponent(childCategory);
        assertTrue(category.getChildren().contains(childCategory));
    }

    @Test
    public void testDisplay() {
        Category childCategory = new Category(2, "Child Category", "Child Description");
        category.addComponent(childCategory);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        category.display();
        System.setOut(System.out);

        assertTrue(outContent.toString().contains("Category: C1"));
        assertTrue(outContent.toString().contains("Category: Child Category"));
    }

    @Test
    public void testSettersAndGetters() {
        category.setCode(1);
        category.setName("Test Category");
        category.setDescription("Test Description");
        category.setParentCategoryName("Parent Category");

        assertEquals(1, category.getCode());
        assertEquals("Test Category", category.getName());
        assertEquals("Test Description", category.getDescription());
        assertEquals("Parent Category", category.getParentCategoryName());
    }

    @Test
    public void testGetChildren() {
        Category subCategory1 = new Category(11, "c1", "Description for c1");
        Category subCategory2 = new Category(12, "c2", "Description for c2");

        category.addComponent(subCategory1);
        category.addComponent(subCategory2);

        List<Composite> children = category.getChildren();

        assertEquals(2, children.size());
        assertTrue(children.contains(subCategory1));
        assertTrue(children.contains(subCategory2));
    }
}
