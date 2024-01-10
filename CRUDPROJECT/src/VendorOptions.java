import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class VendorOptions {
    private Connection connection;

    public VendorOptions(Connection connection) {
        this.connection = connection;
    }

    public void displayOptions() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Book Party Hall");
            System.out.println("2. Book Truff");
            System.out.println("3. Book Gulf");
            System.out.println("4. Book Gaming Center");
            System.out.println("5. Update Vendor Details");
            System.out.println("6. Logout");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    bookPartyHall();
                    break;
                case 2:
                    bookTruff();
                    break;
                case 3:
                    bookGulf();
                    break;
                case 4:
                    bookGamingCenter();
                    break;
                case 5:
                    updateVendorDetails1();
                    break;
                case 6:
                    System.out.println("Logging out. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }
    private void bookPartyHall() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter party hall location: ");
        String location = scanner.nextLine();
        System.out.println("Enter package (e.g., Standard, Premium): ");
        String packageType = scanner.nextLine();
        System.out.println("Enter booking date (YYYY-MM-DD): ");
        String bookingDateStr = scanner.nextLine();

        try {
            Date bookingDate = new SimpleDateFormat("yyyy-MM-dd").parse(bookingDateStr);

            String query = "INSERT INTO bookings (vendor_id, event_type, location, package, booking_date) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, getVendorId1("vendor_username")); // Replace with actual vendor username
                preparedStatement.setString(2, "Party Hall");
                preparedStatement.setString(3, location);
                preparedStatement.setString(4, packageType);
                preparedStatement.setDate(5, new java.sql.Date(bookingDate.getTime()));

                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Party hall booked successfully.");
                } else {
                    System.out.println("Failed to book party hall.");
                }
            }
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
    }

    private int getVendorId1(String vendorUsername) throws SQLException {
        String query = "SELECT id FROM vendors WHERE username=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, vendorUsername);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }
        }
        throw new SQLException("Vendor not found");
    }


    private void bookTruff() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter truff location: ");
        String location = scanner.nextLine();
        System.out.println("Enter truff package (e.g., Basic, Deluxe): ");
        String packageType = scanner.nextLine();
        System.out.println("Enter booking date (YYYY-MM-DD): ");
        String bookingDateStr = scanner.nextLine();

        try {
            Date bookingDate = new SimpleDateFormat("yyyy-MM-dd").parse(bookingDateStr);

            String query = "INSERT INTO bookings (vendor_id, event_type, location, package, booking_date) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, getVendorId1("vendor_username")); 
                preparedStatement.setString(2, "Truff");
                preparedStatement.setString(3, location);
                preparedStatement.setString(4, packageType);
                preparedStatement.setDate(5, new java.sql.Date(bookingDate.getTime()));

                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Truff booked successfully.");
                } else {
                    System.out.println("Failed to book truff.");
                }
            }
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
    }



    private void bookGulf() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Gulf location: ");
        String location = scanner.nextLine();
        System.out.println("Enter Gulf package (e.g., Basic, Premium): ");
        String packageType = scanner.nextLine();
        System.out.println("Enter booking date (YYYY-MM-DD): ");
        String bookingDateStr = scanner.nextLine();

        try {
            Date bookingDate = new SimpleDateFormat("yyyy-MM-dd").parse(bookingDateStr);

            String query = "INSERT INTO bookings (vendor_id, event_type, location, package, booking_date) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, getVendorId1("vendor_username")); 
                preparedStatement.setString(2, "Gulf");
                preparedStatement.setString(3, location);
                preparedStatement.setString(4, packageType);
                preparedStatement.setDate(5, new java.sql.Date(bookingDate.getTime()));

                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Gulf booked successfully.");
                } else {
                    System.out.println("Failed to book Gulf.");
                }
            }
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void bookGamingCenter() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Gaming Center location: ");
        String location = scanner.nextLine();
        System.out.println("Enter Gaming Center package (e.g., Standard, Premium): ");
        String packageType = scanner.nextLine();
        System.out.println("Enter booking date (YYYY-MM-DD): ");
        String bookingDateStr = scanner.nextLine();

        try {
            Date bookingDate = new SimpleDateFormat("yyyy-MM-dd").parse(bookingDateStr);

            String query = "INSERT INTO bookings (vendor_id, event_type, location, package, booking_date) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, getVendorId1("vendor_username")); 
                preparedStatement.setString(2, "Gaming Center");
                preparedStatement.setString(3, location);
                preparedStatement.setString(4, packageType);
                preparedStatement.setDate(5, new java.sql.Date(bookingDate.getTime()));

                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Gaming Center booked successfully.");
                } else {
                    System.out.println("Failed to book Gaming Center.");
                }
            }
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateVendorDetails1() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new location: ");
        String newLocation = scanner.nextLine();

        try {
            String query = "UPDATE vendors SET location=? WHERE username=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, newLocation);
                preparedStatement.setString(2, "vendor_username"); 

                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Vendor details updated successfully.");
                } else {
                    System.out.println("Failed to update vendor details.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
