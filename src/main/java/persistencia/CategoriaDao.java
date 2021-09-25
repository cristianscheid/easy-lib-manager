package persistencia;

import easylibmanager.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import negocio.Categoria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CategoriaDao {

    public Categoria read(int id) {
        Categoria categoria = null;
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Query query = sessao.createQuery("from Categoria where id = " + categoria.getId());
            List resultado = query.list();
            for (Object obj : resultado) {
                categoria = (Categoria) obj;
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
        return categoria;
    }

    public ArrayList<Categoria> readAll() {
        ArrayList<Categoria> categorias = new ArrayList<>();
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Query query = sessao.createQuery("from Categoria");
            List resultado = query.list();
            for (Object obj : resultado) {
                Categoria categoria = (Categoria) obj;
                categorias.add(categoria);
            }
        } catch (HibernateException hibEx) {
            hibEx.printStackTrace();
        }
        return categorias;
    }

    public void createCategoriasIniciais() {
        Session sessao = null;
        ArrayList<String> categoriasIniciais = new ArrayList<>();
        categoriasIniciais.add("Selecione");
        categoriasIniciais.add("Biografias");
        categoriasIniciais.add("Coleções");
        categoriasIniciais.add("Comportamento");
        categoriasIniciais.add("Contos");
        categoriasIniciais.add("Crítica Literária");
        categoriasIniciais.add("Ficção Científica");
        categoriasIniciais.add("Folclore");
        categoriasIniciais.add("Genealogia");
        categoriasIniciais.add("Humor");
        categoriasIniciais.add("Infantojuvenis");
        categoriasIniciais.add("Jogos");
        categoriasIniciais.add("Jornais");
        categoriasIniciais.add("Literatura Brasileira");
        categoriasIniciais.add("Literatura Estrangeira");
        categoriasIniciais.add("Livros Raros");
        categoriasIniciais.add("Manuscritos");
        categoriasIniciais.add("Poesia");
        categoriasIniciais.add("Outros Assuntos");
        categoriasIniciais.add("Administração");
        categoriasIniciais.add("Agricultura");
        categoriasIniciais.add("Antropologia");
        categoriasIniciais.add("Arqueologia");
        categoriasIniciais.add("Arquitetura");
        categoriasIniciais.add("Artes");
        categoriasIniciais.add("Astronomia");
        categoriasIniciais.add("Biologia");
        categoriasIniciais.add("Botânica");
        categoriasIniciais.add("Brasil");
        categoriasIniciais.add("Ciência Política");
        categoriasIniciais.add("Ciências Exatas");
        categoriasIniciais.add("Cinema");
        categoriasIniciais.add("Comunicação");
        categoriasIniciais.add("Contabilidade");
        categoriasIniciais.add("Decoração");
        categoriasIniciais.add("Dicionários");
        categoriasIniciais.add("Didáticos");
        categoriasIniciais.add("Direito");
        categoriasIniciais.add("Documentos");
        categoriasIniciais.add("Ecologia");
        categoriasIniciais.add("Economia");
        categoriasIniciais.add("Engenharia");
        categoriasIniciais.add("Enciclopédias");
        categoriasIniciais.add("Ensino de Idiomas");
        categoriasIniciais.add("Filosofia");
        categoriasIniciais.add("Fotografia");
        categoriasIniciais.add("Geografia");
        categoriasIniciais.add("Guerra");
        categoriasIniciais.add("História do Brasil");
        categoriasIniciais.add("História Geral");
        categoriasIniciais.add("Informática");
        categoriasIniciais.add("Linguística");
        categoriasIniciais.add("Medicina");
        categoriasIniciais.add("Moda");
        categoriasIniciais.add("Música");
        categoriasIniciais.add("Pecuária");
        categoriasIniciais.add("Pedagogia");
        categoriasIniciais.add("Pintura");
        categoriasIniciais.add("Psicologia");
        categoriasIniciais.add("Saúde");
        categoriasIniciais.add("Sociologia");
        categoriasIniciais.add("Teatro");
        categoriasIniciais.add("Turismo");
        categoriasIniciais.add("Artesanato");
        categoriasIniciais.add("Auto ajuda");
        categoriasIniciais.add("Culinária");
        categoriasIniciais.add("Esoterismo");
        categoriasIniciais.add("Esportes");
        categoriasIniciais.add("Hobbies");
        categoriasIniciais.add("Religião");
        categoriasIniciais.add("Sexualidade");
        categoriasIniciais.add("Revistas");
        categoriasIniciais.add("Gibis");
        for (String categoriaInicial : categoriasIniciais) {
            Categoria categoria = new Categoria(categoriaInicial);
            try {
                sessao = HibernateUtil.getSessionFactory().openSession();
                Transaction transacao = sessao.beginTransaction();
                sessao.save(categoria);
                transacao.commit();
            } catch (HibernateException hibEx) {
                hibEx.printStackTrace();
            } finally {
                sessao.close();
            }
        }
    }
}
