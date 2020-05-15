<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@page import="controller.BookServlet" %>--%>
<%@page import="data.CartItem" %>
<%@page import="java.util.ArrayList" %>

<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />
<!--start the middle column--> 

<section>
    <h1>cart!</h1>
    <table>
        <tr>
            <th>id</th>
            <th>price</th>
            <th>quantity</th>
            <th>total Price</th>
        </tr>

        <c:forEach var="item" items="${cart}">
            <tr>
                <td>${item.id}</td>
                <td>${item.price}</td>
                <td>${item.quantity}</td>
                <td>${item.totalprice}</td>
            </tr>
        </c:forEach>
    </table>
    <form action="/ebook/book" method="POST">
        <input type="hidden" name="todo" value="newshopping">
        <input type="submit" value="new shopping">
    </form>
    <%
        ArrayList<CartItem> cart = (ArrayList<CartItem>) session.getAttribute("cart");
        cart.clear();
    %>

    <!--    <p>search from the database</p>
        <table>
    <c:forEach var="item" items="${selectitems}">
        <tr>
            <td>${item.id}</td>
            <td>${item.price}</td>
            <td>${item.quantity}</td>
            <td>${item.totalprice}</td>
        </tr>       
    </c:forEach>
</table>-->
</section>

<!-- end the middle column -->

<jsp:include page="/includes/column_right_news.jsp" />
<jsp:include page="/includes/footer.jsp" />