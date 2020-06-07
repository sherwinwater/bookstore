<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div>
    <c:choose>
        <c:when test="${empty cart}"><p>your cart is empty</p></c:when>
        <c:otherwise><h1>My cart: </h1></c:otherwise>
    </c:choose>
    <c:if test="${not empty cart}">
        <c:set var="priceTotal" value="${0}"></c:set>
            <table>
            <c:forEach var="item" items="${cart}">
                <c:set var="priceTotal" value="${priceTotal + item.totalprice}"></c:set>
                    <tr>
                        <td colspan="2" style="text-align: left">${item.title}</td>
                </tr>
                <tr>
                    <td style="text-align: left">Quantity:${item.quantity}</td>
                    <td style="text-align: right">
                        ${String.format("%.2f",item.price * item.quantity)}</td>
                </tr>

            </c:forEach>
        </table>

    </c:if>
    <br>
    <br>
    <c:choose>
        <c:when test="${not empty cart}">
            <h2>Total Price: $${String.format("%.2f",priceTotal)}</h2>
            <input type="button" value="proceed to checkout" onclick="viewCart()">
        </c:when>
        <c:otherwise> <input type="button" value="proceed to checkout" disabled></c:otherwise>
    </c:choose>

</div>