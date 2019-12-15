package gui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import objects.Student;

public class StudentTableModel extends AbstractTableModel {

    private ArrayList<Student> students;
    private String[] columnNames;

    public StudentTableModel () {
        this(new ArrayList<Student>(), new String[]{});
    }

    public StudentTableModel (ArrayList<Student> students, String[] columnNames) {
        this.students = students;
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
        return students.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = "";
        Student s = this.students.get(rowIndex);

        switch (columnIndex) {
            case 0:
                value = s.getFname();
                break;
            case 1:
                value = s.getLname();
                break;
            case 2:
                value = s.getSid();
                break;
        }
        return value;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    // Custom methods for convenience

    public Student getStudentAt(int rowIndex) {
        return students.get(rowIndex);
    }

    public void addRow(Student s) {
        int rowIndex = students.size();
        students.add(rowIndex, s);
        fireTableRowsInserted(rowIndex, rowIndex);
    }
}
