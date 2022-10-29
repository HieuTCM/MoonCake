/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieutcm.servlet;

import hieutcm.tblCategory.CategoryDAO;
import hieutcm.tblCategory.CategoryDTO;
import hieutcm.tblProduct.ProductDAO;
import hieutcm.tblProduct.ProductDTO;
import hieutcm.tblSupplier.SupplierDAO;
import hieutcm.tblSupplier.SupplierDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "EditServlet", urlPatterns = {"/EditServlet"})
public class EditServlet extends HttpServlet {
    private final String EDIT_PAGE="editPage.jsp";

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
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        int id = Integer.parseInt(request.getParameter("txtID"));
        PrintWriter out = response.getWriter();
        String page = request.getParameter("index");
        String url = EDIT_PAGE;
        try {
            ProductDAO proDAO = new ProductDAO();
            proDAO.getProByID(id);
            ProductDTO proDTO = proDAO.getProDTO();
            request.setAttribute("PROLIST", proDTO);
            
            SupplierDAO supDAO = new SupplierDAO();
            supDAO.getAllSupplier();
            List<SupplierDTO> supDTO = supDAO.getSupList();
            request.setAttribute("SUPLIST", supDTO);
            
            CategoryDAO cateDAO  = new CategoryDAO();
            cateDAO.getAllCate();
            List<CategoryDTO> cateDTO = cateDAO.getCateList();
            request.setAttribute("CATELIST", cateDTO);
            
            request.setAttribute("INDEX", page);
        }
        catch(SQLException ex)
        {
            log("EditServlet _ SQL " + ex.getMessage());
        }
        catch (NamingException ex)
        {
            log("EditServlet _ Naming " + ex.getMessage());
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(EditServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParseException ex) {
            Logger.getLogger(EditServlet.class.getName()).log(Level.SEVERE, null, ex);
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
