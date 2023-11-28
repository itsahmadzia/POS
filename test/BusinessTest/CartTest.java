/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package BusinessTest;

import BusinessLayer.Cart;
import BusinessLayer.Item;
import BusinessLayer.Order;
import BusinessLayer.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CartTest {

    private Cart cart;

    @Before
    public void setUp() {
        cart = new Cart();
    }

    @Test
    public void testClear() {
        Product product1 = new Product(1, 1, null, 10.0, "Product1", 20, 5, "Description1");
        Item item1 = new Item(product1, 2);
        cart.add(item1);

        //System.out.println("");
        cart.clear();

        assertTrue(cart.isEmpty());
        assertEquals(0, cart.getItems().size());
    }


    @Test
    public void testGenerateOrder() {
        Product product1 = new Product(1, 1, null, 10.0, "Product1", 20, 5, "Description1");

        Item item1 = new Item(product1, 2);
        cart.add(item1);

        String customerName = "James";
        double totalAmountDue = 50.0;
        double amountPaid = 60.0;
        String operatorName = "Operator";

        Order order = cart.generateOrder(customerName, totalAmountDue, amountPaid, operatorName);

        assertEquals(customerName, order.getCustomerName());
        assertEquals(totalAmountDue, order.getTotal(), 0.001);
        assertEquals(amountPaid, order.getAmountPaid(), 0.001);
        assertEquals(operatorName, order.getOperatorName());
        assertEquals(cart.getItems(), order.items);
    }


    @Test
    public void testIsEmpty() {
        assertTrue(cart.isEmpty());
        Product product1 = new Product(1, 1, null, 10.0, "Product1", 20, 5, "Description1");

        Item item1 = new Item(product1, 2);
        cart.add(item1);

        assertFalse(cart.isEmpty());
    }


    private void addItemToCart(Cart cart, Product product, int quantity) {
        Item item = new Item(product, quantity);
        cart.add(item);
    }
}
