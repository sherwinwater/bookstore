package controller;

import data.Book;
import data.CartDB;
import data.CartItem;
import data.Email;
import data.EmailDB;
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
        final Object lock = request.getSession().getId().intern();
        Email emailer = new Email();
        synchronized (lock) {
            emailer = (Email) session.getAttribute("emailer");
        }

        if (emailer == null) {
            emailer = new Email();
        }

        switch (todo) {
            case "add":
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String email = request.getParameter("email");
                if (firstname != null && lastname != null && email != null) {
                    emailer = new Email(email);
                    emailer.setFirstname(firstname);
                    emailer.setLastname(lastname);
                    String msg_email = "";
                    if (!EmailDB.emailerExists(email)) {
                        EmailDB.insert(emailer);
                        msg_email = email + " successfully registers in the list.";
                    } else {
                        msg_email = email + " is already in the list!";
                    }
                    request.setAttribute("msg_email", msg_email);
                    request.setAttribute("firstname", firstname);
                    request.setAttribute("lastname", lastname);
                    request.setAttribute("email", email);
                    url = "/email/email.jsp";
                }
                break;

            default:
                url = "/catalog/catalog.jsp";
        }

        synchronized (lock) {
            session.setAttribute("emailer", emailer);
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
