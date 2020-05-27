package data;

import java.sql.*;
import java.util.ArrayList;

public class CreditCardDB {
    private static final String TABLE = "Creditcard";
    
    public static int insert(CreditCard creditcard) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "INSERT INTO " + TABLE + " (firstname,lastname,CreditCardType,"
                + "CreditCardNumber,CreditCardExpirationDate,Invoice_invoiceID)"
                + "VALUES(?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, creditcard.getFirstname());
            ps.setString(2, creditcard.getLastname());
            ps.setString(3, creditcard.getCardtype());
            ps.setString(4, creditcard.getCardnumber());
            ps.setString(5, creditcard.getExpirationdate());
            ps.setString(6, creditcard.getInvoiceID());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

//    public static int update(CreditCard creditcard) {
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
//        PreparedStatement ps = null;
//
//        String query = "UPDATE "+TABLE+" SET "
//                + "product_price = ? "
//                + "WHERE product_id = ?";
//        try {
//            ps = connection.prepareStatement(query);
//            ps.setDouble(1, creditcard.getPrice());
//            ps.setString(2, creditcard.getId());
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
//    public static void delete(CreditCard creditcard) {
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
//        PreparedStatement ps = null;
//
//        String query = "DELETE FROM "+TABLE+" "
//                + "WHERE product_id = ?";
//        try {
//            ps = connection.prepareStatement(query);
//            ps.setString(1, creditcard.getId());
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
//    public static boolean creditcardExists(String id) {
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        String query = "SELECT * FROM "+TABLE+" "
//                + "WHERE product_id = ?";
//        try {
//            ps = connection.prepareStatement(query);
//            ps.setString(1, id);
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
//    public static ArrayList<CreditCard> select(String id) {
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        ArrayList<CreditCard> creditcardList = new ArrayList<>();
//
//        String query = "SELECT * FROM "+TABLE+" "
//                + "WHERE product_id = ?";
//        try {
//            ps = connection.prepareStatement(query);
//            ps.setString(1, id);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                CreditCard creditcard = new CreditCard(rs.getString("product_id"),
//                        rs.getDouble("product_price"),rs.getString("product_title"),
//                rs.getString("product_author"));
//                creditcard.setQuantity(rs.getInt("product_quantity"));
//                creditcard.setTotalprice(rs.getDouble("product_totalprice"));
//                creditcardList.add(creditcard);
//            }
//            return creditcardList;
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
