package tables;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tables.Flughafen;
import tables.Passagier;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-09-05T08:14:39")
@StaticMetamodel(Land.class)
public class Land_ { 

    public static volatile SingularAttribute<Land, String> landName;
    public static volatile ListAttribute<Land, Flughafen> flughafenList;
    public static volatile SingularAttribute<Land, Short> landId;
    public static volatile ListAttribute<Land, Passagier> passagierList;

}