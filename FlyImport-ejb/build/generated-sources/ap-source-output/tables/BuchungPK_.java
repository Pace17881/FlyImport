package tables;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-09-05T08:14:39")
@StaticMetamodel(BuchungPK.class)
public class BuchungPK_ { 

    public static volatile SingularAttribute<BuchungPK, Short> linieId;
    public static volatile SingularAttribute<BuchungPK, Date> abflugDatum;
    public static volatile SingularAttribute<BuchungPK, Integer> buchungNr;
    public static volatile SingularAttribute<BuchungPK, Integer> passagierNr;

}