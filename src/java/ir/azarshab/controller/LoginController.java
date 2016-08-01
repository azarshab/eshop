package ir.azarshab.controller;

import ir.azarshab.model.User;
import ir.azarshab.session_beans.UserFacade;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named("loginController")
@RequestScoped
public class LoginController implements Serializable {

    @EJB
    private ir.azarshab.session_beans.UserFacade ejbFacade;
    private String username;
    private String password;

    public LoginController() {
    }

    private UserFacade getFacade() {
        return ejbFacade;
    }

    public String login() {
        if (ejbFacade.login(username, password)) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().put("user", getFacade().getUserByUsername(username));
            return "index";
        } else {
            showMessage("ورود نا موفق", "نام کاربری یا رمز عبور شما شتیاه میباشد", FacesMessage.SEVERITY_ERROR);
            return "login";
        }
    }

    public boolean hasUserLogin() {
        FacesContext context = FacesContext.getCurrentInstance();
        User user = (User) context.getExternalContext().getSessionMap().get("user");
        return user != null;
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

    private void showMessage(String titleString, String description, FacesMessage.Severity severity) {
        FacesMessage message = new FacesMessage(severity, titleString, description);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
