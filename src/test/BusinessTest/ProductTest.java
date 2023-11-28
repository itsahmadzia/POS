/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package test.BusinessTest;

import BusinessLayer.Product;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest {

    @Test
    public void testUpdateStock() {
        Product product = new Product();
        product.setStock_quantity(10);

        assertTrue(product.updateStock(5));
        assertEquals(5, product.getStock_quantity());

        assertFalse(product.updateStock(10)); //order more than in stock ,fail
        assertEquals(5, product.getStock_quantity()); 
    }

    @Test
    public void testGetRemainingPackets() {
        Product product = new Product();
        product.setStock_quantity(20);
        product.setQuantity_per_pack(5);

        assertEquals(4.0, product.getRemainingPackets(), 0.01);
    }

    @Test
    public void testGet_Price_of_one_pack() {
        Product product = new Product();
        product.setPrice(2.5);
        product.setQuantity_per_pack(10);

        assertEquals(25.0, product.get_Price_of_one_pack(), 0.01);
    }

}
