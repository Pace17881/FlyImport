package tables;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tables.Buchung;
import tables.Fluggesellschaft;
import tables.Flughafen;
import tables.FlugliniePK;
import tables.Flugzeug;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-09-05T08:14:39")
@StaticMetamodel(Fluglinie.class)
public class Fluglinie_ { 

    public static volatile SingularAttribute<Fluglinie, Double> preis;
    public static volatile SingularAttribute<Fluglinie, Flughafen> ziFhId;
    public static volatile SingularAttribute<Fluglinie, Short> sitzeBelegt;
    public static volatile SingularAttribute<Fluglinie, FlugliniePK> flugliniePK;
    public static volatile ListAttribute<Fluglinie, Buchung> buchungList;
    public static volatile SingularAttribute<Fluglinie, Short> dauer;
    public static volatile SingularAttribute<Fluglinie, Flugzeug> typ;
    public static volatile SingularAttribute<Fluglinie, Fluggesellschaft> gesellschaftId;
    public static volatile SingularAttribute<Fluglinie, Flughafen> stFhId;

}