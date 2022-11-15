import category.Item;
import java.util.ArrayList;
import java.util.Scanner;

public class Customer {
    private float totalPrice = 0, price = 0, itemPrice;
    ArrayList<CartOrders> cart = new ArrayList<>();
    CustomerOrderList orderList = CustomerOrderList.getOrderList();
    Scanner sc = new Scanner(System.in);
    Menu menu=Menu.getMenu();
    private int i;
    private int itemNo;

    public void displayCart() {
        System.out.println("====================== Cart ========================");
        System.out.printf("%5s %16s %13s %13s\n", "Item No", "ItemName", "Quantity", "Price");
        i = 1;
        System.out.println("====================================================");
        for (CartOrders o : cart) {
            System.out.printf("%3d %20s %8dx %16.2f\n", i++, o.getItemName(), o.getQuantity(), o.getTotalPrice());
        }
        System.out.println("====================================================");
        System.out.println("          Total Price(+ GST %6) : " + totalPrice);

        System.out.println("====================================================");
    }

    public void customerView() {
        int count;
        do {
            System.out.println("1. View Menu");
            System.out.println("2. Order food");
            System.out.println("3. Update cart item");
            System.out.println("4. Delete cart item");
            System.out.println("5. View cart");
            System.out.println("6. Conform order");
            System.out.println("0. Back to main Menu");
            System.out.println("Enter your choice :");
            try {
                count = Integer.parseInt(sc.nextLine());
                if (count > 6) {
                    System.err.println("Enter the valid key");
                    count = 10;
                }
            } catch (Exception e) {
                count = 10;
                System.err.println("Enter the valid key");
            }
            boolean bool;
            switch (count) {
                case 1 ->  ///view Menu
                        menu.DisplayMenu(1);

                case 2 -> {////order food
                    menu.DisplayMenu(1);
                    System.out.println("Select which item: f:food b:beverages");
                    char menuNo = Character.toLowerCase(sc.nextLine().charAt(0));
                    if (menuNo == 'f')
                        addItemToCart(menu.foodMenu);
                    else
                        addItemToCart(menu.beverages);
                }
                case 3 -> {///update cart
                    if (cart.size() == 0) System.out.println("Still not order food");
                    else {
                        System.out.println("===================================================");
                        System.out.println("Enter the cart item number:");
                        itemNo = Integer.parseInt(sc.nextLine());
                        itemNo--;
                        System.out.printf("""

                                Item Name : %s
                                Quantity  : %d
                                Price     : %.2f
                                """, cart.get(itemNo).getItemName(), cart.get(itemNo).getQuantity(), cart.get(itemNo).getTotalPrice());
                        bool = false;
                        if (itemNo < cart.size() && itemNo > -1) {
                            totalPrice -= (cart.get(itemNo).getTotalPrice() * 0.06 + cart.get(itemNo).getTotalPrice());
                            getSizeFromMenu(menu.foodMenu, itemNo);
                            getSizeFromMenu(menu.beverages, itemNo);
                            bool = true;
                        }
                        if (!bool) {
                            System.err.println("item not found ");
                        } else {
                            System.out.println("item updated successful");
                        }
                        if (cart.size() > 0)
                            displayCart();
                    }
                }
                case 4 -> {//delete food
                    if (cart.size() == 0) System.out.println("Still not order food");
                    else {
                        System.out.println("===================================================");
                        bool = false;
                        System.out.println("Enter the item number:");
                        try {
                            itemNo = Integer.parseInt(sc.nextLine());
                            itemNo--;
                            if (itemNo < cart.size() && itemNo > -1) {
                                totalPrice -= (cart.get(itemNo).getTotalPrice() * 0.06 + cart.get(itemNo).getTotalPrice());
                                cart.remove(itemNo);
                                bool = true;
                            }
                            if (!bool) {
                                System.err.println("item not found ");
                            } else {
                                System.out.println("item deleted successful");
                            }
                        } catch (Exception e) {
                            System.err.println("Enter the valid key");
                        }
                        if (cart.size() > 0) displayCart();
                    }
                }
                case 5 -> {
                    if (cart.size() > 0) displayCart();
                    else System.out.println("Still not order food");
                }
                case 6 -> {// conform order food
                    if (cart.size() == 0) System.out.println("Still not order food");
                    else {
                        String remark;
                        System.out.println("===================================================");
                        System.out.println("Enter key to final order : y:Yes / n:No ");
                        char key = sc.nextLine().charAt(0);
                        if (Character.toLowerCase(key) == 'y') {
                            System.out.println("Enter your Name MR/MRS : ");
                            String Name = sc.nextLine();
                            System.out.println("Enter your table no : ");
                            int tableNo = Integer.parseInt(sc.nextLine());
                            System.out.println("If you have any remark : y:Yes / n:No ");
                            char keys = sc.nextLine().charAt(0);
                            if (Character.toLowerCase(keys) == 'y') {
                                System.out.println("Enter your remark :");
                                remark = sc.nextLine();
                            } else {
                                remark = "-";
                            }
                            int count2;
                            String paymentBy;
                            System.out.println("Payment Mode :");
                            System.out.println("     1. UPI");
                            System.out.println("     2. Cash");
                            System.out.println("Enter your choice : ");
                            do {
                                try {
                                    count2 = Integer.parseInt(sc.nextLine());
                                    if (count2 > 3)
                                        System.err.println("Enter the valid key");
                                } catch (Exception e) {
                                    count2 = 3;
                                    System.err.println("Enter the valid key");
                                }
                            }while (count2!=1 && count2!=2);
                            if(count2==1) paymentBy="UPI";
                            else paymentBy="Cash";
                            Order order = new Order(Name, tableNo, totalPrice, remark,paymentBy, cart);
                            orderList.addOrders(order);
                            displayCart();
                            System.out.println("              Ordered Successfully");
                            count=0;
                            System.out.println("=====================================================");
                        }
                    }
                }
            }
        } while (count != 0);

    }

    private void addItemToCart(ArrayList<Item> menu) {//order food
        System.out.println("Enter the dish number:");
        int count = 1;
        do {
            try {
                itemNo = Integer.parseInt(sc.nextLine());
                if (menu.size() < itemNo) {
                    System.err.println("Enter the valid key");
                } else count = 0;
            } catch (Exception e) {
                System.err.println("Enter the valid key");
            }
        } while (count != 0);
        itemPrice = 0;
        boolean bool = false;
        i = 1;
        for (Item fd : menu) {
            if (itemNo == i && fd.getStatus().equalsIgnoreCase("available")) {
                if (fd.getSize().equalsIgnoreCase("unlimited")) {
                    System.out.println("Size available for " + fd.getItemName());
                    System.out.println("no. Size    Price    ");
                    System.out.println("1. limited     : " + fd.getMediumSizePrice());
                    System.out.println("2. unlimited   : " + fd.getLargeSizePrice());
                    int choice = 0;
                    do {
                        System.out.println("Enter your choice:");
                        try {
                            choice = Integer.parseInt(sc.nextLine());
                            if (choice == 1) price = fd.getMediumSizePrice();
                            else if (choice == 2) price = fd.getLargeSizePrice();
                            else {
                                System.err.println("Enter the valid choice");
                            }
                        } catch (Exception e) {
                            System.err.println("Enter the valid choice");
                        }
                    } while (choice != 1 && choice != 2);
                } else {
                    price = fd.getMediumSizePrice();
                }

                System.out.println("Enter the quantity :");
                int quantity = Integer.parseInt(sc.nextLine());

                cart.add(new CartOrders(fd.getItemName(), quantity, price * quantity));
                itemPrice = price * quantity;
                bool = true;
            }
            if(fd.getStatus().equalsIgnoreCase("available")) i++;
        }
        totalPrice += ((itemPrice * 0.06) + itemPrice);
        if (!bool) {
            System.err.println("item not found ");
        } else {
            System.out.println("item added to cart ");
        }
        displayCart();
    }

    private void getSizeFromMenu(ArrayList<Item> Menu, int itemNo) {
        String itemName = cart.get(itemNo).getItemName();
        for (Item fd : Menu) {
            if (fd.getItemName().equalsIgnoreCase(itemName)) {
                if (fd.getSize().equalsIgnoreCase("unlimited")) {
                    System.out.println("Size available for " + fd.getItemName());
                    System.out.println("no. Size    Price    ");
                    System.out.println("1. limited     : " + fd.getMediumSizePrice());
                    System.out.println("2. unlimited   : " + fd.getLargeSizePrice());
                    int choice = 0;
                    do {
                        System.out.println("Enter your choice:");
                        try {
                            choice = Integer.parseInt(sc.nextLine());
                            if (choice == 1) price = fd.getMediumSizePrice();
                            else if (choice == 2) price = fd.getLargeSizePrice();
                        } catch (Exception e) {
                            System.err.println("Enter the valid choice");
                        }
                    } while (choice != 1 && choice != 2);
                } else {
                    price = fd.getMediumSizePrice();
                }

                System.out.println("Enter the quantity :");
                int quantity = Integer.parseInt(sc.nextLine());
                cart.get(itemNo).setTotalPrice(price * quantity);//UPDATE CART
                cart.get(itemNo).setQuantity(quantity);
                itemPrice = cart.get(itemNo).getTotalPrice();
                totalPrice += ((itemPrice * 0.06) + itemPrice);

            }
        }

    }


}
