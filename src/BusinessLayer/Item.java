package BusinessLayer;
/**
 * The Item class represents an item in an order. It is associated with a product
 * and includes information such as quantity ordered, whether it is packed,
 * and the total price.
 */
public  class  Item {
    private int quantityorder;
    private String itemname;
    Boolean pack = false;
    private double price;
    private Product product;
    
    /**
     * Constructs a new Item with the specified product and quantity ordered.
     *
     * @param product        The product associated with the item.
     * @param quantityOrdered The quantity of the product ordered.
     */
      public Item(Product product, int quantityOrdered) {
        this.product = product;
        this.quantityorder = quantityOrdered;
        this.pack = false; 
    }
      /**
        * Sets the pack status for this item.
        *
        * @param pack The packing status to be set. True if the item is packed, false otherwise.
        */
    public void setPack(Boolean pack) {
        this.pack = pack;
    }
     /**
     * Constructs an empty Item.
     */
    public Item(){}
    
    
    /**
     * Gets the quantity ordered for this item.
     *
     * @return The quantity ordered.
     */
    public int getQuantityorder() {
        return quantityorder;
    }
    /**
     * Sets the price of this item.
     *
     * @param price The price to be set.
     */

    public void setPrice(double price) {
        this.price = price;
    }
    /**
     * Sets the quantity ordered for this item, ensuring it is non-negative.
     *
     * @param quantityorder The quantity ordered to be set.
     */
    public void setQuantityorder(int quantityorder) {
          if(quantityorder>0)
        this.quantityorder = quantityorder;
          else {
              this.quantityorder=0;
          }
    }
     /**
     * Gets the packing status of this item.
     *
     * @return True if the item is packed, false otherwise.
     */
    public Boolean getPack() {
        return pack;
    }

    /**
     * Constructs a new Item with the specified quantity ordered.
     *
     * @param quantityorder The quantity ordered for the item.
     */
    public Item(int quantityorder) {
        setQuantityorder(quantityorder);
    }
      /**
     * Gets the price of this item.
     *
     * @return The price of the item.
     */
    public double getPrice() {
        return price;
    }
    /**
     * Gets the name of this item.
     *
     * @return The name of the item.
     */
    public String getItemname() {
        return itemname;
    }
    /**
     * Calculate and returns the total price for this item based on the associated product.
     *
     * @param p The associated product.
     * @return The total price for this item.
     */
    public double total(Product p) {
        if (pack) {
            if (p.updateStock(p.getQuantity_per_pack() * quantityorder)) {
                price = p.get_Price_of_one_pack() * quantityorder;
                itemname = p.getName();
            }
        } else {
            if (p.updateStock(quantityorder)) {
                price = p.getPrice() * quantityorder;
                itemname = p.getName();
            }
        }
        return price;
    }
    /**
     * Gets the associated product of this item.
     *
     * @return The associated product.
     */
     public Product getProduct() {
        return product;
    }
    /**
     * Sets the associated product for this item.
     *
     * @param p The product to be set.
     */
    public void setProduct(Product p) {
          product=p;
    }
    
}
