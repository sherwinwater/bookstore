<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/includes/header_admin.jsp" />

<section id="content_admin">
    <jsp:include page="/includes/nav_book_admin.jsp" />

    <div>
        <h2>Update books</h2>
        <input type="button" value="Show Books" onclick="showBooks()" > <span>&nbsp;&nbsp;&nbsp;</span>
        <div class="sort_nav" style="display:inline-block">
            <span>
                <a href="#" id="price_btn" class="sort" style="display: inline-block" onclick="sortResults('price', 'ASC')" >Price</a>
                <a href="#" class="sort" id="price_ASC" style="display: none" onclick="sortResults('price', 'ASC')" >
                    Price <i class="fas fa-chevron-down"></i></span></a>
            <a href="#" class="sort" id="price_DESC" style="display: none" onclick="sortResults('price', 'DESC')" >
                Price <i class="fas fa-chevron-up"></i></a>
            </span>
            <span>
                <a href="#" id="price_btn" class="sort" onclick="sortResults('price', 'ASC')" >Price</a>
                <a href="#" class="sort" id="price_ASC" style="display: none" onclick="sortResults('price', 'ASC')" >
                    Price <i class="fas fa-chevron-down"></i></span></a>
            <a href="#" class="sort" id="price_DESC" style="display: none" onclick="sortResults('price', 'DESC')" >
                Price <i class="fas fa-chevron-up"></i></a>
            </span>
        </div>
        <div><table id="book_update_table"></table></div>
    </div>

</section>

<jsp:include page="/includes/footer_admin.jsp" />