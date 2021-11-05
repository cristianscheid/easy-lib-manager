package persistencia;

import easylibmanager.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import negocio.Autor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AutorDao {

    private static final Logger logger = LogManager.getLogger(AutorDao.class);

    public void create(Autor autor) {
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            sessao.save(autor);
            transacao.commit();
            logger.trace("Autor " + autor.getId() + " created");
        } catch (HibernateException hibEx) {
            logger.error(hibEx.getMessage(), hibEx);
        } finally {
            sessao.close();
        }
    }

    public void update(Autor autor) {
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("from Autor where id = " + autor.getId());
            List resultado = query.list();
            for (Object obj : resultado) {
                Autor autor_bd = (Autor) obj;
                autor_bd.setId(autor.getId());
                autor_bd.setNomeCompleto(autor.getNomeCompleto());
                sessao.update(autor_bd);
                transacao.commit();
                logger.trace("Autor " + autor.getId() + " updated");
            }
        } catch (HibernateException hibEx) {
            logger.error(hibEx.getMessage(), hibEx);
        }
    }

    public void delete(Autor autor) {
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("from Autor where id = " + autor.getId());
            List resultado = query.list();
            for (Object obj : resultado) {
                Autor autor_bd = (Autor) obj;
                sessao.delete(autor_bd);
                transacao.commit();
                logger.trace("Autor " + autor.getId() + " deleted");
            }
        } catch (HibernateException hibEx) {
            logger.error(hibEx.getMessage(), hibEx);
        }
    }

    public Autor read(int id) {
        Autor autor = null;
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Query query = sessao.createQuery("from Autor where id = " + id);
            List resultado = query.list();
            for (Object obj : resultado) {
                autor = (Autor) obj;
            }
        } catch (HibernateException hibEx) {
            logger.error(hibEx.getMessage(), hibEx);
        }
        return autor;
    }

    public ArrayList<Autor> readAll() {
        ArrayList<Autor> autores = new ArrayList<>();
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Query query = sessao.createQuery("from Autor");
            List resultado = query.list();
            for (Object obj : resultado) {
                Autor autor = (Autor) obj;
                autores.add(autor);
            }
        } catch (HibernateException hibEx) {
            logger.error(hibEx.getMessage(), hibEx);
        }
        return autores;
    }

    public Autor readName(String nomeCompleto) {
        Autor autor = null;
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Query query = sessao.createQuery("from Autor where nome_completo = '" + nomeCompleto + "'");
            List resultado = query.list();
            for (Object obj : resultado) {
                autor = (Autor) obj;
            }
        } catch (HibernateException hibEx) {
            logger.error(hibEx.getMessage(), hibEx);
        }
        return autor;
    }

    public ArrayList<Autor> readFilter(Autor autor) {
        String sql = "from autor where 1=1";
        if (autor.getNomeCompleto() != null) {
            sql += " and lower(nome_completo) like lower('%" + autor.getNomeCompleto() + "%')";
        }
        ArrayList<Autor> autores = new ArrayList<>();
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Query query = sessao.createQuery(sql);
            List resultado = query.list();
            for (Object obj : resultado) {
                autor = (Autor) obj;
                autores.add(autor);
            }
        } catch (HibernateException hibEx) {
            logger.error(hibEx.getMessage(), hibEx);
        }
        return autores;
    }
}
