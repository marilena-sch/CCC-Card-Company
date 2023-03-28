package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eva Apple
 */

public class DB_Check {


    public static boolean CheckSellerID(String account_id) throws ClassNotFoundException {
        boolean valid = false;
        Connection con = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ccc", "root", "");
            stmt = con.createStatement();


            StringBuilder insQuery = new StringBuilder();

            insQuery.append("SELECT * FROM sellers ")
                    .append(" WHERE ")
                    .append(" account_id = ").append("'").append(account_id).append("';");

            stmt.execute(insQuery.toString());

            if (stmt.getResultSet().next() == true) {
                System.out.println("#DB: The member already exists");
                valid = true;
            }

        } catch (SQLException ex) {
            // Log exception
            Logger.getLogger(DB_Check.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // close connection
            closeDataBConnection(stmt, con);
        }

        return valid;
    }
    public static boolean CheckIndividualID(String account_id) throws ClassNotFoundException {
        boolean valid = false;
        Connection con = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ccc", "root", "");
            stmt = con.createStatement();


            StringBuilder insQuery = new StringBuilder();

            insQuery.append("SELECT * FROM individuals ")
                    .append(" WHERE ")
                    .append(" account_id = ").append("'").append(account_id).append("';");

            stmt.execute(insQuery.toString());

            if (stmt.getResultSet().next() == true) {
                System.out.println("#DB: The member already exists");
                valid = true;
            }

        } catch (SQLException ex) {
            // Log exception
            Logger.getLogger(DB_Check.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // close connection
            closeDataBConnection(stmt, con);
        }

        return valid;
    }

    public static boolean CheckCompanyID(String account_id) throws ClassNotFoundException {
        boolean valid = false;
        Connection con = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ccc", "root", "");
            stmt = con.createStatement();


            StringBuilder insQuery = new StringBuilder();

            insQuery.append("SELECT * FROM companies ")
                    .append(" WHERE ")
                    .append(" account_id = ").append("'").append(account_id).append("';");

            stmt.execute(insQuery.toString());

            if (stmt.getResultSet().next() == true) {
                System.out.println("#DB: The member already exists");
                valid = true;
            }

        } catch (SQLException ex) {
            // Log exception
            Logger.getLogger(DB_Check.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // close connection
            closeDataBConnection(stmt, con);
        }

        return valid;
    }

        public static boolean CheckEmployeeID(String employee_id) throws ClassNotFoundException {
        boolean valid = false;
        Connection con = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ccc", "root", "");
            stmt = con.createStatement();


            StringBuilder insQuery = new StringBuilder();

            insQuery.append("SELECT * FROM employees ")
                    .append(" WHERE ")
                    .append(" employee_id = ").append("'").append(employee_id).append("';");

            stmt.execute(insQuery.toString());

            if (stmt.getResultSet().next() == true) {
                System.out.println("#DB: The member already exists");
                valid = true;
            }

        } catch (SQLException ex) {
            // Log exception
            Logger.getLogger(DB_Check.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // close connection
            closeDataBConnection(stmt, con);
        }

        return valid;
    }
    
    public static void closeDataBConnection(Statement stmt, Connection con) {
        // Close connection
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(DB_Check.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DB_Check.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

