package murach.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import murach.business.Product;
import murach.database.DBConn;

public class ProductDAO {
  
  Connection conn = DBConn.getDBConn();
          
  private String getUserID() throws SQLException {
    String userID = "";
    
    Statement statement = conn.createStatement();
    ResultSet userIDResult = statement.executeQuery(
      " SELECT user_id FROM User " +
      " WHERE Email = 'jsmith@gmail.com'"
    );
    
    while(userIDResult.next()) {
      userID = userIDResult.getString("user_id");
    }
            
    return userID;
  }
          
  private List<Product> getListProduct() throws SQLException {
    List<Product> listProduct = new ArrayList();
    
    ResultSet result;
    try (Statement statement = conn.createStatement()) {
      result = statement.executeQuery(
              " SELECT * FROM product " );
      while(result.next()) {
        Product product = new Product();
        
        product.setCode(result.getString("code"));
        product.setDescription(result.getString("description"));
        product.setPrice(result.getDouble("price"));
        
        listProduct.add(product);
      }
    }
    
    result.close();
            
    return listProduct;
  }
  
}
