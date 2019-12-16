package gui;

import gui.grade.ViewGradeFrame;
import objects.Course;
import objects.Semester;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import backend.Backend;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.SystemColor;

public class AllCoursesFrame extends JFrame implements FrameActions{

	private JPanel contentPane;

	private JTable courseTable ;
	private CourseTableModel tableModel ;

	private JButton viewBtn ;
	private JButton editBtn ;
	private JButton deleteBtn ;
	private JButton addBtn ;

	private JScrollPane courseTableScroll;
	private JLabel selectedCourseLabel ;
	private JTextField selectedCourseField;
	private Course selectedCourse;

	private JLabel lblSemesterName;
	private JButton btnBack;
	private Backend backend;
	private Semester semester;
	private ArrayList<Course> data;
	/**
	 * Create the frame.
	 */
	public AllCoursesFrame(Backend backend) {
	    this.backend = backend;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 844, 566);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		final String[] columnNames = {"Course", "ID", "Number of Students"};
		semester=backend.getSemester();
		lblSemesterName = new JLabel(semester.getYear()+"-"+semester.getTerm());
		lblSemesterName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSemesterName.setBounds(57, 20, 135, 37);
		contentPane.add(lblSemesterName);


		data = backend.getAllCourses(semester);

		tableModel = new CourseTableModel(data, columnNames);
		courseTable = new JTable(tableModel);  // Create JTable with custom model
		courseTable.setFont(new Font("Tahoma", Font.PLAIN, 17));

		courseTable.setBounds(52, 69, 333, 221);
		courseTable.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jTableMouseClicked(evt);
			}
		});
		contentPane.add(courseTable);

		viewBtn = new JButton("View");
		viewBtn.setBackground(SystemColor.controlHighlight);
		viewBtn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		viewBtn.setBounds(649, 172, 130, 37);
		contentPane.add(viewBtn);

		editBtn = new JButton("Edit");
		editBtn.setBackground(SystemColor.controlHighlight);
		editBtn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		editBtn.setBounds(649, 236, 130, 37);
		contentPane.add(editBtn);

		addBtn = new JButton("Add new");
		addBtn.setBackground(SystemColor.controlHighlight);
		addBtn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		addBtn.setBounds(649, 373, 130, 37);
		contentPane.add(addBtn);

		deleteBtn = new JButton("Delete");
		deleteBtn.setBackground(SystemColor.controlHighlight);
		deleteBtn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		deleteBtn.setBounds(649, 308, 130, 37);
		contentPane.add(deleteBtn);

		courseTableScroll= new JScrollPane(courseTable);
		courseTableScroll.setBounds(31, 69, 588, 357);
		contentPane.add(courseTableScroll);

		selectedCourseField = new JTextField();
		selectedCourseField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		selectedCourseField.setEditable(false);
		selectedCourseField.setBounds(649, 107, 130, 37);
		contentPane.add(selectedCourseField);
		selectedCourseField.setColumns(10);

		selectedCourse = null;
		selectedCourseLabel = new JLabel("Selected Course");
		selectedCourseLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		selectedCourseLabel.setBounds(643, 69, 148, 24);
		contentPane.add(selectedCourseLabel);


		viewBtn.setEnabled(false);  // enabled when a semester is selected
		
		btnBack = new JButton("Back");
		btnBack.setBackground(SystemColor.controlHighlight);
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnBack.setBounds(31, 451, 108, 37);
		contentPane.add(btnBack);
		addActions();
	}

	public void jTableMouseClicked(java.awt.event.MouseEvent evt) {
		// Get the semester at the selected row index
		int selectedRowIndex = courseTable.getSelectedRow();
		selectedCourse = tableModel.getCourseAt(selectedRowIndex);

		// set the selected row data into jtextfields
		String courseInfo = selectedCourse.getCourseId();
		selectedCourseField.setText(courseInfo);
		viewBtn.setEnabled(true);
	}

	public void addActions(){
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<courseTable.getRowCount();i++) {
					String name=(String) courseTable.getValueAt(i, 0);
					String newid=(String) courseTable.getValueAt(i, 1);
					boolean courseExist=false;
					for(Course course: data) {
						if(course.getCourseId().equals(data.get(i))) {
							courseExist=true;
							alert("This ID exists!");
							break;
						}
					}
					if(!courseExist) {
						data.get(i).setName(name);
						data.get(i).setCourseId(newid);
						backend.updateCourse(data.get(i));
					}
					/*try {
						backend.updateCourse(data.get(i));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}*/
				}
			}
		});
		
		viewBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backend.setCourse(selectedCourse);
				openNext();
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPrevious();
			}
		});
		
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int selectedRowIndex = courseTable.getSelectedRow();
				 selectedCourse = tableModel.getCourseAt(selectedRowIndex);
			        // set the selected row data into jtextfields
			        String semesterInfo = selectedCourse.toString();
			        selectedCourseField.setText(semesterInfo);	
			        try {
			        	backend.deleteCourse(selectedCourse);
					} catch (SQLException ex) {
						alert(ex.toString());
					}
					tableModel.deleteRow(selectedCourse);
			}
		});
		
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCourseFrame next = new AddCourseFrame(backend);
				next.setLocationRelativeTo(null);
				next.setVisible(true);
			}
		});
	}
	
	public void alert(String message){
        JOptionPane.showMessageDialog(null, message);
    }

	// Open the course menu frame next
	public void openNext() {
		ViewGradeFrame next = new ViewGradeFrame(backend) ;
		next.setLocationRelativeTo(null);
		next.setVisible(true);
		dispose();
	}

	// This is the first window, no previous window exists
	public void openPrevious() {
		SemesterFrame prevframe = new SemesterFrame(backend) ;
		prevframe.setLocationRelativeTo(null);
		prevframe.setVisible(true);
		dispose();
	}
}
