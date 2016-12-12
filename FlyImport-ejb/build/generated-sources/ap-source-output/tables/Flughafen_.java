package tables;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tables.Fluglinie;
import tables.Land;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-09-05T08:14:39")
@StaticMetamodel(Flughafen.class)
public class Flughafen_ { 

    public static volatile SingularAttribute<Flughafen, Land> landId;
    public static volatile ListAttribute<Flughafen, Fluglinie> fluglinieList;
    public static volatile SingularAttribute<Flughafen, String> stadt;
    public static volatile SingularAttribute<Flughafen, String> flughafenId;
    public static volatile ListAttribute<Flughafen, Fluglinie> fluglinieList1;

}