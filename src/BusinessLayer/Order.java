import java.util.Date;

public class Order extends ItemContainer{
    private int order_id;
    private String customerName;
    private double total;
    Date timestamp ;

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getOrder_id() {
        return order_id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Date getTimestamp() {
        return timestamp;
    }
    public void cancel(){

    }
    public String generateInvoice(){
        double sum=0;
        StringBuilder text= new StringBuilder();
        text.append("OrDEr Id: ").append(order_id).append("\n");
        text.append("Timee : ").append(timestamp).append("\n");
        text.append("Name : ").append(customerName).append("\n");
        for(Item i : super.items){
            text.append("name  ").append(i.getItemname()).append("                  Quantity ").append(i.getQuantityorder()).append("                   Price ").append(i.getPrice()).append("\n");
       sum+=i.getPrice();
        }
        total=sum;
        text.append("               Total ").append(total) ;
        return text.toString();
    }
}
