package murach.email;

import java.io.IOException;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import murach.business.User;


@WebServlet(urlPatterns = {"/emailList"},
            initParams={@WebInitParam(name  = "relativePathToFile",
                                      value = "/WEB-INF/EmailList.txt")})
public class EmailListServlet extends HttpServlet {

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
//    String url = "/index.html";
    String url = "/index.jsp";
    
    // Get current action
    String action = request.getParameter("action");
    if (action == null) {
      action = "join";  // Default action
    }
    
    // Perform action and set URL to appropriate page
    if (action.equals("join")) {
//      url = "/index.html";    // The "join" page
      url = "/index.jsp";    // The "join" page
    }
    else if (action.equals("add")) {
      // Get parameters from the request
      String firstName  = request.getParameter("firstName");
      String lastName   = request.getParameter("lastName");
      String email      = request.getParameter("email");
      
      String isMarried  = request.getParameter("married");
      String[] cars     = request.getParameterValues("cars");
      
      System.out.println("isMarried : " + isMarried);
      if (cars != null)
        Arrays.asList(cars).stream().forEach((s) -> System.out.println(s));
      
      // Store data in User object and save User object in database
      User user = new User(firstName, lastName, email);
//      UserDB.insert(user);

      // Validate the parameters
      String message;
      if (firstName == null || lastName == null || email == null ||
          firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
        message = "Please fill out all three text boxes.";
        url = "/index.jsp";
      }
      else {
        message = "";
        url = "/thanks.jsp";
      }

      // Set User object in request object and set URL
      request.setAttribute("user", user);
      request.setAttribute("message", message);
      request.setAttribute("id", 1);
//      url = "/thanks.jsp";    // The "thanks" page
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
