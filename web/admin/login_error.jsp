<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_admin.jsp" />

<!-- start the middle column -->

<section>
    <h1>Admin Login Form - Error</h1>
    <p>You did not log in successfully.</p>
    <p>Please check your username and password and try again.</p>
    <p>If that doesn't work, you may need to implement the JDBC realm 
        as described in chapter 16.</p>
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