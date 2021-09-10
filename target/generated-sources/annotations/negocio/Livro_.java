package negocio;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import negocio.Autor;
import negocio.Categoria;
import negocio.Editora;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-09-10T19:52:10")
@StaticMetamodel(Livro.class)
public class Livro_ { 

    public static volatile SingularAttribute<Livro, Integer> ano;
    public static volatile SingularAttribute<Livro, String> isbn;
    public static volatile SingularAttribute<Livro, Categoria> categoria;
    public static volatile SingularAttribute<Livro, String> titulo;
    public static volatile SingularAttribute<Livro, Integer> id;
    public static volatile SingularAttribute<Livro, Boolean> Disponivel;
    public static volatile SingularAttribute<Livro, Editora> editora;
    public static volatile SingularAttribute<Livro, Autor> autor;

}