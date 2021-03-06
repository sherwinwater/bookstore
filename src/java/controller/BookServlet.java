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

@WebServlet(name = "BookServlet", urlPatterns = {"/book"})
public class BookServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/catalog/catalog.jsp";
        String todo = request.getParameter("todo");

        // session with threads-safe
        HttpSession session = request.getSession();
        final Object lock = request.getSession().getId().intern();
        ArrayList<CartItem> cart = new ArrayList<>();
        synchronized (lock) {
            cart = (ArrayList<CartItem>) session.getAttribute("cart");
        }
        if (cart == null) {
            cart = new ArrayList<>();
        }

        String book_java = "java";
        String book_PHP = "PHP";
        String book_Javascripts = "Javascripts";

        int qyt_book_java = BookDB.search(book_java).size();
        int qyt_book_PHP = BookDB.search(book_PHP).size();
        int qyt_book_Javascripts = BookDB.search(book_Javascripts).size();

        session.setAttribute("qyt_book_java", qyt_book_java);
        session.setAttribute("qyt_book_PHP", qyt_book_PHP);
        session.setAttribute("qyt_book_Javascripts", qyt_book_Javascripts);

        switch (todo) {
            case "shopping":
                url = "/catalog/catalog.jsp";
                break;
            case "view":
                url = "/cart/cart.jsp";
                break;
            case "add":
                String book_id = request.getParameter("book_id");
                String book_author = request.getParameter("book_author");
                String book_title = request.getParameter("book_title");
                Double book_price = Double.parseDouble(request.getParameter("book_price"));
                int book_qty = Integer.parseInt(request.getParameter("book_qty"));
                Book newbook = new Book(book_id, book_price);

                CartItem cartitem = new CartItem(book_id, book_price);
                cartitem.setQuantity(book_qty);

                if (cart.isEmpty()) {
                    cart.add(cartitem);
                } else {
                    boolean isHasBook = false;
                    for (CartItem item : cart) {
                        if (item.getId().equals(book_id)) {
                            item.setQuantity(book_qty);
                            isHasBook = true;
                        }
                    }
                    if (!isHasBook) {
                        cart.add(cartitem);
                    }
                }

                url = "/catalog/catalog.jsp";
                break;
            case "remove":
                String removeId = request.getParameter("book_id_rm");
                Iterator<CartItem> itrCart = cart.iterator();
                while (itrCart.hasNext()) {
                    if (itrCart.next().getId().equals(removeId)) {
                        itrCart.remove();
                    }
                }

                url = "/cart/cart.jsp";
                break;

            case "update":
                int book_qty2 = Integer.parseInt(request.getParameter("quantity"));
                String book_id2 = request.getParameter("book_id");
                for (CartItem item : cart) {
                    if (item.getId().equals(book_id2)) {
                        item.setQuantity(book_qty2);
                    }
                }
                url = "/cart/cart.jsp";
                break;

            case "checkout":
                for (CartItem item : cart) {
                    BookDB.insert(item);
                }
//                CartItem updateitem = new CartItem("1001",222.0);
//                System.out.println("updateitem 1001 222.0:  "+BookDB.update(updateitem));
//                ArrayList<CartItem> selectitems = BookDB.select("1002");
//                request.setAttribute("selectitems", selectitems);
//                System.out.println("item is here?" +BookDB.itemExists("10011"));
                url = "/order/checkout.jsp";
                break;
            case "newshopping":
                cart.clear();
                url = "/catalog/catalog.jsp";
                break;
            case "search":
                String search = request.getParameter("search");
                ArrayList<Book> bookList = new ArrayList<>();
                bookList = BookDB.search(search);
                request.setAttribute("bookList", bookList);
                url = "/catalog/search.jsp";
                break;
        }

        synchronized (lock) {
            session.setAttribute("cart", cart);
        }

        ServletContext sc = request.getServletContext();
        sc.getRequestDispatcher(url).forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

}
