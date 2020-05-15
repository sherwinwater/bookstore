<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />

<section>
    <h1>cart!</h1>
    <c:choose>
        <c:when test="${empty cart}"><p>your cart is empty</p></c:when>
        <c:otherwise><p>your cart: </p></c:otherwise>
    </c:choose>

    <c:if test="${not empty cart}">
        <table>
            <tr>
                <th>id</th>
                <th>price</th>
                <th>quantity</th>
                <th>total Price</th>
                <th>remove item</th>
            </tr>
            <c:forEach var="item" items="${cart}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.price}</td>
                    <td>
                        <form action="/ebook/book" method="POST">
                            <input type="hidden" name="book_id" value="${item.id}">
                            <input type="number" name="quantity" value="${item.quantity}" min="1">
                            <input type="hidden" name="todo" value="update">
                            <input type="submit" value="update">
                        </form>
                    </td>
                    <td>${item.totalprice}</td>
                    <td>
                        <form action="/ebook/book" method="POST">
                            <input type="hidden" name="todo" value="remove">
                            <input type="hidden" name="quantity" value="0">
                            <input type="hidden" name="book_id_rm" value="${item.id}">
                            <input type="hidden" name="book_quantity_rm" value="${item.quantity}" min="1">
                            <input type="hidden" name="book_price_rm" value="${item.price}">
                            <input type="submit" value="remove item" >
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <form action="<c:url value='/book'/>" method="POST">
            <input type="hidden" name="todo" value="checkout">
            <input type="submit" value="checkout">
        </form>
    </c:if>

    <form action="<c:url value='/book'/>" method="POST">
        <input type="hidden" name="todo" value="shopping">
        <input type="submit" value="continue shopping">
    </form>

</section>

<!-- end the middle column -->

<jsp:include page="/includes/column_right_news.jsp" />
<jsp:include page="/includes/footer.jsp" />
