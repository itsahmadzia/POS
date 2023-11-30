package BusinessLayer;
import DBLayer.OrderDAO;
import java.util.Date;
import java.util.List;
/**
 * The Order class represents a customer order containing a collection of items.
 * It extends the ItemContainer class and provides methods to manage and the process order.
 */
public class Order extends ItemContainer{
    private int order_id;
    private String customerName;
    private String operatorName;
    private double total;
    Date timestamp ;
    OrderDAO OrderDAO;
    private double amountPaid;
    
    /**
     * Constructs a new Order with the specified customer information, timestamp, items, total amount,
     * amount paid, and operator name.
     *
     * @param customerName The name of the customer placing the order.
     * @param timestamp    The timestamp indicating when the order was placed.
     * @param items        The list of items included in the order.
     * @param total        The total amount of the order.
     * @param amountPaid   The amount paid by the customer.
     * @param operatorName The name of the operator/sales assistant processing the order.
     */
    public Order(String customerName, Date timestamp, List<Item> items,double total,double amountPaid,String operatorName) {
        this.customerName = customerName;
        this.timestamp = timestamp;
        this.total = total;
        this.amountPaid = amountPaid;
        this.operatorName = operatorName;
        this.items.addAll(items);
        this.OrderDAO = new OrderDAO();
    }
    /**
     * Constructs an empty Order.
     */
    public Order(){
        this.OrderDAO = new OrderDAO();
    }
    /**
     * Sets the order ID.
     *
     * @param order_id The unique id for the order.
     */
    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
    /**
     * Sets the name of the customer.
     *
     * @param customerName The name of the customer.
     */

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    /**
     * Gets the order ID.
     *
     * @return The unique id for the order.
     */
    public int getOrder_id() {
        return order_id;
    }
    /**
    * Returns the customer name associated with this order.
    *
    * @return The customer name.
    */
    public String getCustomerName() {
        return customerName;
    }
    /**
     * Sets the timestamp of the order.
     *
     * @param timestamp The timestamp indicating when the order was placed.
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    /**
     * Gets the timestamp of the order.
     *
     * @return The timestamp indicating when the order was placed.
     */
    public Date getTimestamp() {
        return timestamp;
    }
    /*
    public void cancel(){

    }*/
    
    /**
     * Generates an invoice for the order, including details of items, quantities, and total amount.
     *
     * @return The generated invoice as a string.
     */
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
     /**
     * Saves the order to the database using the OrderDAO.
     *
     * @param order The order to be saved.
     * @return The order ID assigned to the saved order.
     */
    
    public int saveOrder(Order order) {
        if (OrderDAO != null) {
            order_id = OrderDAO.saveOrder(order);
        } else {
            System.out.println("OrderDAO is null. Cannot save order.");
        }
        return order_id;
    }

    /**
     * Gets the amount paid by the customer.
     *
     * @return The amount paid by the customer.
     */
    public double getAmountPaid(){
        return amountPaid;
    }
     /**
     * Gets the total amount of the order.
     *
     * @return The total amount of the order.
     */
    public double getTotal(){
        return total;
    }
    /**
     * Gets the name of the operator processing the order.
     *
     * @return The name of the operator.
     */
    public String getOperatorName() {
        return operatorName;
    }
    /**
     * Updates the stock in the database for the specified order details.
     *
     * @param orderId         The ID of the order.
     * @param productName     The name of the product.
     * @param quantityOrdered The quantity ordered.
     */
    public void updateStockInDB(int orderId, String productName, int quantityOrdered) {
         OrderDAO.updateStock( orderId,  productName,  quantityOrdered);
    }
    /**
     * Sets the name of the operator processing the order.
     *
     * @param operatorName The name of the operator.
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

}
