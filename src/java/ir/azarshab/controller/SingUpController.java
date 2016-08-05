package ir.azarshab.controller;

import ir.azarshab.model.User;
import ir.azarshab.model.UserRole;
import ir.azarshab.session_beans.UserFacade;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

@Named("singUpController")
@RequestScoped
public class SingUpController implements Serializable {

    @EJB
    private ir.azarshab.session_beans.UserFacade ejbFacade;
    @EJB
    private ir.azarshab.session_beans.UserRoleFacade ejbFacadeUserRole;

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String shomareMelli;
    private String email;
    private String tel;
    private String address;

    private List<User> users;
    private User selectedUser;
    private List<UserRole> userRoles;

    // this user used for add user in user manage page
    private User addedUser = new User();

    public SingUpController() {
    }

    @PostConstruct
    public void init() {
        populateFields();
        users = ejbFacade.findAll();
        userRoles = ejbFacadeUserRole.findAll();
    }

    private UserFacade getFacade() {
        return ejbFacade;
    }

    public String singUp() {
        User user = saveUser();
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("user", user);
        return "index";
    }

    public void addUser() {
        addedUser.setUserRole(ejbFacadeUserRole.getRegularUserRole());
        getFacade().create(addedUser);
        users.add(addedUser);
        addedUser = new User();
        showMessage("کاربر با موفقیت ثبت شد", "کاربر با موفقیت ثبت شد", FacesMessage.SEVERITY_INFO);
    }

    private User saveUser() {
        User user = new User();
        user.setFname(firstName);
        user.setLname(lastName);
        user.setUsername(username);
        user.setPassword(password);
        user.setShomareMelli(shomareMelli);
        user.setEmail(email);
        user.setTel(tel);
        user.setAddress(address);
        user.setUserRole(ejbFacadeUserRole.getRegularUserRole());
        getFacade().create(user);
        return user;
    }

    public void populateFields() {
        FacesContext context = FacesContext.getCurrentInstance();
        User user = (User) context.getExternalContext().getSessionMap().get("user");
        if (user != null) {
            setFirstName(user.getFname());
            setLastName(user.getLname());
            setUsername(user.getUsername());
            setPassword(user.getPassword());
            setShomareMelli(user.getShomareMelli());
            setEmail(user.getEmail());
            setTel(user.getTel());
            setAddress(user.getAddress());
        }
    }

    public String editUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        User user = (User) context.getExternalContext().getSessionMap().get("user");
        user.setFname(firstName);
        user.setLname(lastName);
        user.setUsername(username);
        user.setPassword(password);
        user.setShomareMelli(shomareMelli);
        user.setEmail(email);
        user.setTel(tel);
        user.setAddress(address);
        getFacade().edit(user);
        context.getExternalContext().getSessionMap().put("user", user);
        return "index";
    }

    public String deleteUser(String username) {
        User user = getFacade().getUserByUsername(username);
        getFacade().remove(user);
        return "login";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getShomareMelli() {
        return shomareMelli;
    }

    public void setShomareMelli(String shomareMelli) {
        this.shomareMelli = shomareMelli;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public User getAddedUser() {
        return addedUser;
    }

    public void setAddedUser(User addedUser) {
        this.addedUser = addedUser;
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().clear();
        return "login";
    }

    public void onRowEditUser(RowEditEvent event) {
        User user = (User) event.getObject();
        ejbFacade.edit(user);
        users.remove(user);
        users.add(user);
        FacesMessage msg = new FacesMessage("ویرایش", "اطلاعات با موفقیت ویرایش شد.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        User user = (User) event.getObject();
        FacesMessage msg = new FacesMessage("لغو ویرایش", "اطلاعات تغییری نکرد");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void removeUserRole(User user) {
        ejbFacade.remove(user);
        users.remove(user);
        FacesMessage msg = new FacesMessage("حذف  کاربر", " کاربر با موفقیت حذف شد.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    private void showMessage(String titleString, String description, FacesMessage.Severity severity) {
        FacesMessage message = new FacesMessage(severity, titleString, description);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
