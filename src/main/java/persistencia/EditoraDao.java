package persistencia;

import easylibmanager.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import negocio.Editora;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EditoraDao {

    private static final Logger logger = LogManager.getLogger(EditoraDao.class);

    public void create(Editora editora) {
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            sessao.save(editora);
            transacao.commit();
            logger.trace("Editora " + editora.getId() + " created");
        } catch (HibernateException hibEx) {
            logger.error(hibEx.getMessage(), hibEx);
        } finally {
            sessao.close();
        }
    }

    public void update(Editora editora) {
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("from Editora where id = " + editora.getId());
            List resultado = query.list();
            for (Object obj : resultado) {
                Editora editora_bd = (Editora) obj;
                editora_bd.setId(editora.getId());
                editora_bd.setNome(editora.getNome());
                sessao.update(editora_bd);
                transacao.commit();
                logger.trace("Editora " + editora.getId() + " updated");
            }
        } catch (HibernateException hibEx) {
            logger.error(hibEx.getMessage(), hibEx);
        }
    }

    public void delete(Editora editora) {
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("from Editora where id = " + editora.getId());
            List resultado = query.list();
            for (Object obj : resultado) {
                Editora editora_bd = (Editora) obj;
                sessao.delete(editora_bd);
                transacao.commit();
                logger.trace("Editora " + editora.getId() + " deleted");
            }
        } catch (HibernateException hibEx) {
            logger.error(hibEx.getMessage(), hibEx);
        }
    }

    public Editora read(int id) {
        Editora editora = null;
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Query query = sessao.createQuery("from Editora where id = " + editora.getId());
            List resultado = query.list();
            for (Object obj : resultado) {
                editora = (Editora) obj;
            }
        } catch (HibernateException hibEx) {
            logger.error(hibEx.getMessage(), hibEx);
        }
        return editora;
    }

    public Editora readName(String nome) {
        Editora editora = null;
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Query query = sessao.createQuery("from Editora where nome = '" + nome + "'");
            List resultado = query.list();
            for (Object obj : resultado) {
                editora = (Editora) obj;
            }
        } catch (HibernateException hibEx) {
            logger.error(hibEx.getMessage(), hibEx);
        }
        return editora;
    }

    public ArrayList<Editora> readAll() {
        ArrayList<Editora> editoras = new ArrayList<>();
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Query query = sessao.createQuery("from Editora");
            List resultado = query.list();
            for (Object obj : resultado) {
                Editora editora = (Editora) obj;
                editoras.add(editora);
            }
        } catch (HibernateException hibEx) {
            logger.error(hibEx.getMessage(), hibEx);
        }
        return editoras;
    }

}
