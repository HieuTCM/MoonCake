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
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Minh Hieu
 */
public class ViewListProductServlet extends HttpServlet {
    private final String MAIN_PAGE="moonCakeShop.jsp";
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
        String indexpage = request.getParameter("index");
        int endPage =0;
        if(indexpage == null)
        {
            indexpage = "1";
        }
        int index = Integer.parseInt(indexpage);
        try {
            ProductDAO proDAO = new ProductDAO();
            List<ProductDTO> proDTO = proDAO.getShop(index);
            request.setAttribute("PROLIST", proDTO);
            int size = proDAO.getCount();
            endPage = size/6;
            if (size%6 !=0)
            {
                endPage++;
            }
            CategoryDAO cateDAO = new CategoryDAO();
            cateDAO.getAllCate();
            List<CategoryDTO> cateDTO = cateDAO.getCateList();
            request.setAttribute("CATELIST", cateDTO);
            request.setAttribute("ENDPAGE", endPage);
            request.setAttribute("INDEX", "1");
        }
        catch(SQLException ex)
        {
            log("ViewListProductServlet _ SQL " + ex.getMessage());
        }
        catch (NamingException ex)
        {
            log("ViewListProductServlet _ Naming " + ex.getMessage());
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
