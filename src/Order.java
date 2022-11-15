import java.util.ArrayList;

public class Order {
    private final String customerName;
    private final int tableNo;
    private final float totalPrice;
    private final String remark;
    ArrayList<CartOrders> orderItems;
    private String orderStatus="notCompleted";
    private final String paymentBy;

    public Order(String customerName, int tableNo, float totalPrice, String remark,String payment, ArrayList<CartOrders> orderItems) {
        this.customerName = customerName;
        this.tableNo = tableNo;
        this.totalPrice = totalPrice;
        this.remark = remark;
        this.paymentBy =payment;
        this.orderItems = orderItems;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public ArrayList<CartOrders> getOrderItems() {
        return orderItems;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public String getRemark() {
        return remark;
    }

    public String getCustomerName() {
        return customerName;
    }
    public int getTableNo() {
        return tableNo;
    }

    public String getPaymentBy() {
        return paymentBy;
    }

}
