<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_admin.jsp" />

<!-- start the middle column -->

<section>
    <h1>Admin Login Form</h1>
    <p>Please enter your username and password to continue.</p>
    <form action="j_security_check" method="get">
        <label class="pad_top">Username</label>
        <input type="text" name="j_username"><br>
        <label class="pad_top">Password</label>
        <input type="password" name="j_password"><br>
        <label>&nbsp;</label>
        <input type="submit" value="Login" class="margin_left">    
    </form>
</section>

<!-- end the middle column -->

<jsp:include page="/includes/footer.jsp" />