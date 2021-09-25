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
                JOptionPane.showMessageDialog(null, "Cadastro alterado com sucesso!");
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
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
                JOptionPane.showMessageDialog(null, "Cadastro excluído com sucesso!");
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
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
            hibEx.printStackTrace();
        }
        return editora;
    }

    public Editora readName(String nome) {
        Editora editora = null;
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Query query = sessao.createQuery("from Editora nome = '" + nome + "'");
            List resultado = query.list();
            for (Object obj : resultado) {
                editora = (Editora) obj;
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
        return editora;
    }

    public ArrayList<Editora> readAll() {
        ArrayList<Editora> editoras = new ArrayList<>();
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("from Editora");
            List resultado = query.list();
            for (Object obj : resultado) {
                Editora editora = (Editora) obj;
                editoras.add(editora);
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
        return editoras;
    }

}
