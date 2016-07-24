package ir.azarshab.controller;

import ir.azarshab.model.Kala;
import ir.azarshab.session_beans.KalaFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("kalaManagementController")
@RequestScoped
public class KalaManagementController implements Serializable {

    @EJB
    private ir.azarshab.session_beans.KalaFacade ejbFacade;
    private String name;
    private Double geymatKharid;
    private Double geymatForosh;

    public KalaManagementController() {
    }

    private KalaFacade getFacade() {
        return ejbFacade;
    }

    public String addKala() {
        Kala kala = new Kala();
        kala.setName(name);
        kala.setGeymatForosh(geymatForosh);
        kala.setGeymatKharid(geymatKharid);
        getFacade().create(kala);
        return "kalaManagement";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getGeymatKharid() {
        return geymatKharid;
    }

    public void setGeymatKharid(Double geymatKharid) {
        this.geymatKharid = geymatKharid;
    }

    public Double getGeymatForosh() {
        return geymatForosh;
    }

    public void setGeymatForosh(Double geymatForosh) {
        this.geymatForosh = geymatForosh;
    }

}
