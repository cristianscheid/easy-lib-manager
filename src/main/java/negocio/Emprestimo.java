package negocio;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.envers.Audited;
import org.hibernate.envers.AuditTable;
        
@Audited
@Entity
@Table(name = "emprestimo")

public class Emprestimo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
//    @Column(name = "data_emprestimo")
    @Temporal(TemporalType.DATE)
    Date data_emprestimo;
//    @Column(name = "data_devolucao")
    @Temporal(TemporalType.DATE)
    Date data_devolucao;
    @OneToOne
    Cliente cliente;
    @OneToOne
    Livro livro;

    public Emprestimo(int id, Date data_emprestimo, Date data_devolucao, Cliente cliente, Livro livro) {
        this.id = id;
        this.data_emprestimo = data_emprestimo;
        this.data_devolucao = data_devolucao;
        this.cliente = cliente;
        this.livro = livro;
    }

    public Emprestimo(Date data_emprestimo, Date data_devolucao, Cliente cliente, Livro livro) {
        this.data_emprestimo = data_emprestimo;
        this.data_devolucao = data_devolucao;
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

    public Date getData_emprestimo() {
        return data_emprestimo;
    }

    public void setData_emprestimo(Date data_emprestimo) {
        this.data_emprestimo = data_emprestimo;
    }

    public Date getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(Date data_devolucao) {
        this.data_devolucao = data_devolucao;
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
