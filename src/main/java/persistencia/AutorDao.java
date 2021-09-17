package persistencia;

import easylibmanager.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import negocio.Autor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AutorDao {

    public void create(Autor autor) {
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            sessao.save(autor);
            transacao.commit();
            JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso!");
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        } finally {
            sessao.close();
        }
    }

    public void update(Autor autor) {
        List resultado = null;
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("select * from autor where id = " + autor.getId());
            resultado = query.list();
            for (Object obj : resultado) {
                Autor autor_bd = (Autor) obj;
                autor_bd.setId(autor.getId());
                autor_bd.setNomeCompleto(autor.getNomeCompleto());
                sessao.update(autor_bd);
                transacao.commit();
                JOptionPane.showMessageDialog(null, "Cadastro alterado com sucesso!");
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
    }

    public void delete(Autor autor) {
        List resultado = null;
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("select * from autor where id = " + autor.getId());
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

    public Autor read(int id) {
        List resultado = null;
        Session sessao = null;
        Autor autor = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("select * from autor where id = " + autor.getId());
            resultado = query.list();
            for (Object obj : resultado) {
                autor = (Autor) obj;
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
        return autor;
    }

    public ArrayList<Autor> readAll() {

        List resultado = null;
        Session sessao = null;
        ArrayList<Autor> autores = new ArrayList<>();
        Autor autor = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("select * from autor where id = " + autor.getId());
            resultado = query.list();
            for (Object obj : resultado) {
                autor = (Autor) obj;
                autores.add(autor);
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
        return autores;
    }

//        public Autor readName(String nomeCompleto) throws DataBaseException {
//        String sql = "SELECT * FROM autor WHERE nome_completo = '" + nomeCompleto + "'";
//        Autor autor = null;
//        try {
//            ResultSet rs = connection.runQuerySQL(sql);
//
//            if (rs.isBeforeFirst()) {
//                rs.next();
//                int id_autor = rs.getInt("id");
//                autor = new Autor(id_autor, nomeCompleto);
//            }
//
//        } catch (SQLException ex) {
//            throw new DataBaseException(ex.getMessage());
//        }
//        return autor;
//    }
//    public ArrayList<Autor> readFilter(Autor autor) throws DataBaseException {
//        String sql = "SELECT * FROM autor WHERE 1=1";
//        if (autor.getNomeCompleto() != null) {
//            sql += " AND nome_completo ILIKE '%" + autor.getNomeCompleto() + "%'";
//        }
//        ArrayList<Autor> autores = new ArrayList<>();
//        Autor aux = null;
//        try {
//            ResultSet rs = connection.runQuerySQL(sql);
//            if (rs.isBeforeFirst()) {
//                while (rs.next()) {
//                    int id = rs.getInt("id");
//                    String NomeCompleto = rs.getString("nome_completo");
//                    aux = new Autor(id, NomeCompleto);
//                    autores.add(aux);
//                }
//            }
//        } catch (SQLException ex) {
//            throw new DataBaseException(ex.getMessage());
//        }
//        return autores;
//    }
}
