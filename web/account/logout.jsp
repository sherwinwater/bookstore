<%@page import="java.util.Enumeration"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<section>
    <h1>Welcome Readers!</h1>
    <p>You have logged out.</p>
    <p>Thanks for visiting. Make yourself at home. Feel free to browse through 
        our book catalog. When you do, you can read samples from on our site.
        We think our catalog contains some great books, and we 
        hope you like it as much as we do.</p>

    <p>If you find an book that you like, we hope that you will use this site 
        to order it. Most of the book we carry are not available anywhere else!</p>

    <%
        Enumeration names = session.getAttributeNames();
            while (names.hasMoreElements()) {
                System.out.println((String) names.nextElement());
            }
            
        session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        System.out.println("session : "+session);
    %>



</section>