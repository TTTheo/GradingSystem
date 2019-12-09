package gui.grade;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import gui.FrameActions;
import gui.SemesterFrame;
import objects.Category;
import objects.Course;
import objects.Part;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class EditCategoryFrame extends JFrame implements FrameActions{

	private JPanel contentPane;
	private JTable CategoryTable;
	private JScrollPane scrollPane;		
	private JButton AddNewBtn;
	private JButton DeleteBtn;
	private JTextField textField;
	private JButton EditSelectedButton, ApplyChangesButton;
	private Course course = new Course();

	/**
	 * Create the frame.
	 */
	public EditCategoryFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
//		init();
	}
	
	public EditCategoryFrame(Course course) {
		this();
		this.course = course;
		init();
		addActions();
//		System.out.println(this.course.getName());
	}
	
	public void init(){
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 118, 520, 378);
		contentPane.add(scrollPane);
		
		this.generateTable(course);
		
		AddNewBtn = new JButton("Add New");
		AddNewBtn.setBounds(605, 291, 129, 35);
		contentPane.add(AddNewBtn);
		
		DeleteBtn = new JButton("Delete Selected");
		DeleteBtn.setBounds(605, 197, 129, 35);
		contentPane.add(DeleteBtn);
		
		JLabel CourseNameLabel = new JLabel(this.course.getName());
		CourseNameLabel.setBounds(28, 46, 163, 16);
		contentPane.add(CourseNameLabel);
		
		JLabel lblNewLabel = new JLabel("Selected:");
		lblNewLabel.setBounds(605, 119, 79, 16);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(605, 145, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		ApplyChangesButton = new JButton("Apply Changes");
		ApplyChangesButton.setBounds(116, 522, 129, 35);
		contentPane.add(ApplyChangesButton);
		
		JButton CancelButton = new JButton("Cancel");
		CancelButton.setForeground(Color.RED);
		CancelButton.setBounds(326, 522, 129, 35);
		contentPane.add(CancelButton);
		
		EditSelectedButton = new JButton("Edit Selected");
		EditSelectedButton.setBounds(605, 244, 129, 35);
		contentPane.add(EditSelectedButton);
	}
	
	public void generateTable(Course course) {
		CategoryTable = new JTable(new MyTableModel());
		scrollPane.setViewportView(CategoryTable);
	}
	
	class MyTableModel extends AbstractTableModel {
		private String[] columnNames = {"Category","Part","Percentage","Total score"};
	    private Object[][] data;
	    private int rowCount;
	    
		public MyTableModel() {
			int rowToGenerate = 0;
			rowToGenerate += course.getCategoryCount();
			for (Category c : course.getCategories()) {
				rowToGenerate += c.getPartNum();
			}
			rowCount = rowToGenerate;
			data = new Object[rowToGenerate][4];
			//Fill out the Content List
			int currentRow = 0;
			for (Category c : course.getCategories()) {
					//Fill out the first row of every category
//					System.out.println("rowToGenerate: " + rowToGenerate + " CategoryCount: " + course.getCategoryCount()
//									  + " currentRow: " + currentRow);
					data[currentRow][0] = c.getName();
					data[currentRow][2] = c.getPercentage();
					currentRow ++;
					int partNum = c.getPartNum();
					for (int index = 0; index < partNum; index ++) {
						data[currentRow][1] = c.getPartAt(index).getName();
						data[currentRow][2] = c.getPartAt(index).getPercentage();
						data[currentRow][3] = c.getPartAt(index).getTotalScore();
						currentRow++;
					}
			}
		}
		
	    /*
	     * Don't need to implement this method unless your table's
	     * editable.
	     */
	    public boolean isCellEditable(int row, int col) {
	        //Note that the data/cell address is constant,
	        //no matter where the cell appears onscreen.
	        if (col < 2) {
	            return false;
	        } else {
	            return true;
	        }
	    }

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return rowCount;
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 4;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			return data[rowIndex][columnIndex];
		}
		@Override
		public void setValueAt(Object value, int row, int col) {
	        data[row][col] = value;
	        fireTableCellUpdated(row, col);
	    }
	}
	
	public void addActions(){
		ApplyChangesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Extract information from the table and put them back to the Coruse instance accordingly
				int c_Count, p_Count;
				c_Count = p_Count = 0;
				for (int rowNum = 0; rowNum < CategoryTable.getRowCount(); rowNum++) {
					if (CategoryTable.getValueAt(rowNum, 0) != null) {//then this row is for a category
						String categoryName = (String) CategoryTable.getValueAt(rowNum, 0);
						double categoryPercent = 0.0;
						try {
							categoryPercent = (double) CategoryTable.getValueAt(rowNum, 2);
						} catch (ClassCastException e1) {
							categoryPercent = Double.parseDouble((String) CategoryTable.getValueAt(rowNum, 2));
						}
						course.getCategories().get(c_Count).setName(categoryName);
						course.getCategories().get(c_Count).setPercentage(categoryPercent);
					} else {//then this row is for a part
						String partName = (String) CategoryTable.getValueAt(rowNum, 1);
						double percentage = 0.0;
						double totalScore = 0.0;
						try {
							percentage = (double) CategoryTable.getValueAt(rowNum, 2);
						} catch (ClassCastException e1) {
							percentage = Double.parseDouble((String) CategoryTable.getValueAt(rowNum, 2));
						}
						try {
							totalScore = (double) CategoryTable.getValueAt(rowNum, 3);
						} catch (ClassCastException e1) {
							totalScore = Double.parseDouble((String) CategoryTable.getValueAt(rowNum, 3));
						}
						course.getCategories().get(c_Count).getPartAt(p_Count).setName(partName);
						course.getCategories().get(c_Count).getPartAt(p_Count).setPercentage(percentage);
						course.getCategories().get(c_Count).getPartAt(p_Count).setTotalScore(totalScore);
						p_Count ++;
						if (course.getCategories().get(c_Count).getPartNum() == p_Count) {
							c_Count++;
							p_Count = 0;
						}
					}		
				}
				double c_PercentageSum = 0;
				for (Category c : course.getCategories()) {
					double c_Percentage = c.getPercentage();
					c_PercentageSum += c_Percentage;
					double p_PercentageSum = 0;
					for (Part p : c.getPartList()) {
						p_PercentageSum += p.getPercentage();
					}
					if (p_PercentageSum > c_Percentage) {
						JOptionPane.showMessageDialog(null, "Percentages of all Parts DON'T add up, please EDIT THEM");
						return;
					}
				}
				if (c_PercentageSum > 100) {
					JOptionPane.showMessageDialog(null, "Percentages of all Categories DON'T add up, please EDIT THEM");
					return;
				}
				EditCategoryFrame refresh = new EditCategoryFrame(course);
				refresh.setVisible(true);
				dispose();
			}
		});
	}
	
	public void alert(String message){
        JOptionPane.showMessageDialog(null, message);
    }

	// Open the semester frame next
	public void openNext() {
		SemesterFrame next = new SemesterFrame();
		next.setVisible(true);
		dispose();
	}

	// This is the first window, no previous window exists
	public void openPrevious() {
		dispose();
	}
}