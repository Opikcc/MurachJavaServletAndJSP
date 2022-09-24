package murach.email;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import murach.business.User;

@WebServlet("/murachTest")
public class MurachTestServlet extends HttpServlet {

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    
//    String url = "/murach_test.jsp";
    String url = "/test.jsp";
    
    GregorianCalendar calendar = new GregorianCalendar();
    int currentYear = calendar.get(Calendar.YEAR);
    
    request.setAttribute("currentYear", currentYear);
    request.setAttribute("message", "Hello There!!!");
    
    User user = new User("Asep@Gmail.Com", "Asep", "Anduk");
    user.setWantUpdates("Yes");
    request.setAttribute("user", user);
    
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
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }

}
