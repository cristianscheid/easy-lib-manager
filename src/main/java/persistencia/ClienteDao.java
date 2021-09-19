package persistencia;

import easylibmanager.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
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
            JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso!");
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        } finally {
            sessao.close();
        }
    }

    public void update(Cliente cliente) {
        List resultado = null;
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("select * from cliente where id = " + cliente.getId());
            resultado = query.list();
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
                JOptionPane.showMessageDialog(null, "Cadastro alterado com sucesso!");
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
    }

    public void delete(Cliente cliente) {
        List resultado = null;
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("select * from cliente where id = " + cliente.getId());
            resultado = query.list();
            for (Object obj : resultado) {
                Autor autor_bd = (Autor) obj;
                sessao.delete(autor_bd);
                transacao.commit();
                JOptionPane.showMessageDialog(null, "Cadastro excluído com sucesso!");
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
    }

    public Cliente read(int id) {
        List resultado = null;
        Session sessao = null;
        Cliente cliente = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("select * from cliente where id = " + cliente.getId());
            resultado = query.list();
            for (Object obj : resultado) {
                cliente = (Cliente) obj;
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
        return cliente;
    }

    public ArrayList<Cliente> readAll() {
        List resultado = null;
        Session sessao = null;
        ArrayList<Cliente> clientes = new ArrayList<>();
        Cliente cliente = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("select * from cliente where id = " + cliente.getId());
            resultado = query.list();
            for (Object obj : resultado) {
                cliente = (Cliente) obj;
                clientes.add(cliente);
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
        return clientes;
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
//
//    public ArrayList<Cliente> readFilter(Cliente cliente) throws DataBaseException
//    {
//        String sql = "SELECT * FROM cliente WHERE 1=1";
//        if (cliente.getId() != 0)
//        {
//            sql += " AND id = " + cliente.getId() + "";
//        }
//        if (cliente.getNome() != null)
//        {
//            sql += " AND nome ILIKE '%" + cliente.getNome() + "%'";
//        }
//        if (cliente.getSobrenome() != null)
//        {
//            sql += " AND sobrenome ILIKE '%" + cliente.getSobrenome() + "%'";
//        }
//        if (cliente.getEmail() != null)
//        {
//            sql += " AND email ILIKE '%" + cliente.getEmail() + "%'";
//        }
//        if (cliente.getCpf() != null)
//        {
//            sql += " AND cpf LIKE '%" + cliente.getCpf() + "%'";
//        }
//        if (cliente.getTelefone() != null)
//        {
//            sql += " AND telefone LIKE '%" + cliente.getTelefone() + "%'";
//        }
//        if (cliente.getCelular() != null)
//        {
//            sql += " AND celular LIKE '%" + cliente.getCelular() + "%'";
//        }
//
//        ArrayList<Cliente> clientes = new ArrayList<>();
//        Cliente aux = null;
//        try
//        {
//            ResultSet rs = connection.runQuerySQL(sql);
//            if (rs.isBeforeFirst())
//            {
//                while (rs.next())
//                {
//                    int id = rs.getInt("id");
//                    String nome = rs.getString("nome");
//                    String sobrenome = rs.getString("sobrenome");
//                    String cpf = rs.getString("cpf");
//                    String email = rs.getString("email");
//                    String telefone = rs.getString("telefone");
//                    String celular = rs.getString("celular");
//                    aux = new Cliente(id, nome, sobrenome, cpf, email, telefone, celular);
//                    clientes.add(aux);
//                }
//            }
//        } catch (SQLException ex)
//        {
//            throw new DataBaseException(ex.getMessage());
//        }
//        return clientes;
//    }
//
//    public Cliente readCpf(String cpf) throws DataBaseException
//    {
//        String sql = "SELECT * FROM cliente WHERE cpf = '" + cpf + "'";
//        Cliente cliente = null;
//        try
//        {
//            ResultSet rs = connection.runQuerySQL(sql);
//
//            if (rs.isBeforeFirst())
//            {
//                while (rs.next())
//                {
//                    int id = rs.getInt("id");
//                    String nome = rs.getString("nome");
//                    String sobrenome = rs.getString("sobrenome");
//                    String email = rs.getString("email");
//                    String telefone = rs.getString("telefone");
//                    String celular = rs.getString("celular");
//                    cliente = new Cliente(id, nome, sobrenome, cpf, email, telefone, celular);
//                }
//            }
//        } catch (SQLException ex)
//        {
//            throw new DataBaseException(ex.getMessage());
//        }
//        return cliente;
//
//    }
}
