<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />
<!--start the middle column--> 

<section>
    <h1>cart!</h1>
    <table>
        <tr>
            <th>id</th>
            <th>price</th>
            <th>qty</th>
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

    <p>search from the database</p>
    <table>
        <c:forEach var="item" items="${selectitems}">
            <tr>
                <td>${item.id}</td>
                <td>${item.price}</td>
                <td>${item.quantity}</td>
                <td>${item.totalprice}</td>
            </tr>       
        </c:forEach>
    </table>
</section>

<!-- end the middle column -->

<jsp:include page="/includes/column_right_news.jsp" />
<jsp:include page="/includes/footer.jsp" />