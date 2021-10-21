package negocio;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.envers.Audited;
import org.hibernate.envers.AuditTable;
        
@Audited
@Entity
@Table(name = "definicoes")

public class Definicoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "valor_multa")
    private BigDecimal valorMulta;
    @Column(name = "prazo_emprestimo")
    private int prazoEmprestimo;

    public Definicoes(BigDecimal valorMulta, int prazoEmprestimo) {
        this.id = 0;
        this.valorMulta = valorMulta;
        this.prazoEmprestimo = prazoEmprestimo;
    }

    public Definicoes() {
    }

    public BigDecimal getValorMulta() {
        return valorMulta;
    }

    public void setValorMulta(BigDecimal valorMulta) {
        this.valorMulta = valorMulta;
    }

    public int getPrazoEmprestimo() {
        return prazoEmprestimo;
    }

    public void setPrazoEmprestimo(int prazoEmprestimo) {
        this.prazoEmprestimo = prazoEmprestimo;
    }

}
