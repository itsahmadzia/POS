
package BusinessTest;

import BusinessLayer.Item;
import BusinessLayer.Order;
import BusinessLayer.Product;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderTest {

    private Order order;

    @Before
    public void setUp() {
        List<Item> items = new ArrayList<>();
        
        Product product1 = new Product(-1, -1, null, 10.0, "Product1", 0, 0, "");
        Product product2 = new Product(-1, -1, null, 15.0, "Product2", 0, 0, "");

        items.add(new Item(product1, 2));
        items.add(new Item(product2, 3));

        order = new Order("Customer1", new Date(), items, 0.0, 0.0, "Operator1");
    }

    @Test
    public void testGetOrder_id() {
        order.setOrder_id(123);
        assertEquals(123, order.getOrder_id());
    }

    @Test
    public void testGetCustomerName() {
        assertEquals("Customer1", order.getCustomerName());
    }

    @Test
    public void testSetTimestamp() {
        Date newTimestamp = new Date();
        order.setTimestamp(newTimestamp);
        assertEquals(newTimestamp, order.getTimestamp());
    }

    @Test
    public void testGenerateInvoice() {
      String invoice = order.generateInvoice();
        assertTrue(invoice.contains("Order Id"));
        assertTrue(invoice.contains("Timee"));
        assertTrue(invoice.contains("Name"));
        assertTrue(invoice.contains("Total"));
    }

    // this test fails 
    // price column in order_t_item trying to ref a price value in the product table
    @Test
    public void testSaveOrder() {
        int orderId = order.saveOrder(order);
        assertTrue(orderId > 0);
    }
}