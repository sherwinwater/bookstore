<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value='/styles/main.css'/>">
        <script src="https://kit.fontawesome.com/87f181d628.js" crossorigin="anonymous"></script>
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
<!--            <input type="search" name="search" placeholder="search book title" id="searchTxt">-->
            <ul>
                <li><a href="<c:url value='/' />">Home</a></li>
                <li><a href="<c:url value='/admin'/>" >Admin</a></li>
                <li><a href="<c:url value='/user?todo=logout'/>">Logout</a></li>
                <li><a href="#" onclick="showPage('/ebook/admin/books.jsp')">Books</a></li>
                <li><a href="#" onclick="showPage('order')">Order</a></li>
                <li><a href="<c:url value='/admin/users' />">Users</a></li>
                <li><a href="<c:url value='/admin/invoices' />">Invoices</a></li>
            </ul>

        </nav>
                
   