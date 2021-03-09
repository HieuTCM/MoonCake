/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Database.*;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author Minh Hieu
 */

public class ProductModel extends AbstractTableModel{
    ProductList pros = null;
    public ProductModel(ProductList pros){
        this.pros = pros;
    }
    public ProductList getPro(){
        return pros;
    }
    
    @Override
    public int getRowCount() {
        return pros.size();
    }

    @Override
    public int getColumnCount() {
       return 3;
    }
    @Override
    public String getColumnName(int column) {
        String columnName="";
        switch(column)
        {
            case 0: columnName = "Pro. Code";break;
            case 1: columnName = "Pro. Name";break;
            case 2: columnName = "Pro. Price";break;
        }
        return columnName;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Product pro = pros.get(row);
        Object obj = null;
        switch(column)
        {
            case 0: obj = pro.getID();break;
            case 1: obj = pro.getName();break;
            case 2: obj = pro.getPrice();break;
        }
        return obj;
    }
}
