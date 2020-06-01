

<h2>Insert new book</h2>
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

<h2>Update books</h2>
<table id="book_update_table"></table>

<!--    <thead>
    <th>id</th>
    <th>title</th>
    <th>author</th>
    <th>price</th>
    <th>inventory</th>
    <th>imgURL</th>
    <th>location</th>
    <th>vendor</th>
    <th>owner</th>
</thead>
<tbody>
    <tr>
        <td><input type="text" name="id" ></td>
        <td><input type="text" name="title" ></td>
        <td><input type="text" name="author" ></td>
        <td><input type="text" name="price" ></td>
        <td><input type="text" name="inventory" ></td>
        <td><input type="text" name="imgURL" ></td>
        <td><input type="text" name="location" ></td>
        <td><input type="text" name="vendor" ></td>
        <td><input type="text" name="owner" ></td>
    </tr>
</tbody>-->


