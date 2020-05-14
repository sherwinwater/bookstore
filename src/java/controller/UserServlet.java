package controller;

import business.PasswordUtil;
import data.Book;
import data.BookDB;
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

@WebServlet(name = "UserServlet", urlPatterns = {"/user"})
public class UserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "/account/login.jsp";

        String todo = request.getParameter("todo");

        HttpSession session = request.getSession();
        final Object lock = request.getSession().getId().intern();
        User user = new User();
        synchronized (lock) {
            user = (User) session.getAttribute("user");
        }

        if (user == null) {
            user = new User();
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String message = "";
        String salt = "";
        String saltedAndHashedPassword;

        switch (todo) {
            case "signup":

                // check strength requirements
                try {
                    PasswordUtil.checkPasswordStrength(password);
                    message = "";
                } catch (Exception e) {
                    message = e.getMessage();
                }
                request.setAttribute("message_signup", message);

                // check username duplicate
                String msg_user = "";
                if (UserDB.userExists(username)) {
                    msg_user = username + " is already in the list!";
                }
                request.setAttribute("msg_user", msg_user);

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

                    user = new User(username);
                    user.setHashpassword(saltedAndHashedPassword);
                    user.setSalt(salt);
                    UserDB.insert(user);
                    request.setAttribute("username", username);
                    url = "/account/login_ok.jsp";
                }
                break;

            case "login":
                if (!UserDB.select(username).isEmpty()) {
                    salt = UserDB.select(username).get(0).getSalt();
                    try {
                        saltedAndHashedPassword = PasswordUtil.hashAndSaltPassword(password, salt);

                    } catch (NoSuchAlgorithmException ex) {
                        saltedAndHashedPassword = ex.getMessage();
                    }

                    if (UserDB.userLogin(username, saltedAndHashedPassword)) {
                        request.setAttribute("username", username);
                        user = new User(username);
                        url = "/account/login_ok.jsp";
                    } else {
                        message = "wrong username or password";
                    }
                } else {
                    message = "user does not exsit.";
                }
                request.setAttribute("message", message);
                break;

            case "logout":
                user = null;
                url = "/account/login.jsp";
                break;
        }

        synchronized (lock) {
            session.setAttribute("user", user);
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

}
