/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieutcm.servlet;

import hieutcm.cart.CartObj;
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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Minh Hieu
 */
@WebServlet(name = "BuyNowServlet", urlPatterns = {"/BuyNowServlet"})
public class BuyNowServlet extends HttpServlet {
    private final String INVALID="invalid.html";
    private final String VIEW_CART_PAGE = "viewCart.jsp";
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
        int id = Integer.parseInt(request.getParameter("txtProID"));
        int quantity = Integer.parseInt(request.getParameter("txtQuality"));
        String url = INVALID;
        try {
            
            HttpSession session = request.getSession();
            CartObj cart = (CartObj)session.getAttribute("CART");
            if (cart == null) {
                cart  = new CartObj();
            }
            cart.addQuantityItemToCart(id,quantity);
            session.setAttribute("CART", cart);
            int count =cart.getItems().size();
            session.setAttribute("COUNT", count);
            
            ProductDAO proDAO = new ProductDAO();
            proDAO.getAllProduct();
            List<ProductDTO> proDTO = proDAO.getProList();
            request.setAttribute("PROLIST", proDTO);
            int total =  0;
            if (cart != null) {
                for (ProductDTO x : proDTO)
                {
                  for(Integer key : cart.getItems().keySet())
                  {
                      if(x.getProID() == key)
                      {
                          total= total +(x.getPrice()*cart.getItems().get(key));
                      }
                  }
                }
                request.setAttribute("TOTAL", total);
            }
            
            url =VIEW_CART_PAGE;
        }
        catch(SQLException ex)
        {
            log("BuyNowServlet _ SQL " + ex.getMessage());
        }
        catch (NamingException ex)
        {
            log("BuyNowServlet _ Naming " + ex.getMessage());
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
