<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<aside id="sidebarA">
    <nav>
        <ul>
            <li><a href="<c:url value='/' />">
                    Home</a></li>
            <!--<li id="catalog">-->
            <li >
                <a href="<c:url value='/catalog/catalog.jsp' />" >
                    Browse Catalog</a></li>
            <div id="list"></div>
            <li><a href="<c:url value='/email/email.jsp' />">
                    Join Email List</a></li>
            <li><a href="<c:url value='/customer_service' />">
                    Customer Service</a></li>
            <li id="catalog">click view</li>
        </ul>
    </nav>
</aside>

<script>
    var catalog = document.getElementById("catalog");
    var list = document.getElementById("list");

    catalog.addEventListener('click', function () {
//        list.innerHTML = "he";

        list.innerHTML =
                `<input type="radio" id="price1" name="price" value="10">
<label for="price1">< 10</label><br>
<input type="radio" id="price2" name="price" value="30">
<label for="price2">< 30</label><br>  
<input type="radio" id="price3" name="price" value="70">
<label for="price3">< 70</label><br>
<input type="radio" id="price4" name="price" value="200">
<label for="price4">< 200</label><br>`;

    });

</script>