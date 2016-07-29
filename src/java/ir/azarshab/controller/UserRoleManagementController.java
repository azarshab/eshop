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
import org.primefaces.event.CellEditEvent;

@Named("userRoleManagementController")
@SessionScoped
public class UserRoleManagementController implements Serializable {

    @EJB
    private ir.azarshab.session_beans.UserRoleFacade ejbFacade;

    public UserRoleManagementController() {
    }

    private List<UserRole> roles;

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

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

}
