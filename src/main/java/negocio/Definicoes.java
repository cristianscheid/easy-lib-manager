package negocio;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "definicoes")

public class Definicoes {

    @Column(name = "valor_multa")
    private BigDecimal valorMulta;
    @Column(name = "prazo_emprestimo")
    private int prazoEmprestimo;

    public Definicoes(BigDecimal valorMulta, int prazoEmprestimo) {
        this.valorMulta = valorMulta;
        this.prazoEmprestimo = prazoEmprestimo;
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
