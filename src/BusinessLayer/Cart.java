package BusinessLayer;

import java.util.Date;
import java.util.List;
/**
 * The Cart class represents a cart containing a collection of items.
 * It extends the ItemContainer class and provides methods to manage and process cart items.
 */
public class Cart extends ItemContainer{
     /**
     * Default constructor for the Cart class.
     * An empty constructor.
     */
    public Cart() {
    }
     /**
     * Clears the cart by resetting the ID and removing all items.
     */
    public void clear(){
      super.ID=0;
      super.items.clear();

    }
    /**
     * Gets the list of items in the cart.
     *
     * @return The list of items in the cart.
     */
    public List<Item> getItems() {
        return items;
    }
    /**
     * Generates an order from the cart with the specified customer name, total amount due,
     * amount paid, and operator name.
     *
     * @param customerName    The name of the customer placing the order.
     * @param totalAmountDue  The total amount due for the order.
     * @param amountPaid      The amount paid by the customer.
     * @param operatorName    The name of the operator processing the order.
     * @return The generated order.
     */
    public Order generateOrder(String customerName, double totalAmountDue, double amountPaid,String operatorName) {
        
        Order order = new Order(customerName, new Date(), this.items,totalAmountDue,amountPaid,operatorName);
        order.items = super.items;
        return order;
    }
    /**
     * Checks if the cart is empty.
     *
     * @return True if the cart is empty, false otherwise.
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
}
