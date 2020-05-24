<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<section>   
    <h1>Log In</h1>
    <p><i id="message_login"></i></p>
    <form action="<c:url value='/user'/>" method="post" id="login">
        <input type="hidden" name="todo" value="login">        
        <label class="pad_top">Username:</label>
        <input type="text" name="username" ><br>
        <label class="pad_top">Password:</label>
        <input type="password" name="password"><br>
        <label>&nbsp;</label>
        <input type="submit" value="Log In" class="margin_left">
    </form>
    <br>
    <h1>Sign Up</h1>
    <p><i id="message_signup"></i></p>
    <form action="<c:url value='/test'/>" method="post" id="signup" onSubmit="">
        <input type="hidden" name="todo" value="signup">        
        <label class="pad_top">Username:</label>
        <input type="text" name="username" placeholder="email" 
               id="signup_username" onkeyup="checkUsername('ajaxuser?todo=checkname&username=')"><br>
        <label class="pad_top">Password:</label>
        <input type="password" name="password"><br>
        <label>&nbsp;</label>
        <input type="submit" value="Sign Up" class="margin_left">
    </form>

</section>