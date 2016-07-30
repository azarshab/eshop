package ir.azarshab.controller;

import ir.azarshab.model.UserRole;
import ir.azarshab.session_beans.UserRoleFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

@Named("userRoleManagementController")
@SessionScoped
public class UserRoleManagementController implements Serializable {

    @EJB
    private ir.azarshab.session_beans.UserRoleFacade ejbFacade;

    public UserRoleManagementController() {
    }

    private List<UserRole> roles;
    private UserRole selectedRole;

    @PostConstruct
    public void init() {
        roles = ejbFacade.findAll();
    }

    private String roleName;
    private Integer roleValue;

    private UserRoleFacade getFacade() {
        return ejbFacade;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleValue() {
        return roleValue;
    }

    public void setRoleValue(Integer roleValue) {
        this.roleValue = roleValue;
    }

    public String userRoleAdd() {
        UserRole userRole = new UserRole();
        userRole.setRoleName(roleName);
        userRole.setRoleValue(roleValue);
        getFacade().create(userRole);
        roles.add(userRole);
        return "userRole";
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public void onRowEdit(RowEditEvent event) {
        UserRole userRole = (UserRole) event.getObject();
        ejbFacade.edit(userRole);
        roles.remove(userRole);
        roles.add(userRole);
        FacesMessage msg = new FacesMessage("ویرایش", "اطلاعات با موفقیت ویرایش شد.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        UserRole userRole = (UserRole) event.getObject();
        FacesMessage msg = new FacesMessage("لغو ویرایش", "اطلاعات تغییری نکرد");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public UserRole getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(UserRole selectedRole) {
        this.selectedRole = selectedRole;
    }

    public void removeUserRole(UserRole role) {
        ejbFacade.remove(role);
        roles.remove(role);
        FacesMessage msg = new FacesMessage("حذف نقش کاربری", "نقش کاربری با موفقیت حذف شد.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
