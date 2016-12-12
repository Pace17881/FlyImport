package tables;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tables.Fluglinie;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-09-05T08:14:39")
@StaticMetamodel(Fluggesellschaft.class)
public class Fluggesellschaft_ { 

    public static volatile SingularAttribute<Fluggesellschaft, String> gesellschaftName;
    public static volatile ListAttribute<Fluggesellschaft, Fluglinie> fluglinieList;
    public static volatile SingularAttribute<Fluggesellschaft, String> gesellschaftId;

}