/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.azarshab.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author k1
 */
@Entity
@Table(name = "kala")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kala.findAll", query = "SELECT k FROM Kala k"),
    @NamedQuery(name = "Kala.findById", query = "SELECT k FROM Kala k WHERE k.id = :id"),
    @NamedQuery(name = "Kala.findByName", query = "SELECT k FROM Kala k WHERE k.name = :name"),
    @NamedQuery(name = "Kala.findByGeymatKharid", query = "SELECT k FROM Kala k WHERE k.geymatKharid = :geymatKharid"),
    @NamedQuery(name = "Kala.findByGeymatForosh", query = "SELECT k FROM Kala k WHERE k.geymatForosh = :geymatForosh")})
public class Kala implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "geymat_kharid")
    private Double geymatKharid;
    @Column(name = "geymat_forosh")
    private Double geymatForosh;

    public Kala() {
    }

    public Kala(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kala)) {
            return false;
        }
        Kala other = (Kala) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ir.azarshab.Kala[ id=" + id + " ]";
    }

    
    
}
