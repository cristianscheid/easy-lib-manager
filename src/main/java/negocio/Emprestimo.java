package negocio;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Audited
@Entity
@Table(name = "emprestimo")

public class Emprestimo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "data_emprestimo")
    @Temporal(TemporalType.DATE)
    Date dataEmprestimo;
    @Column(name = "data_devolucao")
    @Temporal(TemporalType.DATE)
    Date dataDevolucao;
    @OneToOne
    Cliente cliente;
    @OneToOne
    Livro livro;

    public Emprestimo(int id, Date data_emprestimo, Date data_devolucao, Cliente cliente, Livro livro) {
        this.id = id;
        this.dataEmprestimo = data_emprestimo;
        this.dataDevolucao = data_devolucao;
        this.cliente = cliente;
        this.livro = livro;
    }

    public Emprestimo(Date data_emprestimo, Date data_devolucao, Cliente cliente, Livro livro) {
        this.dataEmprestimo = data_emprestimo;
        this.dataDevolucao = data_devolucao;
        this.cliente = cliente;
        this.livro = livro;
    }

    public Emprestimo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

}
