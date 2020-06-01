<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value='/styles/main.css'/>">
    </head>
    <body>
        <header>
            <a href="<c:url value='/index.html'/>">
                <img src="<c:url value='/images/logo.png'/>" 
                     alt="Book Store Logo" width="188"></a>
            <h1>Sherwin Bookstore</h1>
            <h2>Best place to read & search</h2>
        </header>

        <nav id='nav_bar'>    
            <input type="search" name="search" placeholder="search book title" id="searchTxt">
            <ul>
                <li><a href="#" onclick="showPage('./admin/admin.html')">Admin</a></li>
                <li><a href="#" onclick="showPage('./account/login.jsp')">Account</a></li>                
                <li><a href="<c:url value=''/>">Hi ${user.username}</a></li>
                <li><a href="<c:url value='/user?todo=logout'/>">Logout</a></li>

                <li><a href="#" onclick="showPage('./cart')">Cart</a></li>
                <li><a href="#" onclick="showPage('order')">Order</a></li>
            </ul>

        </nav>