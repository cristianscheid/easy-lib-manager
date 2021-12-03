package negocio;

import org.hibernate.envers.Audited;

import javax.persistence.*;

@Audited
@Entity
@Table(name = "livro")

public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "isbn")
    String isbn;
    @Column(name = "ano")
    int ano;
    @Column(name = "titulo")
    String titulo;
    @Column(name = "disponivel")
    Boolean Disponivel;
    @OneToOne
    Autor autor;
    @OneToOne
    Editora editora;
    @OneToOne
    Categoria categoria;
    @Column(name = "excluido")
    Boolean Excluido;

    public Livro(int id, String isbn, int ano, String titulo, Boolean Disponivel, Autor autor, Editora editora, Categoria categoria, Boolean Excluido) {
        this.id = id;
        this.isbn = isbn;
        this.ano = ano;
        this.titulo = titulo;
        this.Disponivel = Disponivel;
        this.autor = autor;
        this.editora = editora;
        this.categoria = categoria;
        this.Excluido = Excluido;
    }

    public Livro(String isbn, int ano, String titulo, Boolean Disponivel, Autor autor, Editora editora, Categoria categoria, Boolean Excluido) {
        this.isbn = isbn;
        this.ano = ano;
        this.titulo = titulo;
        this.Disponivel = Disponivel;
        this.autor = autor;
        this.editora = editora;
        this.categoria = categoria;
        this.Excluido = Excluido;
    }

    public Livro() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Boolean getDisponivel() {
        return Disponivel;
    }

    public void setDisponivel(Boolean Disponivel) {
        this.Disponivel = Disponivel;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Boolean getExcluido() {
        return Excluido;
    }

    public void setExcluido(Boolean Excluido) {
        this.Excluido = Excluido;
    }

    public String getDisponivelString() {
        if (Excluido) {
            return "Excluído";
        } else if (Disponivel) {
            return "Sim";
        } else {
            return "Não";
        }
    }

}
