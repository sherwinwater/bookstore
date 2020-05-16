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
import data.BookDB;
import data.CartItem;

@WebServlet(name = "CatalogServlet", urlPatterns = {"/catalogs"})
public class CatalogServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");

        String todo = request.getParameter("todo");
        String search = request.getParameter("search");
        String msg ="";
        switch (todo) {
            case "search":
                ArrayList<Book> bookList = new ArrayList<>();
                bookList = BookDB.search(search);
                request.setAttribute("bookList", bookList);
                msg = bookList.get(0).getId();
                break;
        }
        response.getWriter().write(msg);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
