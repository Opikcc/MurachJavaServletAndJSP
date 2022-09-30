package murach.sql;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SqlGatewayServlet extends HttpServlet {

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    try ( PrintWriter out = response.getWriter()) {
      /* TODO output your page here. You may use following sample code. */
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Servlet SqlGatewayServlet</title>");      
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Servlet SqlGatewayServlet at " + request.getContextPath() + "</h1>");
      out.println("</body>");
      out.println("</html>");
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    
    String sqlStatement = request.getParameter("sqlStatement");
    String sqlResult = "";
    
    try {
      // Load the driver
      Class.forName("com.mysql.jdbc.Driver");
      
      // Get a connection
      String dbUrl    = "jdbc:mysql://localhost:3306/murach_test";
      String username = "root";
      String password = "password";

      Connection connection = DriverManager.getConnection(dbUrl, username, password);
      
      // Create a statement
      Statement statement = connection.createStatement();
      
      // Parse the SQL string
      sqlStatement = sqlStatement.trim();
      if (sqlStatement.length() >= 6) {
        String sqlType = sqlStatement.substring(0, 6);
        if (sqlType.equalsIgnoreCase("select")) {
          // Create the HTML for the result set
          ResultSet resultSet = statement.executeQuery(sqlStatement);
          sqlResult = SQLUtil.getHtmlTable(resultSet);
          resultSet.close();
        }
        else {
          int i = statement.executeUpdate(sqlStatement);
          if (i == 0) { // A DDL statement
            sqlResult = "<p>The statement executed successfully.</p>";
          }
          else {  // An INSERT, UPDATE, or DELETE statement
            sqlResult = "<p>The statement executed successfully.<br>" +
                        i + " row(s) affected.";
          }
        }
      }
      statement.close();
      connection.close();
    }
    catch (ClassNotFoundException e) {
      sqlResult = "<p>Error loading the database driver: <br>"
                  + e.getMessage() + "</p>";
    }
    catch (SQLException e) {
      sqlResult = "<p>Error executing the SQL statement: <br>"
                  + e.getMessage() + "</p>";
      
    }
    
    HttpSession session = request.getSession();
    session.setAttribute("sqlResult", sqlResult);
    session.setAttribute("sqlStatement", sqlStatement);
    
    String url = "/index.jsp";
    getServletContext().getRequestDispatcher(url).forward(request, response);
  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }

}
