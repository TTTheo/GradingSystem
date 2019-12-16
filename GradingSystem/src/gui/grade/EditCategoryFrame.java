package gui.grade;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import backend.Backend;
import dao.CategoryDao;
import dao.PartDao;
import gui.*;
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
import java.awt.Font;
import java.awt.SystemColor;

public class EditCategoryFrame extends JFrame implements FrameActions{

	private JPanel contentPane;
	private JTable CategoryTable;
	private JScrollPane scrollPane;		
	private JButton editBtn;
	private JTextField textField;
	private JButton ApplyChangesButton;
	private JButton CancelButton ;
	private final Action action = new SwingAction();

	private Course course;
	private String courseId;
	private Backend backend;
	/**
	 * Create the frame.
	 */
	public EditCategoryFrame(Backend backend) {
		this.backend = backend;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 627);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		init();
		addActions();
	}
	
	public void init(){
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 118, 520, 378);
		contentPane.add(scrollPane);

		course = backend.getCourse();
		courseId = backend.getCourse().getCourseId();
		this.generateTable(course);
		
		editBtn = new JButton("Edit");
		editBtn.setBackground(SystemColor.controlHighlight);
		editBtn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		editBtn.setBounds(590, 312, 143, 35);
		contentPane.add(editBtn);
		
		JLabel CourseNameLabel = new JLabel(courseId);
		CourseNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		CourseNameLabel.setBounds(28, 46, 163, 26);
		contentPane.add(CourseNameLabel);
		
		JLabel lblNewLabel = new JLabel("Use Previous Structure:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(575, 118, 250, 17);
		contentPane.add(lblNewLabel);

		ApplyChangesButton = new JButton("Apply Changes");
		ApplyChangesButton.setBackground(SystemColor.controlHighlight);
		ApplyChangesButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		ApplyChangesButton.setBounds(116, 522, 157, 35);
		contentPane.add(ApplyChangesButton);


//		textField = new JTextField();
//		textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		textField.setEditable(false);
//		textField.setBounds(605, 145, 143, 35);
//		contentPane.add(textField);
//		textField.setColumns(10);
		
		CancelButton = new JButton("Cancel");
		CancelButton.setBackground(SystemColor.controlHighlight);
		CancelButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		CancelButton.setForeground(Color.BLACK);
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
					categoryList = categoryDAO.getAll(courseId);
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
		
	    public boolean isCellEditable(int row, int col) {
			return false;
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
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CategoryFrame next = new CategoryFrame(backend) ;
				next.setLocationRelativeTo(null);
				next.setVisible(true);
				dispose();
			}
		});
		CancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPrevious();
			}
		});
		ApplyChangesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Extract information from the table and put them back to the Coruse instance accordingly
				CategoryDao categoryDAO = new CategoryDao();
				PartDao partDAO = new PartDao();
				ArrayList<Category> categoryList = new ArrayList<Category>();
				ArrayList<Part> partList = new ArrayList<Part>();
				try {
					categoryList = categoryDAO.getAll(courseId);
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
						Category cToUpdate = new Category(categoryName, partNum, courseId, categoryPercent);
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
				EditCategoryFrame refresh = new EditCategoryFrame(backend);
				refresh.setVisible(true);
				dispose();
			}
		});
		
	}
	
	public void alert(String message){
        JOptionPane.showMessageDialog(null, message);
    }

	public void openNext() {
		dispose();
	}

	// This is the first window, no previous window exists
	public void openPrevious() {
		ViewGradeFrame previousFrame = new ViewGradeFrame(backend) ;
		previousFrame.setLocationRelativeTo(null);
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