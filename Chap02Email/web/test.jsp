<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" scope="request" class="murach.business.User" />
<jsp:include page="/includes/header.html" />

    <h1>Thanks for joining our email list</h1>
    
    <label>Email:</label>
    <span><jsp:getProperty name="user" property="email" /></span><br>
    
    <label>Email:</label>
    <span><%= user.getEmail() %></span><br>
    
    <label>Email:</label>
    <span>${user.email}</span>
    
    <p>The current year is ${currentYear}</p>
    
    <c:if test="${message != null}">
      <p><i>${message}</i></p>
    </c:if>
    
    <%
      String message = (String) request.getAttribute("message");
      if (message != null && !message.isEmpty()) {
    %>
    <p><i><%=message%></i></p>
    <% } %>
    <c:if test="${user.wantUpdates == 'Yes'}">
      <p>This user wants updates!</p>
    </c:if>
<jsp:include page="/includes/footer.jsp" />