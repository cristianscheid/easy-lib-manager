package persistencia;

import easylibmanager.HibernateUtil;
import negocio.Log;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogDao {

    public void create(Log log) {
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            sessao.save(log);
            transacao.commit();
        } catch (HibernateException hibEx) {
            System.out.println(hibEx);
            System.out.println(hibEx.getMessage());
        } finally {
            sessao.close();
        }
    }

    public ArrayList<Log> readAll() {
        ArrayList<Log> logs = new ArrayList<>();
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Query query = sessao.createQuery("from Log");
            List resultado = query.list();
            for (Object obj : resultado) {
                Log log = (Log) obj;
                logs.add(log);
            }
        } catch (HibernateException hibEx) {
            System.out.println(hibEx);
            System.out.println(hibEx.getMessage());
        }
        return logs;
    }

    public ArrayList<Log> readLogsPeriodo(ArrayList<Log> logs, Date dataInicial, Date dataFinal) {
        ArrayList<Log> logsPeriodo = new ArrayList<>();
        dataInicial.setHours(0);
        dataInicial.setMinutes(0);
        dataInicial.setSeconds(0);
        dataFinal.setHours(23);
        dataFinal.setMinutes(59);
        dataFinal.setSeconds(59);
        for (Log log : logs) {
            Date dataLog = Date.from(log.getDataHora().atZone(ZoneId.systemDefault()).toInstant());
            if (dataLog.compareTo(dataInicial) >= 0 && dataLog.compareTo(dataFinal) <= 0) {
                logsPeriodo.add(log);
            }
        }
        return logsPeriodo;
    }

    public ArrayList<Log> readLogsTipo(ArrayList<Log> logs, String tipo) {
        ArrayList<Log> logsTipo = new ArrayList<>();
        for (Log log : logs) {
            if (log.getTipo().equals(tipo)) {
                logsTipo.add(log);
            }
        }
        return logsTipo;
    }
}
