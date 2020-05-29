<%@page import="java.util.Enumeration"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<section>
    <p>You have logged out.</p><br>
    <a href="#" onclick="showPage('./account/login.html')">Login Again</a>   

    <%
//        Enumeration names = session.getAttributeNames();
//        while (names.hasMoreElements()) {
//            System.out.println((String) names.nextElement());
//        }
//        session = request.getSession(false);
//        if (session != null) {
//            session.invalidate();
//        }
    %>

</section>