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
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Minh Hieu
 */
@WebServlet(name = "RegisterControllerServlet", urlPatterns = {"/RegisterControllerServlet"})
public class RegisterControllerServlet extends HttpServlet {

    private final String INVALID_PAGE = "invalid.html";
    private final String MAIN_PAGE = "ViewListProductServlet";
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
        PrintWriter out = response.getWriter();
        String url = MAIN_PAGE;
        String username = request.getParameter("txtUserName");
        String password = request.getParameter("txtPassword");
        String fullName = request.getParameter("txtFulname");
        String Email = request.getParameter("txtEmail");
        String txtPhone = request.getParameter("txtPhone");
        try {
            AccountDAO dao = new AccountDAO();
            AccountDTO dto = new AccountDTO();
            dto.setUsername(username);
            dto.setPassword(password);
            dto.setFullname(fullName);
            dto.setEmail(Email);
            dto.setPhone(txtPhone);
            boolean result = dao.Register(dto);
            if(result)
            {
                request.setAttribute("REGISTER","success");
            }
            else {
                request.setAttribute("REGISTER2","fail");
            }
        }
        catch(SQLException ex)
        {
            log("RegisterServlet _ SQL " + ex.getMessage());
        }
        catch (NamingException ex)
        {
            log("RegisterServlet _ Naming " + ex.getMessage());
        }
        finally
        {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
