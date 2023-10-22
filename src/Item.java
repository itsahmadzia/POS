public abstract class  Item {
    private int quantityorder;
    Boolean pack;
    private double price;

    public void setPack(Boolean pack) {
        this.pack = pack;
    }
    Item(){}

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

    public void total(Product p) {
        if(pack)
         price = p.get_Price_of_one_pack() * quantityorder;
        else
            price = p.getPrice() * quantityorder;
    }
}
