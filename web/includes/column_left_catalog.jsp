<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<aside id="sidebarA">
    <nav>
        <ul>
            <li><a href="<c:url value='/' />">
                    Home</a></li>
            <li id="catalog"><a class="current" href="#">Browse Catalog</a></li>
            <div id="list"></div>
            <li><a href="<c:url value='/email/email.jsp' />">
                    Join Email List</a></li>
            <li><a href="<c:url value='/customer_service' />">
                    Customer Service</a></li>
        </ul>
    </nav>
</aside>

<script>
    var catalog = document.getElementById("catalog");
    var list = document.getElementById("list");
    var isClicked = true;
    catalog.addEventListener('click', function () {
//        list.innerHTML = "he";
        if (isClicked) {
            list.innerHTML =
                    ` <ul>
        <li id="java" onclick='ajaxAsyncRequest("catalogs?todo=search&search=java")'>
<a href="#" class="subcatalog">Java(${qyt_book_java})</a></li>
        <li ><a href="<c:url value='/book?todo=search&&search=PHP' />" 
class="subcatalog">PHP(${qyt_book_PHP})</a></li>
        <li ><a href="<c:url value='/book?todo=search&&search=Javascripts' />" 
class="subcatalog">Javascripts(${qyt_book_Javascripts})</a></li>
    </ul> `;
//    
//            list.innerHTML =
//                    ` <ul>
//        <li id="java"><a href="<c:url value='/book?todo=search&&search=java' />" 
//class="subcatalog">Java(${qyt_book_java})</a></li>
//        <li ><a href="<c:url value='/book?todo=search&&search=PHP' />" 
//class="subcatalog">PHP(${qyt_book_PHP})</a></li>
//        <li ><a href="<c:url value='/book?todo=search&&search=Javascripts' />" 
//class="subcatalog">Javascripts(${qyt_book_Javascripts})</a></li>
//    </ul> `;

    
    
    
            isClicked = false;
        } else {
            list.innerHTML = "";
            isClicked = true;
        }

    });

</script>