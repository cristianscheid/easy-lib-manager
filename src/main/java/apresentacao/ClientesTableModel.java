package apresentacao;

import negocio.Cliente;
import java.util.ArrayList;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class ClientesTableModel implements TableModel {

    private ArrayList<Cliente> clientes;

    public ClientesTableModel(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public String getColumnName(int columnIndex) {
        String[] vet
                = {
                    "Id", "Nome", "Sobrenome", "CPF", "Email", "Telefone", "Celular"
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
        Cliente aux = clientes.get(rowIndex);
        Object[] vet
                = {
                    aux.getId(), aux.getNome(), aux.getSobrenome(), aux.getCpf(), aux.getEmail(), aux.getTelefone(), aux.getCelular()
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
