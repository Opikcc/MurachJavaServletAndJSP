package murach.email;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test")
public class TestServlet extends HttpServlet {

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    
//    response.sendRedirect("http://www.google.com");
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    try {
      ServletContext sc = this.getServletContext();
      String path = sc.getRealPath("/WEB-INF/application.properties");
      
      String custEmailServlet = getServletContext().getInitParameter("custServEmail");
      
      out.println("<h1>HTML from servlet<h1>");
      out.println("<p>Path : " + path + "</p>");
      out.println("custEmailServlet : " + custEmailServlet);
    }
    finally {
      out.close();
    }
    
    log("email = " + getServletContext().getInitParameter("custServEmail"));
    
    try {
      throw new NullPointerException();
    }
    catch (Exception e) {
      log("An Exception Occured.", e);
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
    processRequest(request, response);
  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }

}
