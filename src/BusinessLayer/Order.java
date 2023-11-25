package BusinessLayer;
import DBLayer.OrderDAO;
import java.util.Date;
import java.util.List;

public class Order extends ItemContainer{
    private int order_id;
    private String customerName;
    private String operatorName;
    private double total;
    Date timestamp ;
    OrderDAO OrderDAO;
    private double amountPaid;
    
    public Order(String customerName, Date timestamp, List<Item> items,double total,double amountPaid,String operatorName) {
        this.customerName = customerName;
        this.timestamp = timestamp;
        this.total = total;
        this.amountPaid = amountPaid;
        this.operatorName = operatorName;
        this.items.addAll(items);
        this.OrderDAO = new OrderDAO();
    }
    
    public Order(){
        this.OrderDAO = new OrderDAO();
    }

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
    
    public int saveOrder(Order order) {
        if (OrderDAO != null) {
            order_id = OrderDAO.saveOrder(order);
        } else {
            System.out.println("OrderDAO is null. Cannot save order.");
        }
        return order_id;
    }


    public double getAmountPaid(){
        return amountPaid;
    }
    public double getTotal(){
        return total;
    }
    public String getOperatorName() {
        return operatorName;
    }
    public void updateStockInDB(int orderId, String productName, int quantityOrdered) {
         OrderDAO.updateStock( orderId,  productName,  quantityOrdered);
    }

}
