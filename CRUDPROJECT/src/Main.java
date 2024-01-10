import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "Ajith@2003");
            
            Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to the Birthday Party Booking System!");

            while (true) {
                System.out.println("1. Vendor Login");
                System.out.println("2. Customer Login");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        Vendor vendor = new Vendor(connection);
                        vendor.login();
                        break;
                    case 2:
                        Customer customer = new Customer(connection);
                        customer.login();
                        break;
                    case 3:
                        System.out.println("Exiting the system. Goodbye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Please choose again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
