import java.util.ArrayList;
import java.util.Scanner;

public class CustomerOrderList {

    public float totalSale = 0;
    public int completedOrder=0;
    int count;
    Scanner sc = new Scanner(System.in);
    private static CustomerOrderList ordersList = null;

    private CustomerOrderList() {
    }

    static public CustomerOrderList getOrderList() {
        if (ordersList == null) {
            ordersList = new CustomerOrderList();
        }
        return ordersList;
    }

    static ArrayList<Order> orders = new ArrayList<>();

    void addOrders(Order order) {
        orders.add(order);
    }
    public void viewOrders() {
        count = 1;
        System.out.println("============================================= ORDER LIST ==========================================");
        System.out.printf("%5s %10s %10s %10s %15s %15s %20s\n", "CustomerNo", "CustomerName", "TableNo", "Price", "Order Status", "Remark", "Items");
        System.out.println("===================================================================================================");
        for (Order o : orders) {
            if (!o.getOrderStatus().equalsIgnoreCase("notCompleted")) {
                ArrayList<CartOrders> items = o.getOrderItems();
                System.out.printf("%5d %12s %15d ", count++, o.getCustomerName(), o.getTableNo());
                System.out.printf("%13.2f %15s  %13s", o.getTotalPrice(), o.getOrderStatus(), o.getRemark());
                for (int i = 0; i < items.size(); i++) {
                    if (i > 0) System.out.print("                                                   ");
                    System.out.printf("\t%10d x%5s", items.get(i).getQuantity(), items.get(i).getItemName());
                    if (i < items.size() - 1) System.out.print(",\n%19s"+" ");
                }
                System.out.println();
            }
        }
        System.out.println("===================================================================================================");
        System.out.printf("                                         TotalSales =   %.2f\n", totalSale);
        System.out.println("===================================================================================================");

    }


    public int pendingOrder() {
        count = 0;
        for (Order o : orders) {
            if (o.getOrderStatus().equalsIgnoreCase("notCompleted")) {
                count++;
            }
        }
        return count;
    }

    public void conformOrder(int index) {
        System.out.println("=====================================================");
        System.out.println(" Customer Details:-");
        System.out.println(" Name    : "+orders.get(index).getCustomerName());
        System.out.println(" TableNo : "+orders.get(index).getTableNo());
        System.out.println(" Price   : "+orders.get(index).getTotalPrice());
        System.out.print(" Get Payment From Customer: ");
        System.out.println(" Customer Selected payment Mode as "+orders.get(index).getPaymentBy().toUpperCase());
        System.out.println(" 1. If customer paid confirm order");
        System.out.println(" 2. else Cancel order");
        System.out.println("Enter your choice :");
        do {
            try {
                count = Integer.parseInt(sc.nextLine());
                if (count > 2)
                    System.err.println("Enter the valid key");
                switch (count) {
                    case 1 -> {
                        orders.get(index).setOrderStatus("Completed");
                        totalSale += orders.get(index).getTotalPrice();
                        completedOrder++;
                        count = 0;
                        System.out.println("Order Completed Successfully");
                    }
                    case 2 -> {
                        orders.get(index).setOrderStatus("Canceled");
                        count = 0;
                        System.out.println("Order Canceled Successfully");
                    }
                }
            } catch (Exception e) {
                System.err.println("Enter the valid key");
            }
        } while (count != 0);
        System.out.println("=====================================================");
    }


    public void takeOrders() {
        count = 1;
        System.out.println("============================================== ORDER LIST =========================================");
        System.out.printf("%5s %10s %10s %10s %15s %15s %20s\n", "CustomerNo", "CustomerName", "TableNo", "Price", "Order Status", "Remark", "Items");
        System.out.println("===================================================================================================");
        for (Order o : orders) {
            if (o.getOrderStatus().equalsIgnoreCase("notCompleted")) {
                ArrayList<CartOrders> items = o.getOrderItems();
                System.out.printf("%5d %10s %10d ", count++, o.getCustomerName(), o.getTableNo());
                System.out.printf("%13.2f %15s  %13s", o.getTotalPrice(), o.getOrderStatus(), o.getRemark());
                for (int i = 0; i < items.size(); i++) {
                    if (i > 0) System.out.print("                                                   ");
                    System.out.printf("\t%10d x%5s", items.get(i).getQuantity(), items.get(i).getItemName());
                    if (i < items.size() - 1 && i>0) System.out.print(",\n");
                }
                System.out.println();
            }
        }
        System.out.println("===================================================================================================");
    }

}
