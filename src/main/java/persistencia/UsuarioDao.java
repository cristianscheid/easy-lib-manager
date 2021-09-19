package persistencia;

import easylibmanager.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import negocio.Autor;
import negocio.Usuario;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UsuarioDao {

    public void create(Usuario usuario) {
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            sessao.save(usuario);
            transacao.commit();
            JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso!");
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        } finally {
            sessao.close();
        }
    }

    public void update(Usuario usuario) {
        List resultado = null;
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("select * from usuario where id = " + usuario.getId());
            resultado = query.list();
            for (Object obj : resultado) {
                Usuario usuario_bd = (Usuario) obj;
                usuario_bd.setId(usuario.getId());
                usuario_bd.setNome(usuario.getNome());
                usuario_bd.setSobrenome(usuario.getSobrenome());
                usuario_bd.setLogin(usuario.getLogin());
                usuario_bd.setSenha(usuario.getSenha());
                usuario_bd.setCpf(usuario.getCpf());
                sessao.update(usuario_bd);
                transacao.commit();
                JOptionPane.showMessageDialog(null, "Cadastro alterado com sucesso!");
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
    }

    public void delete(Usuario usuario) {
        List resultado = null;
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("select * from usuario where id = " + usuario.getId());
            resultado = query.list();
            for (Object obj : resultado) {
                Usuario usuario_bd = (Usuario) obj;
                sessao.delete(usuario_bd);
                transacao.commit();
                JOptionPane.showMessageDialog(null, "Cadastro excluído com sucesso!");
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
    }

    public Usuario read(int id) {
        List resultado = null;
        Session sessao = null;
        Usuario usuario = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("select * from usuario where id = " + id);
            resultado = query.list();
            for (Object obj : resultado) {
                usuario = (Usuario) obj;
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
        return usuario;
    }

    public ArrayList<Usuario> readAll() {
        List resultado = null;
        Session sessao = null;
        ArrayList<Usuario> usuarios = new ArrayList<>();
        Usuario usuario = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("select * from usuario");
            resultado = query.list();
            for (Object obj : resultado) {
                usuario = (Usuario) obj;
                usuarios.add(usuario);
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
        return usuarios;
    }

    public Usuario readLoginPassword(Usuario usuario) {
        List resultado = null;
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("select * from usuario where "
                    + "login = '" + usuario.getLogin() + "' AND senha = '" + usuario.getSenha() + "'");
            resultado = query.list();
            for (Object obj : resultado) {
                usuario = (Usuario) obj;
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
        return usuario;
    }
}
