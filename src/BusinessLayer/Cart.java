package BusinessLayer;

public class Cart extends ItemContainer{


public void clear(){
  super.ID=0;
  super.items.clear();

}
public Order generateOrder(){
    Order i = new Order();
    i.items=super.items;


return i;
}
}
