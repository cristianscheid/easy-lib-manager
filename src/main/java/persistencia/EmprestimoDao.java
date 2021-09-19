package persistencia;

import easylibmanager.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import negocio.Emprestimo;
import negocio.Livro;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmprestimoDao {

    public void create(Emprestimo emprestimo) {
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            sessao.save(emprestimo);
            transacao.commit();
            JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso!");
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        } finally {
            sessao.close();
        }
    }

    public void update(Emprestimo emprestimo) {
        List resultado = null;
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("select * from emprestimo where id = " + emprestimo.getId());
            resultado = query.list();
            for (Object obj : resultado) {
                Emprestimo emprestimo_bd = (Emprestimo) obj;
                emprestimo_bd.setId(emprestimo.getId());
                emprestimo_bd.setDataEmprestimo(emprestimo.getDataEmprestimo());
                emprestimo_bd.setDataDevolucao(emprestimo.getDataDevolucao());
                emprestimo_bd.setCliente(emprestimo.getCliente());
                emprestimo_bd.setLivro(emprestimo.getLivro());
                sessao.update(emprestimo_bd);
                transacao.commit();
                JOptionPane.showMessageDialog(null, "Cadastro alterado com sucesso!");
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
    }

    public ArrayList<Emprestimo> readAll() {
        List resultado = null;
        Session sessao = null;
        ArrayList<Emprestimo> emprestimos = new ArrayList<>();
        Emprestimo emprestimo = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("select * from emprestimo");
            resultado = query.list();
            for (Object obj : resultado) {
                emprestimo = (Emprestimo) obj;
                emprestimos.add(emprestimo);
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
        return emprestimos;
    }

    public Emprestimo readLivroEmprestado(Livro livro) {
        List resultado = null;
        Session sessao = null;
        Emprestimo emprestimo = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("SELECT * FROM emprestimo WHERE data_devolucao IS NULL AND livro_id = " + livro.getId());
            resultado = query.list();
            for (Object obj : resultado) {
                emprestimo = (Emprestimo) obj;
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
        return emprestimo;
    }
}
