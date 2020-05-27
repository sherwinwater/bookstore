package data;

import java.sql.*;
import java.util.ArrayList;

public class InvoiceDB {
    private static final String TABLE = "Invoice";
    
    public static int insert(Invoice invoice) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "INSERT INTO " + TABLE + " (invoiceID,cart_cartID)"
                + "VALUES(?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, invoice.getInvoice_id());
            ps.setString(2, invoice.getCart_id());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

//    public static int update(Invoice invoice) {
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
//        PreparedStatement ps = null;
//
//        String query = "UPDATE "+TABLE+" SET "
//                + "invoicename = ? "
//                + "WHERE invoicename = ?";
//        try {
//            ps = connection.prepareStatement(query);
//            ps.setString(1, invoice.getInvoicename());
//            ps.setString(2, invoice.getInvoicename());
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
//    public static void delete(Invoice invoice) {
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
//        PreparedStatement ps = null;
//
//        String query = "DELETE FROM "+TABLE+" "
//                + "WHERE invoicename = ?";
//        try {
//            ps = connection.prepareStatement(query);
//            ps.setString(1, invoice.getInvoicename());
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
//    public static boolean invoiceExists(String invoicename) {
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        String query = "SELECT * FROM "+TABLE+" "
//                + "WHERE invoicename = ?";
//        try {
//            ps = connection.prepareStatement(query);
//            ps.setString(1, invoicename);
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
//    public static boolean invoiceLogin(String invoicename,String password) {
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        String query = "SELECT * FROM "+TABLE+" "
//                + "WHERE invoicename = ? AND hashpassword = ?";
//        try {
//            ps = connection.prepareStatement(query);
//            ps.setString(1, invoicename);
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
//    public static ArrayList<Invoice> select(String invoicename) {
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        ArrayList<Invoice> invoiceList = new ArrayList<>();
//
//        String query = "SELECT * FROM "+TABLE+" "
//                + "WHERE invoicename = ?";
//        try {
//            ps = connection.prepareStatement(query);
//            ps.setString(1, invoicename);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                Invoice invoice = new Invoice(rs.getString("invoicename"));
//                invoice.setHashpassword(rs.getString("hashpassword"));
//                invoice.setSalt(rs.getString("salt"));
//                invoiceList.add(invoice);
//            }
//            return invoiceList;
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
