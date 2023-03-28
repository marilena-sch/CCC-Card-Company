/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import database.DB_Check;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Eva Apple
 */
@WebServlet(name = "Questions", urlPatterns = {"/Questions"})
public class Questions extends HttpServlet {

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
            String question_id = request.getParameter("question_id");

            Connection con = null;
            Statement stmt = null;
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ccc", "root", "");
            stmt = con.createStatement();
            System.out.println("Database is connected !");

            if (DB_Check.CheckCompanyID(question_id)==false) {
                response.setStatus(400);
            }else{
                String transaction_company= "SELECT * FROM transactions WHERE customer_id=?";
                PreparedStatement statement_transaction_company = con.prepareStatement(transaction_company);
                statement_transaction_company.setString(1, question_id);
                ResultSet result_transaction_company = statement_transaction_company.executeQuery();


                    String str = "<h2>Συναλλαγές</h2>"
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
                    String str1= "";

                while(result_transaction_company.next()){
                    int transaction_id = result_transaction_company.getInt("Transaction-ID");
                    int customer_id = result_transaction_company.getInt("Customer-ID");
                    int seller_id = result_transaction_company.getInt("Seller-ID");
                    String day = result_transaction_company.getString("Day");
                    String month = result_transaction_company.getString("Month");
                    String year = result_transaction_company.getString("Year");
                    int trans_amount=result_transaction_company.getInt("Transaction amount");
                    String trans_type = result_transaction_company.getString("Transaction type");
                    int employee_id=result_transaction_company.getInt("Transaction employee-ID");

                    str1 = str1 + "<td>" + transaction_id + "</td>"
                            + "<td>" + customer_id + "</td>"
                            + "<td>" + seller_id + "</td>"
                            + "<td>" + day + "</td>"
                            + "<td>" + month + "</td>"
                            + "<td>" + year + "</td>"
                            + "<td>" + trans_amount + "</td>"
                            + "<td>" + trans_type + "</td>"
                            + "<td>" + employee_id + "</td>"
                            + "</tr>";

                }
                str = str + str1 + "</table>" + "\n";
                out.println(str);
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
            Logger.getLogger(Questions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Questions.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Questions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Questions.class.getName()).log(Level.SEVERE, null, ex);
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
