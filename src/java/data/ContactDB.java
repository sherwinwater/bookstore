package data;

import java.sql.*;
import java.util.ArrayList;

public class ContactDB {
    private static final String TABLE = "contact";
    
    public static int insert(Contact contact) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "INSERT INTO " + TABLE + " (FirstName,LastName,Email,"
                + "CompanyName,Address1,Address2,City,State,Zip,Country)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, contact.getFirstName());
            ps.setString(2, contact.getLastName());
            ps.setString(3, contact.getEmail());
            ps.setString(4, contact.getCompanyName());
            ps.setString(5, contact.getAddress1());
            ps.setString(6, contact.getAddress2());
            ps.setString(7, contact.getCity());
            ps.setString(8, contact.getState());
            ps.setString(9, contact.getZip());
            ps.setString(10, contact.getCountry());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int update(Contact contact) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE "+TABLE+" SET "
                + "Email = ? "
                + "WHERE Email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, contact.getEmail());
            ps.setString(2, contact.getEmail());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static void delete(Contact contact) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM "+TABLE+" "
                + "WHERE email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, contact.getEmail());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static boolean contactExists(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM "+TABLE+" "
                + "WHERE Email = ?";
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

    public static ArrayList<Contact> select(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Contact> contactList = new ArrayList<>();

        String query = "SELECT * FROM "+TABLE+" "
                + "WHERE Email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                Contact contact = new Contact(rs.getString("Email"));
                contact.setFirstName(rs.getString("FirstName"));
                contact.setLastName(rs.getString("LastName"));
                contact.setEmail(rs.getString("Email"));
                contact.setCompanyName(rs.getString("CompanyName"));
                contact.setAddress1(rs.getString("Address1"));
                contact.setAddress2(rs.getString("Address2"));
                contact.setCity(rs.getString("City"));
                contact.setState(rs.getString("State"));
                contact.setZip(rs.getString("Zip"));
                contact.setCountry(rs.getString("Country"));
                contactList.add(contact);
            }
            return contactList;
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
