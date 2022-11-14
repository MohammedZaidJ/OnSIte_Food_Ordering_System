import Data.Restaurant;
import java.util.Scanner;

public class Admin {
    String userName;
    String password;

    CustomerOrderList orderList = CustomerOrderList.getOrderList();
    Scanner sc=new Scanner(System.in);
    Menu menu=Menu.getMenu();
    Restaurant rest;
    public void adminSignup(){
        System.out.println("======================================================");
        System.out.println("                 CREATING NEW ADMIN USER  ");
        System.out.println("======================================================");
        System.out.println("Enter the admin user ID : ");
        this.userName=sc.nextLine();
        String password1, password2;
        do {
            System.out.println("Create Password :");
            password1 = sc.next();
            System.out.println("Confirm Password :");
            password2 = sc.next();
            if(!password1.equals(password2)){
                System.err.println("Password not matched");
            }
        }while(!password1.equals(password2));
        this.password=password1;
        System.out.println("You have successfully created a new Admin ");
        System.out.println("======================================================");
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
    public void AdminView(){
        int count;
        sc.nextLine();
        do{
            System.out.println("1.Restaurant Details");
            System.out.println("2.Menu");
            System.out.println("3.View Orders History");
            System.out.println("4.Take Orders ("+orderList.pendingOrder()+")");
            System.out.println("0.Logout");
            System.out.println("Enter your Choice:");
            try {
                count = Integer.parseInt(sc.nextLine());
                if(count>4) System.err.println("Enter the valid key");
            }
            catch (Exception e) { count =10;
                System.err.println("Enter the valid Key ");
            }
            switch (count) {
                    case 1 -> restaurantDetail();

                    case 2 -> menu.editMenu("AdminView");
                    case 3 -> {
                        if(CustomerOrderList.orders.size()>0)
                            orderList.viewOrders();
                        else
                            System.out.println("Currently zero orders");
                    }
                    case 4 ->{
                        if(orderList.pendingOrder()>0) {
                            orderList.takeOrders();
                            System.out.println("Enter the customer number:");
                            int customerNo;
                            try {
                                customerNo = Integer.parseInt(sc.nextLine());
                                customerNo--;
                                if (customerNo < CustomerOrderList.orders.size() && customerNo > -1) {
                                    orderList.conformOrder(customerNo);
                                }
                            } catch (Exception e) {
                                System.err.println("Enter the valid key");
                            }
                        }
                        else System.out.println("Currently zero orders");


                    }
                    case 5->{

                    }
                }

        }while (count!=0);

    }

    public void restaurantDetail() {
        System.out.println("=========================================");
        System.out.println("||-> Name             : "+rest.getName());
        System.out.println("||-> Location         : "+rest.getAddress());
        System.out.println("||-> Total Customer   : "+CustomerOrderList.orders.size());
        System.out.println("||-> Completed Orders : "+orderList.completedOrder);
        System.out.println("||-> Pending Orders   : "+orderList.pendingOrder());
        System.out.printf("||-> Total Sales      : %.2f\n",orderList.totalSale);
        System.out.println("=========================================");
    }

    public void getRestaurantDetail(){
        rest=new Restaurant("Hotel Buhari","2/27 Anna Colony, Chennai-600045");
    }
    public void restaurantView(){
        System.out.println("=========================================");
        System.out.println("                  WELCOME");
        System.out.println("                    TO");
        System.out.println("                "+rest.getName().toUpperCase());
        System.out.println("=========================================");

    }
    public void adminLogin(){
        int flag = 0;
        do {
            Scanner sc=new Scanner(System.in);
            System.out.println("login Id :");
            String login = sc.nextLine();
            System.out.println("Password :");
            String password = sc.nextLine();
            if (login.equals(getUserName()) && password.equals(getPassword())) {
                flag = 1;
            } else {
                System.err.println("Enter valid username and password.");
            }
        } while (flag != 1);
        System.out.println("you have successfully logged in");
        AdminView();
    }

}
