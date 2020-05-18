<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value='/styles/main.css'/>">
        <!--<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>-->
    </head>
    <body>
        <header>
            <a href="<c:url value='/index.jsp'/>">
                <img src="<c:url value='/images/logo.png'/>" 
                     alt="Book Store Logo" width="188"></a>
            <h1>Sherwin Bookstore</h1>
            <h2>Best place to read & search</h2>
        </header>

        <nav id='nav_bar'>    
<!--            <form action="<c:url value='/book'/>" method="post" id="searchform">
                <input type="hidden" name="todo" value="search">        
                <input type="search" name="search" placeholder="search book title">
                <input type="submit" value="Search" class="margin_right">
            </form>-->

            <input type="search" name="search" placeholder="search book title" id="searchTxt">

<!--            <ul>
                <li><a href="#" onclick="show('ll')">Admin</a></li>
                <li><a href="#" onclick="show('login')">Account</a></li>
                    <c:if test="${not empty user}">
                    <li><a href="<c:url value=''/>">Hi ${user.username}</a></li>
                    <li><a href="<c:url value='/user?todo=logout'/>">Logout</a></li>
                    </c:if>
                <li><a href="#" onclick="show('cart')">Cart</a></li>
                <li><a href="#" onclick="show('order')">Order</a></li>
            </ul>-->
        </nav>