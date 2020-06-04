<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./styles/main.css">
    </head>
    <body>
        <header>
            <a href="./index.html">
                <img src="./images/logo.png" 
                     alt="Book Store Logo" width="188"></a>
            <h1>Sherwin Bookstore</h1>
            <h2>Best place to read & search</h2>
        </header>

        <nav id='nav_bar'>    
            <input type="search" name="search" placeholder="search book title" 
                   id="searchTxt" style="width: 20em">
            <ul>
                <!--<li><a href="#" onclick="showPage('./admin/login.html')">Admin</a></li>-->
                <li><a href="admin" >Admin</a></li>
                <li><a href="#" onclick="showPage('./account/login.html')">Account</a>   
                    <ul id="login_sucess" style="display: none">
                        <li><a href="<c:url value=''/>"></a></li>
                        <li><a href="<c:url value='/user?todo=logout'/>">Logout</a></li>
                    </ul>
                </li>
                <li><a href="#" onclick="viewCart()">Cart</a></li>
                <li><a href="#" onclick="showPage('./order/order.jsp')">Order</a></li>
                <!--<li><a href="#" onclick="showPage('./account/logout.jsp')">Logout</a></li>-->
                <li><a href="#" onclick="logoutUser()">Logout</a></li>
                <li><a href="#" onclick="showPage('./account/showsession.jsp')">session</a></li>
                <li><a href="#" onclick="showPage('./order/invoice.jsp')">Invoice</a></li>
            </ul>

        </nav>