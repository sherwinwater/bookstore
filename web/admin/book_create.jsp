<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/includes/header_admin.jsp" />

<section id="content_admin">
    <jsp:include page="/includes/nav_book_admin.jsp" />
    <div>
        <h2>Create  new book</h2>
        <form action="#" id="bookForm" name="bookForm" method="get" onsubmit="insertBook();return false;">
            <label class="pad_top">id</label>
            <input type="text" name="id" onkeyup="checkBookId()">
            <span id="msg_id_bookForm" style="color: red;font-style: italic"></span><br>
            <label class="pad_top">title</label>
            <input type="text" name="title" ><br>
            <label class="pad_top">author</label>
            <input type="text" name="author" ><br>
            <label class="pad_top">price</label>
            <input type="number" step="any" name="price" ><br>
            <label class="pad_top">inventory</label>
            <input type="number" name="inventory" ><br>
            <label class="pad_top">imgURL</label>
            <input type="text" name="imgURL" ><br>
            <label class="pad_top">location</label>
            <input type="text" name="location" ><br>
            <label class="pad_top">vendor</label>
            <input type="text" name="vendor" ><br>
            <label class="pad_top">owner</label>
            <input type="text" name="owner" ><br>
            <input type="submit" value="Insert">
        </form>
    </div>
    <br><br>
    quantity of books<input type="number" id="quantity_books_create"> 
    <input type="button" value="insertSamples" onclick="insertBookSamples()" >
    <p id="msg_booksample_create"></p>
</section>

<jsp:include page="/includes/footer_admin.jsp" />