package data;

import java.sql.*;
import java.util.ArrayList;

public class UserDB {
    private static final String TABLE = "user";
    
    public static int insert(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "INSERT INTO " + TABLE + " (firstname,lastname,"
                + "password,email)"
                + "VALUES(?,?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getFirstname());
            ps.setString(2, user.getLastname());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getEmail());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int update(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE "+TABLE+" SET "
                + "lastname = ? "
                + "WHERE firstname = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getLastname());
            ps.setString(2, user.getFirstname());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static void delete(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM "+TABLE+" "
                + "WHERE firstname = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getFirstname());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static boolean userExists(String email) {
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

    public static ArrayList<User> select(String firstname) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<User> userList = new ArrayList<>();

        String query = "SELECT * FROM "+TABLE+" "
                + "WHERE firstname = ?";
        int i = 0;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, firstname);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getString("email"));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setPassword(rs.getString("password"));
                userList.add(user);
                System.out.println(++i);
            }
            return userList;
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
