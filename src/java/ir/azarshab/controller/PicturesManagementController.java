package ir.azarshab.controller;

import ir.azarshab.model.Pictures;
import ir.azarshab.model.Category;
import ir.azarshab.session_beans.CategoryFacade;
import ir.azarshab.session_beans.PicturesFacade;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

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
    private String absolutePath;
    private List<String> selectedCategoryIdsList;
    private UploaderBB uploaderBB = new UploaderBB();

    private Integer selectedCategoryId;
    private List<Category> categorys;
    private List<String> imagesAddress;

    public PicturesManagementController() {
    }

    @PostConstruct
    public void initPicture() {
        categorys = ejbFacadeCategory.findAll();
        imagesAddress = new ArrayList<>();
        if (!categorys.isEmpty()) {
            selectedCategoryId = categorys.get(2).getId();
            filterPicturesByCategory();
        }
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

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public List<String> getSelectedCategoryIdsList() {
        return selectedCategoryIdsList;
    }

    public void setSelectedCategoryIdsList(List<String> selectedCategoryIdsList) {
        this.selectedCategoryIdsList = selectedCategoryIdsList;
    }

    public Integer getSelectedCategoryId() {
        return selectedCategoryId;
    }

    public void setSelectedCategoryId(Integer selectedCategoryId) {
        this.selectedCategoryId = selectedCategoryId;
    }

    public List<String> getImagesAddress() {
        return imagesAddress;
    }

    public void setImagesAddress(List<String> imagesAddress) {
        this.imagesAddress = imagesAddress;
    }

    public String addPictures() {
        Pictures p = new Pictures();
        p.setName(name);
        p.setTitle(title);
        p.setDescription(description);
        p.setPhotoDate(photoDate);
        p.setRelativePath(absolutePath);
        List<Category> categorysObjects = new ArrayList<>();
        for (int i = 0; i < selectedCategoryIdsList.size(); i++) {
            Category category = ejbFacadeCategory.find(Integer.valueOf(selectedCategoryIdsList.get(i)));
            categorysObjects.add(category);
        }
        p.setCategoryList(categorysObjects);
        getFacade().create(p);
        showMessage("عکس با موفقیت ثبت شد", "عکس با موفقیت ثبت شد", FacesMessage.SEVERITY_INFO);
        return "";
    }

    private void showMessage(String titleString, String description, FacesMessage.Severity severity) {
        FacesMessage message = new FacesMessage(severity, titleString, description);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void handleFileUpload(FileUploadEvent event) {
        uploaderBB.handleFileUpload(event);
        absolutePath = UploaderBB.PREFIX_PATH + File.separator + event.getFile().getFileName();
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void filterPicturesByCategory() {
        imagesAddress.clear();
        List<Pictures> pictures = getFacade().getPicturesByCatValue(2);
        for (int i = 0; i < pictures.size(); i++) {
            imagesAddress.add(pictures.get(i).getRelativePath());
        }
    }

    public StreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String imagePath = context.getExternalContext().getRequestParameterMap().get("imagePath");
            return new DefaultStreamedContent(new FileInputStream(new File(imagePath)), "image/jpeg");
        }
    }

}
