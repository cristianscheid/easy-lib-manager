package apresentacao;

import negocio.Livro;
import java.util.ArrayList;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class TableModelLivro implements TableModel {

    private ArrayList<Livro> livros;
    private String[] colunas = {"Id", "Disponível", "ISBN", "Ano", "Título", "Autor", "Editora", "Categoria"};

    public TableModelLivro(ArrayList<Livro> livros) {
        this.livros = livros;
    }

    public ArrayList<Livro> getLivros() {
        return livros;
    }

    public void setLivros(ArrayList<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public int getRowCount() {
        return livros.size();
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
        Livro aux = livros.get(rowIndex);
        Object[] vet
                = {
                    aux.getId(),
                    aux.getDisponivelString(),
                    aux.getIsbn(),
                    aux.getAno(),
                    aux.getTitulo(),
                    aux.getAutor().getNomeCompleto(),
                    aux.getEditora().getNome(),
                    aux.getCategoria().getNome(),
                };
        return vet[columnIndex];
    }

    @Override
    public void setValueAt(Object o, int i, int i1) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addTableModelListener(TableModelListener tl) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeTableModelListener(TableModelListener tl) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
