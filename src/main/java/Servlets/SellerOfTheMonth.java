    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import static database.DB_Check.closeDataBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Eleni-Maria
 */
@WebServlet(name = "SellerOfTheMonth", urlPatterns = {"/SellerOfTheMonth"})
public class SellerOfTheMonth extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Connection con = null;
            Statement stmt = null;
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ccc", "root", "");
            stmt = con.createStatement();
            System.out.println("Database is connected !");
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime previous_month = now.minusMonths(1);
            int month = previous_month.getMonthValue();
            int year = previous_month.getYear();
            int debt_seller=0;
            double new_debt=0;
            
            String month_seller = "SELECT *, COUNT(seller_id) AS NumberOfTransactions FROM transactions WHERE month ='" + month + "' AND year='" + year + "' AND transaction_type=? GROUP BY seller_id ORDER BY NumberOfTransactions DESC LIMIT 1";
            PreparedStatement statement_monthseller = con.prepareStatement(month_seller);
            statement_monthseller.setString(1, "Xrewsh");
            ResultSet result_monthseller = statement_monthseller.executeQuery();
            
            String res1 = "<h2>Έμπορος του Μήνα:</h2>"
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
            while (result_monthseller.next()) {
                String account_id = result_monthseller.getString("seller_id");
                
                String best_seller = "SELECT * FROM sellers WHERE account_id = ? ";
                PreparedStatement statement_bestseller = con.prepareStatement(best_seller);
                statement_bestseller.setString(1, account_id);
                ResultSet result_bestseller = statement_bestseller.executeQuery();
                
                debt_seller = result_bestseller.getInt("current_debt");
                new_debt = debt_seller - (debt_seller*0.05);
                
                String update_sellers
                        ="UPDATE sellers SET current_debt=? WHERE account_id=?";
                PreparedStatement statement_update_sellers=con.prepareStatement(update_sellers);
                statement_update_sellers.setDouble(1,new_debt);
                statement_update_sellers.setString(2,account_id);
                ResultSet result_update_sellers = statement_update_sellers.executeQuery();
                stmt.executeUpdate(update_sellers);
                
                int acc_id = result_update_sellers.getInt("Account-ID");
                String first_name = result_update_sellers.getString("First Name");
                String last_name = result_update_sellers.getString("Last Name");
                int seller_fee = result_update_sellers.getInt("Seller Fee");
                int profit = result_update_sellers.getInt("Profit");
                int current_debt = result_update_sellers.getInt("Current Debt");
                
                str1 = str1 + "<td>" + acc_id + "</td>"
                        + "<td>" + first_name + "</td>"
                        + "<td>" + last_name + "</td>"
                        + "<td>" + seller_fee + "</td>"
                        + "<td>" + profit + "</td>"
                        + "<td>" + current_debt + "</td>"
                        + "</tr>";
            }
            res1 = res1 + str1 + "</table>" + "\n";
            out.println(res1);
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
            Logger.getLogger(SellerOfTheMonth.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SellerOfTheMonth.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SellerOfTheMonth.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SellerOfTheMonth.class.getName()).log(Level.SEVERE, null, ex);
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
