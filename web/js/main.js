var links = document.getElementsByClassName("link");
var email = document.getElementById("email");
var service = document.getElementById("service");
var searchTxt = document.getElementById("searchTxt");
var content = document.getElementById("content");
var contents = document.getElementsByClassName("content");
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
                document.getElementById("content").innerHTML = res;
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
    getTable(resJason.cart, "viewCart");
}

async function showSearchResults(page) {
    let response = await fetch(page);
    let resJason = await response.json();
    getTable(resJason.bookList, "addCart");
}

async function showSearchandCatalog(page) {
    let cart_id = "cart" + randomID();
    let page2 = page + "&cart_id=" + cart_id;
    let response = await fetch(page2);
    let resJason = await response.json();
    getTable(resJason.bookList, "addCart");
    showSubcatalog(resJason);
}

// --end show page 
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

searchTxt.addEventListener("keyup", () => {
    showSearchResults("ajaxsearch?search=" + searchTxt.value);
});
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
}

// add cart
async function getCart(i, action) {
    let book_ids = book_prices = book_quantitys = book_quantitys_update
            = book_titles = book_authors = page = "";
    switch (action) {
        case "add":
            book_ids = document.getElementsByClassName('book_id');
            book_prices = document.getElementsByClassName('book_price');
            book_quantitys = document.getElementsByClassName('book_quantity');
            book_titles = document.getElementsByClassName('book_title');
            book_authors = document.getElementsByClassName('book_author');
            book_inventorys = document.getElementsByClassName('book_inventory');
            page = "ajaxcart?todo=" + action
                    + "&book_id=" + book_ids[i].innerHTML
                    + "&book_price=" + book_prices[i].innerHTML
                    + "&book_quantity=" + book_quantitys[i].value
                    + "&book_title=" + book_titles[i].innerHTML
                    + "&book_author=" + book_authors[i].innerHTML
                    + "&book_inventory=" + book_inventorys[i].innerHTML;
            break;
        case "update":
        case "remove":
            book_ids = document.getElementsByClassName('book_id');
            book_prices = document.getElementsByClassName('book_price');
            book_quantitys = document.getElementsByClassName('book_quantity');
            book_titles = document.getElementsByClassName('book_title');
            book_authors = document.getElementsByClassName('book_author');
            page = "ajaxcart?todo=" + action
                    + "&book_id=" + book_ids[i].innerHTML
                    + "&book_price=" + book_prices[i].innerHTML
                    + "&book_quantity=" + book_quantitys[i].value
                    + "&book_title=" + book_titles[i].innerHTML
                    + "&book_author=" + book_authors[i].innerHTML;
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
            getTable(resJason.cart, "viewCart");
        }

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


function getTable(args, action) {

    switch (action) {
        case "addCart":
            if (args[0] == null) {   // cannot be === null
                content.innerHTML = "no results";
            } else {
                var thead = tbody = "";
                var i = 0;
                thead = ` <table> <thead><tr>
                        <th>ID</th>
                        <th>Author</th>
                        <th>Title</th>
                        <th>Price</th>
                        <th>Quantity</th>
                      <!--   <th>Inventory</th> 
                        <th>Action</th> --!>
                    </tr></thead> <tbody> `;
                for (var item in args) {
                    tbody += ` <tr>
                        <td class="book_id">${args[item].id}</td>
                        <td class="book_author">${args[item].author}</td>
                        <td class="book_title">${args[item].title}</td>
                        <td class="book_price">${args[item].price}</td>
                        <td> 
                            <!-- <input type="number" name="book_quantity" value="1" min="1" 
                                 class="book_quantity" onchange="checkQuantity(${i})" ><br>
                    <p style="font-size:90%">in stock <span class="book_inventory">${args[item].inventory}</span></p>
                    <p class="msg_quantity" style="color:red;"></p>
                            --!>
                    
                        <form action="# method="get" onsubmit="getCart(${i},'add');return false">
                        <input type="number" name="book_quantity" value="${args[item].quantity}" min="1" 
                                    step="1" max="${args[item].inventory}" class="book_quantity" style="display:inline-block">
                        <input type="submit" value="Add to Cart" class="book_add" style="display:inline-block">
                        </form>
                        <p style="font-size:90%">in stock <span class="book_inventory">${args[item].inventory}</span></p>
                    
                        </td>
                <!--        <td class="book_inventory">${args[item].inventory}</td> 
                        <td>
                        <input type="button" value="Add to Cart" class="book_add" onclick="getCart(${i},'add')">  --!>
                        </td>
                        </tr>`;
                    i++;
                }
                content.innerHTML = thead + tbody + `</tbody> </table>`;
            }
            break;

        case "viewCart":
            if (args[0] == null) {   // cannot be === null
                content.innerHTML = "no results";
            } else {
                var thead = tbody = "";
                var i = 0;
                thead = `<h1>Your cart</h1>`;
                thead += ` <table> <thead><tr>
                        <th>ID</th>
                        <th>Author</th>
                        <th>Title</th>
                        <th>Price</th>
                        <th>Quantity</th>
                         <th>Action</th> 
                    </tr></thead> <tbody> `;
                for (var item in args) {
                    tbody += ` <tr>
                        <td class="book_id">${args[item].id}</td>
                        <td class="book_author">${args[item].author}</td>
                        <td class="book_title">${args[item].title}</td>
                        <td class="book_price">${args[item].price}</td>
                        <td> 
                        
                            <!-- <input type="number" name="book_quantity" value="${args[item].quantity}" min="1" 
                                    step="1" max="10" class="book_quantity" style="display:inline-block" onchange="checkQuantity(${i})">
                            <input type="button" value="update" class="book_update" style="display:inline-block"
                            onclick="getCart(${i},'update')">
                        <p class="msg_quantity" style="color:red;"></p>
                        --!>
                        
                        <form action="# method="get" onsubmit="getCart(${i},'update');return false">
                            <input type="number" name="book_quantity" value="${args[item].quantity}" min="1" 
                                    step="1" max="${args[item].inventory}" class="book_quantity" style="display:inline-block">
                            <input type="submit" value="update" class="book_update" style="display:inline-block">
                        </form>
                        <p style="font-size:90%">in stock <span class="book_inventory">${args[item].inventory}</span></p>
                        
                        </td>
                        <td>
                        <input type="button" value="Remove item" class="book_remove" style="display:inline-block"
                            onclick="getCart(${i},'remove')" >
                        </td>
                        </tr>`;
                    i++;
                }
                content.innerHTML = thead + tbody + `</tbody> </table><br><br>`;
            }

            let shopping = `<input type="button" value="Continue Shopping" style="display:inline-block"
                                   onclick='showSearchandCatalog("ajaxsearch?search=")' >`;
            let checkout = `<input type="button" value="Checkout" style="display:inline-block"
                            onclick="checkoutCart()" >`;
            content.innerHTML += `<br>` + shopping + checkout;

            break;
    }
}

function showBookQuantity(data) {
    document.getElementById('java_quantity').innerHTML = "Java(" + data.java_quantity + ")";
    document.getElementById('PHP_quantity').innerHTML = "PHP(" + data.PHP_quantity + ")";
    document.getElementById('JavaScript_quantity').innerHTML = "JavaScript(" + data.JavaScript_quantity + ")";
}

function checkQuantity(i) {
//    let book_quantitys = document.getElementsByClassName('book_quantity');
//    let book_inventorys = document.getElementsByClassName('book_inventory');
//    let msg = document.getElementsByClassName("msg_quantity");
//    msg[i].innerHTML = "";
//    if (book_quantitys[i].value > book_inventorys[i].innerHTML) {
//        book_quantitys[i].value = book_inventorys[i].innerHTML;
//        msg[i].innerHTML = "max " + book_inventorys[i].innerHTML;
//    }
}