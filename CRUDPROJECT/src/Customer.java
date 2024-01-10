import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Customer {
    private Connection connection;
    private String username;
    private String password;

    public Customer(Connection connection) {
        this.connection = connection;
    }

    public void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        username = scanner.nextLine();
        System.out.print("Enter password: ");
        password = scanner.nextLine();

        if (authenticateCustomer()) {
            CustomerOptions options = new CustomerOptions(connection, username);
            options.displayOptions();
        } else {
            System.out.println("Authentication failed. Please try again.");
        }
    }

    private boolean authenticateCustomer() {
        try {
            String query = "SELECT * FROM customers WHERE username=? AND password=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
