<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_catalog.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- start the middle column -->
<section id="content">
    <h1 class='content'>Welcome Readers!</h1>
    <p class='content'>Thanks for visiting. Make yourself at home. Feel free to browse through 
        our book catalog. When you do, you can read samples from on our site.
        We think our catalog contains some great books, and we 
        hope you like it as much as we do.</p>
    <p class='content'>If you find an book that you like, we hope that you will use this site 
        to order it. Most of the book we carry are not available anywhere else!</p>
    <div id="subnavbar">
    </div>

</section>
<!--<script src="<c:url value='/js/main.js' />"></script>-->


<!--<script>
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
            current[0].className = current[0].className.replace("current", "");
            this.className += " current";
        });
    }

    // show and hide subcatalog
    function showSubcatalog() {
        var e = document.getElementById("subcatalog");
        e.style.display = (e.style.display == 'block') ? 'none' : 'block';
    }

//    function show(navbar) {
//        contents[0].innerHTML = "";
//        contents[1].innerHTML = "";
//        contents[2].innerHTML = "";
//        var e = document.getElementById('subnavbar');
//        e.innerHTML = '';
//        e.style.display ='block';
//    }

    searchTxt.addEventListener("keyup", () => {
        ajaxAsyncRequest("ajaxsearch?search=" + searchTxt.value);
    });

    var response;
    var responseJason;
    // retrieve data from the database
    function ajaxAsyncRequest(reqURL) {
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
                    response = xmlhttp.responseText;
                    responseJason = JSON.parse(response);
//                    content.innerHTML = responseJason.bookList[0].price;
                    showSearchResults(responseJason.bookList);
                    showSc();
//                    alert(xmlhttp.responseText);
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
            content.innerHTML ="<table><tr>ss</table>";
//            content.innerHTML = `<table style="border: 1px solid black;
//    border-collapse: collapse;">
//        <tr>
//            <th>ID</th>
//            <th>Author</th>
//            <th>Title</th>
//            <th>Price</th>
//            <th>Quantity</th>
//            <th>Action</th>
//        </tr>`;
//            for (var x in args) {
//                content.innerHTML += `<tr>
//            <td>\${args[x].id}</td>
//            <td>\${args[x].author}</td>
//            <td>\${args[x].title}</td>
//            <td>\${args[x].price}</td>
//            <td>111</td>
//            <td>add to cart</td>
//        </tr><br>`;
//            }
//            content.innerHTML += `</table>`;

//                content.innerHTML += args[x].id + " " + args[x].title +
//                        args[x].author + " " + args[x].price + " " + "<br>";
        }

    }

    function showSc() {
        document.getElementById("subcatalog").innerHTML = `
                <li onclick='ajaxAsyncRequest("ajaxsearch?search=java")'>
                    <a href="#" class="subcatalog" >Java(\${responseJason.qyt_book_java})</a></li>
                <li onclick='ajaxAsyncRequest("ajaxsearch?search=PHP")'>
                    <a href="#" class="subcatalog" >PHP(\${responseJason.qyt_book_PHP})</a></li>
                <li onclick='ajaxAsyncRequest("ajaxsearch?search=JavaScript")'>
                    <a href="#" class="subcatalog" >JavaScript(\${responseJason.qyt_book_JavaScript})</a></li>
`;
    }


</script>-->

<!-- end the middle column -->
<jsp:include page="/includes/column_right_news.jsp" />
<jsp:include page="/includes/footer.jsp" />