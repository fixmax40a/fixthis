import inventory.Inventory;
import inventory.InventoryCalculator;
import products.Bread;
import products.Cheese;
import products.IProduct;
import products.Sausage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Inventory<IProduct> inventory;
        List<Inventory<IProduct>> products = new ArrayList<>();

        double prise;
        int quantity;
        String message;

        System.out.println("\nKeeping the tree products : \n1 - Bread\n2 - Cheese\n3 - Sausage\n or (quit\\q) - exit and print result\n");

        while (true) {
            System.out.print("Please, enter the product or(quit\\q) for exit : ");
            message = scanner.next();
            if (message.equals("quit") || message.equals("q")) {
                break;
            }
            if (inputValidate(message)) {
                switch (message) {
                    case "1":
                        System.out.print("Enter number of bread : ");
                        quantity = scanner.nextInt();
                        System.out.print("enter coast of bread : ");
                        prise = scanner.nextDouble();
                        inventory = new Inventory<>(new Bread(prise, quantity));
                        products.add(inventory);
                        break;
                    case "2":
                        System.out.print("Enter number of cheese : ");
                        quantity = scanner.nextInt();
                        System.out.print("enter coast of cheese : ");
                        prise = scanner.nextDouble();
                        inventory = new Inventory<>(new Cheese(prise, quantity));
                        products.add(inventory);
                        break;
                    case "3":
                        System.out.print("Enter number of sausage :  ");
                        quantity = scanner.nextInt();
                        System.out.print("enter coast of sausage : ");
                        prise = scanner.nextDouble();
                        inventory = new Inventory<>(new Sausage(prise, quantity));
                        products.add(inventory);
                        break;
                }
            } else continue;
        }

        InventoryCalculator.printResult(products);

    }

    private static boolean inputValidate(String s) {
        switch (s) {
            case "1":
            case "2":
            case "3":
                return true;
        }
        System.out.println("Entry\n1 - bread\n2 - cheese\n3 - sausage\n(quit\\q) - for exit : ");
        return false;
    }
}