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
        if (current[0] != this) {
            current[0].className = current[0].className.replace("current", "");
//            this.className += " current";
            this.className = "link current";
        }
    });
}

// show and hide subcatalog
function showSubcatalog() {
    showBookQuantity();
    var e = document.getElementById("subcatalog");
    e.style.display = (e.style.display == 'block') ? 'none' : 'block';
}

searchTxt.addEventListener("keyup", () => {
    ajaxAsyncRequest("ajaxsearch?search=" + searchTxt.value);
});

var responseJason;
// retrieve data from the database
function ajaxAsyncRequest(reqURL, isShowSubcatalog) {
    //Creating a new XMLHttpRequest object
    var xmlhttp;
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest(); //for IE7+, Firefox, Chrome, Opera, Safari
    } else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP"); //for IE6, IE5
    }
    //Create a asynchronous GET request
    xmlhttp.open("GET", reqURL, true);

    //When readyState is 4 then get the server output
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4) {
            if (xmlhttp.status === 200) {
                responseJason = JSON.parse(xmlhttp.responseText);
//                alert(xmlhttp.responseText);
//                alert(responseJason);
                showSearchResults(responseJason.bookList);
                if (isShowSubcatalog == true) {
                    showSubcatalog();
                }
            } else {
                alert('Something is wrong !!');
            }
        }
    };
    xmlhttp.send(null);
}

function showSearchResults(args) {
    if (args[0] == null) {
        content.innerHTML = "no results";
    } else {
        var thead = tbody = "";
        thead = `<table><thead><tr>
            <th>ID</th>
            <th>Author</th>
            <th>Title</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Action</th>
        </tr></thead><tbody>`;
        for (var x in args) {
            tbody += `<tr>
            <td>${args[x].id}</td>
            <td>${args[x].author}</td>
            <td>${args[x].title}</td>
            <td>${args[x].price}</td>
            <td><input type="number" value="1" min="1"></td>
            <td><input type="button" value="add to cart"></td></tr>`;
        }
        content.innerHTML = thead + tbody + `</tbody></table>`;
    }

}

function showBookQuantity() {
    document.getElementById('java_quantity').innerHTML = "Java(" + responseJason.java_quantity + ")";
    document.getElementById('PHP_quantity').innerHTML = "PHP(" + responseJason.PHP_quantity + ")";
    document.getElementById('JavaScript_quantity').innerHTML = "JavaScript(" + responseJason.JavaScript_quantity + ")";
}

