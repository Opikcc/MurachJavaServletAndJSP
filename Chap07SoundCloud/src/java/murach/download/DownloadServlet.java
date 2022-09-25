package murach.download;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import murach.business.User;
import murach.business.UserIO;
import murach.util.CookieUtil;

public class DownloadServlet extends HttpServlet {

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    try ( PrintWriter out = response.getWriter()) {
      /* TODO output your page here. You may use following sample code. */
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Servlet DownloadServlet</title>");      
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Servlet DownloadServlet at " + request.getContextPath() + "</h1>");
      out.println("</body>");
      out.println("</html>");
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    
    HttpSession session = request.getSession();
    
    Date currentDate = new Date();
    
    // Application Information
    HashMap<String, String> appData = new HashMap();
    appData.put("appName", "Sistem informasi Lagu-Lagu");
    appData.put("version", "v1.0");
    
    // Get current action
    String action = request.getParameter("action");
    if (action == null) {
      action = "viewAlbums";  // Default action
    }
    
    // Perform action and set URL to appropriate page
    String url = "/index.jsp";
    
    if (action.equals("viewAlbums")) {
      url = "/index.jsp";
    }
    else if (action.equals("checkUser")) {
      url = checkUser(request, response);
    }
    
    // Set  attribute
    session.setAttribute("currentDate", currentDate);
    request.setAttribute("appData", appData);
    
    User user = new User("John", "Smith", "jsmith@gmail.com");
    session.setAttribute("user", user);
    
    String[] colors = {"red", "green", "blue"};
    ServletContext sc = this.getServletContext();
    sc.setAttribute("colors", colors);
    
    ArrayList<User> users = new ArrayList();
    users.add(user);
    session.setAttribute("users", users);
    
    log("currentDate : " + currentDate);
    log("appData : " + appData);
    log("user : " + user);
    
    // Forward to the view
    getServletContext()
            .getRequestDispatcher(url)
            .forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    
    String action = request.getParameter("action");
    
    // Perform action and set URL to appropriate page
    String url = "/index.jsp";
    if (action.equals("registerUser")) {
      url = registerUser(request, response);
    }
    
    // Forward to the view
    getServletContext()
            .getRequestDispatcher(url)
            .forward(request, response);
  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }
  
  private String checkUser(HttpServletRequest request, HttpServletResponse response) {
    
    String productCode = request.getParameter("productCode");
    HttpSession session = request.getSession();
    session.setAttribute("productCode", productCode);
    User user = (User) session.getAttribute("user");
    
    String url;
    // If User object doesn't exist, check email cookie
    if (user == null) {
      Cookie[] cookies = request.getCookies();
      String emailAddress = CookieUtil.getCookieValue(cookies, "emailCookie");
      
      // If cookie doesn't exist, go to Registration page
      if (emailAddress == null || emailAddress.equals("")) {
        url = "/register.jsp";
      }
      // If cookie exists, create User object and go to Downloads page
      else {
        user = UserIO.getUser(emailAddress, user);
        url = "/" + productCode + "_download.jsp";
      }
    }
    // If User object exists, go to Downloads page
    else {
      url = "/" + productCode + "_download.jsp"; 
    }
    
    return url;
  }
  
  private String registerUser(HttpServletRequest request, HttpServletResponse response) {
    
    // Get the user data
    String email = request.getParameter("email");
    String firstName = request.getParameter("firstName");
    String lastName = request.getParameter("lastName");
    
    // Store the data in a User object
    User user = new User();
    user.setEmail(email);
    user.setFirstName(firstName);
    user.setLastName(lastName);
    
    // Write the User object to a file
    UserIO.add(user, email);
    
    // Store the User object as a session attribute
    HttpSession session = request.getSession();
    session.setAttribute("user", user);
    
    // Add a cookie that stores the user's email to browser
    Cookie c = new Cookie("emailCookie", email);
    c.setMaxAge(60 * 60 * 24 * 365 * 2);  // Set age to 2 years
    c.setPath("/"); // Allow entire app to access it
    response.addCookie(c);
    
    // Create an return a URL for the appropriate Download page
    String productCode = (String) session.getAttribute("productCode");
    String url = "/" + productCode + "_download.jsp";
    return url;
  }

}
