package Servlets;

import database.DB_Check;
import static database.DB_Check.closeDataBConnection;

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

@WebServlet(name = "CloseAcc", urlPatterns = {"/CloseAcc"})
public class CloseAcc extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String account_id = request.getParameter("close_account_id");

            Connection con = null;
            Statement stmt = null;
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ccc", "root", "");
            stmt = con.createStatement();
            System.out.println("Database is connected !");
            
            
            if (DB_Check.CheckCompanyID(account_id) == true){
                PreparedStatement deletecompanies = con.prepareStatement("DELETE FROM companies WHERE current_debt=0 AND account_id=?");
                deletecompanies.setString(1, account_id);
                int i = deletecompanies.executeUpdate();
                
                if (i > 0) {
                   out.println("Company successfully removed...");
                }
            }else if(DB_Check.CheckSellerID(account_id) == true){
                PreparedStatement deletesellers = con.prepareStatement("DELETE FROM sellers WHERE current_debt=0 AND account_id=?");
                deletesellers.setString(1, account_id);
                int j = deletesellers.executeUpdate();
                
                if (j > 0) {
                    out.println("Seller successfully removed...");
                }
            }else if(DB_Check.CheckIndividualID(account_id) == true){
                PreparedStatement deleteindividuals = con.prepareStatement("DELETE FROM individuals WHERE current_debt=0 AND account_id=?");
                deleteindividuals.setString(1, account_id);
                int k = deleteindividuals.executeUpdate();
                
                if (k > 0) {
                    out.println("Individual successfully removed...");
                }
            }else{
                response.setStatus(400);
            }
                
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CloseAcc.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CloseAcc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CloseAcc.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CloseAcc.class.getName()).log(Level.SEVERE, null, ex);
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