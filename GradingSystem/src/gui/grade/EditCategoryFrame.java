package gui.grade;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import backend.CourseBackend;
import dao.CategoryDao;
import dao.CourseDao;
import dao.PartDao;
import gui.AddPartFrame;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class EditCategoryFrame extends JFrame implements FrameActions{

	private CourseBackend coursebackend = new CourseBackend() ;
	private JPanel contentPane;
	private JTable CategoryTable;
	private JScrollPane scrollPane;		
	private JButton AddNewBtn;
	private JTextField textField;
	private JButton ApplyChangesButton;
	private Course course = new Course();
	private String courseID;
	private final Action action = new SwingAction();


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
	}
	
	public EditCategoryFrame(Course course) {
		this();
		this.course = course;
		this.courseID = course.getCourseid();
		init();
		addActions();
	}
	
	public void init(){
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 118, 520, 378);
		contentPane.add(scrollPane);
		
		this.course = coursebackend.getCourse("CAS1") ;
		this.generateTable(course);
		
		AddNewBtn = new JButton("Add New");
		AddNewBtn.setBounds(605, 212, 129, 35);
		contentPane.add(AddNewBtn);
		
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
				//
				CategoryDao categoryDAO = new CategoryDao();
				PartDao partDAO = new PartDao();
				ArrayList<Category> categoryList = new ArrayList<Category>();
				ArrayList<Part> partList = new ArrayList<Part>();
				try {
					categoryList = categoryDAO.getAll(courseID);
				} catch (SQLException e) {
					System.out.println("Failed to extract CATEGROIES from the DB!");
				}
				int rowToGenerate = categoryList.size();
				for (Category c : categoryList) {
					try {
						partList = partDAO.getAll(c.getCid());
					} catch (SQLException e) {
						System.out.println("Failed to extract PARTS from the DB!");
					}
					rowToGenerate += partList.size();
				}
				rowCount = rowToGenerate;
				data = new Object[rowToGenerate][4];
				int currentRow = 0;
				for (Category c : categoryList) {
					try {
						partList = partDAO.getAll(c.getCid());
					} catch (SQLException e) {
						System.out.println("Failed to extract PARTS from the DB!");
					}
					data[currentRow][0] = c.getName();
					data[currentRow][2] = c.getPercentage();
					currentRow ++;
					int partNum = c.getPartNum();
					for (int index = 0; index < partNum; index ++) {
						data[currentRow][1] = partList.get(index).getName();
						data[currentRow][2] = partList.get(index).getPercentage();
						data[currentRow][3] = partList.get(index).getTotalScore();
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
			return rowCount;
		}
		@Override
		public String getColumnName(int col) {
		    return columnNames[col];
		}
		
		@Override
		public int getColumnCount() {
			return 4;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
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
				CategoryDao categoryDAO = new CategoryDao();
				PartDao partDAO = new PartDao();
				ArrayList<Category> categoryList = new ArrayList<Category>();
				ArrayList<Part> partList = new ArrayList<Part>();
				try {
					categoryList = categoryDAO.getAll(courseID);
				} catch (SQLException e1) {
					System.out.println("Failed to extract CATEGROIES from the DB!");
				}
				int currentCID = 0;
				int partNum = 0;
				for (int rowNum = 0; rowNum < CategoryTable.getRowCount(); rowNum++) {
					if (CategoryTable.getValueAt(rowNum, 0) != null) {//then this row is for a category
						//
						String categoryName = (String) CategoryTable.getValueAt(rowNum, 0);
						for (Category c : categoryList) {
							if (c.getName().equalsIgnoreCase(categoryName)) {
								currentCID = c.getCid();
								partNum = c.getPartNum();
							} else {
								System.out.println("Failed to match Front-End course with DB!");
							}
						}
						double categoryPercent = 0.0;
						try {
							categoryPercent = (double) CategoryTable.getValueAt(rowNum, 2);
						} catch (ClassCastException e1) {
							categoryPercent = Double.parseDouble((String) CategoryTable.getValueAt(rowNum, 2));
						}
						Category cToUpdate = new Category(categoryName, partNum, courseID, categoryPercent);
						cToUpdate.setCid(currentCID);
						categoryDAO.update(cToUpdate);
						//
					} else {//then this row is for a part
						try {
							partList = partDAO.getAll(currentCID);
						} catch (SQLException e2) {
							System.out.println("Failed to extract PARTS from the DB!");
						}
						String partName = (String) CategoryTable.getValueAt(rowNum, 1);
						int currentPID = 0;
						for (Part p : partList) {
							if (p.getName().equalsIgnoreCase(partName)) {
								currentPID = p.getPid();
							} else {
								System.out.println("Failed to match Front-End Part with DB!");
							}
						}
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
						Part pToUpdate = new Part(partName,
												  currentCID,
												  totalScore,
												  percentage);
						pToUpdate.setPid(currentPID);
						partDAO.update(pToUpdate);
					}		
				}
				double c_PercentageSum = 0;
				for (Category c : categoryList) {
					double c_Percentage = c.getPercentage();
					int c_ID = c.getCid();
					c_PercentageSum += c_Percentage;
					double p_PercentageSum = 0;
					try {
						for (Part p : partDAO.getAll(c_ID)) {
							p_PercentageSum += p.getPercentage();
							if (p_PercentageSum > c_Percentage) {
								JOptionPane.showMessageDialog(null, "Percentages of all Parts DON'T add up, please EDIT THEM");
								return;
							}
						}
					} catch (SQLException e1) {
						System.out.println("Failed to match Front-End CATEGORY with DB!");
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
		
		AddNewBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddPartFrame newFrame = new AddPartFrame(course);
				newFrame.setVisible(true);
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
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}