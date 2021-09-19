package persistencia;

import easylibmanager.HibernateUtil;
import java.util.List;
import javax.swing.JOptionPane;
import negocio.Autor;
import negocio.Definicoes;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DefinicoesDao {

    public void update(Definicoes definicoes) {
        List resultado = null;
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("select * from definicoes");
            resultado = query.list();
            for (Object obj : resultado) {
                Definicoes definicoes_bd = (Definicoes) obj;
                definicoes_bd.setValorMulta(definicoes.getValorMulta());
                definicoes_bd.setPrazoEmprestimo(definicoes.getPrazoEmprestimo());
                sessao.update(definicoes_bd);
                transacao.commit();
                JOptionPane.showMessageDialog(null, "Cadastro alterado com sucesso!");
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
//            String sql = "UPDATE definicoes SET valor_multa = '" + String.valueOf(definicoes.getValorMulta()) + "', "
//                    + "prazo_emprestimo = '" + String.valueOf(definicoes.getPrazoEmprestimo()) + "'";
    }

    public Definicoes read() {
        List resultado = null;
        Session sessao = null;
        Definicoes definicoes = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("select * from definicoes");
            resultado = query.list();
            for (Object obj : resultado) {
                definicoes = (Definicoes) obj;
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
        return definicoes;
    }
}
