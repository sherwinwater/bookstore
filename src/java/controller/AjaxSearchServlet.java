package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import data.Book;
import data.CartDB;
import data.BookDB;
import data.CartItem;
import data.User;
import org.json.JSONObject;

@WebServlet(name = "AjaxSearchServlet", urlPatterns = {"/ajaxsearch"})
public class AjaxSearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        PrintWriter out = response.getWriter();

        String search = request.getParameter("search");
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        ArrayList<Book> bookList = new ArrayList<>();
//        bookList = BookDB.search(search);
        bookList = BookDB.searchResults(search,pageNumber,pageSize);

        String book_java = "java";
        String book_PHP = "PHP";
        String book_JavaScript = "JavaScript";

        int qyt_book = BookDB.search(search).size();
        int qyt_book_java = BookDB.search(book_java).size();
        int qyt_book_PHP = BookDB.search(book_PHP).size();
        int qyt_book_JavaScript = BookDB.search(book_JavaScript).size();

        JSONObject jo = new JSONObject();
        jo.put("book_quantity", qyt_book);
        jo.put("java_quantity", qyt_book_java);
        jo.put("PHP_quantity", qyt_book_PHP);
        jo.put("JavaScript_quantity", qyt_book_JavaScript);
        jo.put("bookList", bookList);

        // session with threads-safe
        HttpSession session = request.getSession();
        final Object lock = request.getSession().getId().intern();
        ArrayList<CartItem> cart = new ArrayList<>();
        String cart_id = new String();
        synchronized (lock) {
            cart = (ArrayList<CartItem>) session.getAttribute("cart");
            cart_id = (String) session.getAttribute("cart_id");
        }
        if (cart == null) {
            cart = new ArrayList<>();
        }
        if (cart_id == null) {
            cart_id = request.getParameter("cart_id");
        }
        synchronized (lock) {
            session.setAttribute("cart", cart);
            session.setAttribute("cart_id", cart_id);
        }
//        System.out.println("cart_id  "+cart_id);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(jo);
    }
}
