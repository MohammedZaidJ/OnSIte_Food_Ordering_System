import java.util.ArrayList;

public class Order {
    String customerName;
    int tableNo;
    float totalPrice;
    String remark;
    ArrayList<CartOrders> orderItems;
     String orderStatus="notCompleted";

    public Order(String customerName, int tableNo, float totalPrice, String remark, ArrayList<CartOrders> orderItems) {
        this.customerName = customerName;
        this.tableNo = tableNo;
        this.totalPrice = totalPrice;
        this.remark = remark;
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
}
