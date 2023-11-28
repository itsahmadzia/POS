package BusinessLayer;

import DBLayer.ProductDAO;
import java.util.Date;
import java.util.List;

public class Product implements Composite{
    private int id;
    private int category_code;
    private Date exp;
    private double price;//price per tablet

    private String name;
    private int stock_quantity;//total tablets available
    private int quantity_per_pack;//for a standard panadol we have 14 tablets
    private String description;
    
    ProductDAO ProductDAO;
    
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
    public Product(int id) {
        this.id = id;
    }


    public Product(){
        this.ProductDAO = new ProductDAO();
    }
    @Override
    public void display() {
        System.out.println("Product: " + name);
        System.out.println("Expiry "+exp);
        System.out.println("Price   "+ price);

    }

    public void setCategory_code(int category_code) {
        this.category_code = category_code;
    }

    public int getCategory_code() {
        return category_code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock_quantity(int stock_quantity) {

        this.stock_quantity = stock_quantity;
    }

    public void setExp(Date exp) {
        this.exp = exp;
    }

    public Date getExp() {
        return exp;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public int getStock_quantity() {
        return stock_quantity;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity_per_pack() {
        return quantity_per_pack;
    }

    public void setQuantity_per_pack(int quantity_per_pack) {
        this.quantity_per_pack = quantity_per_pack;
    }


    public Boolean updateStock(int quantity_ordered){
        if(stock_quantity-quantity_ordered<0){
            return false;
        }

       stock_quantity  = stock_quantity-quantity_ordered;
        return true;
    }
    public Double getRemainingPackets(){
        return (double) (stock_quantity/quantity_per_pack);
    }

    public Double get_Price_of_one_pack(){
        return (double) (price*quantity_per_pack);
    }

    public double getProductPriceFromDB(int productId) {
        return price;
    }
    
    public boolean getPackFromDB(int productId, int orderedQuantity) {
        return getPackFromDB(productId, orderedQuantity);
    }//!!!!!!!!!!!!!!
    
    public boolean productExists(int productId) {
        return ProductDAO.productExists(productId);
    }
    public Product getProductFromDB(int productId) {
        return ProductDAO.getProductFromDatabase(productId);
    }
    public int getProductbyNameFromDB(String productName) {
        return ProductDAO.getIDbyName(productName);
    }
    
    public List<Product> searchProductsByNameFromDB(String searchName){
        return ProductDAO.searchProductsByName(searchName);
  
    }
    public List<Product> searchProductsByIdFromDB(int searchId){
        return ProductDAO.searchProductsById(searchId);
    
    }
     //for testing
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
