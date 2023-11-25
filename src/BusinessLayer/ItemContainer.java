package BusinessLayer;
import java.util.ArrayList;
import java.util.List;

public  class ItemContainer {
    protected int ID;
    public List<Item> items = new ArrayList<>();

    public void add(Item i) {
        items.add(i);
    }

    public double total_price(){
        double sum=0;
        for(Item i : items){
          sum+=  i.getPrice();
        }
        return  sum;
    }
    
    public void  remove(Item i){
        items.remove(i);
    } 
    
    public Item findItemById(int productId) {
        for (Item item : items) {
            if (item.getProduct().getId() == productId) {
                return item;
            }
        }
        return null;
    }

}
