package negocio;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import negocio.Cliente;
import negocio.Livro;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-09-10T19:52:10")
@StaticMetamodel(Emprestimo.class)
public class Emprestimo_ { 

    public static volatile SingularAttribute<Emprestimo, Cliente> cliente;
    public static volatile SingularAttribute<Emprestimo, LocalDate> dataEmprestimo;
    public static volatile SingularAttribute<Emprestimo, Livro> livro;
    public static volatile SingularAttribute<Emprestimo, Integer> id;
    public static volatile SingularAttribute<Emprestimo, LocalDate> dataDevolucao;

}