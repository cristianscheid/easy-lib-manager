package apresentacao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import negocio.Definicoes;
import negocio.Emprestimo;
import persistencia.DefinicoesDao;

public class TableModelEmprestimo implements TableModel {

    private ArrayList<Emprestimo> emprestimos;
    String[] colunas = {"Id", "Cliente", "Livro", "Data empréstimo", "Data devolução", "Qtd dias", "Multa"};
    Definicoes definicoes;
    Date dataAtual;

    public TableModelEmprestimo(ArrayList<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
        DefinicoesDao definicoesDao = new DefinicoesDao();
        this.definicoes = definicoesDao.read();
        this.dataAtual = new Date();
    }

    public ArrayList<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(ArrayList<Emprestimo> clientes) {
        this.emprestimos = clientes;
    }

    @Override
    public int getRowCount() {
        return emprestimos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
            return Integer.class;
        }
        return String.class;
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Emprestimo aux = emprestimos.get(rowIndex);
        int diasAtraso;
        int diasEmprestado;
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        String dataEmprestimo = dateFormatter.format(aux.getDataEmprestimo());
        String dataDevolucao;
        BigDecimal valorMulta = null;
        String valorMultaString = "-";
        if (aux.getDataDevolucao() == null) {
            dataDevolucao = "-";
            diasEmprestado = (int) ((dataAtual.getTime() - aux.getDataEmprestimo().getTime()) / (1000 * 60 * 60 * 24));
            if (diasEmprestado > definicoes.getPrazoEmprestimo()) {
                diasAtraso = diasEmprestado - definicoes.getPrazoEmprestimo();
                valorMulta = definicoes.getValorMulta().multiply(new BigDecimal(diasAtraso));
                valorMultaString = "R$ " + String.valueOf(valorMulta);
            }
        } else {
            diasEmprestado = (int) ((aux.getDataDevolucao().getTime() - aux.getDataEmprestimo().getTime()) / (1000 * 60 * 60 * 24));
            dataDevolucao = dateFormatter.format(aux.getDataDevolucao());
        }

        Object[] vet
                = {
                    aux.getId(),
                    aux.getCliente().getId() + " - " + aux.getCliente().getNome(),
                    aux.getLivro().getId() + " - " + aux.getLivro().getTitulo(),
                    dataEmprestimo,
                    dataDevolucao,
                    diasEmprestado,
                    valorMultaString
                };
        return vet[columnIndex];
    }

    @Override
    public void setValueAt(Object o, int i, int i1
    ) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addTableModelListener(TableModelListener tl
    ) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeTableModelListener(TableModelListener tl
    ) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
