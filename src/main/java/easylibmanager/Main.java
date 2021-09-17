package easylibmanager;

import negocio.Autor;
import persistencia.AutorDao;

public class Main {
    
    public static void main(String[] args) {
        
        Autor autor = new Autor(15, "Jorge");
        AutorDao dao = new AutorDao();
        dao.create(autor);
    }
}
