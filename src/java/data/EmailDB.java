package data;

import java.sql.*;
import java.util.ArrayList;

public class EmailDB {
    private static final String TABLE = "email";
    
    public static int insert(Email emailer) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "INSERT INTO " + TABLE + " (firstname,lastname,"
                + "email)"
                + "VALUES(?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, emailer.getFirstname());
            ps.setString(2, emailer.getLastname());
            ps.setString(3, emailer.getEmail());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int update(Email emailer) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE "+TABLE+" SET "
                + "lastname = ? "
                + "WHERE firstname = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, emailer.getLastname());
            ps.setString(2, emailer.getFirstname());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static void delete(Email emailer) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM "+TABLE+" "
                + "WHERE firstname = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, emailer.getFirstname());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static boolean emailerExists(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM "+TABLE+" "
                + "WHERE email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
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

    public static ArrayList<Email> select(String firstname) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Email> emailerList = new ArrayList<>();

        String query = "SELECT * FROM "+TABLE+" "
                + "WHERE firstname = ?";
        int i = 0;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, firstname);
            rs = ps.executeQuery();
            while (rs.next()) {
                Email emailer = new Email(rs.getString("email"));
                emailer.setFirstname(rs.getString("firstname"));
                emailer.setLastname(rs.getString("lastname"));
                emailerList.add(emailer);
                System.out.println(++i);
            }
            return emailerList;
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
