<%@page import="java.util.Enumeration"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<section>
    <%
        Enumeration names = session.getAttributeNames();
        while (names.hasMoreElements()) {%>
    <p>sessions: <%= (String) names.nextElement()%></p>

    <%}
    %>
    <p>cart[0]id ${cart[0].id} </p>
    <p>username ${user.username} </p>
    <p>contact ${contact.email} </p>


</section>