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
import data.UserDB;
import org.json.JSONObject;

@WebServlet(name = "AjaxCartServlet", urlPatterns = {"/ajaxcart"})
public class AjaxCartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        synchronized (lock) {
            session.setAttribute("cart", cart);
        }
        
        JSONObject itemsIncart = new JSONObject();
        switch (todo) {
            case "view":
                itemsIncart = new JSONObject();
                itemsIncart.put("cart", cart);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(itemsIncart);

                break;
            case "add":
                response.setHeader("Cache-Control", "no-cache");
                response.setHeader("Pragma", "no-cache");
                PrintWriter out = response.getWriter();

                String book_id = request.getParameter("book_id");
                String book_author = request.getParameter("book_author");
                String book_title = request.getParameter("book_title");
                Double book_price = Double.parseDouble(request.getParameter("book_price"));
                int book_quantity = Integer.parseInt(request.getParameter("book_quantity"));

                CartItem cartitem = new CartItem(book_id, book_price,book_title,book_author);
                cartitem.setQuantity(book_quantity);

                if (cart.isEmpty()) {
                    cart.add(cartitem);
                } else {
                    boolean isHasBook = false;
                    for (CartItem item : cart) {
                        if (item.getId().equals(book_id)) {
                            item.setQuantity(book_quantity);
                            isHasBook = true;
                        }
                    }
                    if (!isHasBook) {
                        cart.add(cartitem);
                    }
                }

                itemsIncart = new JSONObject();
                itemsIncart.put("cart", cart);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(itemsIncart);

                break;
            case "remove":
                String removeId = request.getParameter("book_id");
                Iterator<CartItem> itrCart = cart.iterator();
                while (itrCart.hasNext()) {
                    if (itrCart.next().getId().equals(removeId)) {
                        itrCart.remove();
                    }
                }
                itemsIncart = new JSONObject();
                itemsIncart.put("cart", cart);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(itemsIncart);

                break;

            case "update":
                int book_quantity_update = Integer.parseInt(request.getParameter("book_quantity_update"));
                book_id = request.getParameter("book_id");
                for (CartItem item : cart) {
                    if (item.getId().equals(book_id)) {
                        item.setQuantity(book_quantity_update);
                    }
                }

                itemsIncart = new JSONObject();
                itemsIncart.put("cart", cart);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(itemsIncart);

                break;

            case "checkout":
                for (CartItem item : cart) {
                    CartDB.insert(item);
                }
//                CartItem updateitem = new CartItem("1001",222.0);
//                System.out.println("updateitem 1001 222.0:  "+CartDB.update(updateitem));
//                ArrayList<CartItem> selectitems = CartDB.select("1002");
//                request.setAttribute("selectitems", selectitems);
//                System.out.println("item is here?" +CartDB.itemExists("10011"));
//                url = "/order/checkout.jsp";
                break;

            case "search":
                String search = request.getParameter("search");
                ArrayList<Book> bookList = new ArrayList<>();
                bookList = BookDB.search(search);
                request.setAttribute("bookList", bookList);

                String book_java = "java";
                String book_PHP = "PHP";
                String book_JavaScript = "JavaScript";

                int qyt_book_java = BookDB.search(book_java).size();
                int qyt_book_PHP = BookDB.search(book_PHP).size();
                int qyt_book_JavaScript = BookDB.search(book_JavaScript).size();

                request.setAttribute("qyt_book_java", qyt_book_java);
                request.setAttribute("qyt_book_PHP", qyt_book_PHP);
                request.setAttribute("qyt_book_JavaScript", qyt_book_JavaScript);

//                url = "/catalog/search.jsp";
                break;
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

}
