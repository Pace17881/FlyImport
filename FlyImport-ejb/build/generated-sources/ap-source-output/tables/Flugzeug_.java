package tables;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tables.Fluglinie;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-09-05T08:14:39")
@StaticMetamodel(Flugzeug.class)
public class Flugzeug_ { 

    public static volatile SingularAttribute<Flugzeug, String> hersteller;
    public static volatile ListAttribute<Flugzeug, Fluglinie> fluglinieList;
    public static volatile SingularAttribute<Flugzeug, String> typ;
    public static volatile SingularAttribute<Flugzeug, Short> anzahlSitze;

}