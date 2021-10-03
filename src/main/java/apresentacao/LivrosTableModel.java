package apresentacao;

import negocio.Livro;
import java.util.ArrayList;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class LivrosTableModel implements TableModel {

    private ArrayList<Livro> livros;

    public LivrosTableModel(ArrayList<Livro> livros) {
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
        return 8;
    }

    @Override
    public String getColumnName(int columnIndex) {
        String[] vet
                = {
                    "Id", "Disponível", "ISBN", "Ano", "Título", "Autor", "Editora", "Categoria"
                };
        return vet[columnIndex];
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
                    aux.getId(), aux.isDisponivelString(), aux.getIsbn(), aux.getAno(), aux.getTitulo(),
                    aux.getAutor().getNomeCompleto(), aux.getEditora().getNome(), aux.getCategoria().getNome()
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
