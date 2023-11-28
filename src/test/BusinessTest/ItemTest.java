/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package test.BusinessTest;

import BusinessLayer.Item;
import BusinessLayer.Product;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTest {

    private Product testProduct;

    @Before
    public void setUp() {
        testProduct = new Product(1, 1, null, 10.0, "Product1", 20, 5, "Description1");
    }

    @Test
    public void testConstructorWithProductAndQuantity() {
        Item item = new Item(testProduct, 5);

        assertEquals(testProduct, item.getProduct());
        assertEquals(5, item.getQuantityorder());
        assertFalse(item.getPack());
    }

    @Test
    public void testDefaultConstructor() {
        Item item = new Item();

        assertNull(item.getProduct());
        assertEquals(0, item.getQuantityorder());
        assertFalse(item.getPack());
    }

    @Test
    public void testSetPack() {
        Item item = new Item();
        item.setPack(true);

        assertTrue(item.getPack());
    }

    @Test
    public void testSetQuantityorder() {
        Item item = new Item();
        item.setQuantityorder(10);

        assertEquals(10, item.getQuantityorder());

        // Test that quantity can't be set to a negative value
        item.setQuantityorder(-5);
        assertEquals(0, item.getQuantityorder());
    }

    @Test
    public void testSetPrice() {
        Item item = new Item();
        item.setPrice(25.0);

        assertEquals(25.0, item.getPrice(), 0.001);
    }

    @Test
    public void testTotalForNonPack() {
        Item item = new Item(testProduct, 3);
        item.total(testProduct);

        assertEquals(testProduct.getPrice() * 3, item.getPrice(), 0.001);
        assertEquals(testProduct.getName(), item.getItemname());
    }

    @Test
    public void testTotalForPack() {
        testProduct.setQuantity_per_pack(10);
        Item item = new Item(testProduct, 2);
        item.setPack(true);
        item.total(testProduct);

        assertEquals(testProduct.get_Price_of_one_pack() * 2, item.getPrice(), 0.001);
        assertEquals(testProduct.getName(), item.getItemname());
    }

    @Test
    public void testSetAndGetProduct() {
        Item item = new Item();
        item.setProduct(testProduct);

        assertEquals(testProduct, item.getProduct());
    }
}
