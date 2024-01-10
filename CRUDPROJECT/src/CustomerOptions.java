import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CustomerOptions {
    private Connection connection;
    private String customerUsername;

    public CustomerOptions(Connection connection, String customerUsername) {
        this.connection = connection;
        this.customerUsername = customerUsername;
    }

    public void displayOptions() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Book Event");
            System.out.println("2. View Bookings");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Update Customer Details");
            System.out.println("5. Logout");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    bookEvent1();
                    break;
                case 2:
                    viewBookings();
                    break;
                case 3:
                    cancelBooking();
                    break;
                case 4:
                    updateCustomerDetails();
                    break;
                case 5:
                    System.out.println("Logging out. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private void bookEvent1() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter event type (e.g., Party, Truff, Gulf, Gaming Center): ");
        String eventType = scanner.nextLine();
        System.out.println("Enter booking date (YYYY-MM-DD): ");
        String bookingDateStr = scanner.nextLine();

        try {
            Date bookingDate = new SimpleDateFormat("yyyy-MM-dd").parse(bookingDateStr);

            String query = "INSERT INTO bookings (customer_id, event_type, booking_date) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, getCustomerId1(customerUsername));
                preparedStatement.setString(2, eventType);
                preparedStatement.setDate(3, new java.sql.Date(bookingDate.getTime()));

                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Event booked successfully.");
                } else {
                    System.out.println("Failed to book event.");
                }
            }
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void viewBookings() {
        try {
            String query = "SELECT * FROM bookings WHERE customer_id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, getCustomerId1(customerUsername));

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    System.out.println("Your Bookings:");
                    while (resultSet.next()) {
                        System.out.println("Event Type: " + resultSet.getString("event_type"));
                        System.out.println("Booking Date: " + resultSet.getDate("booking_date"));
                        System.out.println("-------------------------");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void cancelBooking() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter event type to cancel: ");
        String eventType = scanner.nextLine();

        try {
            String query = "DELETE FROM bookings WHERE customer_id=? AND event_type=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, getCustomerId1(customerUsername));
                preparedStatement.setString(2, eventType);

                int rowsDeleted = preparedStatement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Booking canceled successfully.");
                } else {
                    System.out.println("Failed to cancel booking. Make sure the event type is correct.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getCustomerId1(String customerUsername) throws SQLException {
        String query = "SELECT id FROM customers WHERE username=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, customerUsername);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }
        }
        throw new SQLException("Customer not found");
    }

  

    private void updateCustomerDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new password: ");
        String newPassword = scanner.nextLine();

        try {
            String query = "UPDATE customers SET password=? WHERE username=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, newPassword);
                preparedStatement.setString(2, customerUsername);

                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Customer details updated successfully.");
                } else {
                    System.out.println("Failed to update customer details.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}