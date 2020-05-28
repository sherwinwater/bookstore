package data;

import java.sql.*;
import java.util.ArrayList;

public class OrderDB {

    private static final String TABLE = "Order";

    public static int insert(Order order) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "INSERT INTO " + TABLE + " (orderID,Invoice_invoiceID)"
                + "VALUES(?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, order.getOrderID());
            ps.setString(2, order.getInvoiceID());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

//    public static int update(Order order) {
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
//        PreparedStatement ps = null;
//
//        String query = "UPDATE "+TABLE+" SET "
//                + "ordername = ? "
//                + "WHERE ordername = ?";
//        try {
//            ps = connection.prepareStatement(query);
//            ps.setString(1, order.getOrdername());
//            ps.setString(2, order.getOrdername());
//            return ps.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e);
//            return 0;
//        } finally {
//            DBUtil.closePreparedStatement(ps);
//            pool.freeConnection(connection);
//        }
//    }
//
//    public static void delete(Order order) {
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
//        PreparedStatement ps = null;
//
//        String query = "DELETE FROM "+TABLE+" "
//                + "WHERE ordername = ?";
//        try {
//            ps = connection.prepareStatement(query);
//            ps.setString(1, order.getOrdername());
//
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e);
//        } finally {
//            DBUtil.closePreparedStatement(ps);
//            pool.freeConnection(connection);
//        }
//    }
//
//    public static boolean orderExists(String ordername) {
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        String query = "SELECT * FROM "+TABLE+" "
//                + "WHERE ordername = ?";
//        try {
//            ps = connection.prepareStatement(query);
//            ps.setString(1, ordername);
//            rs = ps.executeQuery();
//            return rs.next();
//        } catch (SQLException e) {
//            System.out.println(e);
//            return false;
//        } finally {
//            DBUtil.closeResultSet(rs);
//            DBUtil.closePreparedStatement(ps);
//            pool.freeConnection(connection);
//        }
//    }
//    
//    public static boolean orderLogin(String ordername,String password) {
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        String query = "SELECT * FROM "+TABLE+" "
//                + "WHERE ordername = ? AND hashpassword = ?";
//        try {
//            ps = connection.prepareStatement(query);
//            ps.setString(1, ordername);
//            ps.setString(2, password);
//            rs = ps.executeQuery();
//            return rs.next();
//        } catch (SQLException e) {
//            System.out.println(e);
//            return false;
//        } finally {
//            DBUtil.closeResultSet(rs);
//            DBUtil.closePreparedStatement(ps);
//            pool.freeConnection(connection);
//        }
//    }
//
//    public static ArrayList<Order> select(String ordername) {
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        ArrayList<Order> orderList = new ArrayList<>();
//
//        String query = "SELECT * FROM "+TABLE+" "
//                + "WHERE ordername = ?";
//        try {
//            ps = connection.prepareStatement(query);
//            ps.setString(1, ordername);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                Order order = new Order(rs.getString("ordername"));
//                order.setHashpassword(rs.getString("hashpassword"));
//                order.setSalt(rs.getString("salt"));
//                orderList.add(order);
//            }
//            return orderList;
//        } catch (SQLException e) {
//            System.out.println(e);
//            return null;
//        } finally {
//            DBUtil.closeResultSet(rs);
//            DBUtil.closePreparedStatement(ps);
//            pool.freeConnection(connection);
//        }
//    }
}
