package tables;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tables.BuchungPK;
import tables.Fluglinie;
import tables.Passagier;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-09-05T08:14:39")
@StaticMetamodel(Buchung.class)
public class Buchung_ { 

    public static volatile SingularAttribute<Buchung, BuchungPK> buchungPK;
    public static volatile SingularAttribute<Buchung, Fluglinie> fluglinie;
    public static volatile SingularAttribute<Buchung, Date> buchungDatum;
    public static volatile SingularAttribute<Buchung, Passagier> passagier;

}