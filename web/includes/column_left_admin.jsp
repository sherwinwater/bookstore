<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<aside id="sidebarA">
    <nav>
        <ul>
            <li><a href="<c:url value='/' />">Home</a></li>
            <li><a href="<c:url value='/admin' />">Admin Menu</a></li>
            <li><a href="<c:url value='/admin/users' />">Users</a></li>
            <li><a href="#" onclick="showPage('../admin/books.jsp')">Books</a></li>
            <li><a href="<c:url value='/admin/invoices' />">Invoices</a></li>
        </ul>
    </nav>
</aside>