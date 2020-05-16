<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_catalog.jsp" />
<!--start the middle column--> 

<section>
    <!--retrieve book from database ebookshop-->
    <c:if test="${empty bookList}">
        <p><i>Please input search content</i></p>
    </c:if>

    <table>
        <c:if test="${not empty bookList}">
            <tr>
                <th>ID</th>
                <th>Author</th>
                <th>Title</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Action</th>
            </tr>
        </c:if>
        <c:forEach var="row" items="${bookList}">
            <tr>
                <td>${row.id}</td>
                <td>${row.author}</td>
                <td>${row.title}</td>
                <td>${row.price}</td>
            <form action="<c:url value='/book'/>" method="POST">
                <td>
                    <input type="number" name="book_qty" value="1" min="1">
                </td>
                <td>
                    <input type="hidden" name="todo" value="add">
                    <input type="hidden" name="book_id" value=${row.id}>
                    <input type="hidden" name="book_author" value=${row.author}>
                    <input type="hidden" name="book_title" value=${row.title}>
                    <input type="hidden" name="book_price" value=${row.price}>
                    <input type="submit" value="add to cart">
                </td>
            </form>
            </tr>
        </c:forEach>
    </table>
        <p id="message"></p>
    <c:if test="${not empty bookList}">
        <form action="<c:url value='/book'/>" method="POST">
            <input type="hidden" name="todo" value="view">
            <input type="submit" value="view cart">
        </form>
    </c:if>
</section>

<!-- end the middle column -->

<jsp:include page="/includes/column_right_news.jsp" />
<jsp:include page="/includes/footer.jsp" />






