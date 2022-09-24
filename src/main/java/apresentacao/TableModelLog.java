package apresentacao;

import negocio.Log;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TableModelLog implements TableModel {

    private ArrayList<Log> logs;
    private String[] colunas = {"Id", "Usuario", "IP", "Tipo", "Data/Hora", "Mensagem"};

    public TableModelLog(ArrayList<Log> logs) {
        this.logs = logs;
    }

    public ArrayList<Log> getLogs() {
        return logs;
    }

    public void setLogs(ArrayList<Log> logs) {
        this.logs = logs;
    }

    @Override
    public int getRowCount() {
        return logs.size();
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
        Log aux = logs.get(rowIndex);
//        colunas = {"Id", "Usuario", "IP", "Tipo", "Data/Hora", "Mensagem"};
        Object[] vet
                = {
                    aux.getId(),
                    aux.getUsuario().getNome(),
                    aux.getIp(),
                    aux.getTipo(),
                    aux.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm:ss")),
                    aux.getMensagem()
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
