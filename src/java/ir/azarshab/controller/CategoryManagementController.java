package ir.azarshab.controller;

import ir.azarshab.model.Category;
import ir.azarshab.session_beans.CategoryFacade;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.event.RowEditEvent;

@Named("categoryManagementController")
@SessionScoped
public class CategoryManagementController implements Serializable {

    @EJB
    private ir.azarshab.session_beans.CategoryFacade ejbFacade;

    public CategoryManagementController() {
    }

    private List<Category> categorys;
    private Category selectedCategory;

    @PostConstruct
    public void init() {
        categorys = ejbFacade.findAll();
    }

    private String categoryName;
    private Integer categoryValue;

    private CategoryFacade getFacade() {
        return ejbFacade;
    }

    public List<Category> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<Category> categorys) {
        this.categorys = categorys;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryValue() {
        return categoryValue;
    }

    public void setCategoryValue(Integer categoryValue) {
        this.categoryValue = categoryValue;
    }

    public String categoryAdd() {
        Category category = new Category();
        category.setCatName(categoryName);
        category.setCatValue(categoryValue);
        getFacade().create(category);
        categorys.add(category);
        return "category";
    }

    public void onRowEdit(RowEditEvent event) {
        Category category = (Category) event.getObject();
        ejbFacade.edit(category);
        categorys.remove(category);
        categorys.add(category);
        FacesMessage msg = new FacesMessage("ویرایش", "اطلاعات با موفقیت ویرایش شد.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        Category category = (Category) event.getObject();
        FacesMessage msg = new FacesMessage("لغو ویرایش", "اطلاعات تغییری نکرد");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public Category getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(Category selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public void removeCategory(Category category) {
        ejbFacade.remove(category);
        categorys.remove(category);
        FacesMessage msg = new FacesMessage("حذف نقش کاربری", "نقش کاربری با موفقیت حذف شد.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public Category getCategory(java.lang.Integer id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = Category.class)
    public static class CategoryControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CategoryManagementController controller = (CategoryManagementController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "categoryController");
            return controller.getCategory(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Category) {
                Category o = (Category) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Category.class.getName()});
                return null;
            }
        }

    }

}
