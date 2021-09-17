package persistencia;

import easylibmanager.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import negocio.Autor;
import negocio.Categoria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CategoriaDao {

    public Categoria read(int id) {
        List resultado = null;
        Session sessao = null;
        Categoria categoria = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("select * from categoria where id = " + categoria.getId());
            resultado = query.list();
            for (Object obj : resultado) {
                categoria = (Categoria) obj;
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
        return categoria;
    }

    public ArrayList<Categoria> readAll() {
        List resultado = null;
        Session sessao = null;
        ArrayList<Categoria> categorias = new ArrayList<>();
        Categoria categoria = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("select * from categoria");
            resultado = query.list();
            for (Object obj : resultado) {
                categoria = (Categoria) obj;
                categorias.add(categoria);
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
        return categorias;

    }

}
