package persistencia;

import easylibmanager.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import negocio.Cliente;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClienteDao {

    private static final Logger logger = LogManager.getLogger(ClienteDao.class);

    public void create(Cliente cliente) {
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            sessao.save(cliente);
            transacao.commit();
            logger.trace("Cliente " + cliente.getId() + " created");
        } catch (HibernateException hibEx) {
            logger.error(hibEx.getMessage(), hibEx);
        } finally {
            sessao.close();
        }
    }

    public void update(Cliente cliente) {
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("from Cliente where id = " + cliente.getId());
            List resultado = query.list();
            for (Object obj : resultado) {
                Cliente cliente_bd = (Cliente) obj;
                cliente_bd.setId(cliente.getId());
                cliente_bd.setNome(cliente.getNome());
                cliente_bd.setSobrenome(cliente.getSobrenome());
                cliente_bd.setCpf(cliente.getCpf());
                cliente_bd.setEmail(cliente.getEmail());
                cliente_bd.setTelefone(cliente.getTelefone());
                cliente_bd.setCelular(cliente.getCelular());
                cliente_bd.setExcluido(cliente.getExcluido());
                sessao.update(cliente_bd);
                transacao.commit();
                logger.trace("Cliente " + cliente.getId() + " updated");
            }
        } catch (HibernateException hibEx) {
            logger.error(hibEx.getMessage(), hibEx);
        }
    }

    public void delete(Cliente cliente) {
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("from Cliente where id = " + cliente.getId());
            List resultado = query.list();
            for (Object obj : resultado) {
                Cliente cliente_bd = (Cliente) obj;
                sessao.delete(cliente_bd);
                transacao.commit();
                logger.trace("Cliente " + cliente.getId() + " deleted");
            }
        } catch (HibernateException hibEx) {
            logger.error(hibEx.getMessage(), hibEx);
        }
    }

    public Cliente read(int id) {
        Cliente cliente = null;
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Query query = sessao.createQuery("from Cliente where id = " + cliente.getId());
            List resultado = query.list();
            for (Object obj : resultado) {
                cliente = (Cliente) obj;
            }
        } catch (HibernateException hibEx) {
            logger.error(hibEx.getMessage(), hibEx);
        }
        return cliente;
    }

    public ArrayList<Cliente> readAll() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Query query = sessao.createQuery("from Cliente");
            List resultado = query.list();
            for (Object obj : resultado) {
                Cliente cliente = (Cliente) obj;
                clientes.add(cliente);
            }
        } catch (HibernateException hibEx) {
            logger.error(hibEx.getMessage(), hibEx);
        }
        return clientes;
    }

    public ArrayList<Cliente> readAllSemExcluidos() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Query query = sessao.createQuery("from Cliente");
            List resultado = query.list();
            for (Object obj : resultado) {
                Cliente cliente = (Cliente) obj;
                if (!cliente.getExcluido()) {
                    clientes.add(cliente);
                }
            }
        } catch (HibernateException hibEx) {
            logger.error(hibEx.getMessage(), hibEx);
        }
        return clientes;
    }

    public ArrayList<Cliente> readFilter(Cliente cliente) {
        String sql = "from Cliente where 1=1";
        if (cliente.getId() != 0) {
            sql += " and id = " + cliente.getId() + "";
        }
        if (cliente.getNome() != null) {
            sql += " and lower(nome) like lower('%" + cliente.getNome() + "%')";
        }
        if (cliente.getSobrenome() != null) {
            sql += " and lower(sobrenome) like lower('%" + cliente.getSobrenome() + "%')";
        }
        if (cliente.getEmail() != null) {
            sql += " and lower(email) like lower('%" + cliente.getEmail() + "%')";
        }
        if (cliente.getCpf() != null) {
            sql += " and cpf like '%" + cliente.getCpf() + "%'";
        }
        if (cliente.getTelefone() != null) {
            sql += " and telefone like '%" + cliente.getTelefone() + "%'";
        }
        if (cliente.getCelular() != null) {
            sql += " and celular like '%" + cliente.getCelular() + "%'";
        }
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Query query = sessao.createQuery(sql);
            List resultado = query.list();
            for (Object obj : resultado) {
                cliente = (Cliente) obj;
                clientes.add(cliente);
            }
        } catch (HibernateException hibEx) {
            logger.error(hibEx.getMessage(), hibEx);
        }
        return clientes;
    }

    public Cliente readCpf(String cpf) {
        Cliente cliente = null;
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Query query = sessao.createQuery("from Cliente where cpf = '" + cpf + "'");
            List resultado = query.list();
            for (Object obj : resultado) {
                cliente = (Cliente) obj;
            }
        } catch (HibernateException hibEx) {
            logger.error(hibEx.getMessage(), hibEx);
        }
        return cliente;
    }
}
