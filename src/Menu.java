import category.Item;
//import category.ItemName;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    ArrayList<Item> foodMenu = new ArrayList<>();
    ArrayList<Item> beverages = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    private String permitStatus;
    private String size;
    char c;
    int itemNo, i = 0;
    float mediumSizePrice;
    float largeSizePrice = 0;

    private void addToMenu() {
        foodMenu.add(new Item("Chilli Paneer", "available", "Veg", "limited", 150, 0));
        foodMenu.add(new Item("Tandoori Aloo", "available", "Veg", "unlimited", 180, 300));
        foodMenu.add(new Item("Tangri kabab", "available", "Non Veg", "unlimited", 120, 220));
        foodMenu.add(new Item("Fish Tikka", "available", "Non Veg", "limited", 150, 0));
        foodMenu.add(new Item("Chicken Tandoori", "available", "Non Veg", "unlimited", 180, 300));
        foodMenu.add(new Item("Shahi Paneer", "available", "Non Veg", "limited", 170, 0));
        foodMenu.add(new Item("Chicken Malai Tikka", "available", "Non Veg", "unlimited", 120, 220));
        foodMenu.add(new Item("Cheese Korma", "available", "Veg", "limited", 170, 0));
        foodMenu.add(new Item("Aloo Gobhi", "available", "Veg", "limited", 150, 0));
        beverages.add(new Item("Lassi", "available", "Veg", "unlimited", 60, 100));
        beverages.add(new Item("Coca-Cola", "available", "Veg", "unlimited", 120, 80));
        beverages.add(new Item("Red Bull", "available", "Veg", "limited", 50, 0));
        beverages.add(new Item("Soy milk", "available", "Veg", "unlimited", 40, 80));
        beverages.add(new Item("French coffee", "available", "Veg", "limited", 100, 0));
        beverages.add(new Item("Cold coffee", "available", "Veg", "limited", 100, 0));
        beverages.add(new Item("Kashmir Tea", "available", "Veg", "limited", 30, 0));
//        foodMenu.add(new Item(ItemName.Chilli_Paneer));
    }

    public Menu() {
        this.addToMenu();
    }

    public void displayFoodMenu(int check) {
        System.out.println("===================================== FOOD MENU =====================================");
        System.out.printf("%10s %20s %15s %13s %16s\n", "Item No ", "Item Name", "Permit Status", "Size", "Price(LT/ULT)");
        System.out.println("==================================================================================");
        i = 1;
        for (Item o : foodMenu) {
            if(check==1 && o.getStatus().equals("available"))
                System.out.printf("%6d %24s %10s %18s %10.1f/%6.1f\n", i++, o.getItemName(), o.getPermitStatus(), o.getSize(), o.getMediumSizePrice(), o.getLargeSizePrice());
            else if(check==0){
                System.out.printf("%6d %24s %10s %18s %10.1f/%6.1f", i++, o.getItemName(), o.getPermitStatus(), o.getSize(), o.getMediumSizePrice(), o.getLargeSizePrice());
                if(!o.getStatus().equalsIgnoreCase("available")) System.out.print("-> NotAvailable\n");
                else System.out.print("\n");
            }
        }
        System.out.println("=====================================================================================");

    }

    public void displayBeverageMenu(int check) {
        System.out.println("=================================== BEVERAGES MENU ===================================");
        System.out.printf("%10s %20s %15s %13s %16s\n", "Item No ", "Item Name", "Permit Status", "Size", "Price(MN/LG)");
        System.out.println("====================================================================================");
        i = 1;
        for (Item o : beverages) {
            if(check==1 && o.getStatus().equalsIgnoreCase("available"))
                System.out.printf("%6d %24s %10s %18s %10.1f/%6.1f\n", i++, o.getItemName(), o.getPermitStatus(), o.getSize(), o.getMediumSizePrice(), o.getLargeSizePrice());
            else if(check==0){
                System.out.printf("%6d %24s %10s %18s %10.1f/%6.1f\n", i++, o.getItemName(), o.getPermitStatus(), o.getSize(), o.getMediumSizePrice(), o.getLargeSizePrice());
            }
        }
        System.out.println("====================================================================================");

    }

    public void DisplayMenu(int check) {
//        int count;
//        do{
//            System.out.println("1.Food menu");
//            System.out.println("2.Beverage menu");
//            System.out.println("3.Show all menu");
//            System.out.println("0.Back to main ");
//            System.out.println("Enter your choice:");
//            try{
//                count=Integer.parseInt(sc.nextLine());
//                if(count>3) System.err.println("Enter the valid key");
//            }catch (Exception e){
//                count=10;
//                System.err.println("Enter the valid key");
//            }
////            switch ()
//        }while (count!=0);
        displayFoodMenu(check);
        System.out.println("\n");
        displayBeverageMenu(check);
    }

    public void editMenu() {
        int count;
        do {
            System.out.println("1.View menu");
            System.out.println("2.Add new dish");
            System.out.println("3.Modify dish");
            System.out.println("4.Remove dish");
            System.out.println("0.Back to main menu");
            System.out.println("Enter your choice :");
            try {
                count = Integer.parseInt(sc.nextLine());
                if (count > 5) {
                    System.err.println("Enter the valid key");
                }
            } catch (Exception e) { count=10;
                System.err.println("Enter the valid key");
            }
            switch (count) {
                case 1 -> //View menu
                        DisplayMenu(0);
                case 2 -> //Add new food
                    addItemTOMenu();

                case 3 -> {//Modify menu
                    DisplayMenu(0);
                    System.out.println("=======================================");
                    System.out.println("Select which item: f:food b:beverages");
                    c = Character.toLowerCase(sc.nextLine().charAt(0));
                    if (c == 'f')
                        modifyItemInMenu(foodMenu);
                    else
                        modifyItemInMenu(beverages);
                    System.out.println("=======================================");
                }
                case 4 -> { //Remove food
                    DisplayMenu(0);
                    System.out.println("=======================================");
                    System.out.println("Select which item: f:food b:beverages");
                    c = Character.toLowerCase(sc.nextLine().charAt(0));
                    if (c == 'f') deleteItemFromMenu(foodMenu);
                    else deleteItemFromMenu(beverages);
                    System.out.println("=======================================");
                }


            }

        } while (count != 0);
    }

    private void modifyItemInMenu(ArrayList<Item> menu) {
        System.out.println("Enter Item number:");
        boolean bool = false;
        try {
            itemNo = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.err.println("Enter the valid item Id");
        }
        i = 1;
        for (Item fd : menu) {
            if (itemNo == i++) {
                int count1;
                do {
                    System.out.println("1.Item Name       : " + fd.getItemName() +
                            "\n2.Status          : " + fd.getStatus() +
                            "\n3.PermitStatus    : " + fd.getPermitStatus() +
                            "\n4.Size            : " + fd.getSize() +
                            "\n5.MediumSizePrice : " + fd.getMediumSizePrice() +
                            "\n6.LargeSizePrice  : " + fd.getLargeSizePrice() +
                            "\n0.back to main menu");
                    System.out.println("Enter your choice to change the data:");
                    try {
                        count1 = Integer.parseInt(sc.nextLine());
                    } catch (Exception e) {
                        count1 = 10;
                        System.err.println("Enter the valid key");
                    }
                    switch (count1) {
                        case 1 -> {
                            System.out.println("Enter the food Name : ");
                            fd.setItemName(sc.nextLine());
                        }
                        case 2 -> {
                            System.out.println("Enter the food status: y=Available/n=NotAvailable");
                            if (Character.toLowerCase(sc.nextLine().charAt(0)) == 'n') {
                                fd.setStatus("NotAvailable");
                            }
                        }
                        case 3 -> {
                            System.out.println("Enter the food permit status: \n1.veg /2.non veg");
                            int stsNo = Integer.parseInt(sc.nextLine());
                            if (stsNo == 1) permitStatus = "Veg";
                            else permitStatus = "Non-Veg";
                            fd.setPermitStatus(permitStatus);
                        }
                        case 4 -> {
                            System.out.println("Enter the Size :\n1.limited 2.unlimited");
                            int sizeNO = Integer.parseInt(sc.nextLine());
                            if (sizeNO == 1) {
                                size = "limited";
                                fd.setLargeSizePrice(0);
                            } else {
                                size = "unlimited";
                                System.out.println("Enter the large size Price");
                                largeSizePrice = Float.parseFloat(sc.nextLine());
                                fd.setLargeSizePrice(largeSizePrice);
                            }
                            fd.setSize(size);
                        }
                        case 5 -> {
                            System.out.println("Enter the medium size Price:");
                            mediumSizePrice = Float.parseFloat(sc.nextLine());
                            fd.setMediumSizePrice(mediumSizePrice);
                        }
                        case 6 -> {
                            System.out.println("Enter the large size Price");
                            largeSizePrice = Float.parseFloat(sc.nextLine());
                            fd.setLargeSizePrice(largeSizePrice);
                        }
                    }
                } while (count1 != 0);
                bool = true;

            }
        }
        if (!bool) {
            System.err.println("item not found");
        } else {
            System.out.println("item updated successfully");
        }

    }

    private void deleteItemFromMenu(ArrayList<Item> menu) {
        boolean bool = false;
        System.out.println("Enter Item number:");
        try {
            itemNo = Integer.parseInt(sc.nextLine());
            if (itemNo < menu.size() && itemNo > -1) {
                menu.remove(itemNo - 1);
                bool = true;
            }
            if (!bool) {
                System.err.println("item not found");
            } else {
                System.out.println("item delete successfully");
            }
        } catch (Exception e) {
            System.err.println("Enter the valid item Id");
        }
    }

    public void addItemTOMenu() {
        System.out.println("=======================================");
        System.out.println("Enter the food name : ");
        //    Item(foodName,status, permitStatus, size,mediumSizePrice, largeSizePrice)
        String itemName = sc.nextLine();
        System.out.println("Enter the food permit status: \n1.veg /2.non veg");
        int stsNo = Integer.parseInt(sc.nextLine());
        if (stsNo == 1) permitStatus = "Veg";
        else permitStatus = "Non-Veg";
        System.out.println("Enter the size :\n1.limited 2.unlimited");
        int sizeNO = Integer.parseInt(sc.nextLine());
        System.out.println("Enter the medium size price:");
        mediumSizePrice = Float.parseFloat(sc.nextLine());
        if (sizeNO == 2) {
            size = "None";
            System.out.println("Enter the large size price:");
            largeSizePrice = Float.parseFloat(sc.nextLine());
        } else size = "Available";
        System.out.println("Select which list to add item: f:food b:beverages");
        c = Character.toLowerCase(sc.nextLine().charAt(0));
        String status = "Available";
        if (c == 'f')
            foodMenu.add(new Item(itemName, status, permitStatus, size, mediumSizePrice, largeSizePrice));
        else
            beverages.add(new Item(itemName, status, permitStatus, size, mediumSizePrice, largeSizePrice));
        System.out.println("item added successfully");

        System.out.println("=======================================");
    }

}

