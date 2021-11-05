package persistencia;

import easylibmanager.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;
import negocio.Definicoes;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DefinicoesDao {

    private static final Logger logger = LogManager.getLogger(ClienteDao.class);

    public void createDefinicoesIniciais() {
        Definicoes definicoes = new Definicoes(BigDecimal.valueOf(0), 15);
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            sessao.save(definicoes);
            transacao.commit();
        } catch (HibernateException hibEx) {
            logger.error(hibEx.getMessage(), hibEx);
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
                logger.trace("Definicoes updated");
            }
        } catch (HibernateException hibEx) {
            logger.error(hibEx.getMessage(), hibEx);
        }
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
            logger.error(hibEx.getMessage(), hibEx);
        }
        return definicoes;
    }
}
