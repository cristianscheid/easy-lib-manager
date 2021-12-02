package persistencia;

import easylibmanager.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;
import negocio.Definicoes;
import negocio.Log;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DefinicoesDao {

    private Log log;
    private LogDao logDao = new LogDao();

    public void createDefinicoesIniciais() {
        Definicoes definicoes = new Definicoes(BigDecimal.valueOf(0), 15);
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            sessao.save(definicoes);
            transacao.commit();
        } catch (HibernateException hibEx) {
            log = new Log("ERROR", hibEx.getMessage());
            logDao.create(log);
        } finally {
            sessao.close();
        }
    }

    public void update(Definicoes definicoes) {
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("from Definicoes");
            List resultado = query.list();
            for (Object obj : resultado) {
                Definicoes definicoes_bd = (Definicoes) obj;
                definicoes_bd.setValorMulta(definicoes.getValorMulta());
                definicoes_bd.setPrazoEmprestimo(definicoes.getPrazoEmprestimo());
                sessao.update(definicoes_bd);
                transacao.commit();
                log = new Log("TRACE", "Definicoes updated");
            }
        } catch (HibernateException hibEx) {
            log = new Log("ERROR", hibEx.getMessage());
        }
        logDao.create(log);
    }

    public Definicoes read() {
        Definicoes definicoes = null;
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Query query = sessao.createQuery("from Definicoes");
            List resultado = query.list();
            for (Object obj : resultado) {
                definicoes = (Definicoes) obj;
            }
        } catch (HibernateException hibEx) {
            log = new Log("ERROR", hibEx.getMessage());
            logDao.create(log);
        }
        return definicoes;
    }
}
