package gui;

import backend.Backend;
import gui.grade.ViewGradeFrame;
import objects.Course;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class AllCoursesFrame extends JFrame implements FrameActions{

	private JPanel contentPane;

	private JTable courseTable ;
	private CourseTableModel tableModel ;
	private Backend backend ;

	private JButton viewBtn ;
	private JButton editBtn ;
	private JButton deleteBtn ;
	private JButton addBtn ;
	private JButton previousBtn ;

	private JScrollPane courseTableScroll;
	private JLabel selectedCourseLabel ;
	private JTextField selectedCourseField;
	private Course selectedCourse;

	private JLabel lblSemesterName;
	/**
	 * Create the frame.
	 */
	public AllCoursesFrame(Backend backend) {
	    this.backend = backend;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		final String[] columnNames = {"ID", "Number of Students"};

		lblSemesterName = new JLabel(backend.getSemester().toString());
		lblSemesterName.setBounds(57, 20, 135, 37);
		contentPane.add(lblSemesterName);

		ArrayList<Course> data = backend.getAllCourses(backend.getSemester());

		tableModel = new CourseTableModel(data, columnNames);
		courseTable = new JTable(tableModel);  // Create JTable with custom model

		courseTable.setBounds(52, 69, 333, 221);
		courseTable.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jTableMouseClicked(evt);
			}
		});
		contentPane.add(courseTable);

		viewBtn = new JButton("Next");
		viewBtn.setBounds(262, 325, 117, 29);
		contentPane.add(viewBtn);

		editBtn = new JButton("Edit...");
		editBtn.setBounds(426, 125, 117, 29);
		contentPane.add(editBtn);

		addBtn = new JButton("Add new");
		addBtn.setBounds(426, 207, 117, 29);
		contentPane.add(addBtn);

		deleteBtn = new JButton("Delete");
		deleteBtn.setBounds(426, 166, 117, 29);
		contentPane.add(deleteBtn);

		courseTableScroll= new JScrollPane(courseTable);
		courseTableScroll.setBounds(31, 68, 383, 245);
		contentPane.add(courseTableScroll);

		selectedCourseField = new JTextField();
		selectedCourseField.setEditable(false);
		selectedCourseField.setBounds(426, 87, 130, 26);
		contentPane.add(selectedCourseField);
		selectedCourseField.setColumns(10);

		selectedCourse = null;
		selectedCourseLabel = new JLabel("Selected Course");
		selectedCourseLabel.setBounds(431, 69, 123, 16);
		contentPane.add(selectedCourseLabel);


		viewBtn.setEnabled(false);  // enabled when a semester is selected
		
		previousBtn = new JButton("Previous");
		previousBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPrevious();
			}
		});
		previousBtn.setBounds(57, 322, 117, 29);
		contentPane.add(previousBtn);
		addActions();
	}

	public void jTableMouseClicked(java.awt.event.MouseEvent evt) {
		// Get the semester at the selected row index
		int selectedRowIndex = courseTable.getSelectedRow();
		selectedCourse = tableModel.getCourseAt(selectedRowIndex);

		// set the selected row data into jtextfields
		String courseInfo = selectedCourse.getName();
		selectedCourseField.setText(courseInfo);
		viewBtn.setEnabled(true);
	}

	public void addActions(){
		viewBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backend.setCourse(selectedCourse);
				openNext();
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

	public void openPrevious() {
		SemesterFrame previous = new SemesterFrame(backend) ;
		previous.setLocationRelativeTo(null);
		previous.setVisible(true);
		dispose();
	}
}
