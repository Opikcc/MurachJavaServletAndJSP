<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Murach's Java Servlets and JSP</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css" />
  </head>
  <body>
    
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:if test="${sqlStatement == null}">
      <c:set var="sqlStatement" value="select * from user" />
    </c:if>
    
    <h1>The SQL Gateway</h1>
    <p>Enter an SQL statement and click the Execute button.</p>
    
    <p><strong>SQL Statement</strong></p>
    <form action="sqlGateway" method="post">
      <textarea name="sqlStatement" cols="60" rows="8">${sqlStatement}</textarea>
      <input type="submit" value="Execute">
    </form>
      
      <p><strong>SQL result</strong></p>
      ${sqlResult}
  </body>
</html>
