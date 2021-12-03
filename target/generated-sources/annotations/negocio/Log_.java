package negocio;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import negocio.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-12-02T22:32:16")
@StaticMetamodel(Log.class)
public class Log_ { 

    public static volatile SingularAttribute<Log, String> tipo;
    public static volatile SingularAttribute<Log, String> ip;
    public static volatile SingularAttribute<Log, String> mensagem;
    public static volatile SingularAttribute<Log, Usuario> usuario;
    public static volatile SingularAttribute<Log, Integer> id;
    public static volatile SingularAttribute<Log, LocalDateTime> dataHora;

}