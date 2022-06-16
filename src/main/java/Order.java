import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
/**
 * Order class stores all attributes required to keep track of the orders
 *
 * @version 1.0
 */
public class Order {

    private int orderId;
    private boolean isAdvancedOrder;
    private LocalDate orderDate;
    private LocalTime orderTime;
    private Employee createdBy;
    private Employee modifiedBy;
    private Order.STATUS orderStatus;
    private int orderTotal;
    private String customerName;
    private String customerPhone;
    private Map<Item, Integer> orderItemQty;
    private int storeId;

    /**
     *  Constructor for class Order
     *
     * @param orderId order ID
     * @param storeId store ID
     * @param orderDate order date
     * @param orderTime order time
     * @param createdBy user who created the order
     * @param modifiedBy user who modified the order
     * @param orderStatus status of the order
     * @param customerName name of the customer
     * @param customerPhone phone of the customer
     * @param orderItemQty quantity and items in order
     */
    public Order(int orderId, int storeId, LocalDate orderDate, LocalTime orderTime, Employee createdBy, Employee modifiedBy, Order.STATUS orderStatus, String customerName, String customerPhone, Map<Item, Integer> orderItemQty) {
        this.orderId = orderId;
        this.storeId = storeId;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.orderStatus = orderStatus;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.orderItemQty = orderItemQty;
    }

    /**
     * Prints order details to the screen.
     * Details include item, quantity, price and total price of the order.
     *
     * @param orderDetail map of items and quantity
     */
    public static void printOrderDetail(Map<Item, Integer> orderDetail, boolean isIncludeStatus) {
        System.out.println("Your order detail is: ");
        int i = 0;
        int totalPrice = 0;
        for (Item item : orderDetail.keySet()) {
            System.out.println(++i + ". " + item.getItemName() + " quantity: " + orderDetail.get(item) + " price: $" + item.getPrice() * orderDetail.get(item));
            totalPrice += item.getPrice() * orderDetail.get(item);
        }
        System.out.println("Total price: $" + totalPrice);
        if(isIncludeStatus) {
            System.out.println("Order status is prepared.");
        }
    }

    /**
     * Return order time
     *
     * @return      order time
     */
    public LocalTime getOrderTime() {
        return orderTime;
    }

    /**
     * Set order time
     *
     * @param orderTime time of the order
     */
    public void setOrderTime(LocalTime orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * Return store ID
     *
     * @return      store ID
     */
    public int getStoreId() {
        return storeId;
    }

    /**
     * Set store ID
     *
     * @param storestorId store ID
     */
    public void setStoreId(int storestorId) {
        storeId = storeId;
    }

    /**
     * Return order ID
     *
     * @return order ID
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Set order ID
     *
     * @param orderId order ID
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * Return order date
     *
     * @return      date of the order
     */
    public LocalDate getOrderDate() {
        return orderDate;
    }

    /**
     * Set order Date
     *
     * @param orderDate dare of the order
     */
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Return the user, who created the order
     *
     * @return      user, created the order
     */
    public Employee getCreatedBy() {
        return createdBy;
    }

    /**
     * Set user, who created the order
     *
     * @param createdBy user, created the order
     */
    public void setCreatedBy(Employee createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Return the user, who modified the order
     *
     * @return      user, modified the order
     */
    public Employee getModifiedBy() {
        return modifiedBy;
    }

    /**
     * Set the user, who modified the order
     *
     * @param modifiedBy user, modified the order
     */
    public void setModifiedBy(Employee modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * Return order status
     *
     * @return      status of the order
     */
    public Order.STATUS getOrderStatus() {
        return orderStatus;
    }

    /**
     * Set the order status
     *
     * @param orderStatus status of the order
     */
    public void setOrderStatus(Order.STATUS orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * Return advanced order or not
     *
     * @return boolean advanced order
     */
    public boolean isAdvancedOrder() {
        return isAdvancedOrder;
    }

    /**
     * Set advanced order or not
     *
     * @param advancedOrder boolean advanced order
     */
    public void setAdvancedOrder(boolean advancedOrder) {
        isAdvancedOrder = advancedOrder;
    }

    /**
     * Set order total
     *
     * @param orderTotal order total
     */
    public void setOrderTotal(int orderTotal) {
        this.orderTotal = orderTotal;
    }

    /**
     * Return the total of the order
     *
     * @return      order total
     */
    public int getOrderTotal() {
        return orderTotal;
    }

    /**
     * Return customer name
     *
     * @return      name of the customer
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Set customer name
     *
     * @param customerName name of the customer
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Return customer phone
     *
     * @return      phone number of the customer
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     * Set customer phone
     *
     * @param customerPhone phone number of the customer
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    /**
     * Return order items and quantity
     *
     * @return      map of items and quantity
     */
    public Map<Item, Integer> getOrderItemQty() {
        return orderItemQty;
    }

    /**
     * Set order items and quantity
     *
     * @param orderItemQty map of items and quantity
     */
    public void setOrderItemQty(Map<Item, Integer> orderItemQty) {
        this.orderItemQty = orderItemQty;
    }

    /**
     * Show the state of the Order class object
     *
     * @return      String with all attributes of Order class object
     */
    @java.lang.Override
    public java.lang.String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", isAdvancedOrder='" + isAdvancedOrder + '\'' +
                ", orderDate=" + orderDate +
                ", orderTime=" + orderTime +
                ", createdBy=" + createdBy +
                ", modifiedBy=" + modifiedBy +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderTotal=" + orderTotal +
                ", customerName='" + customerName + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", orderItemQty=" + orderItemQty +
                '}';
    }

    /**
     * Group of constants for the order status
     */
    public enum STATUS {
        READY,
        PREPARED
    }
}