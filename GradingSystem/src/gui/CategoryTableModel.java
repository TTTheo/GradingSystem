package gui;

import objects.Category;
import objects.Part;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class CategoryTableModel extends AbstractTableModel {
    private ArrayList<Category> categories;
    private String[] columnNames;

    public CategoryTableModel () {
        this(new ArrayList<Category>(), new String[]{});
    }

    public CategoryTableModel (ArrayList<Category> categories, String[] columnNames) {
        this.categories = categories;
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
        return categories.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = "";
        Category c = categories.get(rowIndex);

        switch (columnIndex) {
            case 0:
                value = c.getName();
                break;
            case 1:
                value = c.getPercentage();
                break;
            case 2:
                value = c.getPartList().size();
                break;
        }
        return value;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if (column != 2) {
            return true;
        }
        return false;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        Category c = categories.get(row);
        switch (col) {
            case 0:
                c.setName((String) value);
                break;
            case 1:
                double newPercent = (Double) value;
                if (getTotalPercent() + newPercent > 100.0) {
                    alert("Percentages do not add to 100!");
                    return; // don't update and don't fire
                }
                c.setPercentage(newPercent);
                break;
        }
        fireTableCellUpdated(row, col);
    }

    // Custom methods for convenience

    public Category getCategoryAt(int rowIndex) {
        return categories.get(rowIndex);
    }

    public void addRow(Category c) {
        int rowIndex = categories.size();
        categories.add(rowIndex, c);
        fireTableRowsInserted(rowIndex, rowIndex);
    }

    public void deleteRow(Category c) {
        int index=categories.indexOf(c);
        categories.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public double getTotalPercent() {
        double total = 0.0;
        for (Category c: categories) {
            total += c.getPercentage();
        }
        return total;
    }

    public void alert(String message){
        JOptionPane.showMessageDialog(null, message);
    }
}
