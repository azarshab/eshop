package ir.azarshab.controller;

import ir.azarshab.model.Pictures;
import ir.azarshab.model.Category;
import ir.azarshab.session_beans.CategoryFacade;
import ir.azarshab.session_beans.PicturesFacade;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("picturesManagementController")
@SessionScoped
public class PicturesManagementController implements Serializable {

    @EJB
    private PicturesFacade ejbFacade;
    @EJB
    private CategoryFacade ejbFacadeCategory;

    private List<Pictures> items = null;
    private Pictures selected;

    private String name;
    private String title;
    private String description;
    private BigInteger photoDate;
    private String relativePath;
    private List<String> selectedCategoryIdsList;

    private List<Category> categorys;

    public PicturesManagementController() {
    }

    @PostConstruct
    public void initPicture() {
        categorys = ejbFacadeCategory.findAll();
    }

    public List<Category> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<Category> categorys) {
        this.categorys = categorys;
    }

    public Pictures getSelected() {
        return selected;
    }

    public void setSelected(Pictures selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PicturesFacade getFacade() {
        return ejbFacade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigInteger getPhotoDate() {
        return photoDate;
    }

    public void setPhotoDate(BigInteger photoDate) {
        this.photoDate = photoDate;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public List<String> getSelectedCategoryIdsList() {
        return selectedCategoryIdsList;
    }

    public void setSelectedCategoryIdsList(List<String> selectedCategoryIdsList) {
        this.selectedCategoryIdsList = selectedCategoryIdsList;
    }

    public String addPictures() {
        Pictures p = new Pictures();
        p.setName(name);
        p.setTitle(title);
        p.setDescription(description);
        p.setPhotoDate(photoDate);
        p.setRelativePath(relativePath);
        List<Category> categorysObjects = new ArrayList<>();
        for (int i = 0; i < selectedCategoryIdsList.size(); i++) {
            Category category = ejbFacadeCategory.find(Integer.valueOf(selectedCategoryIdsList.get(i)));
            categorysObjects.add(category);
        }
        p.setCategoryList(categorysObjects);
        getFacade().create(p);
        return "";
    }

}