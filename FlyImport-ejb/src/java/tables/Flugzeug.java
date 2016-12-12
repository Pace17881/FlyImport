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
@Table(name = "FLUGZEUG")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Flugzeug.findAll", query = "SELECT f FROM Flugzeug f")
})
public class Flugzeug implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "TYP")
    private String typ;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "HERSTELLER")
    private String hersteller;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ANZAHL_SITZE")
    private short anzahlSitze;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typ")
    private List<Fluglinie> fluglinieList;

    public Flugzeug()
    {
    }

    public Flugzeug(String typ)
    {
        this.typ = typ;
    }

    public Flugzeug(String typ, String hersteller, short anzahlSitze)
    {
        this.typ = typ;
        this.hersteller = hersteller;
        this.anzahlSitze = anzahlSitze;
    }

    public String getTyp()
    {
        return typ;
    }

    public void setTyp(String typ)
    {
        this.typ = typ;
    }

    public String getHersteller()
    {
        return hersteller;
    }

    public void setHersteller(String hersteller)
    {
        this.hersteller = hersteller;
    }

    public short getAnzahlSitze()
    {
        return anzahlSitze;
    }

    public void setAnzahlSitze(short anzahlSitze)
    {
        this.anzahlSitze = anzahlSitze;
    }

    @XmlTransient
    public List<Fluglinie> getFluglinieList()
    {
        return fluglinieList;
    }

    public void setFluglinieList(List<Fluglinie> fluglinieList)
    {
        this.fluglinieList = fluglinieList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (typ != null ? typ.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Flugzeug))
        {
            return false;
        }
        Flugzeug other = (Flugzeug) object;
        if ((this.typ == null && other.typ != null) || (this.typ != null && !this.typ.equals(other.typ)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "tables.Flugzeug[ typ=" + typ + " ]";
    }
    
}
