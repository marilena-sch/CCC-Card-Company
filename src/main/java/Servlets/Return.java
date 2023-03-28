/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import database.DB_Check;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Eleni-Maria
 */
public class Return extends HttpServlet {

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
            String trans_id = request.getParameter("trans_id");
            String cust_id = request.getParameter("cust_id");
            String sell_id = request.getParameter("sell_id");
            String amount_trans = request.getParameter("amount_trans");
            int return_amount = Integer.parseInt(amount_trans);
            String transaction_year = request.getParameter("transaction_year");
            String transaction_month = request.getParameter("transaction_month");
            String transaction_day = request.getParameter("transaction_day");
            String transaction_type=request.getParameter("transaction_type");
            String cust_check=request.getParameter("cust_check");
            int available_individual=0;
            int debt_individual=0;
            int available_company=0;
            int debt_company=0;
            int profit_seller=0;
            int result1=0;
            int result2=0;
            int result3=0;
            int result4=0;

            Connection con = null;
            Statement stmt = null;
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ccc", "root", "");
            stmt = con.createStatement();
            System.out.println("Database is connected !");

            if(transaction_type.equals("Pistwsh")){
                if(cust_check.equals("Company")){
                    if (DB_Check.CheckSellerID(sell_id) == false||DB_Check.CheckCompanyID(cust_id)==false) {
                        response.setStatus(400);
                    }
                    else{
                        String id_companies= "SELECT * FROM companies WHERE account_id=? AND expiration_date>= CURDATE() ";
                        PreparedStatement statement_id_companies = con.prepareStatement(id_companies);
                        statement_id_companies.setString(1, cust_id);
                        ResultSet result_id_companies = statement_id_companies.executeQuery();

                        String id_seller="SELECT * FROM sellers WHERE account_id=? AND profit>=?";
                        PreparedStatement statement_id_sellers= con.prepareStatement(id_seller);
                        statement_id_sellers.setString(1,sell_id);
                        statement_id_sellers.setString(2, amount_trans);
                        ResultSet result_id_sellers=statement_id_sellers.executeQuery();

                        while(result_id_companies.next()){
                            String companies_id = result_id_companies.getString("account_id");
                            String seller_ID=result_id_sellers.getString("account_id");
                            available_company=result_id_companies.getInt("available_balance");
                            debt_company=result_id_companies.getInt("current_debt");
                            profit_seller=result_id_sellers.getInt("profit");
                            result1=available_company+return_amount;
                            result2=profit_seller-return_amount;
                            result3=debt_company-return_amount;

                            String insert_transactions
                                    = "INSERT INTO transactions(transaction_id,costumer_id,seller_id,day,month,year,transaction_amount,transaction_type,employee_id)"
                                    + "VALUES ('" + trans_id + "','" + companies_id + "','" +seller_ID+ "','" + transaction_day + "', '" + transaction_month + "','" + transaction_year + "','" + amount_trans + "','" + transaction_type  + "','" + '0' + "')";
                            stmt.executeUpdate(insert_transactions);

                            String new_transaction = "SELECT * FROM transactions WHERE transaction_id = ? ";
                            PreparedStatement statement_transaction = con.prepareStatement(new_transaction);
                            statement_transaction.setString(1, trans_id);
                            ResultSet result_transaction = statement_transaction.executeQuery();

                            String str = "<h2>Νέα Επιστροφή:</h2>"
                                    + "<table class=\"table table-striped\">" + "\n"
                                    + "<th>Transaction-ID</th>\n"
                                    + "<th>Customer-ID</th>\n"
                                    + "<th>Seller-ID</th>\n"
                                    + "<th>Day</th>\n"
                                    + "<th>Month</th>\n"
                                    + "<th>Year</th>\n"
                                    + "<th>Transaction amount</th>\n"
                                    + "<th>Transaction type</th>\n"
                                    + "<th>Transaction employee-ID</th>\n"
                                    + "</tr><tr>\n";
                            String str1 = "";

                            String update_companies
                                    ="UPDATE companies SET current_debt=? AND available_balance=? WHERE account_id=?";
                            PreparedStatement statement_update_companies=con.prepareStatement(update_companies);
                            statement_update_companies.setInt(1,result3);
                            statement_update_companies.setInt(2,result1);
                            statement_update_companies.setString(3,companies_id);
                            stmt.executeUpdate(update_companies);

                            String update_sellers
                                    ="UPDATE sellers SET profit=? WHERE account_id=?";
                            PreparedStatement statement_update_sellers=con.prepareStatement(update_sellers);
                            statement_update_sellers.setInt(1,result2);
                            statement_update_sellers.setString(2,seller_ID);
                            stmt.executeUpdate(update_sellers);

                            while(result_transaction.next()){
                                int newtransaction_id = result_transaction.getInt("Transaction-ID");
                                String newcustomer_id = result_transaction.getString("Customer-ID");
                                String newseller_id = result_transaction.getString("Seller-ID");
                                int new_day = result_transaction.getInt("Day");
                                int new_month = result_transaction.getInt("Month");
                                int new_year = result_transaction.getInt("Year");
                                String newtransaction_amount = result_transaction.getString("Transaction amount");
                                String newtransaction_type = result_transaction.getString("Transaction type");

                                str1 = str1 + "<td>" + newtransaction_id + "</td>\n"
                                        + "<td>" + newcustomer_id + "</td>\n"
                                        + "<td>" + newseller_id + "</td>\n"
                                        + "<td>" + new_day + "</td>\n"
                                        + "<td>" + new_month + "</td>\n"
                                        + "<td>" + new_year + "</td>\n"
                                        + "<td>" + newtransaction_amount + "</td>\n"
                                        + "<td>" + newtransaction_type + "</td>\n"
                                        + "<td>" + "0" + "</td>\n"
                                        + "</tr>";
                            }
                            str = str + str1 + "</table>" + "\n";
                            out.println(str);
                        }
                    }
                }else if(cust_check.equals("Individual")){
                    if(DB_Check.CheckSellerID(sell_id) == false||DB_Check.CheckIndividualID(cust_id)==false) {
                        response.setStatus(400);
                    }
                    else{
                        String id_individuals = "SELECT * FROM individuals WHERE account_id=? AND expiration_date>= CURDATE()";
                        PreparedStatement statement_id_individuals = con.prepareStatement(id_individuals);
                        statement_id_individuals.setString(1, cust_id);
                        ResultSet result_id_individuals = statement_id_individuals.executeQuery();

                        String id_seller="SELECT account_id FROM sellers WHERE account_id=? AND profit>=?";
                        PreparedStatement statement_id_sellers= con.prepareStatement(id_seller);
                        statement_id_sellers.setString(1,sell_id);
                        statement_id_sellers.setString(2, amount_trans);
                        ResultSet result_id_sellers=statement_id_sellers.executeQuery();

                        while(result_id_individuals.next()){
                            String individual_id = result_id_individuals.getString("account_id");
                            String seller_ID=result_id_sellers.getString("account_id");
                            available_individual=result_id_individuals.getInt("available_balance");
                            debt_individual=result_id_individuals.getInt("current_debt");
                            profit_seller=result_id_sellers.getInt("profit");
                            result1=available_individual+return_amount;
                            result2=profit_seller-return_amount;
                            result4=debt_individual-return_amount;

                            String insert_transactions
                                    = "INSERT INTO transactions(transaction_id,costumer_id,seller_id,day,month,year,transaction_amount,transaction_type,employee_id)"
                                    + "VALUES ('" + trans_id + "','" + individual_id + "','" +seller_ID+ "','" + transaction_day + "', '" + transaction_month + "','" + transaction_year + "','" + amount_trans + "','" + transaction_type  + "','" + '0' + "')";
                            stmt.executeUpdate(insert_transactions);

                            String new_transaction = "SELECT * FROM transactions WHERE transaction_id = ? ";
                            PreparedStatement statement_transaction = con.prepareStatement(new_transaction);
                            statement_transaction.setString(1, trans_id);
                            ResultSet result_transaction = statement_transaction.executeQuery();

                            String str2 = "<h2>Νέα Επιστροφή:</h2>"
                                    + "<table class=\"table table-striped\">" + "\n"
                                    + "<th>Transaction-ID</th>\n"
                                    + "<th>Customer-ID</th>\n"
                                    + "<th>Seller-ID</th>\n"
                                    + "<th>Day</th>\n"
                                    + "<th>Month</th>\n"
                                    + "<th>Year</th>\n"
                                    + "<th>Transaction amount</th>\n"
                                    + "<th>Transaction type</th>\n"
                                    + "<th>Transaction employee-ID</th>\n"
                                    + "</tr><tr>\n";
                            String str3 = "";

                            String update_individual
                                    ="UPDATE companies SET current_debt=? AND available_balance=? WHERE account_id=?";
                            PreparedStatement statement_update_individuals=con.prepareStatement(update_individual);
                            statement_update_individuals.setInt(1,result4);
                            statement_update_individuals.setInt(2,result1);
                            statement_update_individuals.setString(3,individual_id);
                            stmt.executeUpdate(update_individual);

                            String update_sellers
                                    ="UPDATE sellers SET profit=? WHERE account_id=?";
                            PreparedStatement statement_update_sellers=con.prepareStatement(update_sellers);
                            statement_update_sellers.setInt(1,result2);
                            statement_update_sellers.setString(2,seller_ID);
                            stmt.executeUpdate(update_sellers);

                            while(result_transaction.next()){
                                int newtransaction_id = result_transaction.getInt("Transaction-ID");
                                String newcustomer_id = result_transaction.getString("Customer-ID");
                                String newseller_id = result_transaction.getString("Seller-ID");
                                int new_day = result_transaction.getInt("Day");
                                int new_month = result_transaction.getInt("Month");
                                int new_year = result_transaction.getInt("Year");
                                String newtransaction_amount = result_transaction.getString("Transaction amount");
                                String newtransaction_type = result_transaction.getString("Transaction type");

                                str3 = str3 + "<td>" + newtransaction_id + "</td>\n"
                                        + "<td>" + newcustomer_id + "</td>\n"
                                        + "<td>" + newseller_id + "</td>\n"
                                        + "<td>" + new_day + "</td>\n"
                                        + "<td>" + new_month + "</td>\n"
                                        + "<td>" + new_year + "</td>\n"
                                        + "<td>" + newtransaction_amount + "</td>\n"
                                        + "<td>" + newtransaction_type + "</td>\n"
                                        + "<td>" + "0" + "</td>\n"
                                        + "</tr>";
                            }
                            str2 = str2 + str3 + "</table>" + "\n";
                            out.println(str2);
                        }
                    }
                }
            }else{
                System.out.println("Transaction is not credit,it can't be done.");
            }
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
            Logger.getLogger(Return.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Return.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Return.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Return.class.getName()).log(Level.SEVERE, null, ex);
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
