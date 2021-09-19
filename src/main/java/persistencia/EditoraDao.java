package persistencia;

import easylibmanager.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import negocio.Editora;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EditoraDao {

    public void create(Editora editora) {
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            sessao.save(editora);
            transacao.commit();
            JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso!");
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        } finally {
            sessao.close();
        }
    }

    public void update(Editora editora) {
        List resultado = null;
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("select * from editora where id = " + editora.getId());
            resultado = query.list();
            for (Object obj : resultado) {
                Editora editora_bd = (Editora) obj;
                editora_bd.setId(editora.getId());
                editora_bd.setNome(editora.getNome());
                sessao.update(editora_bd);
                transacao.commit();
                JOptionPane.showMessageDialog(null, "Cadastro alterado com sucesso!");
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
    }

    public void delete(Editora editora) {
        List resultado = null;
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("select * from editora where id = " + editora.getId());
            resultado = query.list();
            for (Object obj : resultado) {
                Editora editora_bd = (Editora) obj;
                sessao.delete(editora_bd);
                transacao.commit();
                JOptionPane.showMessageDialog(null, "Cadastro excluído com sucesso!");
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
    }

    public Editora read(int id) {
        List resultado = null;
        Session sessao = null;
        Editora editora = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("select * from editora where id = " + editora.getId());
            resultado = query.list();
            for (Object obj : resultado) {
                editora = (Editora) obj;
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
        return editora;
    }

    public Editora readName(String nome) {
        List resultado = null;
        Session sessao = null;
        Editora editora = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("select * from editora nome = '" + nome + "'");
            resultado = query.list();
            for (Object obj : resultado) {
                editora = (Editora) obj;
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
        return editora;
    }

    public ArrayList<Editora> readAll() {
        List resultado = null;
        Session sessao = null;
        ArrayList<Editora> editoras = new ArrayList<>();
        Editora editora = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("select * from editora");
            resultado = query.list();
            for (Object obj : resultado) {
                editora = (Editora) obj;
                editoras.add(editora);
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
        return editoras;
    }

}
