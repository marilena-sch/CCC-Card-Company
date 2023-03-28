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
public class PayOff extends HttpServlet {

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
            String id_transaction = request.getParameter("id_transaction");
            String id_customer = request.getParameter("id_customer");
            String pay_amount = request.getParameter("pay_amount");
            int payoff_amount = Integer.parseInt(pay_amount);
            String year_trans = request.getParameter("year_trans");
            String month_trans = request.getParameter("month_trans");
            String day_trans = request.getParameter("day_trans");
            String type_trans =request.getParameter("type_trans");
            String user_check=request.getParameter("user_check");
            int available_individual=0;
            int available_company=0;
            int available_seller=0;
            int debt_individual=0;
            int debt_company=0;
            int debt_seller=0;
            int result1=0;
            int result2=0;
            int result3=0;
            int result4=0;
            int result5=0;
            int result6=0;
            
            Connection con = null;
            Statement stmt = null;
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ccc", "root", "");
            stmt = con.createStatement();
            System.out.println("Database is connected !");
            
            if(type_trans.equals("Xrewsh")){
                if(user_check.equals("Company")){
                    if (DB_Check.CheckCompanyID(id_customer)==false) {
                        response.setStatus(400);
                    }else{
                        String id_companies= "SELECT * FROM companies WHERE account_id=? AND expiration_date>= CURDATE() ";
                        PreparedStatement statement_id_companies = con.prepareStatement(id_companies);
                        statement_id_companies.setString(1, id_customer);
                        ResultSet result_id_companies = statement_id_companies.executeQuery();
                        
                        while(result_id_companies.next()){
                            String companies_id = result_id_companies.getString("account_id");
                            
                            String insert_transactions
                                    = "INSERT INTO transactions(transaction_id,costumer_id,seller_id,day,month,year,transaction_amount,transaction_type,employee_id)"
                                    + "VALUES ('" + id_transaction + "','" + companies_id + "','" + '0' + "','" + day_trans + "', '" + month_trans + "','" + year_trans + "','" + pay_amount + "','" + type_trans  + "','" + '0' + "')";
                            stmt.executeUpdate(insert_transactions);
                            
                            String new_transaction = "SELECT * FROM transactions WHERE transaction_id = ? ";
                            PreparedStatement statement_transaction = con.prepareStatement(new_transaction);
                            statement_transaction.setString(1, id_transaction);
                            ResultSet result_transaction = statement_transaction.executeQuery();
                            
                            String str = "<h2>Νέα Πληρωμή:</h2>"
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
                            
                            available_company=result_id_companies.getInt("available_balance");
                            debt_company=result_id_companies.getInt("current_debt");
                            result1 = debt_company-payoff_amount;
                            result4 = available_company+payoff_amount;

                            String update_companies
                                    ="UPDATE companies SET current_debt=? AND available_balance=? WHERE account_id=?";
                            PreparedStatement statement_update_companies=con.prepareStatement(update_companies);
                            statement_update_companies.setInt(1,result4);
                            statement_update_companies.setInt(2,result1);
                            statement_update_companies.setString(3,companies_id);
                            stmt.executeUpdate(update_companies);
                            
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
                                        + "</tr>";
                            }
                            str = str + str1 + "</table>" + "\n";
                            out.println(str);
                        }
                    }
                }else if(user_check.equals("Individual")){
                    if (DB_Check.CheckIndividualID(id_customer)==false) {
                        response.setStatus(400);
                    }else{
                        String id_individuals = "SELECT * FROM individuals WHERE account_id=? AND expiration_date>= CURDATE() ";
                        PreparedStatement statement_id_individuals = con.prepareStatement(id_individuals);
                        statement_id_individuals.setString(1, id_customer);
                        ResultSet result_id_individuals = statement_id_individuals.executeQuery();
                        
                        while(result_id_individuals.next()){
                            String individuals_id = result_id_individuals.getString("account_id");
                            
                            String insert_transactions
                                    = "INSERT INTO transactions(transaction_id,costumer_id,seller_id,day,month,year,transaction_amount,transaction_type,employee_id)"
                                    + "VALUES ('" + id_transaction + "','" + individuals_id + "','" + '0' + "','" + day_trans + "', '" + month_trans + "','" + year_trans + "','" + pay_amount + "','" + type_trans  + "','" + '0' + "')";
                            stmt.executeUpdate(insert_transactions);
                            
                            String new_transaction = "SELECT * FROM transactions WHERE transaction_id = ? ";
                            PreparedStatement statement_transaction = con.prepareStatement(new_transaction);
                            statement_transaction.setString(1, id_transaction);
                            ResultSet result_transaction = statement_transaction.executeQuery();
                            
                            String str2 = "<h2>Νέα Πληρωμή:</h2>"
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
                            
                            debt_individual=result_id_individuals.getInt("current_debt");
                            available_individual=result_id_individuals.getInt("available_balance");
                            result2 = debt_individual-payoff_amount;
                            result4 = available_individual+payoff_amount;

                            String update_individuals
                                    ="UPDATE individuals SET current_debt=? AND available_balance=? WHERE account_id=?";
                            PreparedStatement statement_update_individuals=con.prepareStatement(update_individuals);
                            statement_update_individuals.setInt(1,result2);
                            statement_update_individuals.setInt(2,result4);
                            statement_update_individuals.setString(3,individuals_id);
                            stmt.executeUpdate(update_individuals);
                            
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
                                        + "</tr>";
                            }
                            str2 = str2 + str3 + "</table>" + "\n";
                            out.println(str2);
                        }
                    }
                }else if(user_check.equals("Seller")){
                    if (DB_Check.CheckSellerID(id_customer)==false) {
                        response.setStatus(400);
                    }else{
                        String id_sellers = "SELECT * FROM sellers WHERE account_id=? ";
                        PreparedStatement statement_id_sellers = con.prepareStatement(id_sellers);
                        statement_id_sellers.setString(1, id_customer);
                        ResultSet result_id_sellers = statement_id_sellers.executeQuery();
                        
                        while(result_id_sellers.next()){
                            String sellers_id = result_id_sellers.getString("account_id");
                            
                            String insert_transactions
                                    = "INSERT INTO transactions(transaction_id,costumer_id,seller_id,day,month,year,transaction_amount,transaction_type,employee_id)"
                                    + "VALUES ('" + id_transaction + "','" + '0' +"','" + sellers_id +  "','" + day_trans + "', '" + month_trans + "','" + year_trans + "','" + pay_amount + "','" + type_trans  + "','" + '0' + "')";
                            stmt.executeUpdate(insert_transactions);
                            
                            String new_transaction = "SELECT * FROM transactions WHERE transaction_id = ? ";
                            PreparedStatement statement_transaction = con.prepareStatement(new_transaction);
                            statement_transaction.setString(1, id_transaction);
                            ResultSet result_transaction = statement_transaction.executeQuery();
                            
                            String str4 = "<h2>Νέα Πληρωμή:</h2>"
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
                            String str5 = "";
                            
                            debt_seller=result_id_sellers.getInt("current_debt");
                            available_seller=result_id_sellers.getInt("available_balance");
                            result3 = debt_seller-payoff_amount;
                            result6 = available_seller+payoff_amount;

                            String update_sellers
                                    ="UPDATE individuals SET current_debt=? AND available_balance=? WHERE account_id=?";
                            PreparedStatement statement_update_sellers=con.prepareStatement(update_sellers);
                            statement_update_sellers.setInt(1,result3);
                            statement_update_sellers.setInt(2,result6);
                            statement_update_sellers.setString(3,sellers_id);
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
                                
                                str5 = str5 + "<td>" + newtransaction_id + "</td>\n"
                                        + "<td>" + newcustomer_id + "</td>\n"
                                        + "<td>" + newseller_id + "</td>\n"
                                        + "<td>" + new_day + "</td>\n"
                                        + "<td>" + new_month + "</td>\n"
                                        + "<td>" + new_year + "</td>\n"
                                        + "<td>" + newtransaction_amount + "</td>\n"
                                        + "<td>" + newtransaction_type + "</td>\n"
                                        + "</tr>";
                            }
                            str4 = str4 + str5 + "</table>" + "\n";
                            out.println(str4);
                        }
                    }
                }
            }else{
                System.out.println("Transaction is not debit,it can't be done.");
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
            Logger.getLogger(PayOff.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PayOff.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PayOff.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PayOff.class.getName()).log(Level.SEVERE, null, ex);
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
