package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

public class CourseMenuFrame extends JFrame {

	private JPanel contentPane;



	/**
	 * Create the frame.
	 */
	public CourseMenuFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 365, 394);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNameOfCourse = new JLabel("Name of course");
		lblNameOfCourse.setBounds(60, 32, 162, 15);
		contentPane.add(lblNameOfCourse);
		
		JButton btnManageStudents = new JButton("Manage students");
		btnManageStudents.setBounds(59, 81, 216, 23);
		contentPane.add(btnManageStudents);
		
		JButton btnRecordGrades = new JButton("Record grades");
		btnRecordGrades.setBounds(60, 128, 215, 23);
		contentPane.add(btnRecordGrades);
		
		JButton btnViewGrades = new JButton("View grades");
		btnViewGrades.setBounds(60, 174, 215, 23);
		contentPane.add(btnViewGrades);
		
		JButton btnManageCourseStrcuture = new JButton("Manage Course Strcuture");
		btnManageCourseStrcuture.setBounds(60, 221, 215, 23);
		contentPane.add(btnManageCourseStrcuture);
		
		JButton btnOriginalGrades = new JButton("Original grades");
		btnOriginalGrades.setBounds(60, 266, 215, 23);
		contentPane.add(btnOriginalGrades);
	}

}
