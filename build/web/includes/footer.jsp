<%@ page import="java.util.GregorianCalendar, java.util.Calendar" %>
<%
    GregorianCalendar currentDate = new GregorianCalendar();
    int currentYear = currentDate.get(Calendar.YEAR);
%>
<footer>
    <p>&copy; Copyright <%= currentYear %> Sherwin  &amp; Company, Inc. 
        All rights reserved.</p>
</footer>
</body>
</html>