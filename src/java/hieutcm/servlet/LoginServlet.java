/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieutcm.servlet;

import hieutcm.tblAccount.AccountDAO;
import hieutcm.tblAccount.AccountDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Minh Hieu
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    private final String INVALID_PAGE = "invalid.html";
    private final String MAIN_PAGE = "DispatchServlet";
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = INVALID_PAGE;
        String username = request.getParameter("txtUserName");
        String password = request.getParameter("txtPassword");
        PrintWriter out = response.getWriter();
        try {
                 AccountDAO dao = new AccountDAO();
                 boolean result = dao.checkLogin(username, password);
                 if (result)
                 {
                    dao.Account(username, password);
                    AccountDTO dto = dao.getAccDTO();
                    String fullname = dto.getFullname();
                     url = MAIN_PAGE;
                     //write cookie
                     Cookie cookie = new Cookie(username, password);
                     cookie.setMaxAge(60*1);
                     response.addCookie(cookie);
                    // write session
                     HttpSession session = request.getSession();
                     session.setAttribute("AccDTO", dto);
                     session.setAttribute("NAME", fullname);
                     session.removeAttribute("FAIL");
                     if(dto.isRole())
                     {
                         session.removeAttribute("CART");
                     }
                     
                 }else {
                     String fail = "FAIL";
                     HttpSession session = request.getSession();
                     session.setAttribute("FAIL", fail);
                     url = MAIN_PAGE;
                 }
           
        }
        catch(SQLException ex)
        {
            log("LoginServlet _ SQL " + ex.getMessage());
        }
        catch (NamingException ex)
        {
            log("LoginServlet _ Naming " + ex.getMessage());
        }
         finally{ 
            response.sendRedirect(url);
            out.close(); }
        
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
        processRequest(request, response);
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
        processRequest(request, response);
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
