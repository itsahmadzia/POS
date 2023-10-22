public  class  Item {
    private int quantityorder;
    private String itemname;
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

    public String getItemname() {
        return itemname;
    }

    public void total(Product p) {

        if(pack) {
            if(p.updateStock(p.getQuantity_per_pack()*quantityorder))
            {
                price = p.get_Price_of_one_pack() * quantityorder;
            }


        }
        else {
            if (p.updateStock(quantityorder))
            {
                price = p.getPrice() * quantityorder;
            }
        }
        itemname=p.getName();
    }
}
