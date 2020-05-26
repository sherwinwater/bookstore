package data;

import java.sql.*;
import java.util.ArrayList;

public class BookDB {
    private static final String TABLE = "books";
    
    public static int insert(CartItem item) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "INSERT INTO " + TABLE + " (product_id,product_price,"
                + "product_title,product_author,"
                + "product_quantity,product_totalprice)"
                + "VALUES(?,?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, item.getId());
            ps.setDouble(2, item.getPrice());
            ps.setString(3, item.getTitle());
            ps.setString(4, item.getAuthor());
            ps.setInt(5, item.getQuantity());
            ps.setDouble(6, item.getTotalprice());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int update(CartItem item) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE "+TABLE+" SET "
                + "product_price = ? "
                + "WHERE product_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setDouble(1, item.getPrice());
            ps.setString(2, item.getId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static void delete(CartItem item) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM "+TABLE+" "
                + "WHERE product_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, item.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static boolean itemExists(String id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM "+TABLE+" "
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

    public static ArrayList<CartItem> select(String id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<CartItem> itemList = new ArrayList<>();

        String query = "SELECT * FROM "+TABLE+" "
                + "WHERE product_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                CartItem item = new CartItem(rs.getString("product_id"),
                        rs.getDouble("product_price"),rs.getString("product_title"),
                rs.getString("product_author"));
                item.setQuantity(rs.getInt("product_quantity"));
                item.setTotalprice(rs.getDouble("product_totalprice"));
                itemList.add(item);
            }
            return itemList;
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

        String query = "SELECT * FROM "+ TABLE 
                + " WHERE product_title LIKE ?";
        try {
            ps = connection.prepareStatement(query);
            title = "%"+title+"%";
            ps.setString(1, title);
            rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book(rs.getString("product_id"),
                        rs.getDouble("product_price"),rs.getString("product_title"),
                        rs.getString("product_author"));
                book.setInventory_qty(rs.getInt("product_inventory_qty"));
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
