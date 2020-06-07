<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div>
    <c:choose>
        <c:when test="${empty cart}"><p>your cart is empty</p></c:when>
        <c:otherwise><h1>Estimate total price</h1></c:otherwise>
    </c:choose>
    <c:if test="${not empty cart}">
        <c:set var="priceTotal" value="${0}"></c:set>
        <c:forEach var="item" items="${cart}">
            <c:set var="priceTotal" value="${priceTotal + item.totalprice}"></c:set>
        </c:forEach>
        <c:set var="HST" value="${priceTotal *0.13}"></c:set>
        <c:set var="delivery" value="${priceTotal *0.19}"></c:set>

            <table>
                <tr>
                    <td style="text-align: left">Subtotal</td>
                    <td style="text-align: right">
                    ${String.format("%.2f",priceTotal)}</td>
            </tr>
            <tr>
                <td style="text-align: left">HST(13%)</td>
                <td style="text-align: right">
                    ${String.format("%.2f",HST)}</td>
            </tr>
            <tr>
                <td style="text-align: left">Delivery</td>
                <td style="text-align: right">
                    ${String.format("%.2f",delivery)}</td>
            </tr>
            <tr>
                <td style="text-align: left">Total Price</td>
                <td style="text-align: right">
                    ${String.format("%.2f",priceTotal+HST+delivery)}</td>
            </tr>
        </table>

    </c:if>
    <br><br><br>
    <c:choose>
        <c:when test="${not empty cart}">
            <input type="button" value="proceed to checkout">
        </c:when>
        <c:otherwise> <input type="button" value="proceed to checkout" disabled></c:otherwise>
    </c:choose>

</div>