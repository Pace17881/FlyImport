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
@Table(name = "FLUGHAFEN")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Flughafen.findAll", query = "SELECT f FROM Flughafen f")
})
public class Flughafen implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "FLUGHAFEN_ID")
    private String flughafenId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "STADT")
    private String stadt;
    @JoinColumn(name = "LAND_ID", referencedColumnName = "LAND_ID")
    @ManyToOne(optional = false)
    private Land landId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stFhId")
    private List<Fluglinie> fluglinieList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ziFhId")
    private List<Fluglinie> fluglinieList1;

    public Flughafen()
    {
    }

    public Flughafen(String flughafenId)
    {
        this.flughafenId = flughafenId;
    }

    public Flughafen(String flughafenId, String stadt)
    {
        this.flughafenId = flughafenId;
        this.stadt = stadt;
    }
    
    public Flughafen(String flughafenId, String stadt, Land landId)
    {
        this.flughafenId = flughafenId;
        this.stadt = stadt;
        this.landId = landId;
    }

    public String getFlughafenId()
    {
        return flughafenId;
    }

    public void setFlughafenId(String flughafenId)
    {
        this.flughafenId = flughafenId;
    }

    public String getStadt()
    {
        return stadt;
    }

    public void setStadt(String stadt)
    {
        this.stadt = stadt;
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
    public List<Fluglinie> getFluglinieList()
    {
        return fluglinieList;
    }

    public void setFluglinieList(List<Fluglinie> fluglinieList)
    {
        this.fluglinieList = fluglinieList;
    }

    @XmlTransient
    public List<Fluglinie> getFluglinieList1()
    {
        return fluglinieList1;
    }

    public void setFluglinieList1(List<Fluglinie> fluglinieList1)
    {
        this.fluglinieList1 = fluglinieList1;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (flughafenId != null ? flughafenId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Flughafen))
        {
            return false;
        }
        Flughafen other = (Flughafen) object;
        if ((this.flughafenId == null && other.flughafenId != null) || (this.flughafenId != null && !this.flughafenId.equals(other.flughafenId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "tables.Flughafen[ flughafenId=" + flughafenId + " ]";
    }
    
}
