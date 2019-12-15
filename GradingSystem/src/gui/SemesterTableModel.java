package gui;

import objects.Semester;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

// Source: https://stackoverflow.com/q/12559287/10634812
public class SemesterTableModel extends AbstractTableModel {

    private ArrayList<Semester> semesters;
    private String[] columnNames;

    public SemesterTableModel () {
        this(new ArrayList<Semester>(), new String[]{});
    }

    public SemesterTableModel (ArrayList<Semester> semesters, String[] columnNames) {
        this.semesters = semesters;
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
        return semesters.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = "";
        Semester s = this.semesters.get(rowIndex);

        switch (columnIndex) {
            case 0:
                value = s.getYear();
                break;
            case 1:
                value = s.getTerm();
                break;
            case 2:
                value = s.getNumCourses();
                break;
        }
        return value;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return true;
    }

    // Custom methods for convenience

    public Semester getSemesterAt(int rowIndex) {
        return semesters.get(rowIndex);
    }

    public void addRow(Semester s) {
        int rowIndex = semesters.size();
        semesters.add(rowIndex, s);
        fireTableRowsInserted(rowIndex, rowIndex);
    }
    
    public void deleteRow(Semester s) {
    	//for(int i=0;i<semesters.size();i++) {
    		int index=semesters.indexOf(s);
    		semesters.remove(index);
    		this.fireTableRowsDeleted(index, index);
    	//}
    }
}
