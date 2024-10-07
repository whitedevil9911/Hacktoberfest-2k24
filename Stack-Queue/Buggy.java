import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class BuggyInventoryManager {
    // Stores inventory in a HashMap (Product ID -> Quantity)
    private HashMap<Integer, Product> inventory;
    private static final int LOW_STOCK_THRESHOLD = 5;

    // Constructor
    public BuggyInventoryManager() {
        inventory = new HashMap<>();
    }

    public static void main(String[] args) {
        BuggyInventoryManager manager = new BuggyInventoryManager();
        manager.run();  // Run the program
    }

    // Main run loop
    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        while (choice != 5) {
            System.out.println("\nInventory Management System");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Update Quantity");
            System.out.println("4. Display Inventory");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    addProduct(scanner);
                    break;
                case 2:
                    removeProduct(scanner);
                    break;
                case 3:
                    updateQuantity(scanner);
                    break;
                case 4:
                    displayInventory();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
            }
        }
        scanner.close();
    }

    // Add a new product
    private void addProduct(Scanner scanner) {
        System.out.print("Enter product ID: ");
        int productId = scanner.nextInt();
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();  // Bug: Should use next() instead of nextLine()
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();

        if (inventory.containsKey(productId)) {
            System.out.println("Product already exists! Use Update Quantity.");
        } else {
            Product product = new Product(productId, productName, quantity);
            inventory.put(productId, product);
            System.out.println("Product added.");
        }
    }

    // Remove a product
    private void removeProduct(Scanner scanner) {
        System.out.print("Enter product ID to remove: ");
        int productId = scanner.nextInt();
        if (!inventory.containsKey(productId)) {
            System.out.println("Product does not exist.");
        } else {
            inventory.remove(productId);  // Bug: No check if the product was successfully removed
            System.out.println("Product removed.");
        }
    }

    // Update product quantity
    private void updateQuantity(Scanner scanner) {
        System.out.print("Enter product ID: ");
        int productId = scanner.nextInt();
        if (!inventory.containsKey(productId)) {
            System.out.println("Product does not exist.");
        } else {
            System.out.print("Enter new quantity: ");
            int quantity = scanner.nextInt();
            Product product = inventory.get(productId);
            product.setQuantity(quantity);  // Bug: Doesn't check for negative quantities
            System.out.println("Quantity updated.");
        }
    }

    // Display inventory
    private void displayInventory() {
        System.out.println("\nInventory Details:");
        for (Product product : inventory.values()) {
            System.out.println("ID: " + product.getId() + ", Name: " + product.getName() + ", Quantity: " + product.getQuantity());
            // Bug: No check for low stock and no sorting option
        }
    }
}

// Product class definition
class Product {
    private int id;
    private String name;
    private int quantity;

    public Product(int id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
