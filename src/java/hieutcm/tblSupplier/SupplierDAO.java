/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieutcm.tblSupplier;

import hieutcm.utils.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Minh Hieu
 */
public class SupplierDAO {
    private List<SupplierDTO> supList;
    public  List<SupplierDTO> getSupList()  {
        return supList;
    }
    
    public  void getAllSupplier() 
            throws  SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try 
        {
            con = DBHelper.makeConnection();
            if (con != null)
            {
                String sql = "SELECT SupplierID, CompanyName, Address, Phone "
                        + "FROM tblSuppliers ";               
                stm = con.prepareStatement(sql);
                rs= stm.executeQuery();
                while (rs.next())
                {
                    int supID = rs.getInt("SupplierID");
                    String comName = rs.getString("CompanyName");
                    String Addr = rs.getString("Address");
                    String Phone = rs.getString("Phone");
                    SupplierDTO dto = new SupplierDTO(supID, comName, Addr, Phone);
                    if (this.supList == null)
                    {
                        this.supList = new ArrayList<>();
                    }
                    this.supList.add(dto);
                }
            }
        }
        finally
        {
            if (con != null)
            {
                con.close();
            }
            if (stm != null)
            {
                stm.close();
            }
            if (rs != null)
            {
                rs.close();
            }
        }
    }
        public int getSupID(String supName) throws SQLException, NamingException {
        int sID=0;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null)
            {
                String sql = "Select SupplierID "
                        + "FROM tblSuppliers  where CompanyName LIKE ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, supName);
                rs = stm.executeQuery();
                while (rs.next())
                {
                    sID = rs.getInt("SupplierID");
                }
            }
        }
        finally 
        {
            if (con != null)
            {
                con.close();
            }
            if (stm != null)
            {
                stm.close();
            }
            if (rs != null)
            {
                rs.close();
            }
        }
        return sID;
    }
}
