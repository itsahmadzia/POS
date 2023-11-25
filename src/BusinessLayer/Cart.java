package BusinessLayer;

import java.util.Date;
import java.util.List;

public class Cart extends ItemContainer{
    
    public void clear(){
      super.ID=0;
      super.items.clear();

    }
    
    public List<Item> getItems() {
        return items;
    }
    
    public Order generateOrder(String customerName, double totalAmountDue, double amountPaid,String operatorName) {
        
        Order order = new Order(customerName, new Date(), this.items,totalAmountDue,amountPaid,operatorName);
        order.items = super.items;
        return order;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
    
}
