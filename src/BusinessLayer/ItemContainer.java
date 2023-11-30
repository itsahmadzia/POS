package BusinessLayer;
import java.util.ArrayList;
import java.util.List;
/**
 * The ItemContainer class represents a container for managing a collection of items.
 * It provides methods to add, remove, and find items, as well as calculate the total price
 * of all items in the container.
 */
public  class ItemContainer {
    protected int ID;
    public List<Item> items = new ArrayList<>();
     /**
     * Adds an item to the container.
     *
     * @param i The item to be added.
     */
    public void add(Item i) {
        items.add(i);
    }
    /**
     * Calculates the total price of all items in the container.
     *
     * @return The total price of all items.
     */
    public double total_price(){
        double sum=0;
        for(Item i : items){
          sum+=  i.getPrice();
        }
        return  sum;
    }
    /**
     * Removes an item from the container.
     *
     * @param i The item to be removed.
     */
    public void  remove(Item i){
        items.remove(i);
    } 
     /**
     * Finds an item in the container by its product ID.
     *
     * @param productId The product ID to search for.
     * @return The item with the specified product ID, or null if not found.
     */
    public Item findItemById(int productId) {
        for (Item item : items) {
            if (item.getProduct().getId() == productId) {
                return item;
            }
        }
        return null;
    }

}
