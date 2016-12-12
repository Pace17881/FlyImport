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
public class FlugliniePK implements Serializable
{
    @Basic(optional = false)
    @NotNull
    @Column(name = "LINIE_ID")
    private short linieId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ABFLUG_DATUM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date abflugDatum;

    public FlugliniePK()
    {
    }

    public FlugliniePK(short linieId, Date abflugDatum)
    {
        this.linieId = linieId;
        this.abflugDatum = abflugDatum;
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

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (int) linieId;
        hash += (abflugDatum != null ? abflugDatum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FlugliniePK))
        {
            return false;
        }
        FlugliniePK other = (FlugliniePK) object;
        if (this.linieId != other.linieId)
        {
            return false;
        }
        if ((this.abflugDatum == null && other.abflugDatum != null) || (this.abflugDatum != null && !this.abflugDatum.equals(other.abflugDatum)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "tables.FlugliniePK[ linieId=" + linieId + ", abflugDatum=" + abflugDatum + " ]";
    }
    
}
