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
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author thiem
 */
@Embeddable
public class BuchungPK implements Serializable
{
    @Basic(optional = false)
    @NotNull
    @Column(name = "PASSAGIER_NR")
    private int passagierNr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LINIE_ID")
    private short linieId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ABFLUG_DATUM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date abflugDatum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BUCHUNG_NR")
    private int buchungNr;

    public BuchungPK()
    {
    }

    public BuchungPK(int passagierNr, short linieId, Date abflugDatum, int buchungNr)
    {
        this.passagierNr = passagierNr;
        this.linieId = linieId;
        this.abflugDatum = abflugDatum;
        this.buchungNr = buchungNr;
    }

    public int getPassagierNr()
    {
        return passagierNr;
    }

    public void setPassagierNr(int passagierNr)
    {
        this.passagierNr = passagierNr;
    }

    public short getLinieId()
    {
        return linieId;
    }

    public void setLinieId(short linieId)
    {
        this.linieId = linieId;
    }

    public Date getAbflugDatum()
    {
        return abflugDatum;
    }

    public void setAbflugDatum(Date abflugDatum)
    {
        this.abflugDatum = abflugDatum;
    }

    public int getBuchungNr()
    {
        return buchungNr;
    }

    public void setBuchungNr(int buchungNr)
    {
        this.buchungNr = buchungNr;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (int) passagierNr;
        hash += (int) linieId;
        hash += (abflugDatum != null ? abflugDatum.hashCode() : 0);
        hash += (int) buchungNr;
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BuchungPK))
        {
            return false;
        }
        BuchungPK other = (BuchungPK) object;
        if (this.passagierNr != other.passagierNr)
        {
            return false;
        }
        if (this.linieId != other.linieId)
        {
            return false;
        }
        if ((this.abflugDatum == null && other.abflugDatum != null) || (this.abflugDatum != null && !this.abflugDatum.equals(other.abflugDatum)))
        {
            return false;
        }
        if (this.buchungNr != other.buchungNr)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "tables.BuchungPK[ passagierNr=" + passagierNr + ", linieId=" + linieId + ", abflugDatum=" + abflugDatum + ", buchungNr=" + buchungNr + " ]";
    }
    
}
