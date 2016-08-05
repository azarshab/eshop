package ir.azarshab.model;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author k1
 */
@Entity
@Table(name = "site_content")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SiteContent.findAll", query = "SELECT s FROM SiteContent s"),
    @NamedQuery(name = "SiteContent.findById", query = "SELECT s FROM SiteContent s WHERE s.id = :id"),
    @NamedQuery(name = "SiteContent.findByTitle", query = "SELECT s FROM SiteContent s WHERE s.title = :title"),
    @NamedQuery(name = "SiteContent.findByCreationDate", query = "SELECT s FROM SiteContent s WHERE s.creationDate = :creationDate"),
    @NamedQuery(name = "SiteContent.findByVisitCount", query = "SELECT s FROM SiteContent s WHERE s.visitCount = :visitCount")})
public class SiteContent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 255)
    @Column(name = "title")
    private String title;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Column(name = "creation_date")
    private BigInteger creationDate;
    @Column(name = "visit_count")
    private Integer visitCount;

    public SiteContent() {
    }

    public SiteContent(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SiteContent)) {
            return false;
        }
        SiteContent other = (SiteContent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ir.azarshab.model.SiteContent[ id=" + id + " ]";
    }
    
}
