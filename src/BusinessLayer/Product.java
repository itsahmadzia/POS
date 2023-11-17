public class Product implements Composite{
    private int id;
    private double price;//price per tablet

    private String name;
    private int stock_quantity;//total tablets available

    private int quantity_per_pack;//for a standard panadol we have 14 tablets

    private String description;


    Product(){

    }
    @Override
    public void display() {
        System.out.println("Product: " + name);
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


}
