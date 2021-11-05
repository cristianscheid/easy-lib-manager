package persistencia;

import easylibmanager.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import negocio.Usuario;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UsuarioDao {

    private static final Logger logger = LogManager.getLogger(UsuarioDao.class);

    public void create(Usuario usuario) {
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            sessao.save(usuario);
            transacao.commit();
            logger.trace("Usuario " + usuario.getId() + " created");
        } catch (HibernateException hibEx) {
            logger.error(hibEx.getMessage(), hibEx);
        } finally {
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
                logger.trace("Usuario " + usuario.getId() + " updated");
            }
        } catch (HibernateException hibEx) {
            logger.error(hibEx.getMessage(), hibEx);
        }
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
                logger.trace("Usuario " + usuario.getId() + " deleted");
            }
        } catch (HibernateException hibEx) {
            logger.error(hibEx.getMessage(), hibEx);
        }
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
            logger.error(hibEx.getMessage(), hibEx);
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
            logger.error(hibEx.getMessage(), hibEx);
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
            logger.error(hibEx.getMessage(), hibEx);
        }
        return usuario;
    }

    public void createAdmin() {
        Usuario usuario = new Usuario("admin", "21232f297a57a5a743894a0e4a801fc3");
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            sessao.save(usuario);
            transacao.commit();
        } catch (HibernateException hibEx) {
            logger.error(hibEx.getMessage(), hibEx);
        } finally {
            sessao.close();
        }
    }
}
