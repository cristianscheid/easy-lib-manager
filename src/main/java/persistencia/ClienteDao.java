package persistencia;

import easylibmanager.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import negocio.Autor;
import negocio.Cliente;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ClienteDao {

    public void create(Cliente cliente) {
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            sessao.save(cliente);
            transacao.commit();
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
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
                sessao.update(cliente_bd);
                transacao.commit();
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
    }

    public void delete(Cliente cliente) {
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("from Cliente where id = " + cliente.getId());
            List resultado = query.list();
            for (Object obj : resultado) {
                Autor autor_bd = (Autor) obj;
                sessao.delete(autor_bd);
                transacao.commit();
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
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
            hibEx.printStackTrace();
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
            hibEx.printStackTrace();
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
            hibEx.printStackTrace();
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
            hibEx.printStackTrace();
        }
        return cliente;
    }
}
//    public ArrayList read(Filter filter) throws DataBaseException
//    {
//        ArrayList<Cliente> clientesFiltrados = new ArrayList();
//        ArrayList<Cliente> clientes = this.readAll();
//        for (Cliente cliente : clientes)
//        {
//            if (filter.isApproved(cliente))
//            {
//                clientesFiltrados.add(cliente);
//            }
//        }
//        return clientesFiltrados;
//    }
//
//    public Cliente readName(String name) throws DataBaseException
//    {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
