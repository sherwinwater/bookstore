package data;

import java.sql.*;
import java.util.ArrayList;

public class CartDB {

    private static final String TABLE = "cart";

    public static int insert(CartItem item) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "INSERT INTO " + TABLE + " (product_id,product_price,"
                + "product_title,product_author,"
                + "product_quantity,product_totalprice,cartID,user_username,inventory)"
                + "VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, item.getId());
            ps.setDouble(2, item.getPrice());
            ps.setString(3, item.getTitle());
            ps.setString(4, item.getAuthor());
            ps.setInt(5, item.getQuantity());
            ps.setDouble(6, item.getTotalprice());
            ps.setString(7, item.getCart_id());
            ps.setString(8, item.getUsername());
            ps.setInt(9, item.getInventory());
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

        String query = "UPDATE " + TABLE + " SET "
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

    public static int updateQuantity(CartItem item) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE " + TABLE + " SET "
                + "product_quantity = ? "
                + "WHERE product_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setDouble(1, item.getQuantity());
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

    public static int updateIsOrdered(CartItem item) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE " + TABLE + " SET "
                + "isOrdered = 1 "
                + "WHERE cartID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, item.getCart_id());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static void deleteItem(CartItem item) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM " + TABLE + " "
                + "WHERE product_id = ? AND cartID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, item.getId());
            ps.setString(2, item.getCart_id());

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

    public static ArrayList<CartItem> select(String id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<CartItem> itemList = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE + " "
                + "WHERE product_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                CartItem item = new CartItem(rs.getString("product_id"),
                        rs.getDouble("product_price"), rs.getString("product_title"),
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

    public static ArrayList<CartItem> selectIsOrdered(String username) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<CartItem> itemList = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE + " WHERE user_username = ? AND isOrdered = ? ";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setBoolean(2, false);
            rs = ps.executeQuery();
            while (rs.next()) {
                CartItem item = new CartItem(rs.getString("product_id"),
                        rs.getDouble("product_price"), rs.getString("product_title"),
                        rs.getString("product_author"));
                item.setQuantity(rs.getInt("product_quantity"));
                item.setTotalprice(rs.getDouble("product_totalprice"));
                item.setCart_id(rs.getString("cartID"));
                item.setUsername(username);
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
}
