package jdbc_code;

import java.sql.*;

/*
 *    
 *    Execution =>  java -cp .:mysql-connector-j-8.3.0.jar  JDBCExample.java
 * 
 */

public class JDBCExample {
  // jdbc -> credentials
  private static final String URL = "jdbc:mysql://localhost:3306/JAVA_APP";
  private static final String USERNAME = "new_user";
  private static final String PASSWORD = "password";

  // connection
  private static Connection connection;

  public static void main(String[] args) {
    try {
      // Connect to the database
      connect();

      // Create the Student table if not exists
      createStudentTable();

      // Insert data into the table
      insertStudent("Atul", "Sangli", 'A', 90.50);
      insertStudent("Sangram", "Sangli", 'B', 70.25);
      insertStudent("Satya", "Mumbai", 'B', 61.36);
      insertStudent("Jaydeep", "Pune", 'B', 60.95);
      insertStudent("Prashant", "Sangli", 'C', 55.26);
      insertStudent("Abhi", "Pune", 'C', 55.84);

      // Delete record for Roll_No 5
      deleteStudent(5);

      // Update city from Sangli to Pune
      updateCity("Sangli", "Pune");

      // Display the names of students having marks greater than 60
      displayStudentsWithMarksGreaterThan(60);

      // Display students according to their marks (Descending order)
      displayStudentsDescendingOrder();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      // Close the connection
      try {
        if (connection != null)
          connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  private static void connect() throws SQLException {
    connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
  }

  private static void createStudentTable() throws SQLException {
    try (Statement statement = connection.createStatement()) {
      String createTableSQL = "CREATE TABLE IF NOT EXISTS Student ("
          + "Roll_No INT PRIMARY KEY AUTO_INCREMENT,"
          + "Name VARCHAR(100),"
          + "City VARCHAR(100),"
          + "Grade CHAR(1),"
          + "Marks DECIMAL(5, 2)"
          + ")";
      statement.executeUpdate(createTableSQL);
    }
  }

  private static void insertStudent(String name, String city, char grade, double marks) throws SQLException {
    String insertSQL = "INSERT INTO Student (Name, City, Grade, Marks) VALUES (?, ?, ?, ?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
      preparedStatement.setString(1, name);
      preparedStatement.setString(2, city);
      preparedStatement.setString(3, String.valueOf(grade));
      preparedStatement.setDouble(4, marks);
      preparedStatement.executeUpdate();
    }
  }

  private static void deleteStudent(int rollNo) throws SQLException {
    String deleteSQL = "DELETE FROM Student WHERE Roll_No = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
      preparedStatement.setInt(1, rollNo);
      preparedStatement.executeUpdate();
    }
  }

  private static void updateCity(String oldCity, String newCity) throws SQLException {
    String updateSQL = "UPDATE Student SET City = ? WHERE City = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
      preparedStatement.setString(1, newCity);
      preparedStatement.setString(2, oldCity);
      preparedStatement.executeUpdate();
    }
  }

  private static void displayStudentsWithMarksGreaterThan(double threshold) throws SQLException {
    String querySQL = "SELECT Name FROM Student WHERE Marks > ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(querySQL)) {
      preparedStatement.setDouble(1, threshold);
      ResultSet resultSet = preparedStatement.executeQuery();
      System.out.println("Names of students with marks greater than " + threshold + ":");
      while (resultSet.next()) {
        System.out.println(resultSet.getString("Name"));
      }
    }
  }

  private static void displayStudentsDescendingOrder() throws SQLException {
    String querySQL = "SELECT Name FROM Student ORDER BY Marks DESC";
    try (Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(querySQL)) {
      System.out.println("Names of students in descending order of marks:");
      while (resultSet.next()) {
        System.out.println(resultSet.getString("Name"));
      }
    }
  }
}
