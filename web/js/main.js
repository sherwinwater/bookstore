//import {checkUsername,checkPassword,loginUser,logoutUser} from "./user.js";
//import {randomID} from "./randomid.js";

var links = document.getElementsByClassName("link");
var email = document.getElementById("email");
var service = document.getElementById("service");
var searchTxt = document.getElementById("searchTxt");
var content_welcome = document.getElementById("content_welcome");
var content_admin = document.getElementById("content_admin");
var contents = document.getElementsByClassName("content");
var gridview = document.getElementById("content_gridview");
var listview = document.getElementById("content_listview");
var pagination_listview = document.getElementById("pagination_listview");
var nav_view = document.getElementById("nav_view");

// add current classname
for (var i = 0; i < links.length; i++) {
    links[i].addEventListener("click", function () {
        var current = document.getElementsByClassName("current");
        if (current[0] !== this) {
            current[0].className = current[0].className.replace("current", "");
//            this.className += " current";
            this.className = "link current";
        }
    });
}

// show and hide subcatalog
function showSubcatalog(data) {
    showBookQuantity(data);
    var e = document.getElementById("subcatalog");
    e.style.display = (e.style.display === 'block') ? 'none' : 'block';
}

// show navbar pages
// <objec> method
//function showPage(page) {
//    content.innerHTML = `<object type="text/html" data="${page}" width="300" height="500"></object>`;
//}

//function showPage(e) {
//    (e || window.event).preventDefault();
//
//    fetch('./account/login.html' /*, options */)
//            .then((response) => response.text())
//            .then((html) => {
//                document.getElementById("content").innerHTML = html;
//            })
//            .catch((error) => {
//                console.warn(error);
//            });
//}

function showPage(href) {

//xmlhttp method
//    var xmlhttp = new XMLHttpRequest();
//    xmlhttp.open("GET", href, false);
//    xmlhttp.send();
//    document.getElementById("content").innerHTML = xmlhttp.responseText;

// fetch method;
    fetch(href)
            .then(res => res.text()) // text()--xml, json()---json
            .then(res => {
                if (content_welcome != null) {
                    content_welcome.innerHTML = res;
                    listview.innerHTML = "";
                    gridview.innerHTML = "";
                    pagination_listview.innerHTML = "";
                    nav_view.innerHTML = "";
                }
                if (content_admin != null) {
                    content_admin.innerHTML = res;
                }
//                alert(res);
            })
}

function includePages(sourceHref, domID) {

//xmlhttp method
//    var xmlhttp = new XMLHttpRequest();
//    xmlhttp.open("GET", href, false);
//    xmlhttp.send();
//    document.getElementById("content").innerHTML = xmlhttp.responseText;

// fetch method;
    fetch(sourceHref)
            .then(res => res.text()) // text()--xml, json()---json
            .then(res => {
                let id = document.getElementById(domID);
                id.innerHTML = res;
//                alert(res);
            })
}




// checkout cart page

async function checkoutCart() {
    checkout('contactinfo');
}

// checkout contact information
async function checkContactinfo() {
    let page = "ajaxcheckout?todo=";
    let todo = document.contactinfoForm.todo.value;
    let FirstName = document.contactinfoForm.FirstName.value;
    let LastName = document.contactinfoForm.LastName.value;
    let Email = document.contactinfoForm.Email.value;
    let CompanyName = document.contactinfoForm.CompanyName.value;
    let Address1 = document.contactinfoForm.Address1.value;
    let Address2 = document.contactinfoForm.Address2.value;
    let City = document.contactinfoForm.City.value;
    let State = document.contactinfoForm.State.value;
    let Zip = document.contactinfoForm.Zip.value;
    let Country = document.contactinfoForm.Country.value;
    let contactID = "contact" + randomID();
    page = page + todo + "&FirstName=" + FirstName + "&LastName=" + LastName +
            "&Email=" + Email + "&CompanyName=" + CompanyName +
            "&Address1=" + Address1 + "&Address2=" + Address2 + "&City=" + City +
            "&State=" + State + "&Zip=" + Zip + "&Country=" + Country +
            "&contactID=" + contactID;
    let response = await fetch(page);
    let resJason = await response.json();

    // show invoice page
    checkout("invoice");
}

// checkout invoice page
async function checkoutInvoice() {
    checkout("creditinfo");
}
// checkout credit card page and place order
async function placeOrder() {
    let page = "ajaxcheckout?todo=";
    let todo = document.creditinfoForm.todo.value;
    let firstname = document.creditinfoForm.firstname.value;
    let lastname = document.creditinfoForm.lastname.value;
    let creditCardType = document.creditinfoForm.creditCardType.value;
    let creditCardNumber = document.creditinfoForm.creditCardNumber.value;
    let expirationMonth = document.creditinfoForm.expirationMonth.value;
    let expirationYear = document.creditinfoForm.expirationYear.value;
    let creditCardExpirationDate = expirationMonth + expirationYear;
    let orderID = "order" + randomID();
    let creditID = "credit" + randomID();
    let invoiceID = "invoice" + randomID();
    page = page + todo + "&firstname=" + firstname + "&lastname=" + lastname +
            "&creditCardType=" + creditCardType +
            "&creditCardNumber=" + creditCardNumber +
            "&creditCardExpirationDate=" + creditCardExpirationDate +
            "&orderID=" + orderID +
            "&creditID=" + creditID +
            "&invoiceID=" + invoiceID;
    let response = await fetch(page);
    let resJason = await response.json();

    // show invoice page
    checkout("order");
}

function checkout(action) {
    let page = "";
    switch (action) {
        case "contactinfo":
            page = './order/contactinfo.jsp';
            break;
        case "invoice":
            page = './order/invoice.jsp';
            break;
        case "creditinfo":
            page = './order/creditinfo.jsp';
            break;
        case "order":
            page = './order/order.jsp';
            break;
    }
    showPage(page);
    document.getElementById("sidebarB").style.display = "none";
}

function randomID() {
    let year = new Date().getFullYear().toString();
    let month = (new Date().getMonth() + 1).toString();
    let date = new Date().getDate().toString();
    let hour = new Date().getHours().toString();
    let min = new Date().getMinutes().toString();
    let second = new Date().getSeconds().toString();
    month = month.length == 1 ? "0".toString() + month : month;
    date = date.length == 1 ? "0".toString() + date : date;
    hour = hour.length == 1 ? "0".toString() + hour : hour;
    min = min.length == 1 ? "0".toString() + min : min;
    second = second.length == 1 ? "0".toString() + second : second;
//    if(month.length == 1){
//        month = "0".toString()+month;
//    }
//    if(date.length == 1){
//        date = "0".toString()+date;
//    }
//    if(hour.length == 1){
//        hour = "0".toString()+hour;
//    }
//    if(min.length == 1){
//        min = "0".toString()+min;
//    }
//    if(second.length == 1){
//        second = "0".toString()+second;
//    }

//    let randomnum = Math.floor(Math.random() * 100000).toString();
    return year + month + date + hour + min + second;
}

//call back method
//function foo(link, callback) {
//    httpRequest = new XMLHttpRequest();
//    httpRequest.onreadystatechange = function () {
//        if (httpRequest.readyState === 4) { // request is done
//            if (httpRequest.status === 200) { // successfully
//                callback(httpRequest.responseText); // we're calling our method
//            }
//        }
//    };
//    httpRequest.open('GET', link);
//    httpRequest.send();
//}
//
//function showMsg(a) {
//    content.innerHTML = a;
//}
//
//function show(link) {
//    foo(link, showMsg);
//}

async function viewCart() {
    let page = "ajaxcart?todo=view";
    let response = await fetch(page);
    let resJason = await response.json();
    showPageTable(resJason, "viewCart", 6, 0);

//    let sidebarB = document.getElementById("sidebarB");
//    sidebarB.innerHTML = `<table><tbody>
//        <tr>
//            <td>Subtotal</td>
//            <td>567</td>
//        </tr>
//        <tr>
//            <td>HST</td>
//            <td>13%</td>
//        </tr>
//        <tr>
//            <td>Delivery</td>
//            <td>23.0</td>
//        </tr>
//        <tr>
//            <td>Estimated Subtotal</td>
//            <td>${resJason.totalprice.toFixed(2)}</td>
//        </tr>
//       </tbody></table>`;
//    
    includePages('./cart/cart_totalprice_checkout.jsp', 'sidebarB');


}

async function showSearchResults(arg) {
    let pageNumber = 0;
    let pageSize = 6;
    let page = arg + "&pageNumber=" + pageNumber + "&pageSize=" + pageSize;
    let response = await fetch(page);
    let resJason = await response.json();
    showPageTable(resJason, "addCart", pageSize, pageNumber);
    showProductView('grid');
    includePages('./cart/cart_items_checkout.jsp', 'sidebarB');

}

async function showSearchandCatalog() {
    let pageNumber = 0;
    let pageSize = 6;
    let cart_id = "cart" + randomID();
//    let page2 = page + "&cart_id=" + cart_id;

    let page = "ajaxsearch?search=&cart_id=" + cart_id
            + "&pageNumber=" + pageNumber + "&pageSize=" + pageSize;
//    let response = await fetch(page,{mode:'cors'});
    let response = await fetch(page);
    let resJason = await response.json();
    showPageTable(resJason, "addCart", pageSize, pageNumber);
    showProductView('grid');
    showSubcatalog(resJason);

    includePages('./cart/cart_items_checkout.jsp', 'sidebarB');

}

// --end show page 
if (searchTxt != null) {
    searchTxt.addEventListener("keyup", () => {
        showSearchResults("ajaxsearch?search=" + searchTxt.value);
    });
}

async function checkUsername(page) {
    let response = await fetch(page + document.getElementById("signup_username").value);
    let resJason = await response.json();
    document.getElementById("message_signup_username").innerHTML = resJason.msg_username;
//    document.getElementById("message_signup_username").focus();
}

async function checkPassword(page) {
    let response = await fetch(page + document.getElementById("signup_password").value);
    let resJason = await response.json();
    document.getElementById("message_signup_password").innerHTML = resJason.msg_password;
//    if (resJason.msg_password != null) {
//        document.getElementById("message_signup_password").focus();
//    }
}


// sign up

async function loginUser(action) {
    let username = password = page = "";
    if (action === "signup") {
        username = document.signupForm.username.value;
        password = document.signupForm.password.value;
        page = "ajaxuser?todo=" + action + "&username=" + username + "&password=" + password;
    } else if (action === "login") {
        username = document.loginForm.username.value;
        password = document.loginForm.password.value;
        page = "ajaxuser?todo=" + action + "&username=" + username + "&password=" + password;
    }

// method1
//    let response = await fetch(page);
//    let resJason = await response.json();
//    console.log(resJason);
//    document.getElementById("message_signup").innerHTML = resJason.msg_username;

// POST method
    const settings = {
        method: 'POST',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json'
        }
    };
    try {
        const response = await fetch(page, settings);
        const resJason = await response.json();
        console.log(resJason);
        // do something
        if (resJason.username != null) {
            document.getElementById("login_signup_page").innerHTML = `
        <h1>Welcome ${resJason.username}!</h1>
            <p class='content'>Thanks for visiting. Make yourself at home. Feel free to browse through 
                our book catalog. When you do, you can read samples from on our site.
                We think our catalog contains some great books, and we 
                hope you like it as much as we do.</p>
            <p class='content'>If you find an book that you like, we hope that you will use this site 
                to order it. Most of the book we carry are not available anywhere else!</p>`;
        }
        if (resJason.msg_login != null) {
            document.getElementById("message_login").innerHTML = resJason.msg_login;
        }
        if (resJason.msg_username != null) {
            document.getElementById("message_signup_username").innerHTML = resJason.msg_username;
        }
        if (resJason.msg_password != null) {
            document.getElementById("message_signup_password").innerHTML = resJason.msg_password;
        }
    } catch (e) {
        console.log(e);
    }
}

// logout
async function logoutUser() {
    let page = "ajaxuser?todo=logout";
    let response = await fetch(page);
    let resJason = await response.json();
    showPage('./account/logout.jsp');

    includePages('./cart/cart_items_checkout.jsp', 'sidebarB');
    includePages('./cart/cart_totalprice_checkout.jsp', 'sidebarB');
    document.getElementById("cart_quantity").dataset.count = 0;
}

// add cart
async function getCart(i, action, view) {
    let book_ids = book_prices = book_quantitys = book_quantitys
            = book_titles = book_authors = page = "";
    switch (action + view) {
        case "addgrid":
            book_ids = document.getElementsByClassName('book_id');
            book_prices = document.getElementsByClassName('book_price');
            book_quantitys = document.getElementsByClassName('book_quantity');
            book_titles = document.getElementsByClassName('book_title');
            book_authors = document.getElementsByClassName('book_author');
            book_imgURLs = document.getElementsByClassName('book_imgURL');
            book_inventorys = document.getElementsByClassName('book_inventory');

            if (book_quantitys[i].value == "") {
                book_quantitys[i].value = 1;
            }

            page = "ajaxcart?todo=" + action
                    + "&book_id=" + book_ids[i].innerHTML
                    + "&book_price=" + book_prices[i].innerHTML
                    + "&book_quantity=" + book_quantitys[i].value
                    + "&book_title=" + book_titles[i].innerHTML
                    + "&book_author=" + book_authors[i].innerHTML
                    + "&book_imgURL=" + book_imgURLs[i].src
                    + "&book_inventory=" + book_inventorys[i].innerHTML;
            break;
        case "addlist":
            book_ids = document.getElementsByClassName('book_id_list');
            book_prices = document.getElementsByClassName('book_price_list');
            book_quantitys = document.getElementsByClassName('book_quantity_list');
            book_titles = document.getElementsByClassName('book_title_list');
            book_authors = document.getElementsByClassName('book_author_list');
            book_imgURLs = document.getElementsByClassName('book_imgURL_list');
            book_inventorys = document.getElementsByClassName('book_inventory_list');

            if (book_quantitys[i].value == "") {
                book_quantitys[i].value = 1;
            }

            page = "ajaxcart?todo=" + action
                    + "&book_id=" + book_ids[i].innerHTML
                    + "&book_price=" + book_prices[i].innerHTML
                    + "&book_quantity=" + book_quantitys[i].value
                    + "&book_title=" + book_titles[i].innerHTML
                    + "&book_author=" + book_authors[i].innerHTML
                    + "&book_imgURL=" + book_imgURLs[i].src
                    + "&book_inventory=" + book_inventorys[i].innerHTML;
            break;
        case "updatelist":
        case "removelist":
            book_ids = document.getElementsByClassName('book_id_list');
            book_quantitys = document.getElementsByClassName('book_quantity_list');
            book_prices = document.getElementsByClassName('book_price_list');
            book_totalprices = document.getElementsByClassName('book_totalprice_list');
            page = "ajaxcart?todo=" + action
                    + "&book_id=" + book_ids[i].innerHTML
                    + "&book_price=" + book_prices[i].innerHTML
                    + "&book_quantity=" + book_quantitys[i].value;
            book_totalprices[i].innerHTML = (book_prices[i].innerHTML * book_quantitys[i].value).toFixed(2);

            break;

    }

// method--GET
//    let response = await fetch(page);
//    let resJason = await response.json();
//    console.log(resJason);
//    document.getElementById("message_signup").innerHTML = resJason.msg_username;

// method--POST
    const settings = {
        method: 'POST',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json'
        }
    };
    try {
        const response = await fetch(page, settings);
        const resJason = await response.json();
        if (action == "remove") {
            showPageTable(resJason, "viewCart", 6, 0);
        }
        if (action == "add") {
            includePages('./cart/cart_items_checkout.jsp', 'sidebarB');
        }
        if (action == "remove" || action == "update") {
            includePages('./cart/cart_totalprice_checkout.jsp', 'sidebarB');
        }
        document.getElementById("cart_quantity").dataset.count = resJason.cartSize;

    } catch (e) {
        console.log(e);
    }
}


// xmlhttp method
//// retrieve data from the database
//function ajaxAsyncRequest(reqURL) {
//    //Creating a new XMLHttpRequest object
//    var xmlhttp;
//    if (window.XMLHttpRequest) {
//        xmlhttp = new XMLHttpRequest(); //for IE7+, Firefox, Chrome, Opera, Safari
//    } else {
//        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP"); //for IE6, IE5
//    }
//    //Create a asynchronous GET request
//    xmlhttp.open("GET", reqURL, true);
//    //When readyState is 4 then get the server output
//    xmlhttp.onreadystatechange = function () {
//        if (xmlhttp.readyState == 4) {
//            if (xmlhttp.status === 200) {
//                var responseJason = JSON.parse(xmlhttp.responseText);
//////                alert(xmlhttp.responseText);
////                showSearchResults(responseJason.bookList);
////                if (isShowSubcatalog == true) {
////                    showSubcatalog();
////                }
////                console.log(responseJason);
//
//            } else {
//                alert('Something is wrong !!');
//            }
//        }
//    };
//    xmlhttp.send(null);
//}

// method 1 : front end. Storing data retrieved from DB in html <span> element (hidden)
// parse data into JSON data and show it in the table.
//function getTable(data, action, pageSize, pageNumber) {
//    var gridview = document.getElementById("content_gridview");
//    var listview = document.getElementById("content_listview");
//    var content_welcome = document.getElementById("content_welcome");
//    var nav_view = document.getElementById("nav_view");
//    var pagination_listview = document.getElementById("pagination_listview");
//    var data_table_hidden = document.getElementById("data_table_hidden");
//    var dataStr = JSON.stringify(data);
//    data_table_hidden.innerHTML =
//            `<span id="data_table" hidden>${dataStr}</span>
//                    <span id="action_table" hidden>${action}</span>`;
//    content_welcome.innerHTML = "";
//
//    switch (action) {
//        case "addCart":
//            if (data[0] == null) {   // cannot be === null
//                listview.innerHTML = "no results";
//                gridview.innerHTML = "no results";
//            } else {
//                nav_view.innerHTML =
//                        `<img src="./images/gridview.png" onclick="showProductView('grid')" style="width:20px; display:inline-block">
//                    <img src="./images/listview.png" onclick="showProductView('list')" style="width:20px; display:inline-block">`;
//
//                var thead = tbody = gridbody = "";
//                var i = 0;
//                thead = ` <table> <thead><tr>
//                        <th>ID</th>
//                        <th>Title</th>
//                        <th>Author</th>
//                        <th>Price</th>
//                        <th>Quantity</th>
//                    </tr></thead> <tbody> `;
//
//                // grid view 
//                for (var item = (pageNumber - 1) * pageSize; item < (pageNumber) * pageSize; item++) {
//                    if (data[item] != null) {
//                        gridbody += `<div class="griditem">
//                            <img src="${data[item].imgURL}" class="book_imgURL" alt="product photo" width="148" height="148">
//                           <span class="book_title">${data[item].title}</span><br>
//                           <span class="book_author">by ${data[item].author}</span><br>
//                           <div style='color:green;font-size:1.5em; font-weight:bold;margin-left:0.2em;'>
//                            <span>$</span><span class="book_price" >${data[item].price}</span>
//                           </div>
//                           <span class="book_id" hidden>${data[item].id}</span>
//                         <form action="# method="get" onsubmit="getCart(${i},'add','grid');return false">
//
//                            <input type="number" name="book_quantity" value="${data[item].quantity}" min="1" 
//                                        step="1" max="${data[item].inventory}" class="book_quantity" style="width:4em">
//                            <input type="submit" value="Add to Cart" class="book_add" >
//                            <p style="font-size:90%;margin-left:0.5em">in stock <span class="book_inventory">${data[item].inventory}</span></p>
//                            </form>
//                            </div>`;
//
//                    } else {
//                        break;
//                    }
//                    i++;
//                }
//                showPagination(data, pageSize, pageNumber);
//
//                // list view
//                i = 0;
//                for (var item = (pageNumber - 1) * pageSize; item < (pageNumber) * pageSize; item++) {
//                    if (data[item] != null) {
//                        tbody += ` <tr>
//                        <td class="book_id_list">${data[item].id}</td>
//                        <td >
//                             <img src="${data[item].imgURL}" class="book_imgURL_list"  alt="product photo" width="48" height="48">
//                        <span class="book_title_list">${data[item].title}</span>
//                        </td>
//                        <td class="book_author_list">${data[item].author}</td>
//                        <td class="book_price_list">${data[item].price}</td>
//                        <td> 
//                            <form action="# method="get" onsubmit="getCart(${i},'add','list');return false">
//                            <input type="number" name="book_quantity" value="${data[item].quantity}" min="1" 
//                                        step="1" max="${data[item].inventory}" class="book_quantity_list" ><br>
//                            <input type="submit" value="Add to Cart" class="book_add" >
//                            </form>
//                            <p style="font-size:90%">in stock <span class="book_inventory_list">${data[item].inventory}</span></p>
//                        </td>
//                     </tr>`;
//                    } else {
//                        break;
//                    }
//                    i++;
//                }
//                // listview
//                showPagination(data, pageSize, pageNumber);
//                listview.innerHTML = thead + tbody + `</tbody> </table>`;
//                gridview.innerHTML = gridbody;
//            }
//            break;
//
//        case "viewCart":
//            nav_view.innerHTML = "";
//            gridview.innerHTML = "";
//            pagination_listview.innerHTML = "";
//            showProductView("list");
//            if (data[0] == null) {   // cannot be === null
//                listview.innerHTML = "no results";
//            } else {
//                var thead = tbody = "";
//                var i = 0;
//                var totalprice = 0;
//                thead = `<h1>Your cart</h1>`;
//                thead += ` <table> <thead><tr>
//                        <th>ID</th>
//                        <th>Title</th>
//                        <th>Author</th>
//                        <th>Price</th>
//                        <th>Quantity</th>
//                        <th>Totalprice</th>
//                         <th>Action</th> 
//                    </tr></thead> <tbody> `;
//                for (var item = (pageNumber - 1) * pageSize; item < (pageNumber) * pageSize; item++) {
//                    if (data[item] != null) {
//                        tbody += ` <tr>
//                        <td class="book_id_list">${data[item].id}</td>
//                        <td >
//                             <img src="${data[item].imgURL}" class="book_imgURL_list" alt="product photo" width="48" height="48">
//                        <span class="book_title_list">${data[item].title}</span>
//                        </td>
//                        <td class="book_author_list">${data[item].author}</td>
//                        <td class="book_price_list">${data[item].price}</td>
//                        <td> 
//                            <form action="# method="get" onsubmit="getCart(${i},'update','list');return false">
//                                <input type="number" name="book_quantity" value="${data[item].quantity}" min="1" 
//                                        step="1" max="${data[item].inventory}" class="book_quantity_list" 
//                                        onchange="getCart(${i},'update','list')"><br>
//                                <input type="submit" value="update" class="book_update">
//                            </form>
//                            <p style="font-size:90%">in stock <span class="book_inventory_list">${data[item].inventory}</span></p>
//                        </td>
//                         <td class="book_totalprice_list" style="text-align:right; padding-right:2.5em;">${data[item].totalprice.toFixed(2)}</td>
//                        <td>
//                        <input type="button" value="Remove" class="book_remove" style="display:inline-block"
//                            onclick="getCart(${i},'remove','list')" >
//                        </td>
//                      </tr>`;
//                        totalprice += data[item].totalprice;
//                    } else {
//                        break;
//                    }
//                    i++;
//                }
//                tbody += `<tr><td colspan= "5" id="totalprice_label">total price</td>
//                        <td id="totalprice_content" >${totalprice.toFixed(2)}</td>
//                        <td></td></tr>`;
//                showPagination(data, pageSize, pageNumber);
//
//                let shopping = `<input type="button" value="Continue Shopping" style="display:inline-block"
//                                   onclick='showSearchandCatalog("ajaxsearch?search=")' ><span>&nbsp</span>`;
//                let checkout = `<input type="button" value="Checkout" style="display:inline-block"
//                            onclick="checkoutCart()" >`;
//                listview.innerHTML = thead + tbody + `<br>` + shopping + checkout;
//            }
//            break;
//    }
//}


// method 2 : back end.  Retrieve data directly from DB when click the page number in the meanwhile 
// show all the page numbers in the page.


// search itemes and retrieve data from DB

function getPaginationBar(data, pageSize, pageNumber, view) {

}

async function getPageItems(pageSize, pageNumber, view) {

    // retrieve data from DB

    // show data in the number of page -function

}

function showPageTable(data, action, pageSize, pageNumber) {
    var bookList = data.bookList;
    var cart = data.cart;
    var cartSize = data.cartSize;

    content_welcome.innerHTML = "";

    switch (action) {
        case "addCart":
            if (bookList[0] == null) {   // cannot be === null
                listview.innerHTML = "no results";
                gridview.innerHTML = "no results";
            } else {
                nav_view.innerHTML =
                        `<img src="./images/gridview.png" onclick="showProductView('grid')" style="width:20px; display:inline-block">
                    <img src="./images/listview.png" onclick="showProductView('list')" style="width:20px; display:inline-block">`;

                var thead = tbody = gridbody = "";
                var i = 0;
                thead = ` <table> <thead><tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Author</th>
                        <th>Price</th>
                        <th>Quantity</th>
                    </tr></thead> <tbody> `;

                // grid view 
//                for (var item = (pageNumber - 1) * pageSize; item < (pageNumber) * pageSize; item++) {
                for (var item in bookList) {
                    if (bookList[item] != null) {
                        gridbody += `<div class="griditem">
                            <img src="${bookList[item].imgURL}" class="book_imgURL" alt="product photo" width="148" height="148">
                           <span class="book_title">${bookList[item].title}</span><br>
                           <span class="book_author">by ${bookList[item].author}</span><br>
                           <div style='color:green;font-size:1.5em; font-weight:bold;margin-left:0.2em;'>
                            <span>$</span><span class="book_price" >${bookList[item].price}</span>
                            </div>
                           <span class="book_id" hidden>${bookList[item].id}</span>
                         <form action="# method="get" onsubmit="getCart(${i},'add','grid');return false">

                            <input type="number" name="book_quantity" value="${bookList[item].quantity}" min="0" 
                                        step="1" max="${bookList[item].inventory}" class="book_quantity" 
                                onchange="getCart(${i},'add','grid')" style="width:4em">
                            <input type="submit" value="Add to Cart" class="book_add" >
                            <p style="font-size:90%;margin-left:0.5em">in stock <span class="book_inventory">${bookList[item].inventory}</span></p>
                            </form>
                            </div>`;

                    } else {
                        break;
                    }
                    i++;
                }
                // list view
                i = 0;
//                for (var item = (pageNumber - 1) * pageSize; item < (pageNumber) * pageSize; item++) {
                for (var item in bookList) {
                    if (bookList[item] != null) {
                        tbody += ` <tr>
                        <td class="book_id_list">${bookList[item].id}</td>
                        <td >
                             <img src="${bookList[item].imgURL}" class="book_imgURL_list"  alt="product photo" width="48" height="48">
                        <span class="book_title_list">${bookList[item].title}</span>
                        </td>
                        <td class="book_author_list">${bookList[item].author}</td>
                        <td class="book_price_list">${bookList[item].price}</td>
                        <td> 
                            <form action="# method="get" onsubmit="getCart(${i},'add','list');return false">
                            <input type="number" name="book_quantity" value="${bookList[item].quantity}" min="0" 
                                        step="1" max="${bookList[item].inventory}" class="book_quantity_list" 
                                        onchange="getCart(${i},'add','list')"><br>
                            <input type="submit" value="Add to Cart" class="book_add" >
                            </form>
                            <p style="font-size:90%">in stock <span class="book_inventory_list">${bookList[item].inventory}</span></p>
                        </td>
                     </tr>`;
                    } else {
                        break;
                    }
                    i++;
                }
                // listview
                listview.innerHTML = thead + tbody + `</tbody> </table>`;
                gridview.innerHTML = gridbody;
                showPagination(data.book_quantity, pageSize, pageNumber);
            }
            break;

        case "viewCart":
            nav_view.innerHTML = "";
            gridview.innerHTML = "";
            document.getElementById('pagination_listview').style.display = "none";
            showProductView("list");
            if (cart[0] == null) {   // cannot be === null
                listview.innerHTML = "no results<br><br>";
                listview.innerHTML +=
                        `<input type="button" value="Continue Shopping" style="display:inline-block"
                 onclick='showSearchandCatalog("ajaxsearch?search=")' ><span>&nbsp</span>`;

            } else {
                var thead = tbody = "";
                var i = 0;
                var totalprice = 0;
                thead = `<h1>Your cart</h1>`;
                thead += ` <table> <thead><tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Author</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Totalprice</th>
                         <th>Action</th> 
                    </tr></thead> <tbody> `;
//                for (var item = 0; item < cartSize; item++) {
                for (var item in cart) {
                    if (cart[item] != null) {
                        tbody += ` <tr>
                        <td class="book_id_list">${cart[item].id}</td>
                        <td >
                             <img src="${cart[item].imgURL}" class="book_imgURL_list" alt="product photo" width="48" height="48">
                        <span class="book_title_list">${cart[item].title}</span>
                        </td>
                        <td class="book_author_list">${cart[item].author}</td>
                        <td class="book_price_list">${cart[item].price}</td>
                        <td> 
                            <form action="# method="get" onsubmit="getCart(${i},'update','list');return false">
                                <input type="number" name="book_quantity" value="${cart[item].quantity}" min="1" 
                                        step="1" max="${cart[item].inventory}" class="book_quantity_list" 
                                        onchange="getCart(${i},'update','list')"><br>
                                <input type="submit" value="update" class="book_update">
                            </form>
                            <p style="font-size:90%">in stock <span class="book_inventory_list">${cart[item].inventory}</span></p>
                        </td>
                         <td class="book_totalprice_list" style="text-align:right; padding-right:2.5em;">${cart[item].totalprice.toFixed(2)}</td>
                        <td>
                        <input type="button" value="Remove" class="book_remove" style="display:inline-block"
                            onclick="getCart(${i},'remove','list')" >
                        </td>
                      </tr>`;
//                        totalprice += cart[item].totalprice;
                    } else {
                        break;
                    }
                    i++;
                }
//                tbody += `<tr><td colspan= "5" id="totalprice_label">total price</td>
//                        <td id="totalprice_content_welcome" >${totalprice.toFixed(2)}</td>
//                        <td></td></tr>`;

                let shopping = `<input type="button" value="Continue Shopping" style="display:inline-block"
                                   onclick='showSearchandCatalog("ajaxsearch?search=")' ><span>&nbsp</span>`;
                let checkout = `<input type="button" value="Checkout" style="display:inline-block"
                            onclick="checkoutCart()" >`;
                listview.innerHTML = thead + tbody + `<br>` + shopping + checkout;
            }
            break;
    }
}

function showPagination(itemSize, pageSize, pageNumber) {
    var pagination_listview = document.getElementById("pagination_listview");
    let pageNumbers = Math.ceil(itemSize / pageSize);
    let page = "";
    for (var pageindex = 0; pageindex < pageNumbers; pageindex++) {
        page += `<a href="#" onclick="showPageItems(${pageSize},${pageindex})" id="page${pageindex}">${pageindex + 1}</a>`;
    }

    var pageLast = pageNumber <= 0 ? 0 : pageNumber - 1;
    var pageNext = pageNumber >= pageNumbers - 1 ? pageNumbers - 1 : pageNumber + 1;

    pagination_listview.innerHTML = `
                    <a href="#" onclick="showPageItems(${pageSize},${pageLast})">&laquo;</a>` + page +
            `<a href="#" onclick="showPageItems(${pageSize},${pageNext})">&raquo;</a>`;
    if (pageNumber == 0) {
        document.getElementById("page" + pageNumber).className = "";
        document.getElementById("page" + pageNumber).className = "active";
    }
    document.getElementById('pagination_listview').style.display = "inline-block";
}

async function showPageItems(pageSize, pageNumber) {
    let page = "ajaxsearch?search="
            + "&pageNumber=" + pageNumber + "&pageSize=" + pageSize;
    let response = await fetch(page);
    let resJason = await response.json();
    showPageTable(resJason, "addCart", pageSize, pageNumber);

    document.getElementById("page" + pageNumber).className = "";
    document.getElementById("page" + pageNumber).className = "active";
}

function showProductView(option) {
    switch (option) {
        case "grid":
            document.getElementById('content_gridview').style.display = "flex";
            document.getElementById('content_listview').style.display = "none";
            break;
        case "list":
            document.getElementById('content_gridview').style.display = "none";
            document.getElementById('content_listview').style.display = "block";
            break;
    }
}

function showBookQuantity(data) {
    document.getElementById('catalog').innerHTML = "Browse Catalog(" + data.book_quantity + ")";
    document.getElementById('java_quantity').innerHTML = "Java(" + data.java_quantity + ")";
    document.getElementById('PHP_quantity').innerHTML = "PHP(" + data.PHP_quantity + ")";
    document.getElementById('JavaScript_quantity').innerHTML = "JavaScript(" + data.JavaScript_quantity + ")";
}

//admin part
async function insertBook() {

    //get data
    let id = document.bookForm.id.value;
    let title = document.bookForm.title.value;
    let author = document.bookForm.author.value;
    let price = document.bookForm.price.value;
    let inventory = document.bookForm.inventory.value;
    let imgURL = document.bookForm.imgURL.value;
    let location = document.bookForm.location.value;
    let vendor = document.bookForm.vendor.value;
    let owner = document.bookForm.owner.value;

    // get 
    let page = "ajaxbook?todo=insert" + "&id=" + id + "&title=" + title
            + "&author=" + author + "&price=" + price
            + "&inventory=" + inventory + "&imgURL=" + imgURL
            + "&location=" + location + "&vendor=" + vendor
            + "&owner=" + owner;
    let response = await fetch(page);
    let resJason = await response.json();

    let msg_id_bookForm = document.getElementById('msg_id_bookForm');
    msg_id_bookForm.innerHTML = resJason.msg_data;
}

async function insertBookSamples() {
    let booksize = document.getElementById("quantity_books_create").value;
    let page = "ajaxbook?todo=insertsamples&size=" + booksize;
    let response = await fetch(page);
    let resJason = await response.json();
    let msg_booksample_create = document.getElementById('msg_booksample_create');
    msg_booksample_create.innerHTML = "created " + resJason.size + " books";
}

async function checkBookId() {
    let id = document.bookForm.id.value;
    let page = "ajaxbook?todo=check" + "&id=" + id;
    let response = await fetch(page);
    let resJason = await response.json();

    let msg_id_bookForm = document.getElementById('msg_id_bookForm');
    msg_id_bookForm.innerHTML = resJason.msg_data;
}

function getBookTable(data) {
    let book_update_table = document.getElementById("book_update_table");

    if (data[0] == null) {   // cannot be === null
        book_update_table.innerHTML = "no results";
    } else {
        var thead = tbody = "";
        var i = 0;
        thead = ` <thead><tr>
                        <th >ID</th>
                        <th >Author</th>
                        <th >Title</th>
                        <th >Price</th>
                        <th >Inventory</th> 
                        <th >ImgURL</th> 
                        <th >Location</th> 
                        <th >Vendor</th> 
                        <th >Owner</th> 
                        <th>Action</th>
                    </tr></thead> <tbody> `;
        for (var item in data) {
            tbody += ` <tr>
                        <td ><input type="text" value="${data[item].id}" class="id" style="width:4em"></td>
                        <td ><input type="text" value="${data[item].author}" class="author" style="width:6em"></td>
                        <td ><span ><input type="text" value="${data[item].title}" class="title" style="width:9em"></span>
                        <td ><input type="number" step="any" value="${data[item].price}" class="price" style="width:4em"></td>
                        <td ><input type="number" step="1" value="${data[item].inventory}" class="inventory" style="width:4em"></td>
                        <td ><input type="text" value="${data[item].imgURL}" class="imgURL" style="width:10em">
                                <img src="${data[item].imgURL}" 
                                             alt="product photo" width="148">
                        </td>
                        <td ><input type="text" value="${data[item].location}" class="location" style="width:4em"></td>
                        <td ><input type="text" value="${data[item].vendor}" class="vendor" style="width:4em"></td>
                        <td ><input type="text" value="${data[item].owner}" class="owner" style="width:4em"></td>
                        <td ><input type="button" value="update" class="update" onclick="updateBook(${i},'update')"></td>
                      </tr>`;
            i++;
        }
        book_update_table.innerHTML = thead + tbody + `</tbody>`;
    }
}

async function showBooks() {
    let page = "ajaxbook?todo=search&search=";
    let response = await fetch(page);
    let resJason = await response.json();
    getBookTable(resJason.bookList);
}

// update DB book table
async function updateBook(i, action) {
    let ids = prices = quantitys
            = titles = authors = locations = vendors = owners
            = imgURLs = page = "";
    switch (action) {
        case "add":
            ids = document.getElementsByClassName('id');
            prices = document.getElementsByClassName('price');
            quantitys = document.getElementsByClassName('quantity');
            titles = document.getElementsByClassName('title');
            authors = document.getElementsByClassName('author');
            inventorys = document.getElementsByClassName('inventory');
            page = "ajaxcart?todo=" + action
                    + "&id=" + ids[i].value
                    + "&price=" + prices[i].value
                    + "&quantity=" + quantitys[i].value
                    + "&title=" + titles[i].value
                    + "&author=" + authors[i].value
                    + "&inventory=" + inventorys[i].value;
            break;
        case "update":
        case "remove":
            ids = document.getElementsByClassName('id');
            prices = document.getElementsByClassName('price');
            inventorys = document.getElementsByClassName('inventory');
            titles = document.getElementsByClassName('title');
            authors = document.getElementsByClassName('author');
            imgURLs = document.getElementsByClassName('imgURL');
            locations = document.getElementsByClassName('location');
            vendors = document.getElementsByClassName('vendor');
            owners = document.getElementsByClassName('owner');
            page = "ajaxbook?todo=" + action
                    + "&id=" + ids[i].value
                    + "&price=" + prices[i].value
                    + "&inventory=" + inventorys[i].value
                    + "&title=" + titles[i].value
                    + "&author=" + authors[i].value
                    + "&imgURL=" + imgURLs[i].value
                    + "&location=" + locations[i].value
                    + "&vendor=" + vendors[i].value
                    + "&owner=" + owners[i].value;
            break;
    }

// method--GET
    let response = await fetch(page);
    let resJason = await response.json();
//    console.log(resJason);
//    document.getElementById("message_signup").innerHTML = resJason.msg_username;

// method--POST
//    const settings = {
//        method: 'POST',
//        headers: {
//            Accept: 'application/json',
//            'Content-Type': 'application/json'
//        }
//    };
//    try {
//        const response = await fetch(page, settings);
//        const resJason = await response.json();
//        if (action == "remove") {
//            getTable(resJason.cart, "viewCart");
//        }
//
//    } catch (e) {
//        console.log(e);
//    }
}


// admin part 

// sort by feature

async function sortResults(content, option) {
    if (content == "price") {
        if (option == "ASC") {
            document.getElementById('price_btn').style.display = "none";
            document.getElementById('price_ASC').style.display = "none";
            document.getElementById('price_DESC').style.display = "inline-block";
        }
        if (option == "DESC") {
            document.getElementById('price_DESC').style.display = "none";
            document.getElementById('price_ASC').style.display = "inline-block";
        }
    }
    let page = "ajaxbook?todo=sort&search=&sortContent=" + content + "&option=" + option;
    let response = await fetch(page);
    let resJason = await response.json();
    getBookTable(resJason.bookList);
}


// end admin part

