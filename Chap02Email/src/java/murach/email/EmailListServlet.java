package murach.email;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import murach.business.User;

public class EmailListServlet extends HttpServlet {

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String url = "/index.html";
    
    // Get current action
    String action = request.getParameter("action");
    if (action == null) {
      action = "join";  // Default action
    }
    
    // Perform action and set URL to appropriate page
    if (action.equals("join")) {
      url = "/index.html";    // The "join" page
    }
    else if (action.equals("add")) {
      // Get parameters from the request
      String firstName  = request.getParameter("firstName");
      String lastName   = request.getParameter("lastName");
      String email      = request.getParameter("email");
      
      // Store data in User object and save User object in database
      User user = new User(firstName, lastName, email);
//      UserDB.insert(user);

      // Set User object in request object and set URL
      request.setAttribute("user", user);
      url = "/thanks.jsp";    // The "thanks" page
    }
    
    // Forward request and response objects to specified URL
    getServletContext()
            .getRequestDispatcher(url)
            .forward(request, response);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest  request,
                        HttpServletResponse response) throws ServletException, IOException {
    processRequest(request, response);
  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }

}
