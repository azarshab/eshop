package ir.azarshab.controller;

import ir.azarshab.model.SiteContent;
import ir.azarshab.session_beans.SiteContentFacade;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("siteContentManagementController")
@SessionScoped
public class SiteContentManagementController implements Serializable {

    @EJB
    private ir.azarshab.session_beans.SiteContentFacade ejbFacade;
    private List<SiteContent> items = null;

    private String title;
    private String description;
    private BigInteger creationDate;
    private Integer visitCount;

    public SiteContentManagementController() {
    }

    private SiteContentFacade getFacade() {
        return ejbFacade;
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

    public BigInteger getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(BigInteger creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }

    public String addText() {
        SiteContent s = new SiteContent();
        s.setTitle(title);
        s.setDescription(description);
        s.setCreationDate(creationDate);
        s.setVisitCount(visitCount);
        getFacade().create(s);
        return "siteContent";
    }

}
