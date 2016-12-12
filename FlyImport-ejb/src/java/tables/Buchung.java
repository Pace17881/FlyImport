/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thiem
 */
@Entity
@Table(name = "BUCHUNG")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Buchung.findAll", query = "SELECT b FROM Buchung b")
})
public class Buchung implements Serializable
{
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected BuchungPK buchungPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BUCHUNG_DATUM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date buchungDatum;
    @JoinColumns(
    {
        @JoinColumn(name = "LINIE_ID", referencedColumnName = "LINIE_ID", insertable = false, updatable = false),
        @JoinColumn(name = "ABFLUG_DATUM", referencedColumnName = "ABFLUG_DATUM", insertable = false, updatable = false)
    })
    @ManyToOne(optional = false)
    private Fluglinie fluglinie;
    @JoinColumn(name = "PASSAGIER_NR", referencedColumnName = "PASSAGIER_NR", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Passagier passagier;

    public Buchung()
    {
    }

    public Buchung(BuchungPK buchungPK)
    {
        this.buchungPK = buchungPK;
    }

    public Buchung(BuchungPK buchungPK, Date buchungDatum)
    {
        this.buchungPK = buchungPK;
        this.buchungDatum = buchungDatum;
    }

    public Buchung(int passagierNr, short linieId, Date abflugDatum, int buchungNr)
    {
        this.buchungPK = new BuchungPK(passagierNr, linieId, abflugDatum, buchungNr);
    }

    public BuchungPK getBuchungPK()
    {
        return buchungPK;
    }

    public void setBuchungPK(BuchungPK buchungPK)
    {
        this.buchungPK = buchungPK;
    }

    public Date getBuchungDatum()
    {
        return buchungDatum;
    }

    public void setBuchungDatum(Date buchungDatum)
    {
        this.buchungDatum = buchungDatum;
    }

    public Fluglinie getFluglinie()
    {
        return fluglinie;
    }

    public void setFluglinie(Fluglinie fluglinie)
    {
        this.fluglinie = fluglinie;
    }

    public Passagier getPassagier()
    {
        return passagier;
    }

    public void setPassagier(Passagier passagier)
    {
        this.passagier = passagier;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (buchungPK != null ? buchungPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Buchung))
        {
            return false;
        }
        Buchung other = (Buchung) object;
        if ((this.buchungPK == null && other.buchungPK != null) || (this.buchungPK != null && !this.buchungPK.equals(other.buchungPK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "tables.Buchung[ buchungPK=" + buchungPK + " ]";
    }
    
}
