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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Minh Hieu
 */
@WebServlet(name = "UpdateInformationServlet", urlPatterns = {"/UpdateInformationServlet"})
public class UpdateInformationServlet extends HttpServlet {
    private final String UPDATE_PROFILE_PAGE="editProfile.jsp";
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
        String txtUserID = request.getParameter("txtUserID");
        String txtUsername = request.getParameter("txtUser");
        String txtFullname = request.getParameter("txtName");
        String txtEmail = request.getParameter("txtEmail");
        String txtPhone = request.getParameter("txtPhone");
        String url = UPDATE_PROFILE_PAGE;
        try {
            HttpSession session = request.getSession();
            AccountDTO dto = new AccountDTO();
            dto.setID(Integer.parseInt(txtUserID));
            dto.setUsername(txtUsername);
            dto.setFullname(txtFullname);
            dto.setEmail(txtEmail);
            dto.setPhone(txtPhone);
            AccountDAO dao = new AccountDAO();
            boolean result = dao.updAccount(dto);
            if (result)
            {
                dao.getAccountByID(Integer.parseInt(txtUserID));
                dto = dao.getAccDTO();
                String fullname = dto.getFullname();
                session.removeAttribute("NAME");
                session.removeAttribute("AccDTO");
                session.setAttribute("NAME", fullname);
                session.setAttribute("AccDTO", dto);
                if (session.getAttribute("AccDTO") != null)
                    {
                        AccountDTO accDTO = (AccountDTO) session.getAttribute("AccDTO");
                        request.setAttribute("AccDTO", accDTO);
                    }
                request.setAttribute("Success", "success");
            }else 
            {
                request.setAttribute("fail", "fail");
            }
             
        }
        catch(Exception e)
        {
            
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
