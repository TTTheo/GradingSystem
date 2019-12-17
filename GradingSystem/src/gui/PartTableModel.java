package gui;

import objects.Part;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class PartTableModel extends AbstractTableModel {
    private ArrayList<Part> parts;
    private String[] columnNames;

    public PartTableModel () {
        this(new ArrayList<Part>(), new String[]{});
    }

    public PartTableModel (ArrayList<Part> parts, String[] columnNames) {
        this.parts = parts;
        this.columnNames = columnNames;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Class getColumnClass(int columnNum) {
        return getValueAt(0, columnNum).getClass();
    }
    @Override
    public int getRowCount() {
        return parts.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = "";
        Part c = parts.get(rowIndex);

        switch (columnIndex) {
            case 0:
                value = c.getName();
                break;
            case 1:
                value = c.getPercentage();
                break;
            case 2:
                value = c.getTotalScore();
                break;
        }
        return value;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return true;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        Part c = parts.get(row);
        switch (col) {
            case 0:
                c.setName((String) value);
                break;
            case 1:
                double oldPercent = c.getPercentage();
                double newPercent = (Double) value;
                c.setPercentage(newPercent);
                if (getTotalPercent() > 100.0) {
                    alert("Percentages do not add to 100!");
                    c.setPercentage(oldPercent);
                    return; // don't fire
                }
                // else, everything is fine
                break;
            case 2:
                c.setTotalScore((Double) value);
                break;
        }
        fireTableCellUpdated(row, col);
    }

    // Custom methods for convenience

    public Part getPartAt(int rowIndex) {
        return parts.get(rowIndex);
    }

    public void addRow(Part c) {
        int rowIndex = parts.size();
        parts.add(rowIndex, c);
        fireTableRowsInserted(rowIndex, rowIndex);
    }

    public void deleteRow(Part c) {
        int index=parts.indexOf(c);
        parts.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public double getTotalPercent() {
        double total = 0.0;
        for (Part p: parts) {
            total += p.getPercentage();
        }
        return total;
    }

    public void alert(String message){
        JOptionPane.showMessageDialog(null, message);
    }
}
