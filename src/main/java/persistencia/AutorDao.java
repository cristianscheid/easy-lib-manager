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
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("from Autor where id = " + autor.getId());
            List resultado = query.list();
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
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("from Autor where id = " + autor.getId());
            List resultado = query.list();
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
        Autor autor = null;
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Query query = sessao.createQuery("from Autor where id = " + id);
            List resultado = query.list();
            for (Object obj : resultado) {
                autor = (Autor) obj;
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
        return autor;
    }

    public ArrayList<Autor> readAll() {
        ArrayList<Autor> autores = new ArrayList<>();
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Query query = sessao.createQuery("from Autor");
            List resultado = query.list();
            for (Object obj : resultado) {
                Autor autor = (Autor) obj;
                autores.add(autor);
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
        return autores;
    }

    public Autor readName(String nomeCompleto) {
        Autor autor = null;
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Query query = sessao.createQuery("from Autor where nome_completo = " + nomeCompleto);
            List resultado = query.list();
            for (Object obj : resultado) {
                autor = (Autor) obj;
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
        return autor;
    }
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

    public ArrayList<Autor> readFilter(Autor autor) {
        String sql = "from autor where 1=1";
        if (autor.getNomeCompleto() != null) {
            sql += " and lower(nome_completo) like lower('%" + autor.getNomeCompleto() + "%')";
        }

        ArrayList<Autor> autores = new ArrayList<>();
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Query query = sessao.createQuery(sql);
            List resultado = query.list();
            for (Object obj : resultado) {
                autor = (Autor) obj;
                autores.add(autor);
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
        return autores;
    }
}

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
