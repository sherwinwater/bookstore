package controller;

import data.Book;
import data.BookDB;
import data.CartItem;
import data.User;
import data.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "EmailServlet", urlPatterns = {"/Email"})
public class EmailServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "/index.jsp";

        String todo = request.getParameter("todo");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            synchronized (session) {
                session.setAttribute("userList", user);
            }
        }

        switch (todo) {
            case "add":
                String firsname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String password = request.getParameter("password");
                String email = request.getParameter("email");
                user = new User(email);
                user.setFirstname(firsname);
                user.setLastname(lastname);
                user.setPassword(password);
                String msg_email = "";
                if (!UserDB.userExists(email)) {
                    UserDB.insert(user);
                    msg_email = email + " successfully registers in the list.";
                } else {
                    msg_email= email +" is already in the list!";
                }
                    request.setAttribute("msg_email", msg_email);

                url = "/email/email_result.jsp";
                break;

            default:
                url = "/catalog/catalog.jsp";
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
