<%@taglib prefix="mma" uri="/WEB-INF/murach.tld" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Murach's Java Servlets and JSP</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css" />
  </head>
  <body>
    <h1>CD list</h1>
    <table>
      <tr>
        <th>Description</th>
        <th class="right">Price</th>
        <th>&nbsp;</th>
      </tr>
      <tr>
        <td>86 (the band) - True Life Songs and Pictures</td>
        <td class="right">$14.95</td>
        <td>
          <form action="cart" method="post">
            <input type="hidden" name="productCode" value="8601" />
            <input type="submit" value="Add To Cart" />
          </form>
        </td>
      </tr>
      <tr>
        <td>Paddlefoot = The First CD</td>
        <td class="right">$12.95</td>
        <td>
          <form action="cart" method="post">
            <input type="hidden" name="productCode" value="pf01" />
            <input type="submit" value="Add To Cart" />
          </form>
        </td>
      </tr>
      <tr>
        <td>Paddlefoot = The Second CD</td>
        <td class="right">$14.95</td>
        <td>
          <form action="cart" method="post">
            <input type="hidden" name="productCode" value="pf02" />
            <input type="submit" value="Add To Cart" />
          </form>
        </td>
      </tr>
      <tr>
        <td>Joe Rut - Genuine Wood Grained Finish</td>
        <td class="right">$14.95</td>
        <td>
          <form action="cart" method="post">
            <input type="hidden" name="productCode" value="jr01" />
            <input type="submit" value="Add To Cart" />
          </form>
        </td>
      </tr>
    </table>

    <h4>A Custom JSP tag without scripting variables</h4>
    <mma:cart>
      <table>
        <tr>
          <td><%= pageContext.getAttribute("quantity") %></td>
          <td><%= pageContext.getAttribute("productDescription") %></td>
          <td><%= pageContext.getAttribute("productPrice") %></td>
          <td><%= pageContext.getAttribute("total") %></td>
        </tr>
      </table>
    </mma:cart>

    <h4>A Custom JSP tag with scripting variables</h4>
    <mma:cart>
      <table>
        <tr>
          <td><%= quantity %></td>
          <td><%= productDescription %></td>
          <td><%= productPrice %></td>
          <td><%= total %></td>
        </tr>
      </table>
    </mma:cart>

    <h4>A Custom JSP tag using Expression Language</h4>
    <mma:cart>
      <table>
        <tr>
          <td>${quantity}</td>
          <td>${productDescription}</td>
          <td>${productPrice}</td>
          <td>${total}</td>
        </tr>
      </table>
    </mma:cart>
    
    <p><mma:ifEmptyMark color="blue" field="" /> marks required fields</p>
    
    <label class="pad_top">Last Name</label>
    <input type="text" name="lastName" value="${user.getLastName}">
    <mma:ifEmptyMark color="blue" field="${user.getLastName}" />
      
    <p>The current date is <mma:currentDate />.</p>
    <mma:ifWeekDay>
      <p>Live support available at 1-800-555-2222</p>
    </mma:ifWeekDay>
  </body>
</html>
