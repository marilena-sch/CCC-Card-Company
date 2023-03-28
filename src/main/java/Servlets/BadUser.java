package Servlets;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static database.DB_Check.closeDataBConnection;

/**
 *
 * @author Eva Apple
 */

@WebServlet(name = "BadUser", urlPatterns = {"/BadUser"})
public class BadUser extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            Connection con = null;
            Statement stmt = null;
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ccc", "root", "");
            stmt = con.createStatement();
            System.out.println("Database is connected !");

            String baduser_seller = "SELECT * FROM sellers WHERE current_debt>0 ORDER BY current_debt DESC";
            PreparedStatement statement_badseller = con.prepareStatement(baduser_seller);
            ResultSet result_badseller = statement_badseller.executeQuery();

            String baduser_companies = "SELECT * FROM companies WHERE current_debt>0 ORDER BY current_debt DESC";
            PreparedStatement statement_badcompanies = con.prepareStatement(baduser_companies);
            ResultSet result_badcompanies = statement_badcompanies.executeQuery();

            String baduser_individuals = "SELECT * FROM individuals WHERE current_debt>0 ORDER BY current_debt DESC";
            PreparedStatement statement_badindividuals = con.prepareStatement(baduser_individuals);
            ResultSet result_badindividuals = statement_badindividuals.executeQuery();

            String res1 = "<h2>Κακοί Χρήστες: Έμποροι</h2>"
                    + "<table class=\"table table-striped\">" + "\n"
                    + "<tr>\n"
                    + "<th>Account-ID</th>"
                    + "<th>First Name</th>"
                    + "<th>Last Name</th>"
                    + "<th>Seller Fee</th>"
                    + "<th>Profit</th>"
                    + "<th>Current Debt</th>"
                    + " </tr><tr>";
            String str1 = "";
            while (result_badseller.next()) {
                int account_id = result_badseller.getInt("Account-ID");
                String first_name = result_badseller.getString("First Name");
                String last_name = result_badseller.getString("Last Name");
                int seller_fee = result_badseller.getInt("Seller Fee");
                int profit = result_badseller.getInt("Profit");
                int current_debt = result_badseller.getInt("Current Debt");
                
                str1 = str1 + "<td>" + account_id + "</td>"
                        + "<td>" + first_name + "</td>"
                        + "<td>" + last_name + "</td>"
                        + "<td>" + seller_fee + "</td>"
                        + "<td>" + profit + "</td>"
                        + "<td>" + current_debt + "</td>"
                        + " </tr>";

            }
            res1 = res1 + str1 + "</table>" + "\n";
            out.println(res1);

            String res2 = "<h2>Κακοί Χρήστες: Εταιρίες</h2>"
                    + "<table class=\"table table-striped\">" + "\n"
                    + "<tr>\n"
                    + "<th>Account-ID</th>"
                    + "<th>First Name</th>"
                    + "<th>Last Name</th>"
                    + "<th>Expiration Date</th>"
                    + "<th>Account Limit</th>"
                    + "<th>Current Debt</th>"
                    + "<th>Available Balance</th>"
                    + " </tr><tr>";
            String str2 = "";

            while (result_badcompanies.next()) {
                int Account_id = result_badcompanies.getInt("Account-ID");
                String firstname = result_badcompanies.getString("First Name");
                String lastname = result_badcompanies.getString("Last Name");
                String expiration_date = result_badcompanies.getString("Expiration Date");
                int account_limit = result_badcompanies.getInt("Account Limit");
                int currentdebt = result_badcompanies.getInt("Current Debt");
                int available_balance = result_badcompanies.getInt("Available Balance");

                str2 = str2 + "<td>" + Account_id + "</td>"
                        + "<td>" + firstname + "</td>"
                        + "<td>" + lastname + "</td>"
                        + "<td>" + expiration_date + "</td>"
                        + "<td>" + account_limit + "</td>"
                        + "<td>" + currentdebt + "</td>"
                        + "<td>" + available_balance + "</td>"
                        + " </tr>";

            }
            res2 = res2 + str2 + "</table>" + "\n";
            out.println(res2);

            String res3 = "<h2>Κακοί Χρήστες: Ιδιώτες</h2>"
                    + "<table class=\"table table-striped\">" + "\n"
                    + "<tr>\n"
                    + "<th>Account-ID</th>"
                    + "<th>First Name</th>"
                    + "<th>Last Name</th>"
                    + "<th>Expiration Date</th>"
                    + "<th>Account Limit</th>"
                    + "<th>Current Debt</th>"
                    + "<th>Available Balance</th>"
                    + " </tr><tr>";
            String str3 = "";

            while (result_badindividuals.next()) {
                int account_ID = result_badindividuals.getInt("Account-ID");
                String First_name = result_badindividuals.getString("First Name");
                String Last_name = result_badindividuals.getString("Last Name");
                String Expiration_date = result_badindividuals.getString("Expiration Date");
                int Account_limit = result_badindividuals.getInt("Account Limit");
                int Current_debt = result_badindividuals.getInt("Current Debt");
                int Available_balance = result_badindividuals.getInt("Available Balance");

                str3 = str3 + "<td>" + account_ID + "</td>"
                        + "<td>" + First_name + "</td>"
                        + "<td>" + Last_name + "</td>"
                        + "<td>" + Expiration_date + "</td>"
                        + "<td>" + Account_limit + "</td>"
                        + "<td>" + Current_debt + "</td>"
                        + "<td>" + Available_balance + "</td>"
                        + " </tr>";

            }
            res3 = res3 + str3 + "</table>" + "\n";
            out.println(res3);
            closeDataBConnection(null, con);
        }


    }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BadUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BadUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BadUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BadUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}


