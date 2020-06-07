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
import data.UserDB;
import java.util.Collections;
import java.util.Comparator;
import org.json.JSONObject;

@WebServlet(name = "AjaxCartServlet", urlPatterns = {"/ajaxcart"})
public class AjaxCartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");

        String todo = request.getParameter("todo");
        // session with threads-safe
        HttpSession session = request.getSession();
        final Object lock = request.getSession().getId().intern();
        ArrayList<CartItem> cart = new ArrayList<>();
        User user = new User();
        synchronized (lock) {
            cart = (ArrayList<CartItem>) session.getAttribute("cart");
            user = (User) session.getAttribute("user");
        }
        if (cart == null) {
            cart = new ArrayList<>();
        }
        synchronized (lock) {
            session.setAttribute("cart", cart);
            session.setAttribute("user", user);
        }

        // user
        String username = "Guest";
        if (user != null) {
            username = user.getUsername();
        } else {
            if (!UserDB.userExists(username)) {
                user = new User();
                user.setUsername(username);
                UserDB.insert(user);
            }
        }

        Comparator<CartItem> sortById = Comparator.comparing(CartItem::getId);
        Comparator<CartItem> sortByPrice = Comparator.comparing(CartItem::getTotalprice);

        String cart_id = "";
        JSONObject itemsIncart = new JSONObject();
        int cartSize = 0;
        switch (todo) {
            case "view":
                cartSize = cart.size();
                double totalprice = 0.00;
                for (CartItem item : cart) {
                    totalprice += item.getTotalprice();
                }
//                totalprice = Math.round(totalprice *100.0)/100.0;

                itemsIncart = new JSONObject();
                itemsIncart.put("cart", cart);
                itemsIncart.put("cartSize", cartSize);
                itemsIncart.put("totalprice", totalprice);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(itemsIncart);

                break;
            case "add":
                String book_id = request.getParameter("book_id");
                String book_author = request.getParameter("book_author");
                String book_title = request.getParameter("book_title");
                String book_imgURL = request.getParameter("book_imgURL");
                Double book_price = Double.parseDouble(request.getParameter("book_price"));
                int book_quantity = Integer.parseInt(request.getParameter("book_quantity"));
                int book_inventory = Integer.parseInt(request.getParameter("book_inventory"));
                Double book_totalprice = Math.round(book_price * book_quantity * 100.0) / 100.0;
                if (!cart.isEmpty()) {
                    cart_id = cart.get(0).getCart_id();
                } else {
                    cart_id = (String) request.getSession().getAttribute("cart_id");
                }

                CartItem cartitem = new CartItem(book_id, book_price, book_title, book_author);
                cartitem.setCart_id(cart_id);
//                if (user != null) {
                cartitem.setUsername(username);
//                }
                cartitem.setQuantity(book_quantity);
                cartitem.setInventory(book_inventory);
                cartitem.setImgURL(book_imgURL);
                cartitem.setTotalprice(book_totalprice);

                if (cart.isEmpty()) {
                    cart.add(cartitem);
                    CartDB.insert(cartitem);
                } else {
                    boolean isHasBook = false;
                    for (CartItem item : cart) {
                        if (item.getId().equals(book_id)
                                & item.getQuantity() != book_quantity) {
                            item.setQuantity(book_quantity);
                            CartDB.updateQuantity(item);
                            isHasBook = true;
                        }
                        if (item.getId().equals(book_id)
                                & item.getQuantity() == book_quantity) {
                            isHasBook = true;
                        }
                    }
                    if (!isHasBook) {
                        cart.add(cartitem);
                        CartDB.insert(cartitem);
                    }
                }

                Collections.sort(cart, sortById.thenComparing(sortByPrice));
                cartSize = cart.size();
                itemsIncart = new JSONObject();
                itemsIncart.put("cart", cart);
                itemsIncart.put("cartSize", cartSize);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(itemsIncart);

                break;
            case "remove":
                String removeId = request.getParameter("book_id");
                Iterator<CartItem> itrCart = cart.iterator();
                while (itrCart.hasNext()) {
                    CartItem item = itrCart.next();
                    if (item.getId().equals(removeId)) {
                        itrCart.remove();
                        CartDB.deleteItem(item);
                    }
                }
                cartSize = cart.size();

                itemsIncart = new JSONObject();
                itemsIncart.put("cart", cart);
                itemsIncart.put("cartSize", cartSize);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(itemsIncart);

                break;

            case "update":
                book_quantity = Integer.parseInt(request.getParameter("book_quantity"));
                book_price = Double.parseDouble(request.getParameter("book_price"));
                book_id = request.getParameter("book_id");
                for (CartItem item : cart) {
                    if (item.getId().equals(book_id)
                            & item.getQuantity() != book_quantity) {
                        item.setQuantity(book_quantity);
                        item.setTotalprice(book_quantity * book_price);
                        CartDB.updateQuantity(item);
                    }
                }
                cartSize = cart.size();

                itemsIncart = new JSONObject();
                itemsIncart.put("cart", cart);
                itemsIncart.put("cartSize", cartSize);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(itemsIncart);

                break;

            case "checkout":

                itemsIncart = new JSONObject();
                itemsIncart.put("cart", cart);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(itemsIncart);

//                CartItem updateitem = new CartItem("1001",222.0);
//                System.out.println("updateitem 1001 222.0:  "+CartDB.update(updateitem));
//                ArrayList<CartItem> selectitems = CartDB.select("1002");
//                request.setAttribute("selectitems", selectitems);
//                System.out.println("item is here?" +CartDB.itemExists("10011"));
//                url = "/order/checkout.jsp";
                break;
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

}
