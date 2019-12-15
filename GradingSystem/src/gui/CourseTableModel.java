package gui;

import objects.Course;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

// Source: https://stackoverflow.com/q/12559287/10634812
public class CourseTableModel extends AbstractTableModel {

    private ArrayList<Course> courses;
    private String[] columnNames;

    public CourseTableModel() {
        this(new ArrayList<Course>(), new String[]{});
    }

    public CourseTableModel(ArrayList<Course> courses, String[] columnNames) {
        this.courses = courses;
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
        return courses.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = "";
        Course c = this.courses.get(rowIndex);

        switch (columnIndex) {
            case 0:
                value = c.getCourseId();
                break;
            case 1:
                value = c.getStudents().size();
                break;
        }
        return value;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
    	if(column==0) {
    		return true;
    	}
        return false;
    }

    // Custom methods for convenience

    public Course getCourseAt(int rowIndex) {
        return courses.get(rowIndex);
    }

    public void addRow(Course s) {
        int rowIndex = courses.size();
        courses.add(rowIndex, s);
        fireTableRowsInserted(rowIndex, rowIndex);
    }
    
    public void deleteRow(Course s) {
    	//for(int i=0;i<semesters.size();i++) {
    		int index=courses.indexOf(s);
    		courses.remove(index);
    		this.fireTableRowsDeleted(index, index);
    	//}
    }
}
