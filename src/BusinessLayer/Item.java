package BusinessLayer;
public  class  Item {
    private int quantityorder;
    private String itemname;
    Boolean pack;
    private double price;
    private Product product;
    
      public Item(Product product, int quantityOrdered) {
        this.product = product;
        this.quantityorder = quantityOrdered;
        this.pack = false; 
    }

    public void setPack(Boolean pack) {
        this.pack = pack;
    }
    public Item(){}

    public int getQuantityorder() {
        return quantityorder;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantityorder(int quantityorder) {
          if(quantityorder>0)
        this.quantityorder = quantityorder;
          else {
              this.quantityorder=0;
          }
    }

    public Boolean getPack() {
        return pack;
    }


    public Item(int quantityorder) {
        setQuantityorder(quantityorder);
    }

    public double getPrice() {
        return price;
    }

    public String getItemname() {
        return itemname;
    }

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
     public Product getProduct() {
        return product;
    }

    public void setProduct(Product p) {
          product=p;
    }
}
