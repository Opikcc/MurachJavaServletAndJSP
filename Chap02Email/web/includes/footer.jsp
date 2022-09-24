<%@page import="java.util.GregorianCalendar, java.util.Calendar" %>

<%
  GregorianCalendar calendar = new GregorianCalendar();
  int currentYear = calendar.get(Calendar.YEAR);
%>
      
    <p>&copy; Copyright <%= currentYear %> Mike Murach &amp; Associates</p>
  </body>
</html>
