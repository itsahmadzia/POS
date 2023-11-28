package test.BusinessTest;

import BusinessLayer.Item;
import BusinessLayer.ItemContainer;
import BusinessLayer.Product;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemContainerTest {

    private ItemContainer itemContainer;

    @Before
    public void setUp() {
        itemContainer = new ItemContainer();
    }

    @Test
    public void testAddItem() {
        Item item = new Item(new Product(1, 1, null, 10.0, "Product1", 20, 5, "Description1"), 5);
        itemContainer.add(item);
        assertTrue(itemContainer.items.contains(item));
    }

    @Test
    public void testTotalPrice() {
        Item item1 = new Item(new Product(1, 1, null, 10.0, "Product1", 20, 5, "Description1"), 5);
        Item item2 = new Item(new Product(2, 1, null, 15.0, "Product2", 15, 3, "Description2"), 3);

        itemContainer.add(item1);
        itemContainer.add(item2);

        double expectedTotal = item1.getPrice() * item1.getQuantityorder() + item2.getPrice() * item2.getQuantityorder();
        assertEquals(expectedTotal, itemContainer.total_price(), 0.001);
    }

    @Test
    public void testRemoveItem() {
        Item item = new Item(new Product(1, 1, null, 10.0, "Product1", 20, 5, "Description1"), 5);
        itemContainer.add(item);

        itemContainer.remove(item);
        assertFalse(itemContainer.items.contains(item));
    }

    @Test
    public void testFindItemById() {
        Item item1 = new Item(new Product(1, 1, null, 10.0, "Product1", 20, 5, "Description1"), 5);
        Item item2 = new Item(new Product(2, 1, null, 15.0, "Product2", 15, 3, "Description2"), 3);

        itemContainer.add(item1);
        itemContainer.add(item2);

        assertEquals(item1, itemContainer.findItemById(1));
        assertEquals(item2, itemContainer.findItemById(2));
        assertNull(itemContainer.findItemById(3));
    }
}
