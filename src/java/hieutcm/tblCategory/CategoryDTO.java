/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieutcm.tblCategory;

/**
 *
 * @author Minh Hieu
 */
public class CategoryDTO {
    private int cateID;
    private String cateName;
    private String Description;

    public CategoryDTO() {
    }

    public CategoryDTO(int cateID, String cateName, String Description) {
        this.cateID = cateID;
        this.cateName = cateName;
        this.Description = Description;
    }

    public int getCateID() {
        return cateID;
    }

    public void setCateID(int cateID) {
        this.cateID = cateID;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
}
