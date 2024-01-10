import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Vendor {
    private Connection connection;
    private String username;
    private String password;

    public Vendor(Connection connection) {
        this.connection = connection;
    }


	public void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        username = scanner.nextLine();
        System.out.print("Enter password: ");
        password = scanner.nextLine();

        if (authenticateVendor()) {
            VendorOptions options = new VendorOptions(connection);
            options.displayOptions();
        } else {
            System.out.println("Authentication failed. Please try again.");
        }
    }

    private boolean authenticateVendor() {
        try {
            String query = "SELECT * FROM vendors WHERE username=? AND password=?";
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
