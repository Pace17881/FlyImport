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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
@Table(name = "LAND")
@XmlRootElement
@NamedQueries(
        {
            @NamedQuery(name = "Land.findAll", query = "SELECT l FROM Land l")
        })
public class Land implements Serializable
{

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "LAND_ID", sequenceName = "SEQ_Land")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LAND_ID")
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "LAND_ID")
    private Short landId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "LAND_NAME")
    private String landName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "landId")
    private List<Flughafen> flughafenList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "landId")
    private List<Passagier> passagierList;

    public Land()
    {
    }

    public Land(Short landId)
    {
        this.landId = landId;
    }

    public Land(String landName)
    {
        this.landName = landName;
    }

    public Land(Short landId, String landName)
    {
        this.landId = landId;
        this.landName = landName;
    }

    public Short getLandId()
    {
        return landId;
    }

    public void setLandId(Short landId)
    {
        this.landId = landId;
    }

    public String getLandName()
    {
        return landName;
    }

    public void setLandName(String landName)
    {
        this.landName = landName;
    }

    @XmlTransient
    public List<Flughafen> getFlughafenList()
    {
        return flughafenList;
    }

    public void setFlughafenList(List<Flughafen> flughafenList)
    {
        this.flughafenList = flughafenList;
    }

    @XmlTransient
    public List<Passagier> getPassagierList()
    {
        return passagierList;
    }

    public void setPassagierList(List<Passagier> passagierList)
    {
        this.passagierList = passagierList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (landId != null ? landId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Land))
        {
            return false;
        }
        Land other = (Land) object;
        if ((this.landId == null && other.landId != null) || (this.landId != null && !this.landId.equals(other.landId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "tables.Land[ landId=" + landId + " ]";
    }

}
