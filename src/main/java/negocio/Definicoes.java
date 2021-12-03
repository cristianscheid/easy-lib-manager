package negocio;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.math.BigDecimal;

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
