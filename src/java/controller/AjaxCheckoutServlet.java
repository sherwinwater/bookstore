package controller;

import business.PasswordUtil;
import data.Book;
import data.CartDB;
import data.CartItem;
import data.Contact;
import data.ContactDB;
import data.CreditCard;
import data.CreditCardDB;
import data.Invoice;
import data.InvoiceDB;
import data.Order;
import data.OrderDB;
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

@WebServlet(name = "AjaxCheckoutServlet", urlPatterns = {"/ajaxcheckout"})
public class AjaxCheckoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        PrintWriter out = response.getWriter();
        String todo = request.getParameter("todo");
        JSONObject data = new JSONObject();

        HttpSession session = request.getSession();
        final Object lock = request.getSession().getId().intern();

        String contactID = "";
        String creditID = "";
        String invoiceID = "";
        String orderID = "";
        String cart_id = "";
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

                contactID = request.getParameter("contactID");
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

                session.setAttribute("contactID", contactID);

                contact.setId(contactID);
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
                // credit info
                creditID = request.getParameter("creditID");
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String creditCardType = request.getParameter("creditCardType");
                String creditCardNumber = request.getParameter("creditCardNumber");
                String creditCardExpirationDate = request.getParameter("creditCardExpirationDate");

                CreditCard creditcard = new CreditCard(creditID, firstname, lastname,
                        creditCardType, creditCardNumber, creditCardExpirationDate);

                CreditCardDB.insert(creditcard);

                // Invoice
                contactID = (String) session.getAttribute("contactID");
                invoiceID = request.getParameter("invoiceID");
                cart_id = (String) session.getAttribute("cart_id");
                Invoice invoice = new Invoice(cart_id, invoiceID, creditID, contactID);
                InvoiceDB.insert(invoice);
                session.setAttribute("invoice", invoice);

                // order (orderID, username,invoiceID)
                orderID = request.getParameter("orderID");
                Order order = new Order(orderID, invoiceID);
                OrderDB.insert(order);
                session.setAttribute("order", order);

                // update cart with the isOrdered == 1 in the cart db table
                ArrayList<CartItem> cart = (ArrayList<CartItem>) session.getAttribute("cart");
                for (CartItem item : cart) {
                    CartDB.updateIsOrdered(item);
                }

                // clear cart
                cart.clear();
                session.removeAttribute("cart_id");
                session.removeAttribute("contact");
                session.removeAttribute("creditID");
                session.removeAttribute("invoiceID");
                
                data.put("creditcard", creditcard);
                data.put("order", order);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(data);
                
                //  session invalidate
//                if (session != null) {
//                    session.invalidate();
//                }
                break;
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

}
