import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    Admin admin=new Admin();
    admin.getRestaurantDetail();
    int count;
    do{
        System.out.println("1.Customer");
        System.out.println("2.Admin");
        System.out.println("0.Quit");
        System.out.println("Enter your Choice:");
        Scanner sc=new Scanner(System.in);
        try {
            count = Integer.parseInt(sc.nextLine());
            if(count >2) System.err.println("Enter the valid key");
        }
        catch (Exception e){
            count=10;
            System.err.println("Enter the valid key");
        }
        switch (count) {
            case 1 -> {
                admin.restaurantView();
                Customer customer = new Customer();
                customer.customerView();
            }
            case 2 -> {
                if(admin.userName == null)
                    admin.adminSignup();
                admin.adminLogin();
            }
        }
    }while(count!=0);


    }
}