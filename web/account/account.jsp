<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<sql:setDataSource var="ds" dataSource="jdbc/bookDB" />
<sql:query sql="select * from books" var="rs" dataSource="${ds}" /> 
<sql:query sql="desc books" var="title" dataSource="${ds}" /> 

<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />
 <!--start the middle column--> 

<section>
    <p>account</p>
</section>

<!-- end the middle column -->

<jsp:include page="/includes/column_right_news.jsp" />
<jsp:include page="/includes/footer.jsp" />






