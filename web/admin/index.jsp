<jsp:include page="/includes/header_admin.jsp" />
<%--<jsp:include page="/includes/column_left_admin.jsp" />--%>

<!-- start the middle column -->

<section id="content_admin">
    <h1>Welcome to the Admin application</h1>
    <form action="orders.jsp" method="post">
        <input type="submit" value="Collect Orders">
    </form>
    <form action="reports.htm" method="post">
        <input type="submit" value="Reports">
    </form>
</section>

<!-- end the middle column -->

<jsp:include page="/includes/footer_admin.jsp" />