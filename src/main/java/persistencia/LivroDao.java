package persistencia;

import easylibmanager.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import negocio.Livro;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LivroDao {

    public void create(Livro livro) {
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            sessao.save(livro);
            transacao.commit();
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        } finally {
            sessao.close();
        }
    }

    public void update(Livro livro) {
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("from Livro where id = " + livro.getId());
            List resultado = query.list();
            for (Object obj : resultado) {
                Livro livro_bd = (Livro) obj;
                livro_bd.setId(livro.getId());
                livro_bd.setIsbn(livro.getIsbn());
                livro_bd.setAno(livro.getAno());
                livro_bd.setTitulo(livro.getTitulo());
                livro_bd.setDisponivel(livro.isDisponivel());
                livro_bd.setAutor(livro.getAutor());
                livro_bd.setEditora(livro.getEditora());
                livro_bd.setCategoria(livro.getCategoria());
                sessao.update(livro_bd);
                transacao.commit();
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
    }

    public void delete(Livro livro) {
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("from Livro where id = " + livro.getId());
            List resultado = query.list();
            for (Object obj : resultado) {
                Livro livro_bd = (Livro) obj;
                sessao.delete(livro_bd);
                transacao.commit();
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
    }

    public Livro read(int id) {
        Livro livro = null;
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Query query = sessao.createQuery("from Livro where id = " + id);
            List resultado = query.list();
            for (Object obj : resultado) {
                livro = (Livro) obj;
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
        return livro;
    }

    public ArrayList<Livro> readAll() {
        ArrayList<Livro> livros = new ArrayList<>();
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Query query = sessao.createQuery("from Livro order by id");
            List resultado = query.list();
            for (Object obj : resultado) {
                Livro livro = (Livro) obj;
                livros.add(livro);
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
        return livros;
    }

    public ArrayList<Livro> readFilter(Livro livro) {
        String sql = "from Livro where 1=1";
        if (livro.getId() != 0) {
            sql += " and id = " + livro.getId() + "";
        }
        if (livro.getIsbn() != null) {
            sql += " and isbn like '%" + livro.getIsbn() + "%'";
        }
        if (livro.getAno() != 0) {
            sql += " and ano = " + livro.getAno() + "";
        }
        if (livro.getTitulo() != null) {
            sql += " and lower(titulo) like lower('%" + livro.getTitulo() + "%')";
        }
        if (livro.isDisponivel() != null) {
            sql += " and is_disponivel = " + livro.isDisponivel() + "";
        }
        if (livro.getAutor() != null) {
            sql += " and lower(autor.nome_completo) like lower('%" + livro.getAutor().getNomeCompleto() + "%')";
        }
        if (livro.getEditora() != null) {
            sql += " and lower(editora.nome) like lower('%" + livro.getEditora().getNome() + "%')";
        }
        if (livro.getCategoria() != null) {
            sql += " and categoria_id = " + livro.getCategoria().getId() + "";
        }
        ArrayList<Livro> livros = new ArrayList<>();
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Query query = sessao.createQuery(sql);
            List resultado = query.list();
            for (Object obj : resultado) {
                livro = (Livro) obj;
                livros.add(livro);
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
        return livros;
    }
}
