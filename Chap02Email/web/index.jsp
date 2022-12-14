<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Murach's Java Servlets and JSP</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css" />
  </head>
  <body>
    <h1>Join our email list</h1>
    <p>To join our email list, enter your name and email address below.</p>
    <p><i>${message}</i></p>

    <form action="emailList" method="post">
      <input type="hidden" name="action" value="add" />

      <label>Email : </label>
      <input type="email" name="email" value="${user.email}" /><br />

      <label>First Name : </label>
      <input type="text" name="firstName" value="${user.firstName}" /><br />

      <label>Last Name : </label>
      <input type="text" name="lastName" value="${user.lastName}" /><br />

      <label>&nbsp;</label>
      <input type="submit" value="Join Now" id="submit" />
    </form>
  </body>
</html>
