package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/includes/header.jsp", out, false);
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/includes/column_left_catalog.jsp", out, false);
      out.write("\n");
      out.write("\n");
      out.write("<!-- start the middle column -->\n");
      out.write("<section id=\"content\">\n");
      out.write("    <h1 class='content'>Welcome Readers!</h1>\n");
      out.write("    <p class='content'>Thanks for visiting. Make yourself at home. Feel free to browse through \n");
      out.write("        our book catalog. When you do, you can read samples from on our site.\n");
      out.write("        We think our catalog contains some great books, and we \n");
      out.write("        hope you like it as much as we do.</p>\n");
      out.write("    <p class='content'>If you find an book that you like, we hope that you will use this site \n");
      out.write("        to order it. Most of the book we carry are not available anywhere else!</p>\n");
      out.write("    <div id=\"subnavbar\">\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("</section>\n");
      out.write("\n");
      out.write("<!--<script>\n");
      out.write("    var links = document.getElementsByClassName(\"link\");\n");
      out.write("    var email = document.getElementById(\"email\");\n");
      out.write("    var service = document.getElementById(\"service\");\n");
      out.write("    var searchTxt = document.getElementById(\"searchTxt\");\n");
      out.write("    var content = document.getElementById(\"content\");\n");
      out.write("    var contents = document.getElementsByClassName(\"content\");\n");
      out.write("\n");
      out.write("    // add current classname\n");
      out.write("    for (var i = 0; i < links.length; i++) {\n");
      out.write("        links[i].addEventListener(\"click\", function () {\n");
      out.write("            var current = document.getElementsByClassName(\"current\");\n");
      out.write("            current[0].className = current[0].className.replace(\"current\", \"\");\n");
      out.write("            this.className += \" current\";\n");
      out.write("        });\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    // show and hide subcatalog\n");
      out.write("    function showSubcatalog() {\n");
      out.write("        var e = document.getElementById(\"subcatalog\");\n");
      out.write("        e.style.display = (e.style.display == 'block') ? 'none' : 'block';\n");
      out.write("    }\n");
      out.write("\n");
      out.write("//    function show(navbar) {\n");
      out.write("//        contents[0].innerHTML = \"\";\n");
      out.write("//        contents[1].innerHTML = \"\";\n");
      out.write("//        contents[2].innerHTML = \"\";\n");
      out.write("//        var e = document.getElementById('subnavbar');\n");
      out.write("//        e.innerHTML = '';\n");
      out.write("//        e.style.display ='block';\n");
      out.write("//    }\n");
      out.write("\n");
      out.write("    searchTxt.addEventListener(\"keyup\", () => {\n");
      out.write("        ajaxAsyncRequest(\"ajaxsearch?search=\" + searchTxt.value);\n");
      out.write("    });\n");
      out.write("\n");
      out.write("    var response;\n");
      out.write("    var responseJason;\n");
      out.write("    // retrieve data from the database\n");
      out.write("    function ajaxAsyncRequest(reqURL) {\n");
      out.write("        //Creating a new XMLHttpRequest object\n");
      out.write("        var xmlhttp;\n");
      out.write("        if (window.XMLHttpRequest) {\n");
      out.write("            xmlhttp = new XMLHttpRequest(); //for IE7+, Firefox, Chrome, Opera, Safari\n");
      out.write("        } else {\n");
      out.write("            xmlhttp = new ActiveXObject(\"Microsoft.XMLHTTP\"); //for IE6, IE5\n");
      out.write("        }\n");
      out.write("        //Create a asynchronous GET request\n");
      out.write("        xmlhttp.open(\"GET\", reqURL, true);\n");
      out.write("\n");
      out.write("        //When readyState is 4 then get the server output\n");
      out.write("        xmlhttp.onreadystatechange = function () {\n");
      out.write("            if (xmlhttp.readyState == 4) {\n");
      out.write("                if (xmlhttp.status === 200) {\n");
      out.write("                    response = xmlhttp.responseText;\n");
      out.write("                    responseJason = JSON.parse(response);\n");
      out.write("//                    content.innerHTML = responseJason.bookList[0].price;\n");
      out.write("                    showSearchResults(responseJason.bookList);\n");
      out.write("                    showSc();\n");
      out.write("//                    alert(xmlhttp.responseText);\n");
      out.write("                } else {\n");
      out.write("                    alert('Something is wrong !!');\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("        };\n");
      out.write("        xmlhttp.send(null);\n");
      out.write("    }\n");
      out.write("    function showSearchResults(args) {\n");
      out.write("        if (args[0] == null) {\n");
      out.write("            content.innerHTML = \"no results\";\n");
      out.write("        } else {\n");
      out.write("            content.innerHTML =\"<table><tr>ss</table>\";\n");
      out.write("//            content.innerHTML = `<table style=\"border: 1px solid black;\n");
      out.write("//    border-collapse: collapse;\">\n");
      out.write("//        <tr>\n");
      out.write("//            <th>ID</th>\n");
      out.write("//            <th>Author</th>\n");
      out.write("//            <th>Title</th>\n");
      out.write("//            <th>Price</th>\n");
      out.write("//            <th>Quantity</th>\n");
      out.write("//            <th>Action</th>\n");
      out.write("//        </tr>`;\n");
      out.write("//            for (var x in args) {\n");
      out.write("//                content.innerHTML += `<tr>\n");
      out.write("//            <td>${args[x].id}</td>\n");
      out.write("//            <td>${args[x].author}</td>\n");
      out.write("//            <td>${args[x].title}</td>\n");
      out.write("//            <td>${args[x].price}</td>\n");
      out.write("//            <td>111</td>\n");
      out.write("//            <td>add to cart</td>\n");
      out.write("//        </tr><br>`;\n");
      out.write("//            }\n");
      out.write("//            content.innerHTML += `</table>`;\n");
      out.write("\n");
      out.write("//                content.innerHTML += args[x].id + \" \" + args[x].title +\n");
      out.write("//                        args[x].author + \" \" + args[x].price + \" \" + \"<br>\";\n");
      out.write("        }\n");
      out.write("\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    function showSc() {\n");
      out.write("        document.getElementById(\"subcatalog\").innerHTML = `\n");
      out.write("                <li onclick='ajaxAsyncRequest(\"ajaxsearch?search=java\")'>\n");
      out.write("                    <a href=\"#\" class=\"subcatalog\" >Java(${responseJason.qyt_book_java})</a></li>\n");
      out.write("                <li onclick='ajaxAsyncRequest(\"ajaxsearch?search=PHP\")'>\n");
      out.write("                    <a href=\"#\" class=\"subcatalog\" >PHP(${responseJason.qyt_book_PHP})</a></li>\n");
      out.write("                <li onclick='ajaxAsyncRequest(\"ajaxsearch?search=JavaScript\")'>\n");
      out.write("                    <a href=\"#\" class=\"subcatalog\" >JavaScript(${responseJason.qyt_book_JavaScript})</a></li>\n");
      out.write("`;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("\n");
      out.write("</script>-->\n");
      out.write("\n");
      out.write("<!-- end the middle column -->\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/includes/column_right_news.jsp", out, false);
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/includes/footer.jsp", out, false);
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
