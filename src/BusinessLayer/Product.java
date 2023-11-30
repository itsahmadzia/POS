package BusinessLayer;

import DBLayer.ProductDAO;
import java.util.Date;
import java.util.List;
/**
 * The Product class represents a product. It contains
 * information about the product, such as ID, category code, expiration date,
 * price, name, stock quantity, quantity per pack, and description. This class
 * also interacts with the database through the ProductDAO to perform various
 * operations.
 */
public class Product implements Composite{
    private int id;
    private int category_code;
    private Date exp;
    private double price;//price per tablet

    private String name;
    private int stock_quantity;//total tablets available
    private int quantity_per_pack;//for a standard panadol we have 14 tablets
    private String description;
    /**
    * The data access object (DAO) for performing database operations related to products.
    */
    ProductDAO ProductDAO;
    /**
     * Constructs a new Product with the specified parameters.
     *
     * @param id              The unique id for the product.
     * @param category_code   The category code of the product.
     * @param exp             The expiration date of the product.
     * @param price           The price per tablet of the product.
     * @param name            The name of the product.
     * @param stock_quantity  The total tablets available.
     * @param quantity_per_pack The quantity per pack for the product.
     * @param description     A brief description of the product.
     */
    public Product(int id, int category_code, Date exp, double price, String name,int stock_quantity,int quantity_per_pack,String description){
        this.id = id;
        this.category_code = category_code;
        this.exp = exp;
        this.price = price;
        this.name = name;
        this.stock_quantity = stock_quantity;
        this.quantity_per_pack = quantity_per_pack;
        this.description = description;
        this.ProductDAO = new ProductDAO();
    
    }
    /**
    * Constructs a new Product with the specified ID.
    *
    * @param id The unique identifier for the product.
    */
    public Product(int id) {
        this.id = id;
    }

    /**
     * Constructs an empty Product.
     */
    public Product(){
        this.ProductDAO = new ProductDAO();
    }
     /**
     * Displays information about the product.
     */
    @Override
    public void display() {
        System.out.println("Product: " + name);
        System.out.println("Expiry "+exp);
        System.out.println("Price   "+ price);

    }
    /**
     * Sets the category code of the product.
     *
     * @param category_code The category code to set.
     */

    public void setCategory_code(int category_code) {
        this.category_code = category_code;
    }
    /**
     * Gets the category code of the product.
     *
     * @return The category code of the product.
     */
    public int getCategory_code() {
        return category_code;
    }
    /**
     * Sets the name of the product.
     *
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Sets the ID of the product.
     *
     * @param id The ID to set.
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Sets the description of the product.
     *
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }
     /**
     * Sets the price per tablet of the product.
     *
     * @param price The price to set.
     */
    public void setPrice(double price) {
        this.price = price;
    }
    /**
     * Sets the stock quantity of the product.
     *
     * @param stock_quantity The stock quantity to set.
     */
    public void setStock_quantity(int stock_quantity) {

        this.stock_quantity = stock_quantity;
    }
    /**
     * Sets the expiration date of the product.
     *
     * @param exp The expiration date to set.
     */
    public void setExp(Date exp) {
        this.exp = exp;
    }
    /**
     * Gets the expiration date of the product.
     *
     * @return The expiration date of the product.
     */
    public Date getExp() {
        return exp;
    }
    /**
     * Gets the name of the product.
     *
     * @return The name of the product.
     */
    public String getName() {
        return name;
    }
    /**
     * Gets the price per tablet of the product.
     *
     * @return The price per tablet of the product.
     */
    public double getPrice() {
        return price;
    }
    /**
     * Gets the ID of the product.
     *
     * @return The ID of the product.
     */
    public int getId() {
        return id;
    }
    /**
     * Gets the stock quantity of the product.
     *
     * @return The stock quantity of the product.
     */
    public int getStock_quantity() {
        return stock_quantity;
    }
    /**
     * Gets the description of the product.
     *
     * @return The description of the product.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Gets the quantity per pack of the product.
     *
     * @return The quantity per pack of the product.
     */
    public int getQuantity_per_pack() {
        return quantity_per_pack;
    }
    /**
     * Sets the quantity per pack of the product.
     *
     * @param quantity_per_pack The quantity per pack to set.
     */
    public void setQuantity_per_pack(int quantity_per_pack) {
        this.quantity_per_pack = quantity_per_pack;
    }

    /**
     * Updates the stock quantity of the product based on the ordered quantity.
     *
     * @param quantity_ordered The quantity ordered.
     * @return True if the update is successful, false otherwise.
     */
    public Boolean updateStock(int quantity_ordered){
        if(stock_quantity-quantity_ordered<0){
            return false;
        }

       stock_quantity  = stock_quantity-quantity_ordered;
        return true;
    }
    /**
     * Calculates and returns the remaining packets of the product.
     *
     * @return The remaining packets of the product.
     */
    public Double getRemainingPackets(){
        return (double) (stock_quantity/quantity_per_pack);
    }
    
    /**
     * Calculates and returns the price of one pack of the product.
     *
     * @return The price of one pack of the product.
     */
    public Double get_Price_of_one_pack(){
        return (double) (price*quantity_per_pack);
    }
    
    /**
     * Gets the price of the product from the database.
     *
     * @param productId The ID of the product.
     * @return The price of the product.
     */
    public double getProductPriceFromDB(int productId) {
        return price;
    }
    /*
    public boolean getPackFromDB(int productId, int orderedQuantity) {
        return getPackFromDB(productId, orderedQuantity);
    }*/
    
    /**
    * Checks whether a product with the specified ID exists in the database.
    *
    * @param productId The ID of the product to check.
    * @return True if the product exists, false otherwise.
    */
    
    public boolean productExists(int productId) {
        return ProductDAO.productExists(productId);
    }
    /**
    * Gets a Product object from the database based on the specified product ID.
    *
    * @param productId The ID of the product to retrieve.
    * @return The Product object representing the specified product.
    */
    public Product getProductFromDB(int productId) {
        return ProductDAO.getProductFromDatabase(productId);
    }
    /**
    * Gets the product ID based on the specified product name from the database.
    *
    * @param productName The name of the product to search for.
    * @return The ID of the product with the specified name.
    */
    public int getProductbyNameFromDB(String productName) {
        return ProductDAO.getIDbyName(productName);
    }
    
    /**
    * Searches for products in the database based on the specified product name.
    *
    * @param searchName The name to search for in product names.
    * @return A list of Product objects matching the search criteria.
    */
    public List<Product> searchProductsByNameFromDB(String searchName){
        return ProductDAO.searchProductsByName(searchName);
  
    }
    /**
    * Searches for product in the database based on the specified product ID.
    *
    * @param searchId The ID to search for in product IDs.
    * @return A list of Product objects matching the search criteria.
    */
    public List<Product> searchProductsByIdFromDB(int searchId){
        return ProductDAO.searchProductsById(searchId);
    
    }

    /**
     * Returns a string representation of the product.
     *
     * @return A string containing information about the product.
     */
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", categoryId=" + category_code +
                ", expiryDate=" + exp +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", stockQuantity=" + stock_quantity +
                ", quantityPerPack=" + quantity_per_pack +
                ", description='" + description + '\'' +
                '}';
    }
}
