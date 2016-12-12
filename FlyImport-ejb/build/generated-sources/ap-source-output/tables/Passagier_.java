package tables;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tables.Buchung;
import tables.Land;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-09-05T08:14:39")
@StaticMetamodel(Passagier.class)
public class Passagier_ { 

    public static volatile SingularAttribute<Passagier, String> ort;
    public static volatile SingularAttribute<Passagier, Land> landId;
    public static volatile SingularAttribute<Passagier, String> strasse;
    public static volatile SingularAttribute<Passagier, String> anrede;
    public static volatile SingularAttribute<Passagier, String> name;
    public static volatile ListAttribute<Passagier, Buchung> buchungList;
    public static volatile SingularAttribute<Passagier, Integer> passagierNr;
    public static volatile SingularAttribute<Passagier, String> plz;

}