/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;
import database.DB_Check;

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

/**
 *
 * @author Eva Apple
 */
@WebServlet(name = "Purchase", urlPatterns = {"/Purchase"})
public class Purchase extends HttpServlet {

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
            String transaction_id = request.getParameter("transaction_id");
            String customer_id = request.getParameter("customer_id");
            String seller_id = request.getParameter("seller_id");
            String amount = request.getParameter("amount");
            int purchase_amount = Integer.parseInt(amount);
            String trans_year = request.getParameter("trans_year");
            String trans_month = request.getParameter("trans_month");
            String trans_day = request.getParameter("trans_day");
            String trans_type=request.getParameter("trans_type");
            String customer_check=request.getParameter("customer_check");
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


            if(trans_type.equals("Xrewsh")){
                if(customer_check.equals("Company")){
                    if (DB_Check.CheckSellerID(seller_id) == false||DB_Check.CheckEmployeeID(customer_id)==false) {
                        response.setStatus(400);
                    }else{
                        String id_employee= "SELECT * FROM employees WHERE employee_id=?";
                        PreparedStatement statement_id_employee = con.prepareStatement(id_employee);
                        statement_id_employee.setString(1, customer_id);
                        ResultSet result_id_employee = statement_id_employee.executeQuery();

                        String id_seller="SELECT * FROM sellers WHERE account_id=?";
                        PreparedStatement statement_id_sellers= con.prepareStatement(id_seller);
                        statement_id_sellers.setString(1,seller_id);
                        ResultSet result_id_sellers=statement_id_sellers.executeQuery();

                        while(result_id_employee.next()){
                            String account_id = result_id_employee.getString("account_id");
                            
                            String id_company = "SELECT * FROM companies WHERE account_id = ? AND expiration_date>= CURDATE()"
                                +"AND available_balance>=?";
                            PreparedStatement statement_id_company = con.prepareStatement(id_company);
                            statement_id_company.setString(1, account_id);
                            statement_id_company.setString(2,amount);
                            ResultSet result_id_company = statement_id_company.executeQuery();
                        
                            String seller_ID=result_id_sellers.getString("account_id");
                            available_company=result_id_company.getInt("available_balance");
                            debt_company=result_id_company.getInt("current_debt");
                            profit_seller=result_id_sellers.getInt("profit");
                            result1=available_company-purchase_amount;
                            result2=profit_seller+purchase_amount;
                            result3=debt_company+purchase_amount;
                        
                            String insert_transactions
                                    = "INSERT INTO transactions(transaction_id,costumer_id,seller_id,day,month,year,transaction_amount,transaction_type,employee_id)"
                                    + "VALUES ('" + transaction_id + "','" + id_company + "','" +seller_ID+ "','" + trans_day + "', '" + trans_month + "','" + trans_year + "','" + amount + "','" + trans_type + "','"+ customer_id+"')";
                            stmt.executeUpdate(insert_transactions);
                            
                            String new_transaction = "SELECT * FROM transactions WHERE transaction_id = ? ";
                            PreparedStatement statement_transaction = con.prepareStatement(new_transaction);
                            statement_transaction.setString(1, transaction_id);
                            ResultSet result_transaction = statement_transaction.executeQuery();
                            
                            String str = "<h2>Νέα Αγορά:</h2>"
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
                            statement_update_companies.setString(3,id_company);
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
                                        + "</tr>";
                            }
                            str = str + str1 + "</table>" + "\n";
                            out.println(str);
                        }
                       
                    }
                }else if(customer_check.equals("Individual")){
                    if(DB_Check.CheckSellerID(seller_id) == false||DB_Check.CheckIndividualID(customer_id)==false) {
                        response.setStatus(400);
                    }else {
                        String id_individuals = "SELECT * FROM individuals WHERE account_id=? AND expiration_date>= CURDATE()"
                                +"AND available_balance>=?";
                        PreparedStatement statement_id_individuals = con.prepareStatement(id_individuals);
                        statement_id_individuals.setString(1, customer_id);
                        statement_id_individuals.setString(2,amount);
                        ResultSet result_id_individuals = statement_id_individuals.executeQuery();

                        String id_seller="SELECT account_id FROM sellers WHERE account_id=?";
                        PreparedStatement statement_id_sellers= con.prepareStatement(id_seller);
                        statement_id_sellers.setString(1,seller_id);
                        ResultSet result_id_sellers=statement_id_sellers.executeQuery();

                        while(result_id_individuals.next()){
                            String individual_id = result_id_individuals.getString("account_id");
                            String seller_ID=result_id_sellers.getString("account_id");
                            available_individual=result_id_individuals.getInt("available_balance");
                            debt_individual=result_id_individuals.getInt("current_debt");
                            profit_seller=result_id_sellers.getInt("profit");
                            result1=available_individual-purchase_amount;
                            result2=profit_seller+purchase_amount;
                            result4=debt_individual+purchase_amount;

                            String insert_transactions
                                    = "INSERT INTO transactions(transaction_id,costumer_id,seller_id,day,month,year,transaction_amount,transaction_type,employee_id)"
                                    + "VALUES ('" + transaction_id + "','" + individual_id + "','" +seller_ID+ "','" + trans_day + "', '" + trans_month + "','" + trans_year + "','" + amount + "','" + trans_type + "','"+'0'+"')";
                            stmt.executeUpdate(insert_transactions);
                            
                            String new_transaction = "SELECT * FROM transactions WHERE transaction_id = ? ";
                            PreparedStatement statement_transaction = con.prepareStatement(new_transaction);
                            statement_transaction.setString(1, transaction_id);
                            ResultSet result_transaction = statement_transaction.executeQuery();
                            
                            String str2 = "<h2>Νέα Αγορά:</h2>"
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
            Logger.getLogger(Purchase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Purchase.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Purchase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Purchase.class.getName()).log(Level.SEVERE, null, ex);
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
