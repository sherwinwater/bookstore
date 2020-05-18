<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />
<!--start the middle column--> 

<section id="login">
    <h1>Log In</h1>
    <p><i>${message}</i></p>
    <form action="<c:url value='/user'/>" method="post" id="login">
        <input type="hidden" name="todo" value="login">        
        <label class="pad_top">Username:</label>
        <input type="text" name="username"><br>
        <label class="pad_top">Password:</label>
        <input type="password" name="password"><br>
        <label>&nbsp;</label>
        <input type="submit" value="Log In" class="margin_left">
    </form>
    <br>
    <h1>Sign Up</h1>
    <p><i>${message_signup}</i></p>
    <p><i>${msg_user}</i></p>
    <form action="<c:url value='/user'/>" method="post" id="signup">
        <input type="hidden" name="todo" value="signup">        
        <label class="pad_top">Username:</label>
        <input type="text" name="username" placeholder="email"><br>
        <label class="pad_top">Password:</label>
        <input type="password" name="password"><br>
        <label>&nbsp;</label>
        <input type="submit" value="Sign Up" class="margin_left">
    </form>
    <!--    <h2>Password info</h2>
        <p>Hash:<br>${hashedPassword}</p>
        <p>Salt:<br>${salt}</p>
        <p>Salted Hash:<br>${saltedAndHashedPassword}</p>-->
</section>
<!-- end the middle column -->

<jsp:include page="/includes/column_right_news.jsp" />
<jsp:include page="/includes/footer.jsp" />






