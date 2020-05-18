<%@ page import="java.util.GregorianCalendar, java.util.Calendar" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    GregorianCalendar currentDate = new GregorianCalendar();
    int currentYear = currentDate.get(Calendar.YEAR);
%>
<footer>
    <p>&copy; Copyright <%= currentYear%> Sherwin  &amp; Company, Inc. 
        All rights reserved.</p>
</footer>
<!--<script src="/js/main.js"></script>-->
</body>
</html>