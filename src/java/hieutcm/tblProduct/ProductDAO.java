/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieutcm.tblProduct;

import hieutcm.tblCategory.CategoryDTO;
import hieutcm.utils.DBHelper;
import java.io.Serializable;
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
public class ProductDAO implements Serializable{
    private List<ProductDTO> proList;
    private ProductDTO proDTO =  new ProductDTO();
    public ProductDTO getProDTO(){
        return proDTO;
    }
    public List<ProductDTO> getProList(){
    return proList;
    }
    public void getAllProduct() throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBHelper.makeConnection();
            if (con != null)
            {
                String sql = "Select p.ProductID, p.ProductName, p.SupplierID, p.CategoryID, p.QuantityPerUnit, p.UnitPrice, p.Productimage, p.createDate, p.expirationDate,p.Status,p.isDelete, c.CategoryID, c.CategoryName, c.Description "
                        + "FROM tblProducts p INNER JOIN tblCategories c ON p.CategoryID = c.CategoryID \n";                
                stm=con.prepareStatement(sql);
                rs = stm.executeQuery();
                
                while (rs.next())
                {
                    ProductDTO dto = new ProductDTO();
                    dto.setProID(rs.getInt("ProductID"));
                    dto.setProName(rs.getString("ProductName"));
                    dto.setSupID(rs.getInt("SupplierID"));
                    dto.setCateID(rs.getInt("CategoryID"));
                    dto.setQuantity(rs.getInt("QuantityPerUnit"));
                    dto.setPrice(rs.getInt("UnitPrice"));
                    dto.setImage(rs.getString("Productimage"));
                    dto.setStatus(rs.getBoolean("Status"));
                    dto.setIsDelete(rs.getBoolean("isDelete"));
                    dto.setCraeteDate(rs.getString("createDate"));
                    dto.setExpirationDate(rs.getString("expirationDate"));
                    
                    CategoryDTO cateDTO = new CategoryDTO();
                    cateDTO.setCateID(rs.getInt("CategoryID"));
                    cateDTO.setCateName(rs.getString("CategoryName"));
                    cateDTO.setDescription(rs.getString("Description"));
                    
                    dto.setCate(cateDTO);

                     if (this.proList == null)
                     {
                         this.proList = new ArrayList<>();
                     }
                     this.proList.add(dto);
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
    public boolean deleteProduct(int id) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try{
            con = DBHelper.makeConnection();
            if (con != null)
            {
                String sql = " update tblProducts set isDelete = 1 where ProductID = ? ";
                stm=con.prepareStatement(sql);
                stm.setInt(1, id);
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
        }
        return false;
    }
    public int getCountSearch(String search) throws SQLException, NamingException{
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    ArrayList<ProductDTO> list = new ArrayList();
    
    int count = 0;
    try {
        con = DBHelper.makeConnection();
        String sql = "SELECT count(ProductID) "
                 + "FROM tblProducts p INNER JOIN tblCategories c ON p.CategoryID = c.CategoryID where p.isDelete = 0 and p.ProductName LIKE '%"+search+"%' ";
        stm = con.prepareStatement(sql);
        rs = stm.executeQuery();
        while (rs.next()) {
            count = rs.getInt(1);
        }
    } finally {
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
    
    return count;
}
    public void searchProduct(String search, int index) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null)
            {
                String sql = "Select p.ProductID, p.ProductName, p.SupplierID, p.CategoryID, p.QuantityPerUnit, p.UnitPrice, p.Productimage, p.createDate, p.expirationDate,p.Status,p.isDelete, c.CategoryID, c.CategoryName, c.Description "
                        + "FROM tblProducts p INNER JOIN tblCategories c ON p.CategoryID = c.CategoryID where p.isDelete = 0 and p.ProductName LIKE '%"+search+"%' "
                        + "order by ProductID \n" +
                        " offset "+(index-1)*6+" rows fetch next 6 rows only";
                stm=con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next())
                {
                    ProductDTO dto = new ProductDTO();
                    dto.setProID(rs.getInt("ProductID"));
                    dto.setProName(rs.getString("ProductName"));
                    dto.setSupID(rs.getInt("SupplierID"));
                    dto.setCateID(rs.getInt("CategoryID"));
                    dto.setQuantity(rs.getInt("QuantityPerUnit"));
                    dto.setPrice(rs.getInt("UnitPrice"));
                    dto.setImage(rs.getString("Productimage"));
                    dto.setStatus(rs.getBoolean("Status"));
                    dto.setIsDelete(rs.getBoolean("isDelete"));
                    dto.setCraeteDate(rs.getString("createDate"));
                    dto.setExpirationDate(rs.getString("expirationDate"));
                    
                    CategoryDTO cateDTO = new CategoryDTO();
                    cateDTO.setCateID(rs.getInt("CategoryID"));
                    cateDTO.setCateName(rs.getString("CategoryName"));
                    cateDTO.setDescription(rs.getString("Description"));
                    
                    dto.setCate(cateDTO);

                     if (this.proList == null)
                     {
                         this.proList = new ArrayList<>();
                     }
                     this.proList.add(dto);
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
     public int getCountProByCate(int id) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int size =0;
        try {
            con = DBHelper.makeConnection();
            if (con != null)
            {
                String sql = "Select count(ProductID) "
                        + "FROM tblProducts p INNER JOIN tblCategories c ON p.CategoryID = c.CategoryID where p.isDelete = 0 and c.CategoryID = ? " ;
                    
                stm=con.prepareStatement(sql);
                stm.setInt(1, id);
                rs = stm.executeQuery();
                
                while (rs.next())
                {
                   size = rs.getInt(1);
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
        return size;
    }
    public void getProByCate(int id, int index) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null)
            {
                String sql = "Select p.ProductID, p.ProductName, p.SupplierID, p.CategoryID, p.QuantityPerUnit, p.UnitPrice, p.Productimage, p.createDate, p.expirationDate,p.Status,p.isDelete, c.CategoryID, c.CategoryName, c.Description "
                        + "FROM tblProducts p INNER JOIN tblCategories c ON p.CategoryID = c.CategoryID where p.isDelete = 0 and c.CategoryID = ? order by ProductID \n" +
                    " offset ? rows fetch next 6 rows only";
                stm=con.prepareStatement(sql);
                stm.setInt(1, id);
                stm.setInt(2, (index-1)*6);
                rs = stm.executeQuery();
                
                while (rs.next())
                {
                    ProductDTO dto = new ProductDTO();
                    dto.setProID(rs.getInt("ProductID"));
                    dto.setProName(rs.getString("ProductName"));
                    dto.setSupID(rs.getInt("SupplierID"));
                    dto.setCateID(rs.getInt("CategoryID"));
                    dto.setQuantity(rs.getInt("QuantityPerUnit"));
                    dto.setPrice(rs.getInt("UnitPrice"));
                    dto.setImage(rs.getString("Productimage"));
                    dto.setStatus(rs.getBoolean("Status"));
                    dto.setIsDelete(rs.getBoolean("isDelete"));
                    dto.setCraeteDate(rs.getString("createDate"));
                    dto.setExpirationDate(rs.getString("expirationDate"));
                    
                    CategoryDTO cateDTO = new CategoryDTO();
                    cateDTO.setCateID(rs.getInt("CategoryID"));
                    cateDTO.setCateName(rs.getString("CategoryName"));
                    cateDTO.setDescription(rs.getString("Description"));
                    
                    dto.setCate(cateDTO);

                     if (this.proList == null)
                     {
                         this.proList = new ArrayList<>();
                     }
                     this.proList.add(dto);
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
    public void getProByID(int id) throws SQLException, NamingException
    {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null)
            {
                String sql = "Select p.ProductID, p.ProductName, p.SupplierID, p.CategoryID, p.QuantityPerUnit, p.UnitPrice, p.Productimage, "
                        + "p.createDate, p.expirationDate,p.Status,p.isDelete, p.lastUpdateDate , p.userIDUpdate , "
                        + "c.CategoryID, c.CategoryName, c.Description "
                        + "FROM tblProducts p INNER JOIN tblCategories c ON p.CategoryID = c.CategoryID where p.ProductID = ? ";
                stm=con.prepareStatement(sql);
                stm.setInt(1, id);
                rs = stm.executeQuery();
                
                while (rs.next())
                {
                    proDTO.setProID(rs.getInt("ProductID"));
                    proDTO.setProName(rs.getString("ProductName"));
                    proDTO.setSupID(rs.getInt("SupplierID"));
                    proDTO.setCateID(rs.getInt("CategoryID"));
                    proDTO.setQuantity(rs.getInt("QuantityPerUnit"));
                    proDTO.setPrice(rs.getInt("UnitPrice"));
                    proDTO.setImage(rs.getString("Productimage"));
                    proDTO.setStatus(rs.getBoolean("Status"));
                    proDTO.setIsDelete(rs.getBoolean("isDelete"));
                    proDTO.setCraeteDate(rs.getString("createDate"));
                    proDTO.setExpirationDate(rs.getString("expirationDate"));
                    proDTO.setUpdateDate(rs.getString("lastUpdateDate"));
                    proDTO.setUserID(rs.getInt("userIDUpdate"));
                    
                    CategoryDTO cateDTO = new CategoryDTO();
                    cateDTO.setCateID(rs.getInt("CategoryID"));
                    cateDTO.setCateName(rs.getString("CategoryName"));
                    cateDTO.setDescription(rs.getString("Description"));
                    
                    proDTO.setCate(cateDTO);
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
    public void getAllProductByIndex(int index) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBHelper.makeConnection();
            if (con != null)
            {
                String sql = "Select p.ProductID, p.ProductName, p.SupplierID, p.CategoryID, p.QuantityPerUnit, p.UnitPrice, p.Productimage, p.createDate, p.expirationDate,p.Status,p.isDelete, c.CategoryID, c.CategoryName, c.Description "
                        + "FROM tblProducts p INNER JOIN tblCategories c ON p.CategoryID = c.CategoryID order by ProductID \n" +
                    " offset ? rows fetch next 6 rows only";
                stm=con.prepareStatement(sql);
                stm.setInt(1, (index-1)*6);
                rs = stm.executeQuery();
                
                while (rs.next())
                {
                    ProductDTO dto = new ProductDTO();
                    dto.setProID(rs.getInt("ProductID"));
                    dto.setProName(rs.getString("ProductName"));
                    dto.setSupID(rs.getInt("SupplierID"));
                    dto.setCateID(rs.getInt("CategoryID"));
                    dto.setQuantity(rs.getInt("QuantityPerUnit"));
                    dto.setPrice(rs.getInt("UnitPrice"));
                    dto.setImage(rs.getString("Productimage"));
                    dto.setStatus(rs.getBoolean("Status"));
                    dto.setIsDelete(rs.getBoolean("isDelete"));
                    dto.setCraeteDate(rs.getString("createDate"));
                    dto.setExpirationDate(rs.getString("expirationDate"));
                    
                    CategoryDTO cateDTO = new CategoryDTO();
                    cateDTO.setCateID(rs.getInt("CategoryID"));
                    cateDTO.setCateName(rs.getString("CategoryName"));
                    cateDTO.setDescription(rs.getString("Description"));
                    
                    dto.setCate(cateDTO);

                     if (this.proList == null)
                     {
                         this.proList = new ArrayList<>();
                     }
                     this.proList.add(dto);
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
     public int getAllCount() throws SQLException, NamingException{
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    ArrayList<ProductDTO> list = new ArrayList();
    
    int count = 0;
    try {
        con = DBHelper.makeConnection();
        String sql = "SELECT count(ProductID) FROM tblProducts";
        stm = con.prepareStatement(sql);
        rs = stm.executeQuery();
        while (rs.next()) {
            count = rs.getInt(1);
        }
    } finally {
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
    
    return count;
}
    public int getCount() throws SQLException, NamingException{
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    ArrayList<ProductDTO> list = new ArrayList();
    
    int count = 0;
    try {
        con = DBHelper.makeConnection();
        String sql = "SELECT count(ProductID) FROM tblProducts where isDelete = 0 ";
        stm = con.prepareStatement(sql);
        rs = stm.executeQuery();
        while (rs.next()) {
            count = rs.getInt(1);
        }
    } finally {
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
    
    return count;
}
   public ArrayList<ProductDTO> getShop(int index) throws SQLException, NamingException{
       Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
    ArrayList<ProductDTO> list = new ArrayList();
    try {
        con = DBHelper.makeConnection();
        String sql = "Select p.ProductID, p.ProductName, p.SupplierID, p.CategoryID, p.QuantityPerUnit, p.UnitPrice, p.Productimage, p.createDate, p.expirationDate,p.Status,p.isDelete, c.CategoryID, c.CategoryName, c.Description "
                        + "FROM tblProducts p INNER JOIN tblCategories c ON p.CategoryID = c.CategoryID where p.isDelete = 0  order by ProductID \n" +
                    " offset ? rows fetch next 6 rows only";
    
        stm = con.prepareStatement(sql);
        stm.setInt(1, (index-1)*6);
        rs = stm.executeQuery();
        while (rs.next())
                {
                    ProductDTO dto = new ProductDTO();
                    dto.setProID(rs.getInt("ProductID"));
                    dto.setProName(rs.getString("ProductName"));
                    dto.setSupID(rs.getInt("SupplierID"));
                    dto.setCateID(rs.getInt("CategoryID"));
                    dto.setQuantity(rs.getInt("QuantityPerUnit"));
                    dto.setPrice(rs.getInt("UnitPrice"));
                    dto.setImage(rs.getString("Productimage"));
                    dto.setStatus(rs.getBoolean("Status"));
                    dto.setIsDelete(rs.getBoolean("isDelete"));
                    dto.setCraeteDate(rs.getString("createDate"));
                    dto.setExpirationDate(rs.getString("expirationDate"));
                    
                    CategoryDTO cateDTO = new CategoryDTO();
                    cateDTO.setCateID(rs.getInt("CategoryID"));
                    cateDTO.setCateName(rs.getString("CategoryName"));
                    cateDTO.setDescription(rs.getString("Description"));
                    
                    dto.setCate(cateDTO);

                     if (list == null)
                     {
                         list = new ArrayList<>();
                     }
                     list.add(dto);
                }
    } finally {
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
    return list;
    }
   public boolean updateProduct(ProductDTO dto, int ID) throws SQLException, NamingException{
       Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null)
            {
                int stt =0, delete = 0;
                if (dto.isStatus()!= false)
                {
                    stt=1;
                }else {stt=0;}
                if (dto.isIsDelete() != false){
                    delete=1;
                }else {delete=0;}
                String sql = "update tblProducts \n" +
                             "set [ProductName] = '"+dto.getProName()+"' , [SupplierID] = "+dto.getSupID()+" , [CategoryID] = "+dto.getCateID()+" ,[QuantityPerUnit] = "+dto.getQuantity()+" , \n" +
                             "[UnitPrice] = "+dto.getPrice()+" , [ProductImage] = '"+dto.getImage()+"' , [Status] = "+stt+" , [isDelete] = "+delete+" , [lastUpdateDate]= GETDATE() , [userIDUpdate] = "+ID
                            + " ,[createDate] = '"+dto.getCraeteDate()+"' , [expirationDate] = '"+dto.getExpirationDate()+"' \n" +
                             "Where [ProductID] = "+dto.getProID()+" ";
                stm = con.prepareStatement(sql);
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
   public void searchProductByPrice(int min, int max, int index) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null)
            {
                String sql = "Select p.ProductID, p.ProductName, p.SupplierID, p.CategoryID, p.QuantityPerUnit, p.UnitPrice, p.Productimage, p.createDate, p.expirationDate,p.Status,p.isDelete, c.CategoryID, c.CategoryName, c.Description "
                        + "FROM tblProducts p INNER JOIN tblCategories c ON p.CategoryID = c.CategoryID where p.isDelete = 0 and p.UnitPrice between ? and ? "
                        + "order by ProductID \n" +
                        " offset "+(index-1)*6+" rows fetch next 6 rows only";
                stm=con.prepareStatement(sql);
                stm.setInt(1, min);
                stm.setInt(2, max);
                rs = stm.executeQuery();
                while (rs.next())
                {
                    ProductDTO dto = new ProductDTO();
                    dto.setProID(rs.getInt("ProductID"));
                    dto.setProName(rs.getString("ProductName"));
                    dto.setSupID(rs.getInt("SupplierID"));
                    dto.setCateID(rs.getInt("CategoryID"));
                    dto.setQuantity(rs.getInt("QuantityPerUnit"));
                    dto.setPrice(rs.getInt("UnitPrice"));
                    dto.setImage(rs.getString("Productimage"));
                    dto.setStatus(rs.getBoolean("Status"));
                    dto.setIsDelete(rs.getBoolean("isDelete"));
                    dto.setCraeteDate(rs.getString("createDate"));
                    dto.setExpirationDate(rs.getString("expirationDate"));
                    
                    CategoryDTO cateDTO = new CategoryDTO();
                    cateDTO.setCateID(rs.getInt("CategoryID"));
                    cateDTO.setCateName(rs.getString("CategoryName"));
                    cateDTO.setDescription(rs.getString("Description"));
                    
                    dto.setCate(cateDTO);

                     if (this.proList == null)
                     {
                         this.proList = new ArrayList<>();
                     }
                     this.proList.add(dto);
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
   public int getCountSearchByPrice(int min, int max) throws SQLException, NamingException{
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    ArrayList<ProductDTO> list = new ArrayList();
    
    int count = 0;
    try {
        con = DBHelper.makeConnection();
        String sql = "SELECT count(ProductID) "
                 + "FROM tblProducts p INNER JOIN tblCategories c ON p.CategoryID = c.CategoryID where p.isDelete = 0 and p.UnitPrice between ? and ? ";
        stm = con.prepareStatement(sql);
        stm.setInt(1, min);
        stm.setInt(2, max);
        rs = stm.executeQuery();
        while (rs.next()) {
            count = rs.getInt(1);
        }
    } finally {
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
    
    return count;
}
   public boolean createProduct(ProductDTO dto, int ID) throws SQLException, NamingException{
       Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null)
            {
                int stt =0, delete = 0;
                if (dto.isStatus()!= false)
                {
                    stt=1;
                }else {stt=0;}
                if (dto.isIsDelete() != false){
                    delete=1;
                }else {delete=0;}
                String sql = "INSERT [dbo].[tblProducts] ([ProductName], [SupplierID], [CategoryID], [QuantityPerUnit], \n" +
                             "[UnitPrice], [ProductImage], [Status], [isDelete], [createDate], [expirationDate], [userIDUpdate]) \n" +
                             "VALUES (?, ?, ?, ?, ?, ?,"+stt+" , "+delete+", '"+dto.getCraeteDate()+"', '"+dto.getExpirationDate()+"',?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getProName());
                stm.setInt(2, dto.getSupID());
                stm.setInt(3, dto.getCateID());
                stm.setInt(4, dto.getQuantity());
                stm.setInt(5, dto.getPrice());
                stm.setString(6, dto.getImage());
                stm.setInt(7, ID);
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
   
}
