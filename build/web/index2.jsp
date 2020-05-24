<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<sql:setDataSource var="ds" dataSource="jdbc/bookDB" />
<sql:query sql="select * from books" var="rs" dataSource="${ds}" /> 
<sql:query sql="desc books" var="title" dataSource="${ds}" /> 

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./styles/main.css">
    </head>
    <body>
        <header>
            <a href="<c:url value='/index.jsp'/>">
                <img src="./images/logo.png" 
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

        <aside id="sidebarA">
            <nav>
                <ul>
                    <li><a class="link current" href="./index.jsp" >
                            Home</a></li>
                    <li >
                        <a class="link" href="#" id="catalog" onclick='showSearchandCatalog("ajaxsearch?search=")'>
                            Browse Catalog</a>
                        <ul id="subcatalog" style="display:none; margin-bottom: 0;">
                            <li onclick='showSearchResults("ajaxsearch?search=java")' style="margin-top: 0.5em;">
                                <a href="#"  id='java_quantity' ></a></li>
                            <li onclick='showSearchResults("ajaxsearch?search=PHP")'>
                                <a href="#"  id='PHP_quantity' ></a></li>
                            <li onclick='showSearchResults("ajaxsearch?search=JavaScript")' style="margin-bottom: 0em;">
                                <a href="#"  id='JavaScript_quantity'></a></li>
                        </ul>
                    </li>
                    <li><a class="link" href="#" id="email">
                            Join Email List</a></li>
                    <li><a class="link" href="#" id="service">
                            Customer Service</a></li>
                </ul>
            </nav>
        </aside>
        <!-- start the middle column -->
        <section id="content">
            <h1 class='content'>Welcome<span id="username_welcome"></span> Readers!</h1>
            <p class='content'>Thanks for visiting. Make yourself at home. Feel free to browse through 
                our book catalog. When you do, you can read samples from on our site.
                We think our catalog contains some great books, and we 
                hope you like it as much as we do.</p>
            <p class='content'>If you find an book that you like, we hope that you will use this site 
                to order it. Most of the book we carry are not available anywhere else!</p>
        </section>
        <!-- end the middle column -->

        <aside id="sidebarB">
            <h1>New Release</h1>
            <img src="./images/becoming_MichelleObama.jpg" width="120" 
                 alt="becoming cover">
            <h2><a href="catalog/product/8601" class="no_underline">
                    BECOMING 
                </a></h2>
            <p>by Michelle Obama</p>
            <p class="news_item">The former first lady describes her journey from 
                the South Side of Chicago to the White House, and how she balanced work 
                and family.</p>
        </aside>

        <footer>
            <p>&copy; Copyright <script>document.write(new Date().getUTCFullYear());</script>
                Sherwin  &amp; Company, Inc. 
                All rights reserved.</p>
        </footer>
        <script src="./js/main.js"></script>
    </body>
</html>