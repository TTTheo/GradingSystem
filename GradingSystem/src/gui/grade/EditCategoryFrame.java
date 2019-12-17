package gui.grade;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import backend.Backend;
import dao.CategoryDao;
import dao.PartDao;
import gui.*;
import objects.Category;
import objects.Course;
import objects.Part;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

public class EditCategoryFrame extends JFrame implements FrameActions{

	private JPanel contentPane;
	private JTable CategoryTable;
	private JScrollPane scrollPane;		
	private JButton editBtn;
	private JButton applyBtn;
	private JComboBox comboBox;
	private JButton CancelButton ;

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
		
		editBtn = new JButton("Click here to Edit");
		editBtn.setBackground(SystemColor.controlHighlight);
		editBtn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		editBtn.setBounds(326, 522, 180, 35);
		contentPane.add(editBtn);
		
		JLabel CourseNameLabel = new JLabel(courseId);
		CourseNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		CourseNameLabel.setBounds(28, 46, 163, 26);
		contentPane.add(CourseNameLabel);
		
		JLabel lblNewLabel = new JLabel("Use Previous Structure:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(575, 118, 250, 17);
		contentPane.add(lblNewLabel);

		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 17));
		comboBox.setBounds(560, 150, 250, 33);
		contentPane.add(comboBox);

		// Populate combobox
		comboBox.addItem("");
		for(Course c: backend.getAllCourses()) {
			comboBox.addItem(c.getCourseId());
		}

		applyBtn = new JButton("Apply previous");
		applyBtn.setBackground(SystemColor.controlHighlight);
		applyBtn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		applyBtn.setBounds(560, 200, 180, 35);
		contentPane.add(applyBtn);

		CancelButton = new JButton("Back");
		CancelButton.setBackground(SystemColor.controlHighlight);
		CancelButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		CancelButton.setForeground(Color.BLACK);
		CancelButton.setBounds(116, 522, 157, 35);
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
		applyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selected = (String) comboBox.getSelectedItem();
				if (selected.equals("") || selected.equals(course.getCourseId())) {
					alert("Select a valid course first");
					return;
				}

				// First delete all current categories and parts
				System.out.println("item state changed");
                try {
                	backend.deleteAllCategories(course);
				}
                catch (SQLException ex) {
                	alert(ex.toString());
                	ex.printStackTrace();
				}

			    Course selectedCourse = backend.getCourse(selected);
				ArrayList<Category> categories = new ArrayList<Category>();
			    try {
					categories = backend.getCategories(selectedCourse);
				} catch (SQLException ex) {
			    	alert(ex.toString());
			    	ex.printStackTrace();
				}

			    // Copy the categories for this category
				for (Category c : categories) {
					Category newCategory = new Category(
						c.getName(),
						c.getPartNum(),
						courseId,
						c.getPercentage()
					);
					try {
						backend.addCategory(newCategory);
						for (Part p : backend.getParts(c)) {
							Part newPart = new Part(
								p.getName(),
								newCategory.getCid(),
								p.getTotalScore(),
								p.getPercentage()
							);
							backend.addPart(newPart);
						}
					} catch (SQLException ex) {
						alert(ex.toString());
						ex.printStackTrace();
					}
				}
				EditCategoryFrame refresh = new EditCategoryFrame(backend);
				refresh.setLocationRelativeTo(null);
				dispose();
				refresh.setVisible(true);
			}
		});

		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    openNext();
			}
		});

		CancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPrevious();
			}
		});
	}
	
	public void alert(String message){
        JOptionPane.showMessageDialog(null, message);
    }

	public void openNext() {
		CategoryFrame next = new CategoryFrame(backend);
		next.setLocationRelativeTo(null);
		next.setVisible(true);
		dispose();
	}

	// This is the first window, no previous window exists
	public void openPrevious() {
		ViewGradeFrame previousFrame = new ViewGradeFrame(backend) ;
		previousFrame.setLocationRelativeTo(null);
		previousFrame.setVisible(true);
		dispose();
	}
}