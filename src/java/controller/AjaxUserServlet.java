package controller;

import business.PasswordUtil;
import data.Book;
import data.CartDB;
import data.CartItem;
import data.User;
import data.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

@WebServlet(name = "AjaxUserServlet", urlPatterns = {"/ajaxuser"})
public class AjaxUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "/account/login.jsp";
        String todo = request.getParameter("todo");

        HttpSession session = request.getSession();
        final Object lock = request.getSession().getId().intern();
        User user = new User();
        ArrayList<CartItem> cart = new ArrayList<>();
        synchronized (lock) {
            user = (User) session.getAttribute("user");
            cart = (ArrayList<CartItem>) session.getAttribute("cart");
        }

        if (user == null) {
            user = new User();
        }
        synchronized (lock) {
            session.setAttribute("user", user);
            session.setAttribute("cart", cart);
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String cart_id = request.getParameter("cart_id");
        session.setAttribute("cart_id", cart_id);
        
        String message = "";
        String salt = "";
        String saltedAndHashedPassword;
        String msg_password = "";
        String msg_username = "";

        switch (todo) {
            case "checkname":
                response.setHeader("Cache-Control", "no-cache");
                response.setHeader("Pragma", "no-cache");
                PrintWriter out = response.getWriter();

                // check username duplicate
                msg_username = "NEW USER";
                if (UserDB.userExists(username)) {
                    msg_username = username + " is already in the list!";
                }
                JSONObject msg = new JSONObject();
                msg.put("msg_username", msg_username);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(msg);
                break;

            case "checkpassword":
                response.setHeader("Cache-Control", "no-cache");
                response.setHeader("Pragma", "no-cache");
                out = response.getWriter();
                // check strength requirements
                try {
                    PasswordUtil.checkPasswordStrength(password);
                    msg_password = "";
                } catch (Exception e) {
                    msg_password = e.getMessage();
                }

                msg = new JSONObject();
                msg.put("msg_password", msg_password);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(msg);
                break;

            case "signup":
                msg = new JSONObject();
                if (password == "") {  // "" cannot be null, string is "", object is null
                    msg_password = "Password cannot be empty";
                    msg.put("msg_password", msg_password);
                    request.setAttribute("message_signup", msg_password);
                }
                if (username == "") { // "" cannot be null, string is "", object is null
                    msg_username = "Username cannot be empty";
                    msg.put("msg_username", msg_username);
                    request.setAttribute("msg_username", msg_username);
                }

                // sign up
                if (PasswordUtil.validatePassword(password)
                        && !UserDB.userExists(username)) {

                    // hash and salt password
                    try {
                        salt = PasswordUtil.getSalt();
                        saltedAndHashedPassword = PasswordUtil
                                .hashAndSaltPassword(password, salt);

                    } catch (NoSuchAlgorithmException ex) {
                        saltedAndHashedPassword = ex.getMessage();
                    }

                    user.setUsername(username);
                    user.setHashpassword(saltedAndHashedPassword);
                    user.setSalt(salt);
                    UserDB.insert(user);
                    request.setAttribute("username", username);
                    msg.put("username", username);
                }
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(msg);

                break;

            case "login":
                msg = new JSONObject();
                if (!UserDB.select(username).isEmpty()) {
                    salt = UserDB.select(username).get(0).getSalt();
                    try {
                        saltedAndHashedPassword = PasswordUtil.hashAndSaltPassword(password, salt);

                    } catch (NoSuchAlgorithmException ex) {
                        saltedAndHashedPassword = ex.getMessage();
                    }

                    if (UserDB.userLogin(username, saltedAndHashedPassword)) {
                        request.setAttribute("username", username);
                        user.setUsername(username);
                        cart.clear();
                        cart = CartDB.selectIsOrdered(username);
                        msg.put("username", username);
                    } else {
                        message = "wrong username or password";
                    }
                } else {
                    message = "user does not exsit.";
                }
                msg.put("msg_login", message);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(msg);
                break;

            case "logout":
                user = null;
                cart = (ArrayList<CartItem>) request.getSession().getAttribute("cart");
                cart.clear();
                url = "/account/login.jsp";
                getServletContext()
                        .getRequestDispatcher(url)
                        .forward(request, response);
                break;
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

}
