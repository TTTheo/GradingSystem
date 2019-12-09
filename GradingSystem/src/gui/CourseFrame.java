package gui;

import backend.SemesterBackend;
import objects.Course;
import objects.Semester;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseFrame extends JFrame implements FrameActions{

	private JPanel contentPane;

	private JTable courseTable ;
	private SemesterTableModel tableModel ;
	private SemesterBackend backend ;

	private JButton viewBtn ;
	private JButton editBtn ;
	private JButton deleteBtn ;
	private JButton addBtn ;

	private JScrollPane courseTableScroll;
	private JLabel selectedCourseLabel ;
	private JTextField selectedCourseField;
	private Course selectedCourse;

	private JLabel lblSemesterName;
	/**
	 * Create the frame.
	 */
	public CourseFrame(SemesterBackend backend) {
	    this.backend = backend;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		final String[] columnNames = { "ID", "Number of Students"};

		lblSemesterName = new JLabel(backend.getCurrentSemester().toString());
		lblSemesterName.setBounds(57, 20, 135, 37);
		contentPane.add(lblSemesterName);

		Semester demo = new Semester("Fall", 2019);
		ArrayList<Semester> data;
		try {
			data = backend.getAllSemesters();
		} catch (SQLException e) {
			alert(e.toString());
			data = new ArrayList<>();
			data.add(demo);
		}

		tableModel = new SemesterTableModel(data, columnNames);
		courseTable = new JTable(tableModel);  // Create JTable with custom model


		courseTable.setBounds(52, 69, 333, 221);
		courseTable.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jTableMouseClicked(evt);
			}
		});
		contentPane.add(courseTable);

		viewBtn = new JButton("View");
		viewBtn.setBounds(426, 125, 117, 29);
		contentPane.add(viewBtn);

		editBtn = new JButton("Edit...");
		editBtn.setBounds(426, 166, 117, 29);
		contentPane.add(editBtn);

		addBtn = new JButton("Add new");
		addBtn.setBounds(400, 305, 117, 29);
		contentPane.add(addBtn);

		deleteBtn = new JButton("Delete");
		deleteBtn.setBounds(426, 207, 117, 29);
		contentPane.add(deleteBtn);

		courseTableScroll= new JScrollPane(courseTable);
		courseTableScroll.setBounds(31, 69, 362, 221);
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
		addActions();
	}

	public void jTableMouseClicked(java.awt.event.MouseEvent evt) {
		// Get the semester at the selected row index
		int selectedRowIndex = courseTable.getSelectedRow();
//		selectedSemester = tableModel.getSemesterAt(selectedRowIndex);
//
//		// set the selected row data into jtextfields
//		String semesterInfo = selectedSemester.toString();
//		selectedSemesterField.setText(semesterInfo);
		viewBtn.setEnabled(true);
	}

	public void addActions(){
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
