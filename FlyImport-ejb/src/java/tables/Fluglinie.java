/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author thiem
 */
@Entity
@Table(name = "FLUGLINIE")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Fluglinie.findAll", query = "SELECT f FROM Fluglinie f")
})
public class Fluglinie implements Serializable
{
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FlugliniePK flugliniePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DAUER")
    private short dauer;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PREIS")
    private double preis;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SITZE_BELEGT")
    private short sitzeBelegt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fluglinie")
    private List<Buchung> buchungList;
    @JoinColumn(name = "GESELLSCHAFT_ID", referencedColumnName = "GESELLSCHAFT_ID")
    @ManyToOne(optional = false)
    private Fluggesellschaft gesellschaftId;
    @JoinColumn(name = "ST_FH_ID", referencedColumnName = "FLUGHAFEN_ID")
    @ManyToOne(optional = false)
    private Flughafen stFhId;
    @JoinColumn(name = "ZI_FH_ID", referencedColumnName = "FLUGHAFEN_ID")
    @ManyToOne(optional = false)
    private Flughafen ziFhId;
    @JoinColumn(name = "TYP", referencedColumnName = "TYP")
    @ManyToOne(optional = false)
    private Flugzeug typ;

    public Fluglinie()
    {
    }

    public Fluglinie(FlugliniePK flugliniePK)
    {
        this.flugliniePK = flugliniePK;
    }

    public Fluglinie(FlugliniePK flugliniePK, short dauer, double preis, short sitzeBelegt)
    {
        this.flugliniePK = flugliniePK;
        this.dauer = dauer;
        this.preis = preis;
        this.sitzeBelegt = sitzeBelegt;
    }

    public Fluglinie(short linieId, Date abflugDatum)
    {
        this.flugliniePK = new FlugliniePK(linieId, abflugDatum);
    }

    public FlugliniePK getFlugliniePK()
    {
        return flugliniePK;
    }

    public void setFlugliniePK(FlugliniePK flugliniePK)
    {
        this.flugliniePK = flugliniePK;
    }

    public short getDauer()
    {
        return dauer;
    }

    public void setDauer(short dauer)
    {
        this.dauer = dauer;
    }

    public double getPreis()
    {
        return preis;
    }

    public void setPreis(double preis)
    {
        this.preis = preis;
    }

    public short getSitzeBelegt()
    {
        return sitzeBelegt;
    }

    public void setSitzeBelegt(short sitzeBelegt)
    {
        this.sitzeBelegt = sitzeBelegt;
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

    public Fluggesellschaft getGesellschaftId()
    {
        return gesellschaftId;
    }

    public void setGesellschaftId(Fluggesellschaft gesellschaftId)
    {
        this.gesellschaftId = gesellschaftId;
    }

    public Flughafen getStFhId()
    {
        return stFhId;
    }

    public void setStFhId(Flughafen stFhId)
    {
        this.stFhId = stFhId;
    }

    public Flughafen getZiFhId()
    {
        return ziFhId;
    }

    public void setZiFhId(Flughafen ziFhId)
    {
        this.ziFhId = ziFhId;
    }

    public Flugzeug getTyp()
    {
        return typ;
    }

    public void setTyp(Flugzeug typ)
    {
        this.typ = typ;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (flugliniePK != null ? flugliniePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fluglinie))
        {
            return false;
        }
        Fluglinie other = (Fluglinie) object;
        if ((this.flugliniePK == null && other.flugliniePK != null) || (this.flugliniePK != null && !this.flugliniePK.equals(other.flugliniePK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "tables.Fluglinie[ flugliniePK=" + flugliniePK + " ]";
    }
    
}
