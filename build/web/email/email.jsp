<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<sql:setDataSource var="ds" dataSource="jdbc/bookDB" />
<sql:query sql="select * from books" var="rs" dataSource="${ds}" /> 
<sql:query sql="desc books" var="title" dataSource="${ds}" /> 

<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />
 <!--start the middle column--> 

<section>
    <form action="<c:url value='/Email' />" method="post">
        <label>First Name: </label>
        <input type="text" name="firstname" value="${firstname}"><br>
        <label>Last Name: </label>
        <input type="text" name="lastname" value="${lastname}"><br>
        <label>Email: </label>
        <input type="email" name="email" value="${email}"><br>
        <input type="hidden" name="todo" value="add">
        <input type="submit" value="Join the email list">
        
    </form>
        <br>
        <p><i>${msg_email}</i></p>
</section>

<!-- end the middle column -->

<jsp:include page="/includes/column_right_news.jsp" />
<jsp:include page="/includes/footer.jsp" />






