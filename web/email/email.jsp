<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<sql:setDataSource var="ds" dataSource="jdbc/bookDB" />
<sql:query sql="select * from books" var="rs" dataSource="${ds}" /> 
<sql:query sql="desc books" var="title" dataSource="${ds}" /> 

<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />
<!--start the middle column--> 

<section>
    <form action="<c:url value='/Email' />" method="post" 
          onSubmit="return validateForm()" name="form_email">
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
    <p ><i id="msg_validate"></i></p>
</section>

<script>
    var msg_validate = document.getElementById('msg_validate');
    function validateForm() {
        if (document.form_email.firstname.value == "") {
            msg_validate.innerHTML = "first name should not be left blank";
            document.form_email.firstname.focus();
            return false;
        } else if (document.form_email.lastname.value == "") {
            msg_validate.innerHTML = "last name should not be left blank";
            document.form_email.lastname.focus();
            return false;
        } else if (document.form_email.email.value == "") {
            msg_validate.innerHTML = "email should not be left blank";
            document.form_email.email.focus();
            return false;
        }
    }
</script>

<!-- end the middle column -->

<jsp:include page="/includes/column_right_news.jsp" />
<jsp:include page="/includes/footer.jsp" />






