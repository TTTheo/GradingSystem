package gui;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import backend.Backend;
import objects.*;

public class GradeTableModel extends AbstractTableModel {

    private ArrayList<Category> cats ;
    private ArrayList<Part> parts ;
    private ArrayList<Student> students ;
    private String[] columnNames;
    private ArrayList<Double> finalScores ;
    private Backend backend = new Backend() ;
    private boolean hasFinal = false ;
    
    public ArrayList<Part> getParts() {
		return parts;
	}

	public void setParts(ArrayList<Part> parts) {
		this.parts = parts;
	}

	public GradeTableModel () {
        this(new ArrayList<Category>(), new ArrayList<Student>(), false);
    }

    public GradeTableModel (ArrayList<Category> cats, ArrayList<Student> students, boolean hasFinal) {
        this.cats = cats ;
        this.students = students ;
        this.hasFinal = hasFinal ;
        this.parts = new ArrayList<>() ;
        for(Category cat : cats) {
        	if(cat != null) {
        		ArrayList<Part> parts = cat.getPartList() ;
        		for(Part part : parts) {
        			this.parts.add(part) ;
        		}
        	}
        }
        if(hasFinal) {
        	parts.add(new Part("final")) ;
        }
    }

    @Override
    public String getColumnName(int col) {
    	if(col == 0) {
    		return "Student Name" ;
    	}
    	else if(col == 1) {
    		return "Student id" ;
    	}
    	else return parts.get(col - 2).getName();
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
        return parts.size() + 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = "";
        if(columnIndex == 0) {
        	value = students.get(rowIndex).toString() ;
//        	System.out.println("value  " + value);
        }
        else if(columnIndex == 1) {
        	value = students.get(rowIndex).getSid() ;
//        	System.out.println("value  " + value);
        }else {
        	if(hasFinal && columnIndex -1 == parts.size()) {
        		value = finalScores.get(rowIndex) ;
        		return value ;
        	}
        	Grade grade = null;
			try {
				grade = backend.getGrade(students.get(rowIndex), parts.get(columnIndex - 2));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	if(grade != null) value = grade.getGrade() ;
        	else value = 0 ;
//        	System.out.println("aaaaaa " + grade.getGrade());
        }
        return value;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return true;
    }

    // Custom methods for convenience

    public Grade getGradeAt(int rowIndex, int columnIndex) throws SQLException {
    	if(columnIndex == 0 || columnIndex == 1) return null ;
        return backend.getGrade(students.get(rowIndex), parts.get(columnIndex - 2)) ;
    }
    
    public Part getPartAt(int columnIndex) {
    	if(columnIndex == 0 || columnIndex == 1) return null ;
    	else return parts.get(columnIndex -2) ;
    }

    public void addRow(Student s) {
        int rowIndex = students.size();
        students.add(rowIndex, s);
        fireTableRowsInserted(rowIndex, rowIndex);
    }
    
    public void deleteRow(Student s) {
    	//for(int i=0;i<semesters.size();i++) {
    		int index=students.indexOf(s);
    		students.remove(index);
    		this.fireTableRowsDeleted(index, index);
    	//}
    }
    
    public void doFinal() {
    	finalScores = new ArrayList<>() ;
    	for(Student s : students) {
			double score = 0 ;
			for(Category cat : cats) {
				double catScore = 0 ;
				for(Part part : cat.getPartList()) {
					double partScore = 0 ;
					Grade grade = null;
					try {
						grade = backend.getGrade(s, part);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(grade == null) partScore = 0 ;
					else partScore = grade.getGrade() ;
					catScore += partScore * part.getPercentage() ;
				}
				score += catScore * cat.getPercentage() ;
			}
			finalScores.add(score) ;
		}
    }
}
