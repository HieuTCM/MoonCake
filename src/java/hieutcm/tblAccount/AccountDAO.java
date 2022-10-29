/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieutcm.tblAccount;

import hieutcm.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Minh Hieu
 */
public class AccountDAO implements Serializable{
    public boolean checkLogin (String userName, String password) 
            throws SQLException,NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null)
            {
                String sql = "Select Username "
                    + "From tblUser "
                    + "Where Username = ? And Password = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, userName);
                stm.setString(2, password);   
                rs = stm.executeQuery();
            
                if (rs.next())
                {
                    return true;
                }
            } 
        }
        finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null)
            {
                con.close();
                
            }
        }
        return false;
    }
    private List<AccountDTO> acc;
    public List<AccountDTO> getAccount() {
        return acc;
    }
    private AccountDTO accDTO;
    public AccountDTO getAccDTO(){
        return accDTO;
    }
    public boolean checkExitAcc (String username) 
            throws SQLException,NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null)
            {
                String sql = "Select Username "
                    + "From tblUser "
                    + "Where Username = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                rs = stm.executeQuery();
                if (rs.next())
                {
                    return true;
                }
            } 
        }
        finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null)
            {
                con.close();
                
            }
        }
        return false;
    }
    public boolean Register (AccountDTO dto) 
            throws SQLException, NamingException
    {
        Connection con =  null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
        con = DBHelper.makeConnection();
        if (con !=null)
        {
            boolean check = checkExitAcc(dto.getUsername());
                if (check)
                {
                    return false;
                }else 
                {
            String sql = "INSERT into tblUser ([Username], [Password], [Fullname], [Email],[Phone]) VALUES (?, ?, ?, ?, ?)";
            stm = con.prepareStatement(sql);
            stm.setString(1, dto.getUsername());
            stm.setString(2, dto.getPassword());
            stm.setString(3, dto.getFullname());
            stm.setString(4, dto.getEmail());
            stm.setString(5, dto.getPhone());
            int effectRow = stm.executeUpdate();
                if (effectRow > 0) {
                return true;
                }
                }
        }
        }
        finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null)
            {
                con.close();
                
            }
        }
        return false;
    }
    public boolean updAccount (AccountDTO dto) 
            throws SQLException, NamingException
    {
        Connection con =  null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
        con = DBHelper.makeConnection();
        if (con !=null)
        {
            
            String sql = "Update tblUser set  [Fullname] = ? , [Email] = ? ,[Phone]= ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, dto.getFullname());
            stm.setString(2, dto.getEmail());
            stm.setString(3, dto.getPhone());
            int effectRow = stm.executeUpdate();
                if (effectRow > 0) {
                return true;
                }
                
        }
        }
        finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null)
            {
                con.close();
                
            }
        }
        return false;
    }
    public void getAccountByID(int id) throws SQLException, NamingException{
        Connection con =  null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
        con = DBHelper.makeConnection();
        if (con !=null)
        {
            String sql = "Select UserID, Username, Password, FullName,Email,Phone,Role  "
                    + "From tblUser "
                    + "Where UserID = ?";
            stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while(rs.next()){
                int ID = rs.getInt("UserID");
                String User = rs.getString("Username");
                String pass= rs.getString("Password");
                String name = rs.getString("FullName");
                String mail = rs.getString("Email");
                String phone = rs.getString("phone");
                boolean role = rs.getBoolean("Role");
                if (this.accDTO == null)
                {
                    accDTO = new AccountDTO();
                }
                accDTO.setID(ID);
                accDTO.setUsername(User);
                accDTO.setPassword(pass);
                accDTO.setFullname(name);
                accDTO.setEmail(mail);
                accDTO.setPhone(phone);
                accDTO.setRole(role);
            }
        }
        }
        finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null)
            {
                con.close();
                
            }
        }
    }
    public void Account(String username, String password) throws SQLException, NamingException{
        Connection con =  null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
        con = DBHelper.makeConnection();
        if (con !=null)
        {
            String sql = "Select UserID, Username, Password, FullName,Email,Phone,Role  "
                    + "From tblUser "
                    + "Where Username = ? And Password = ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            rs = stm.executeQuery();
            while(rs.next()){
                int ID = rs.getInt("UserID");
                String User = rs.getString("Username");
                String pass= rs.getString("Password");
                String name = rs.getString("FullName");
                String mail = rs.getString("Email");
                String phone = rs.getString("phone");
                boolean role = rs.getBoolean("Role");
                if (this.accDTO == null)
                {
                    accDTO = new AccountDTO();
                }
                accDTO.setID(ID);
                accDTO.setUsername(User);
                accDTO.setPassword(pass);
                accDTO.setFullname(name);
                accDTO.setEmail(mail);
                accDTO.setPhone(phone);
                accDTO.setRole(role);
            }
        }
        }
        finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null)
            {
                con.close();
                
            }
        }
    }
}
