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
@Table(name = "FLUGGESELLSCHAFT")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Fluggesellschaft.findAll", query = "SELECT f FROM Fluggesellschaft f")
})
public class Fluggesellschaft implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "GESELLSCHAFT_ID")
    private String gesellschaftId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "GESELLSCHAFT_NAME")
    private String gesellschaftName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gesellschaftId")
    private List<Fluglinie> fluglinieList;

    public Fluggesellschaft()
    {
    }

    public Fluggesellschaft(String gesellschaftId)
    {
        this.gesellschaftId = gesellschaftId;
    }

    public Fluggesellschaft(String gesellschaftId, String gesellschaftName)
    {
        this.gesellschaftId = gesellschaftId;
        this.gesellschaftName = gesellschaftName;
    }

    public String getGesellschaftId()
    {
        return gesellschaftId;
    }

    public void setGesellschaftId(String gesellschaftId)
    {
        this.gesellschaftId = gesellschaftId;
    }

    public String getGesellschaftName()
    {
        return gesellschaftName;
    }

    public void setGesellschaftName(String gesellschaftName)
    {
        this.gesellschaftName = gesellschaftName;
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
        hash += (gesellschaftId != null ? gesellschaftId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fluggesellschaft))
        {
            return false;
        }
        Fluggesellschaft other = (Fluggesellschaft) object;
        if ((this.gesellschaftId == null && other.gesellschaftId != null) || (this.gesellschaftId != null && !this.gesellschaftId.equals(other.gesellschaftId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "tables.Fluggesellschaft[ gesellschaftId=" + gesellschaftId + " ]";
    }
    
}
