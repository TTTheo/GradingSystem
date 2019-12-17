package gui.grade;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import backend.Backend;
import objects.*;

public class GradeModel extends AbstractTableModel {

    private Course course;
    private ArrayList<Category> categories;
    private ArrayList<Part> parts;
    private ArrayList<Student> rows;
    private ArrayList<String> columnNames;
    private Backend backend;

    public GradeModel (Backend backend, ArrayList<String> columnNames) {
        this.backend = backend;
        course = backend.getCourse();
        try {
            rows = backend.getAllStudents(course);
            parts = backend.getParts(course);
            categories = backend.getCategories(course);
        } catch (SQLException e) {
            alert(e.toString());
            e.printStackTrace();
        }
        this.columnNames = columnNames;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames.get(col);
    }

    @Override
    public Class getColumnClass(int columnNum) {
        return getValueAt(0, columnNum).getClass();
    }

    public void setColumnNames(ArrayList<String> columnNames) {
        this.columnNames = columnNames;
    }

    public void setParts(ArrayList<Part> parts) {
        this.parts = parts;
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Student student = rows.get(rowIndex);

        Object value = "";
        if (columnIndex == 0) {
            // Student name, IGNORE LNAME FOR DEMO
            value = student.getFname();
        }
        else if (columnIndex == columnNames.size() - 1) {
            // Final score
        	double score = 0.0;
            for(Category cat : categories) {
                double catScore = 0 ;
                for(Part part : cat.getPartList()) {
                    double partScore = 0 ;
                    Grade grade = null;
                    try {
                        grade = backend.getGrade(student, part);
                    } catch (SQLException e) {
                        alert(e.toString());
                        e.printStackTrace();
                    }
                    if(grade == null) {
                        partScore = 0;
                    } else {
                        partScore = grade.getGrade();
                    }
                    catScore += partScore * (part.getPercentage() * 0.01) ;
                }
                score += catScore * (cat.getPercentage() * 0.01);
            }
            value = score;
        } else if (columnIndex < columnNames.size()) {
            // Student's grade for a part
        	value = 0.0;
			try {
				Grade grade = backend.getGrade(student, parts.get(columnIndex - 1));
				if (grade != null) {
                    value = grade.getGrade();
                }
			} catch (SQLException e) {
			    alert(e.toString());
				e.printStackTrace();
			}
        }
        return value;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        Student student = rows.get(row);
        if (col == 0) {
            student.setFname((String) value);
            backend.updateStudent(student);
        } else {
            double newScore = (double) value;
            // Set the student's grade for this part
            Grade grade = null;
            try {
                Part thisPart = parts.get(col - 1);
                if (newScore < 0) {
                    newScore = thisPart.getTotalScore() + newScore;
                }

                grade = backend.getGrade(student, thisPart);
                if (grade != null) {
                    grade.setGrade(newScore);
                    backend.updateGrade(grade);
                } else {
                    grade = new Grade(student.getSid(), thisPart.getPid(), newScore);
                    backend.addGrade(grade);
                }
            } catch (SQLException e) {
                alert(e.toString());
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if (column != columnNames.size() - 1) {
            return true;
        }
        return false;
    }

    // Custom methods for convenience

    public void addRow(Student s) {
        int rowIndex = rows.size();
        rows.add(rowIndex, s);
        fireTableRowsInserted(rowIndex, rowIndex);
    }
    
    public void deleteRow(Student s) {
        int index=rows.indexOf(s);
        rows.remove(index);
        this.fireTableRowsDeleted(index, index);
    }

    public Student getStudentAt(int i) {
        return rows.get(i);
    }

    public void alert(String message){
        JOptionPane.showMessageDialog(null, message);
    }

}
