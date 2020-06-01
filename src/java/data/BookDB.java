package data;

import java.sql.*;
import java.util.ArrayList;

public class BookDB {

    private static final String TABLE = "books";

    public static int insert(Book book) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "INSERT INTO " + TABLE + " (product_id,product_price,"
                + "product_title,product_author,"
                + "product_inventory,imgURL,vendor,owner,location)"
                + "VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, book.getId());
            ps.setDouble(2, book.getPrice());
            ps.setString(3, book.getTitle());
            ps.setString(4, book.getAuthor());
            ps.setInt(5, book.getInventory());
            ps.setString(6, book.getImgURL());
            ps.setString(7, book.getVendor());
            ps.setString(8, book.getOwner());
            ps.setString(9, book.getLocation());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int update(Book book) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE " + TABLE + " SET "
                + "product_price = ? "
                + "WHERE product_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setDouble(1, book.getPrice());
            ps.setString(2, book.getId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static void updateQuantity(CartItem book) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE " + TABLE + " SET "
                + "product_inventory = product_inventory - ? "
                + "WHERE product_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setDouble(1, book.getQuantity());
            ps.setString(2, book.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
//            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static void delete(CartItem book) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM " + TABLE + " "
                + "WHERE product_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, book.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static boolean bookExists(String id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM " + TABLE + " "
                + "WHERE product_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static ArrayList<Book> select(String id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Book> bookList = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE + " "
                + "WHERE product_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book(rs.getString("product_id"),
                        rs.getDouble("product_price"), rs.getString("product_title"),
                        rs.getString("product_author"));
                book.setInventory(rs.getInt("product_inventory"));
                bookList.add(book);
            }
            return bookList;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static ArrayList<Book> search(String title) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Book> bookList = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE
                + " WHERE product_title LIKE ?";
        try {
            ps = connection.prepareStatement(query);
            title = "%" + title + "%";
            ps.setString(1, title);
            rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book(rs.getString("product_id"),
                        rs.getDouble("product_price"), rs.getString("product_title"),
                        rs.getString("product_author"));
                book.setInventory(rs.getInt("product_inventory"));
                book.setLocation(rs.getString("location"));
                book.setImgURL(rs.getString("imgURL"));
                book.setVendor(rs.getString("vendor"));
                book.setOwner(rs.getString("owner"));
                bookList.add(book);
            }
            return bookList;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

}
