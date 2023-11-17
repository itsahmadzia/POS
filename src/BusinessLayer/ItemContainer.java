import java.util.ArrayList;
import java.util.List;

public  class ItemContainer {
protected int ID;
protected List<Item> items = new ArrayList<>();


public void  add(Item i){
items.add(i);
}
public double total_price(){
    double sum=0;
    for(Item i : items){
      sum+=  i.getPrice();
    }
    return  sum;
}

}
