/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
@WebServlet(name = "OpenCompanyAcc", urlPatterns = {"/OpenCompanyAcc"})
public class OpenCompanyAcc extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            String account_id = request.getParameter("account_id");
            int account_ID = Integer.parseInt(account_id);
            String name = request.getParameter("name");
            String exp_date = request.getParameter("exp_date");
            String account_limit = request.getParameter("account_limit");
            int account_lim = Integer.parseInt(account_limit);
            String current_d = request.getParameter("current_d");
            int curr_debt = Integer.parseInt(current_d);
            String available_b = request.getParameter("available_b");
            int available_bal = Integer.parseInt(available_b);
            String password = request.getParameter("password");
            String employee_name = request.getParameter("employee_name");
            String employee_surname = request.getParameter("employee_surname");
            String employee_id = request.getParameter("employee_id");

            Connection con = null;
            Statement stmt = null;
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ccc", "root", "");
            stmt = con.createStatement();
            System.out.println("Database is connected !");

            String insertcompanies
                    = "INSERT INTO companies (account_id,firstname,expiration_date,account_limit,current_debt,available_balance,password)"
                    + "VALUES ('" + account_ID + "','" + name + "','" + exp_date + "','" + account_lim + "','" + curr_debt + "','" + available_bal + "','" + password + "')";
            stmt.executeUpdate(insertcompanies);
            
            String  insertemployees
                    = "INSERT INTO employees (employee_id,account_id,firstname,lastname)"
                    + "VALUES ('"+ employee_id  +"','" + account_ID+ "','" + employee_name + "','" + employee_surname + "')";
            stmt.executeUpdate(insertemployees);

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
            Logger.getLogger(OpenCompanyAcc.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OpenCompanyAcc.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(OpenCompanyAcc.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OpenCompanyAcc.class.getName()).log(Level.SEVERE, null, ex);
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
