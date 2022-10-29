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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@WebServlet(name = "EditControllerServlet", urlPatterns = {"/EditControllerServlet"})
public class EditControllerServlet extends HttpServlet {
    private final String MANAGE_CONTROLLER="ManageProductServlet";
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
        String url = MANAGE_CONTROLLER;
        String page = request.getParameter("index");
        PrintWriter out = response.getWriter();
        String txtID = request.getParameter("txtID");
        System.out.println(txtID);
        String txtName = request.getParameter("txtName");
        String txtSupName = request.getParameter("txtSupName");
        String txtCateName = request.getParameter("txtCateName");
        String txtQuantity = request.getParameter("txtQuantity");
        String txtPrice = request.getParameter("txtPrice");
        String txtImage = request.getParameter("txtImage");
        String img = request.getParameter("file");
        String txtStatus = request.getParameter("txtStatus");
        String txtDelete = request.getParameter("txtDelete");
        String txtCreate = request.getParameter("txtCreate");
        String txtExp = request.getParameter("txtExp");
        String txtUserID = request.getParameter("txtUserID");
        try  {
            SupplierDAO supDAO = new SupplierDAO();
            CategoryDAO cateDAO = new CategoryDAO();
            ProductDAO proDAO = new ProductDAO();
            int userID = Integer.parseInt(txtUserID);
            int proID = Integer.parseInt(txtID);
            int cateID = cateDAO.getCateID(txtCateName);
            int supID = supDAO.getSupID(txtSupName);
            int quantity = Integer.parseInt(txtQuantity);
            int price = Integer.parseInt(txtPrice);
            CategoryDTO cateDTO = cateDAO.getCateByID(cateID);
            String signal="";
            boolean status = false;
            if (txtStatus.equals("Avaliable"))
            {
                status = true;
            }else {status = false;}
            boolean isDelete = false;
            if (txtDelete.equals("No"))
            {
                isDelete = false;
            }else {isDelete = true;}
            if (!img.isEmpty())
            {
                txtImage = img;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = sdf.parse(txtCreate); 
            Date date2 = sdf.parse(txtExp);
            int check = date1.compareTo(date2);
            if (check <=0)
            {
               ProductDTO proDTO = new ProductDTO(proID, txtName, supID, cateID, quantity, price, txtImage, status, isDelete, txtCreate, txtExp, cateDTO);
                boolean rs = proDAO.updateProduct(proDTO,userID);
                
                if (rs)
                {
                    signal="successfull";
                }else{
                    signal="fail";
                } 
            }else {
                signal="failDate";
            }
            request.setAttribute("INDEX", page);
            request.setAttribute("SIGNAL", signal);
        }
        catch(SQLException ex)
        {
            log("EditControllerServlet _ SQL " + ex.getMessage());
        }
        catch (NamingException ex)
        {
            log("EditControllerServlet _ Naming " + ex.getMessage());
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
            Logger.getLogger(EditControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(EditControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
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
