/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author thiem
 */
@Entity
@Table(name = "PASSAGIER")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Passagier.findAll", query = "SELECT p FROM Passagier p")
})
public class Passagier implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PASSAGIER_NR")
    private Integer passagierNr;
    @Size(max = 15)
    @Column(name = "ANREDE")
    private String anrede;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "NAME")
    private String name;
    @Size(max = 10)
    @Column(name = "PLZ")
    private String plz;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "ORT")
    private String ort;
    @Size(max = 40)
    @Column(name = "STRASSE")
    private String strasse;
    @JoinColumn(name = "LAND_ID", referencedColumnName = "LAND_ID")
    @ManyToOne(optional = false)
    private Land landId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "passagier")
    private List<Buchung> buchungList;

    public Passagier()
    {
    }

    public Passagier(Integer passagierNr)
    {
        this.passagierNr = passagierNr;
    }

    public Passagier(Integer passagierNr, String name, String ort)
    {
        this.passagierNr = passagierNr;
        this.name = name;
        this.ort = ort;
    }
    public Passagier(Integer passagierNr, String name, String ort, Land landId)
    {
        this.passagierNr = passagierNr;
        this.name = name;
        this.ort = ort;
        this.landId = landId;
    }

    public Integer getPassagierNr()
    {
        return passagierNr;
    }

    public void setPassagierNr(Integer passagierNr)
    {
        this.passagierNr = passagierNr;
    }

    public String getAnrede()
    {
        return anrede;
    }

    public void setAnrede(String anrede)
    {
        this.anrede = anrede;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPlz()
    {
        return plz;
    }

    public void setPlz(String plz)
    {
        this.plz = plz;
    }

    public String getOrt()
    {
        return ort;
    }

    public void setOrt(String ort)
    {
        this.ort = ort;
    }

    public String getStrasse()
    {
        return strasse;
    }

    public void setStrasse(String strasse)
    {
        this.strasse = strasse;
    }

    public Land getLandId()
    {
        return landId;
    }

    public void setLandId(Land landId)
    {
        this.landId = landId;
    }

    @XmlTransient
    public List<Buchung> getBuchungList()
    {
        return buchungList;
    }

    public void setBuchungList(List<Buchung> buchungList)
    {
        this.buchungList = buchungList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (passagierNr != null ? passagierNr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Passagier))
        {
            return false;
        }
        Passagier other = (Passagier) object;
        if ((this.passagierNr == null && other.passagierNr != null) || (this.passagierNr != null && !this.passagierNr.equals(other.passagierNr)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "tables.Passagier[ passagierNr=" + passagierNr + " ]";
    }
    
}
