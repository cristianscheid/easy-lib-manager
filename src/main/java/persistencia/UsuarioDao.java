package persistencia;

import easylibmanager.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import negocio.Log;
import negocio.Usuario;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UsuarioDao {

    private Log log;
    private LogDao logDao = new LogDao();

    public void create(Usuario usuario) {
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            sessao.save(usuario);
            transacao.commit();
            log = new Log("TRACE", "Usuario " + usuario.getId() + " created");
        } catch (HibernateException hibEx) {
            log = new Log("ERROR", hibEx.getMessage());
        } finally {
            logDao.create(log);
            sessao.close();
        }
    }

    public void update(Usuario usuario) {
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("from Usuario where id = " + usuario.getId());
            List resultado = query.list();
            for (Object obj : resultado) {
                Usuario usuario_bd = (Usuario) obj;
                usuario_bd.setId(usuario.getId());
                usuario_bd.setNome(usuario.getNome());
                usuario_bd.setSobrenome(usuario.getSobrenome());
                usuario_bd.setLogin(usuario.getLogin());
                usuario_bd.setSenha(usuario.getSenha());
                usuario_bd.setCpf(usuario.getCpf());
                usuario_bd.setAdmin(usuario.getAdmin());
                sessao.update(usuario_bd);
                transacao.commit();
                log = new Log("TRACE", "Usuario " + usuario.getId() + " updated");
            }
        } catch (HibernateException hibEx) {
            log = new Log("ERROR", hibEx.getMessage());
        }
        logDao.create(log);
    }

    public void delete(Usuario usuario) {
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("from Usuario where id = " + usuario.getId());
            List resultado = query.list();
            for (Object obj : resultado) {
                Usuario usuario_bd = (Usuario) obj;
                sessao.delete(usuario_bd);
                transacao.commit();
                log = new Log("TRACE", "Usuario " + usuario.getId() + " deleted");
            }
        } catch (HibernateException hibEx) {
            log = new Log("ERROR", hibEx.getMessage());
        }
        logDao.create(log);
    }

    public Usuario read(int id) {
        Usuario usuario = null;
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Query query = sessao.createQuery("from Usuario where id = " + id);
            List resultado = query.list();
            for (Object obj : resultado) {
                usuario = (Usuario) obj;
            }
        } catch (HibernateException hibEx) {
            log = new Log("ERROR", hibEx.getMessage());
            logDao.create(log);
        }
        return usuario;
    }

    public ArrayList<Usuario> readAll() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Query query = sessao.createQuery("from Usuario");
            List resultado = query.list();
            for (Object obj : resultado) {
                Usuario usuario = (Usuario) obj;
                usuarios.add(usuario);
            }
        } catch (HibernateException hibEx) {
            log = new Log("ERROR", hibEx.getMessage());
            logDao.create(log);
        }
        return usuarios;
    }

    public Usuario readLoginPassword(Usuario usuarioAutenticar) {
        Usuario usuario = null;
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Query query = sessao.createQuery("from Usuario where "
                    + "login = '" + usuarioAutenticar.getLogin() + "' and senha = '" + usuarioAutenticar.getSenha() + "'");
            List resultado = query.list();
            for (Object obj : resultado) {
                usuario = (Usuario) obj;
            }
        } catch (HibernateException hibEx) {
            log = new Log("ERROR", hibEx.getMessage());
            logDao.create(log);
        }
        return usuario;
    }

    public void createAdminEVisitante() {
        Usuario usuarioAdmin = new Usuario("Admin", "Admin", "admin", "21232f297a57a5a743894a0e4a801fc3", "-", true);
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            sessao.save(usuarioAdmin);
            transacao.commit();
        } catch (HibernateException hibEx) {
            log = new Log("ERROR", hibEx.getMessage());
            logDao.create(log);
        } finally {
            sessao.close();
        }
    }
}
