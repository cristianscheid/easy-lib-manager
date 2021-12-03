package persistencia;

import easylibmanager.HibernateUtil;
import negocio.Editora;
import negocio.Log;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class EditoraDao {

    private Log log;
    private LogDao logDao = new LogDao();

    public void create(Editora editora) {
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            sessao.save(editora);
            transacao.commit();
            log = new Log("TRACE", "Editora " + editora.getId() + " created");
        } catch (HibernateException hibEx) {
            log = new Log("ERROR", hibEx.getMessage());
        } finally {
            logDao.create(log);
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
                log = new Log("TRACE", "Editora " + editora.getId() + " updated");
            }
        } catch (HibernateException hibEx) {
            log = new Log("ERROR", hibEx.getMessage());
        }
        logDao.create(log);
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
                log = new Log("TRACE", "Editora " + editora.getId() + " deleted");
            }
        } catch (HibernateException hibEx) {
            log = new Log("ERROR", hibEx.getMessage());
        }
        logDao.create(log);
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
            log = new Log("ERROR", hibEx.getMessage());
            logDao.create(log);
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
            log = new Log("ERROR", hibEx.getMessage());
            logDao.create(log);
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
            log = new Log("ERROR", hibEx.getMessage());
            logDao.create(log);
        }
        return editoras;
    }

}
