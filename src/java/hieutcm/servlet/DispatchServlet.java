/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieutcm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {
    private final String MAIN_PAGE = "moonCakeShop.jsp";
    private final String SHOW_PRODUCT= "ViewListProductServlet";
    private final String LOGIN_PAGE="login.html";
    private final String LOGIN_CONTROLLER="LoginServlet";
    private final String REGISTER_CONTROLLER="RegisterControllerServlet";
    private final String CHANGE_PAHE_CONTROLLER="ChangePageServlet";
    private final String CHANGE_PAGE_MANAGER_CONTROLLER="ChangePageManagerServlet";
    private final String SEARCH_BY_CATEGORY="SearchByCateServlet";
    private final String VIEW_DETAIL_PRODUCT="ViewDetailProductServlet";
    private final String SEARCH_CONTROLLER="SearchServlet";
    private final String MANAGE_CONTROLLER="ManageProductServlet";
    private final String DELETE_CONTROLLER="DeleteServlet";
    private final String EDIT_PAGE="EditServlet";
    private final String EDIT_CONTROLLER="EditControllerServlet";
    private final String SEARCH_BY_PRICE_CONTROLLER="SearchByPriceServlet";
    private final String ADD_PAGE="AddServlet";
    private final String ADD_CONTROLLER="AddControllerServlet";
    private final String ADD_TO_CART_CONTROLLER="AddToCartServlet";
    private final String ADD_TO_CART_CONTROLLER2="AddToCartViewPageServlet";
    private final String BUY_NOW_CONTROLLER="BuyNowServlet";
    private final String VIEW_CART_CONTROLLER="ViewCartServlet";
    private final String UPDATE_CART_CONTROLLER="UpdateCartServlet";
    private final String DELETE_PRO_FROM_CART_CONTROLLER="DeleteProFromCartServlet";
    private final String PURCHASE_PAGE="PurchaseServlet";
    private final String PURCHASE_CONTROLLER="PurchaseControllerServlet";
    private final String ORDER_TRACKING_CONTROLLER="OrderTrackingControllerServlet";
    private final String UPDATE_INFORMATION_CONTROLLER="UpdateInformationServlet";
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
        String button = request.getParameter("btAction");
        String url = MAIN_PAGE;
        try{
            if (button == null)
            {
                url= SHOW_PRODUCT;
            }
            else if (button.equals("change"))
            {
                url= SHOW_PRODUCT;
            }
            else if (button.equals("Register"))
            {
                url= REGISTER_CONTROLLER;
            }
            else if(button.equals("login"))
            {
                url=LOGIN_PAGE;
            }
            else if(button.equals("Login"))
            {
                url=LOGIN_CONTROLLER;
            }
            else if(button.equals("changePage"))
            {
                url=CHANGE_PAHE_CONTROLLER;
            } 
            else if(button.equals("searchByCate"))
            {
                url=SEARCH_BY_CATEGORY;
            }else if (button.equals("detail")){
                url=VIEW_DETAIL_PRODUCT;
            } else if (button.equals("Search")){
                url=SEARCH_CONTROLLER;
            }else if (button.equals("manage")){
                url = MANAGE_CONTROLLER;
            }else if (button.equals("Delete"))
            {
                url= DELETE_CONTROLLER;
            }else if (button.equals("Edit"))
            {
                url=EDIT_PAGE;
            }else if(button.equals("Update"))
            {
                url=EDIT_CONTROLLER;
            }else if (button.equals("managerChange"))
            {
                url=CHANGE_PAGE_MANAGER_CONTROLLER;
            }else if (button.equals("SearchByPrice"))
            {
                url =SEARCH_BY_PRICE_CONTROLLER;
            }else if (button.equals("AddProduct"))
            {
                url =ADD_PAGE;
            }
            else if (button.equals("Create"))
            {
                url =ADD_CONTROLLER;
            }
            else if (button.equals("Order"))
            {
                url =ADD_TO_CART_CONTROLLER;
            }
            else if (button.equals("Order2"))
            {
                url =ADD_TO_CART_CONTROLLER2;
            }
            else if (button.equals("BuyNow"))
            {
                url =BUY_NOW_CONTROLLER;
            }
            else if (button.equals("show"))
            {
                url =VIEW_CART_CONTROLLER;
            }
            else if (button.equals("UpdateCart"))
            {
                url =UPDATE_CART_CONTROLLER;
            }
            else if (button.equals("DeleteProductFromCart"))
            {
                url =DELETE_PRO_FROM_CART_CONTROLLER;
            }else if (button.equals("OrderYourCard"))
            {
                url =PURCHASE_PAGE;
            }else if (button.equals("Purchase"))
            {
                url =PURCHASE_CONTROLLER;
            }else if (button.equals("OrderTracking"))
            {
                url =ORDER_TRACKING_CONTROLLER;
            }
            else if (button.equals("UpdateProfile"))
            {
                url =UPDATE_INFORMATION_CONTROLLER;
            }
                    
            
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
