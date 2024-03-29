/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieutcm.tblOrder;

import hieutcm.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Minh Hieu
 */
public class OrderDAO implements Serializable{
    private OrderDTO orderDTO = new OrderDTO();
    public OrderDTO getOrder(){
        return orderDTO;
    }
    public void getOrderByID(int orderID) throws SQLException, NamingException 
    {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null)
            {
                String sql="SELECT OrderID, UserID, ContactName, Phonenumber, OrderDate, ShipAddress, Payments , PaymentsStatus, Total "
                        + " FROM [dbo].[tblOrders] WHERE OrderID = ? ";
                stm=con.prepareStatement(sql);
                stm.setInt(1, orderID);
                rs = stm.executeQuery();
                while(rs.next())
                {
                   int OrderID = rs.getInt("OrderID");
                   int UserID = rs.getInt("UserID");
                   String UserName = rs.getString("ContactName");
                   String Phone = rs.getString("Phonenumber");
                   String OrderDate = rs.getString("OrderDate");
                   String ShipAddress = rs.getString("ShipAddress");
                   String Payments = rs.getString("Payments");
                   int ID = rs.getInt("OrderID");
                   boolean PaymentsStatus = rs.getBoolean("PaymentsStatus");
                   float Total = rs.getFloat("Total");
                   
                   orderDTO = new OrderDTO(OrderID, UserID, UserName, Phone, OrderDate, ShipAddress, Payments, PaymentsStatus, Total);
                }
            }
        }
        finally {
            if (con != null)
            {
                con.close();
            }
            if (stm != null)
            {
                stm.close();
            }
            if(rs != null)
            {
                rs.close();
            }
        }
    }
    public boolean addOrderUserID(int userID, String userName,String userPhone, String address,String payments, boolean paymentStt, float total)
            throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null)
            {
                String sql="INSERT [dbo].[tblOrders] ([UserID], [ContactName], [Phonenumber], [ShipAddress], \n" +
                             "[Payments], [PaymentsStatus], [Total])\n" +
                             "VALUES (?, ?, ?, ?, ?, ?, ?)";
                 stm = con.prepareStatement(sql);
                 stm.setInt(1, userID);
                 stm.setString(2, userName);
                 stm.setString(3, userPhone);
                 stm.setString(4, address);
                 stm.setString(5, payments);
                 stm.setBoolean(6, paymentStt);
                 stm.setFloat(7, total);
                int effectRow = stm.executeUpdate();
                if (effectRow > 0) {
                return true;
                }
            }
        }
        finally {
            if (con != null)
            {
                con.close();
            }
            if (stm != null)
            {
                stm.close();
            }
            if(rs != null)
            {
                rs.close();
            }
        }
        return false;
    }
    
    public boolean addOrderGuest(String userName,String userPhone, String address,String payments, boolean paymentStt, float total) 
            throws SQLException, NamingException{
                Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null)
            {
                String sql="INSERT [dbo].[tblOrders] ([ContactName], [Phonenumber], [ShipAddress], \n" +
                             "[Payments], [PaymentsStatus], [Total])\n" +
                             "VALUES (?, ?, ?, ?, ?, ?)";
                 stm = con.prepareStatement(sql);
                 stm.setString(1, userName);
                 stm.setString(2, userPhone);
                 stm.setString(3, address);
                 stm.setString(4, payments);
                 stm.setBoolean(5, paymentStt);
                 stm.setFloat(6, total);
                int effectRow = stm.executeUpdate();
                if (effectRow > 0) {
                return true;
                }
            }
        }
        finally {
            if (con != null)
            {
                con.close();
            }
            if (stm != null)
            {
                stm.close();
            }
            if(rs != null)
            {
                rs.close();
            }
        }
        return false;
    }
    
    public int getLastOrderID() 
            throws SQLException, NamingException{
                Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int id = 0;
        try {
            con = DBHelper.makeConnection();
            if (con != null)
            {
                String sql="SELECT TOP 1 OrderID FROM [dbo].[tblOrders] ORDER BY OrderID DESC";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next())
                {
                    id = rs.getInt("OrderID");
                }
                
  
            }
        }
        finally {
            if (con != null)
            {
                con.close();
            }
            if (stm != null)
            {
                stm.close();
            }
            if(rs != null)
            {
                rs.close();
            }
        }
        return id;
    }
}
