<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<aside id="sidebarA">
    <nav>
      <ul>
          <li><a class="current" href="<c:url value='/' />">
                  Home</a></li>
          <li><a href="<c:url value='/book?todo=search&&search=' />">
                  Browse Catalog</a></li>
<!--          <li><a href="<c:url value='/catalog/search.jsp' />">
                  Browse Catalog</a></li>-->
          <li><a href="<c:url value='/email/email.jsp' />">
                  Join Email List</a></li>
          <li><a href="<c:url value='/customer_service' />">
                  Customer Service</a></li>
      </ul>
    </nav>
</aside>