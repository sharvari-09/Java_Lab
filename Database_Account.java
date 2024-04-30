import java.sql.*;
import java.util.Scanner;

public class Database_Account {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/bank";
    static final String USER = "username";
    static final String PASS = "password";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        Scanner scanner = new Scanner(System.in);

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Connected to database.");

            while (true) {
                System.out.println("1. Create Account");
                System.out.println("2. Display Balance");
                System.out.println("3. Transfer Amount");
                System.out.println("4. Transaction History");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        createAccount(conn, scanner);
                        break;
                    case 2:
                        displayBalance(conn, scanner);
                        break;
                    case 3:
                        transferAmount(conn, scanner);
                        break;
                    case 4:
                        showTransactionHistory(conn, scanner);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            scanner.close();
        }
    }

    static void createAccount(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter account holder's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter initial balance: ");
        double balance = scanner.nextDouble();

        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO accounts (name, balance) VALUES (?, ?)");
        pstmt.setString(1, name);
        pstmt.setDouble(2, balance);
        pstmt.executeUpdate();
        System.out.println("Account created successfully.");
        pstmt.close();
    }

    static void displayBalance(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter account holder's name: ");
        String name = scanner.nextLine();

        PreparedStatement pstmt = conn.prepareStatement("SELECT balance FROM accounts WHERE name = ?");
        pstmt.setString(1, name);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            double balance = rs.getDouble("balance");
            System.out.println("Balance for " + name + " is $" + balance);
        } else {
            System.out.println("Account not found.");
        }
        pstmt.close();
    }

    static void transferAmount(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter sender's name: ");
        String sender = scanner.nextLine();
        System.out.print("Enter recipient's name: ");
        String recipient = scanner.nextLine();
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();

        // Check sender's balance
        PreparedStatement pstmt = conn.prepareStatement("SELECT balance FROM accounts WHERE name = ?");
        pstmt.setString(1, sender);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            double senderBalance = rs.getDouble("balance");
            if (senderBalance < amount) {
                System.out.println("Insufficient funds.");
                pstmt.close();
                return;
            }
        } else {
            System.out.println("Sender account not found.");
            pstmt.close();
            return;
        }
        pstmt.close();

        // Deduct amount from sender's account
        pstmt = conn.prepareStatement("UPDATE accounts SET balance = balance - ? WHERE name = ?");
        pstmt.setDouble(1, amount);
        pstmt.setString(2, sender);
        pstmt.executeUpdate();
        pstmt.close();

        // Add amount to recipient's account
        pstmt = conn.prepareStatement("UPDATE accounts SET balance = balance + ? WHERE name = ?");
        pstmt.setDouble(1, amount);
        pstmt.setString(2, recipient);
        pstmt.executeUpdate();
        pstmt.close();

        // Insert transaction record
        pstmt = conn.prepareStatement("INSERT INTO transactions (sender, recipient, amount) VALUES (?, ?, ?)");
        pstmt.setString(1, sender);
        pstmt.setString(2, recipient);
        pstmt.setDouble(3, amount);
        pstmt.executeUpdate();
        pstmt.close();

        System.out.println("Transfer successful.");
    }

    static void showTransactionHistory(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter account holder's name: ");
        String name = scanner.nextLine();

        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM transactions WHERE sender = ? OR recipient = ?");
        pstmt.setString(1, name);
        pstmt.setString(2, name);
        ResultSet rs = pstmt.executeQuery();

        System.out.println("Transaction History:");
        while (rs.next()) {
            String sender = rs.getString("sender");
            String recipient = rs.getString("recipient");
            double amount = rs.getDouble("amount");
            System.out.println("Sender: " + sender + ", Recipient: " + recipient + ", Amount: $" + amount);
        }
        pstmt.close();
    }
}
