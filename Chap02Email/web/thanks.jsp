<%@ page import="murach.business.User" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Murach's Java Servlets and JSP</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css" />
  </head>
  <body>
    <h1>Thanks for joining our email list</h1>
    
    <p>Here is the information that you entered:</p>
    
    <label>Email : </label>
    <span>${user.email}</span><br />
    <label>First Name : </label>
    <span>${user.firstName}</span><br />
    <label>Last Name : </label>
    <span>${user.lastName}</span><br />
    
    <% User user = (User) request.getAttribute("user"); %>
    
    <p>Here is the information that you entered:</p>
    
    <label>ID : </label>
    <span><%= request.getAttribute("id") %></span><br />
    <label>Email : </label>
    <span><%= user.getEmail() %></span><br />
    <label>First Name : </label>
    <span><%= user.getFirstName() %></span><br />
    <label>Last Name : </label>
    <span><%= user.getLastName() %></span><br />
    
    <form action="emailList" method="get">
      <input type="hidden" name="action" value="join" />
      <input type="submit" value="Return" />
    </form>
  </body>
</html>
