package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import data.Book;
import data.BookDB;
import data.CartItem;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;

import org.json.JSONObject;

@WebServlet(name = "AjaxBookServlet", urlPatterns = {"/admin/ajaxbook"})
//@ServletSecurity(
//        value = @HttpConstraint(
//                rolesAllowed = {
//                    "programmer", "service"
//                }),
//        httpMethodConstraints = {
//            @HttpMethodConstraint(value = "GET",
//                    rolesAllowed = {
//                        "programmer", "service"
//                    })
//        }
//)

public class AjaxBookServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");

        String todo = request.getParameter("todo");

        String searchContent = "";
        ArrayList<Book> bookList = new ArrayList<>();
        JSONObject data = new JSONObject();
        String msg_data = new String();

        Comparator<Book> sortById = Comparator.comparing(Book::getId);
        Comparator<Book> sortByTitle = Comparator.comparing(Book::getTitle);
        Comparator<Book> sortByPrice = Comparator.comparing(Book::getPrice);

        switch (todo) {
            case "check":
                String book_id = request.getParameter("id");
                if (BookDB.bookExists(book_id)) {
                    msg_data = "id already exsits";
                }
                data.put("msg_data", msg_data);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(data);
                break;

            case "insert":
                book_id = request.getParameter("id");
                if (!BookDB.bookExists(book_id)) {
                    String book_author = request.getParameter("author");
                    String book_title = request.getParameter("title");
                    Double book_price = 0.0;
                    if (request.getParameter("price") != null && !request.getParameter("price").isEmpty()) {
                        book_price = Double.parseDouble(request.getParameter("price"));
                    }
                    int book_inventory = 0;
                    if (request.getParameter("inventory") != null && !request.getParameter("inventory").isEmpty()) {
                        book_inventory = Integer.parseInt(request.getParameter("inventory"));
                    }
                    String book_imgURL = request.getParameter("imgURL");
                    String book_location = request.getParameter("location");
                    String book_vendor = request.getParameter("vendor");
                    String book_owner = request.getParameter("owner");
                    Book book = new Book(book_id, book_price, book_title, book_author);
                    book.setInventory(book_inventory);
                    book.setImgURL(book_imgURL);
                    book.setLocation(book_location);
                    book.setVendor(book_vendor);
                    book.setOwner(book_owner);
                    BookDB.insert(book);
                } else {
                    msg_data = "id already exsits";
                    System.out.println("hhh");
                }
                data.put("msg_data", msg_data);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(data);
                break;

            case "insertsamples":
                int size = Integer.parseInt(request.getParameter("size"));

                LocalDateTime myDateObj = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
                String id = myDateObj.format(myFormatObj);

                for (int i = 0; i < size; i++) {
                    Book booksample = new Book(id + "__" + i, 11.00, "sample", "John Doe");
                    booksample.setInventory(100);
                    booksample.setImgURL("/ebook/images/book_I wish you more by Amy Rosen.jpg");
                    booksample.setLocation("2A");
                    booksample.setVendor("york");
                    booksample.setOwner("JackRose");
                    BookDB.insert(booksample);
                }

                data.put("size", size);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(data);
                break;

            case "update":
                book_id = request.getParameter("id");
                String book_author = request.getParameter("author");
                String book_title = request.getParameter("title");
                Double book_price = 0.0;
                if (request.getParameter("price") != null && !request.getParameter("price").isEmpty()) {
                    book_price = Double.parseDouble(request.getParameter("price"));
                }
                int book_inventory = 0;
                if (request.getParameter("inventory") != null && !request.getParameter("inventory").isEmpty()) {
                    book_inventory = Integer.parseInt(request.getParameter("inventory"));
                }
                String book_imgURL = request.getParameter("imgURL");
                String book_location = request.getParameter("location");
                String book_vendor = request.getParameter("vendor");
                String book_owner = request.getParameter("owner");
                Book book = new Book(book_id, book_price, book_title, book_author);
                book.setInventory(book_inventory);
                book.setImgURL(book_imgURL);
                book.setLocation(book_location);
                book.setVendor(book_vendor);
                book.setOwner(book_owner);
                BookDB.update(book);

                data.put("book", book);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(data);
                break;

            case "search":
                searchContent = request.getParameter("search");
                bookList = BookDB.search(searchContent);

                data.put("bookList", bookList);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(data);
                break;

            case "sort":
                String sortContent = request.getParameter("sortContent");
                String option = request.getParameter("option");

//                if (option.equals("ASC")) {
//                    bookList = BookDB.sort(sortContent);
//                }
//                if (option.equals("DESC")) {
//                    bookList = BookDB.sortDesc(sortContent);
//                }
                // method 2:
                searchContent = request.getParameter("search");
                bookList = BookDB.search(searchContent);
                if (option.equals("ASC")) {
                    Collections.sort(bookList, sortByPrice.thenComparing(sortByTitle).
                            thenComparing(sortById));
                }
                if (option.equals("DESC")) {
                    Collections.sort(bookList, sortByPrice.thenComparing(sortByTitle).
                            thenComparing(sortById).reversed());
                }

                data.put("bookList", bookList);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(data);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

}
