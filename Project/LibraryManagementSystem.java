package Project;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LibraryManagementSystem {

    // Database URL, username, and password
    private static final String DB_URL = "jdbc:mysql://localhost:3306/library_db";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            System.out.println("Connected to the database.");

            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                System.out.println("\nLibrary Management System");
                System.out.println("1. Add Book");
                System.out.println("2. View Books");
                System.out.println("3. Search Book by Title");
                System.out.println("4. Delete Book by ID");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        addBook(connection, scanner);
                        break;
                    case 2:
                        viewBooks(connection);
                        break;
                    case 3:
                        searchBookByTitle(connection, scanner);
                        break;
                    case 4:
                        deleteBookById(connection, scanner);
                        break;
                    case 5:
                        System.out.println("Exiting the system. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 5);

            scanner.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addBook(Connection connection, Scanner scanner) {
        try {
            System.out.print("Enter book title: ");
            String title = scanner.nextLine();

            System.out.print("Enter author name: ");
            String author = scanner.nextLine();

            System.out.print("Enter publication year: ");
            int year = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            String sql = "INSERT INTO books (title, author, year) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, title);
                statement.setString(2, author);
                statement.setInt(3, year);
                statement.executeUpdate();
                System.out.println("Book added successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding book: " + e.getMessage());
        }
    }

    private static void viewBooks(Connection connection) {
        String sql = "SELECT * FROM books";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            System.out.println("\nBooks in the Library:");
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id") + ", Title: " + resultSet.getString("title") + ", Author: " + resultSet.getString("author") + ", Year: " + resultSet.getInt("year"));
            }
        } catch (SQLException e) {
            System.out.println("Error viewing books: " + e.getMessage());
        }
    }

    private static void searchBookByTitle(Connection connection, Scanner scanner) {
        System.out.print("Enter book title to search: ");
        String title = scanner.nextLine();

        String sql = "SELECT * FROM books WHERE title LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + title + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                System.out.println("\nSearch Results:");
                while (resultSet.next()) {
                    System.out.println("ID: " + resultSet.getInt("id") + ", Title: " + resultSet.getString("title") + ", Author: " + resultSet.getString("author") + ", Year: " + resultSet.getInt("year"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error searching book: " + e.getMessage());
        }
    }

    private static void deleteBookById(Connection connection, Scanner scanner) {
        System.out.print("Enter book ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String sql = "DELETE FROM books WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book deleted successfully.");
            } else {
                System.out.println("Book not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting book: " + e.getMessage());
        }
    }
}
