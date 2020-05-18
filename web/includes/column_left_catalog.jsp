<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<aside id="sidebarA">
    <nav>
        <ul>
            <li><a class="link current" href="<c:url value='/' />" >
                    Home</a></li>
            <li onclick='ajaxAsyncRequest("ajaxsearch?search=")'>
                <a class="link" href="#" id="catalog" onclick="showSubcatalog()">
                    Browse Catalog</a></li>
            <ul id="subcatalog" style="display:none">
            </ul> 
            <li><a class="link" href="#" id="email">
                    Join Email List</a></li>
            <li><a class="link" href="#" id="service">
                    Customer Service</a></li>
        </ul>
    </nav>
</aside>