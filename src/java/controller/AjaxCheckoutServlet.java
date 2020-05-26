package controller;

import business.PasswordUtil;
import data.Book;
import data.CartDB;
import data.CartItem;
import data.Contact;
import data.ContactDB;
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

@WebServlet(name = "AjaxCheckoutServlet", urlPatterns = {"/Ajaxcheckout"})
public class AjaxCheckoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String todo = request.getParameter("todo");
        JSONObject data = new JSONObject();

        HttpSession session = request.getSession();
        final Object lock = request.getSession().getId().intern();

        switch (todo) {
            case "contactinfo":
                Contact contact = new Contact();
                synchronized (lock) {
                    contact = (Contact) session.getAttribute("contact");
                }

                if (contact == null) {
                    contact = new Contact();
                }
                synchronized (lock) {
                    session.setAttribute("contact", contact);
                }

                String FirstName = request.getParameter("FirstName");
                String LastName = request.getParameter("LastName");
                String Email = request.getParameter("Email");
                String CompanyName = request.getParameter("CompanyName");
                String Address1 = request.getParameter("Address1");
                String Address2 = request.getParameter("Address2");
                String City = request.getParameter("City");
                String State = request.getParameter("State");
                String Zip = request.getParameter("Zip");
                String Country = request.getParameter("Country");

                contact.setFirstName(FirstName);
                contact.setLastName(LastName);
                contact.setEmail(Email);
                contact.setCompanyName(CompanyName);
                contact.setAddress1(Address1);
                contact.setAddress2(Address2);
                contact.setCity(City);
                contact.setState(State);
                contact.setZip(Zip);
                contact.setCountry(Country);
                ContactDB.insert(contact);

                data.put("contact", contact);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(data);
                break;
            case "order":

                break;
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

}
