package murach.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
  
  public static Connection connection;
  
  public static Connection getDBConn() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    }
    catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    
    if (connection == null) {
      try {
        String dbUrl    = "jdbc:mysql://localhost:3306/murach_test";
        String username = "root";
        String password = "password";
        
        connection = DriverManager.getConnection(dbUrl, username, password);
      }
      catch (SQLException e) {
        for (Throwable t : e) {
          t.printStackTrace();
        }
      }
    }
    
    return connection;
  }
  
}
