package persistencia;

import easylibmanager.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import negocio.Autor;
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
            JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso!");
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        } finally {
            sessao.close();
        }
    }

    public void update(Livro livro) {
        List resultado = null;
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("select * from livro where id = " + livro.getId());
            resultado = query.list();
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
                JOptionPane.showMessageDialog(null, "Cadastro alterado com sucesso!");
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
    }

    public void delete(Livro livro) {
        List resultado = null;
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("select * from livro where id = " + livro.getId());
            resultado = query.list();
            for (Object obj : resultado) {
                Livro livro_bd = (Livro) obj;
                sessao.delete(livro_bd);
                transacao.commit();
                JOptionPane.showMessageDialog(null, "Cadastro excluído com sucesso!");
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
    }

    public Livro read(int id) {
        List resultado = null;
        Session sessao = null;
        Livro livro = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("select * from livro where id = " + id);
            resultado = query.list();
            for (Object obj : resultado) {
                livro = (Livro) obj;
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
        return livro;
    }

    public ArrayList<Livro> readAll() {
        List resultado = null;
        Session sessao = null;
        ArrayList<Livro> livros = new ArrayList<>();
        Livro livro = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("select * from autor order by id");
            resultado = query.list();
            for (Object obj : resultado) {
                livro = (Livro) obj;
                livros.add(livro);
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
        return livros;
    }

//    @Override
//    public ArrayList<Livro> readFilter(Livro livro) throws DataBaseException {
//        String sql = "SELECT * FROM livro "
//                + "INNER JOIN autor ON livro.autor_id = autor.id "
//                + "INNER JOIN editora ON livro.editora_id = editora.id "
//                + "WHERE 1=1";
//
//        if (livro.getId() != 0) {
//            sql += " AND livro.id = " + livro.getId() + "";
//        }
//        if (livro.getIsbn() != null) {
//            sql += " AND isbn LIKE '%" + livro.getIsbn() + "%'";
//        }
//        if (livro.getAno() != 0) {
//            sql += " AND ano = " + livro.getAno() + "";
//        }
//        if (livro.getTitulo() != null) {
//            sql += " AND titulo ILIKE '%" + livro.getTitulo() + "%'";
//        }
//        if (livro.isDisponivel() != null) {
//            sql += " AND is_disponivel = " + livro.isDisponivel() + "";
//        }
//        if (livro.getAutor() != null) {
//            sql += " AND autor.nome_completo ILIKE '%" + livro.getAutor().getNomeCompleto() + "%'";
//        }
//        if (livro.getEditora() != null) {
//            sql += " AND editora.nome ILIKE '%" + livro.getEditora().getNome() + "%'";
//        }
//        if (livro.getCategoria() != null) {
//            sql += " AND categoria_id = " + livro.getCategoria().getId() + "";
//        }
//
//        ArrayList<Livro> livros = new ArrayList<>();
//        Livro aux = null;
//        try {
//            ResultSet rs = connection.runQuerySQL(sql);
//            if (rs.isBeforeFirst()) {
//                while (rs.next()) {
//                    int id = rs.getInt("id");
//                    String isbn = rs.getString("isbn");
//                    int ano = rs.getInt("ano");
//                    String titulo = rs.getString("titulo");
//                    Boolean isDisponivel = rs.getBoolean("is_disponivel");
//                    int autorId = rs.getInt("autor_id");
//                    int editoraId = rs.getInt("editora_id");
//                    int categoriaId = rs.getInt("categoria_id");
//                    autor = autorDao.read(autorId);
//                    editora = editoraDao.read(editoraId);
//                    categoria = categoriaDao.read(categoriaId);
//                    aux = new Livro(id, isbn, ano, titulo, isDisponivel, autor, editora, categoria);
//                    livros.add(aux);
//                }
//            }
//        } catch (SQLException ex) {
//            throw new DataBaseException(ex.getMessage());
//        }
//        return livros;
//    }
}
