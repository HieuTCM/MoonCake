/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;
/**
 *
 * @author Minh Hieu
 */

public class ProductAccess extends DBAccess{
    final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    final String url ="jdbc:sqlserver://127.0.0.1\\SQLEXPRESS:1433;" + 
                        "databasename=Pe2020;user=sa;password=01687961788hieu";
    public ProductAccess()
    {
        super();
        connectDB(driver, url);
    }
}
