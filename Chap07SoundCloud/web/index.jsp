<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Murach's Java Servlets and JSP</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css" />
  </head>
  <body>
    <h1>List of albums</h1>
    
    <p>
      <a href="download?action=checkUser&amp;productCode=8601">
        86 (the band) - True Life Songs and Pictures
      </a><br>
      <a href="download?action=checkUser&amp;productCode=pf01">
        Paddlefoot = The First CD
      </a><br>
      <a href="download?action=checkUser&amp;productCode=pf02">
        Paddlefoot = The Second CD
      </a><br>
      <a href="download?action=checkUser&amp;productCode=jr01">
        Joe Rut - Genuine Wood Grained Finish
      </a>
    </p>
    
    <p>Hello ${user["firstName"]}</p>
    
    <p>The first color is ${colors[0]} <br>
       The second color is ${colors[1]}
    </p>
    
    <p>The first color is ${colors["0"]} <br>
       The second color is ${colors["1"]}
    </p>
    
    <p>The first user email is ${users[0].email}</p>
    <p>The first user email is ${users["0"].email}</p>
    
    <p>The current date is ${currentDate}.</p>
    <h4>Application Details</h4>
    <label>App Name : </label>
    <input type="text" name="appName" value="${appData["appName"]}" disabled /><br />
    <label>App Version : </label>
    <input type="text" name="version" value="${appData["version"]}" disabled /><br />
  </body>
</html>
