/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieutcm.tblCategory;

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
public class CategoryDAO {
    private List<CategoryDTO> cateList;
    public List<CategoryDTO> getCateList() {
        return cateList;
    }
    public CategoryDTO getCateByID(int id) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
         CategoryDTO dto = new CategoryDTO();
        try {
            con = DBHelper.makeConnection();
            if (con != null)
            {
                String sql = "Select CategoryID, CategoryName, Description "
                        + "FROM tblCategories where CategoryID ="+id;
                
                stm = con.prepareStatement(sql);
                
                rs = stm.executeQuery();
                while (rs.next())
                {
                    int CateID = rs.getInt("CategoryID");
                    String CateName = rs . getString("CategoryName");
                    String Discription = rs.getString("Description");
                    dto.setCateID(CateID);
                    dto.setCateName(CateName);
                    dto.setDescription(Discription);
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
        return dto;
    }
        public void getAllCate() throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
         
        try {
            con = DBHelper.makeConnection();
            if (con != null)
            {
                String sql = "Select CategoryID, CategoryName, Description "
                        + "FROM tblCategories ";
                
                stm = con.prepareStatement(sql);
                
                rs = stm.executeQuery();
                while (rs.next())
                {
                    int CateID = rs.getInt("CategoryID");
                    String CateName = rs . getString("CategoryName");
                    String Discription = rs.getString("Description");
                    CategoryDTO dto = new CategoryDTO(CateID, CateName, Discription);
                    if (this.cateList == null)
                    {
                        this.cateList = new ArrayList<>();
                    }
                    this.cateList.add(dto);
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
    public int getCateID(String cateName) throws SQLException, NamingException {
        int cID=0;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null)
            {
                String sql = "Select CategoryID "
                        + "FROM tblCategories  where CategoryName LIKE ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, cateName);
                rs = stm.executeQuery();
                while (rs.next())
                {
                    cID = rs.getInt("CategoryID");
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
        return cID;
    }
}
