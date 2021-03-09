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
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;


public class ProductList extends Vector<Product>{
    public ProductList(){
        super();
    }
    public int find(int ID){
        for(int i=0; i<this.size(); i++){
            if(ID == this.get(i).getID())   return i;
        }
        return -1;
    }
    public Product findProduct(int ID){
        int i = find(ID);
        return i<0? null : this.get(i);
    }
    public void loadFromDB(DBAccess dbObj){
        int ID;
        String name;
        float price;
        String sql = "select * from Product";
        try {
            ResultSet rs = dbObj.excuteQuery(sql);
            while(rs.next()){
                ID = rs.getInt(1);
                name = rs.getString(2);
                price = rs.getFloat(3);
                Product pro = new Product(ID, name, price);
                this.add(pro);
            }
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
