package murach.cart;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import murach.business.Cart;
import murach.business.LineItem;
import murach.business.Product;
import murach.business.ProductIO;

public class CartServlet extends HttpServlet {

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    try ( PrintWriter out = response.getWriter()) {
      /* TODO output your page here. You may use following sample code. */
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Servlet CartServlet</title>");      
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Servlet CartServlet at " + request.getContextPath() + "</h1>");
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
    
    ServletContext sc = getServletContext();
    
    // Get current action
    String action = request.getParameter("action");
    if (action == null)
      action = "cart";
    
    // Perform action and set URL to appropriate page
    String url = "/index.jsp";
    if (action.equals("shop")) {
      url = "/index.jsp";
    }
    else if (action.equals("cart")) {
      String productCode    = request.getParameter("productCode");
      String quantityString = request.getParameter("quantity");
      
      HttpSession session = request.getSession();
      Cart cart = (Cart) session.getAttribute("cart");
      if (cart == null) {
        cart = new Cart();
      }
      
      // If the user enters a negative or invalid quantity,
      // the quantity is automatically reset to 1.
      int quantity;
      try {
        quantity = Integer.parseInt(quantityString);
        if (quantity < 0) {
          quantity = 1;
        }
      }
      catch (NumberFormatException nfe) {
        quantity = 1;
      }
      
      Product product = ProductIO.getProduct(productCode);
      
      LineItem lineItem = new LineItem();
      lineItem.setProduct(product);
      lineItem.setQuantity(quantity);
      if (quantity > 0) {
        cart.addItem(lineItem);
      }
      else if (quantity == 0) {
        cart.removeItem(lineItem);
      }
      
      System.out.println("===CART===");
      System.out.println("Items Count : " + cart.getCount());
      
      for (LineItem item : cart.getItems()) {
        Product produk = item.getProduct();
        System.out.println("===PRODUCT===");
        System.out.println("Code : " + produk.getCode());
        System.out.println("Description : " + produk.getDescription());
        System.out.println("Price : " + produk.getPrice());
        System.out.println("===QUANTITY===");
        System.out.println("Quantity : " + item.getQuantity());
      }
      
      session.setAttribute("cart", cart);
      session.setAttribute("cartTags", cart);
      url = "/cart.jsp";
    }
    else if (action.equals("checkout")) {
      url = "/checkout.jsp";
    }
    
    sc.getRequestDispatcher(url)
            .forward(request, response);
  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }

}
