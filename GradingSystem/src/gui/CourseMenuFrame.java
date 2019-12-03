package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CourseMenuFrame extends JFrame implements FrameActions{

	private JPanel contentPane;
	private JLabel lblNameOfCourse;
	private JButton btnManageStudents;
	private JButton btnRecordGrades;
	private JButton btnViewGrades;
	private JButton btnManageCourseStrcuture;
	private JButton btnOriginalGrades;


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
		init();
		addActions();
	}
	
	public void init(){
		
		lblNameOfCourse = new JLabel("Name of course");
		lblNameOfCourse.setBounds(60, 32, 162, 15);
		contentPane.add(lblNameOfCourse);
		
		btnManageStudents = new JButton("Manage students");
		btnManageStudents.setBounds(59, 81, 216, 23);
		contentPane.add(btnManageStudents);
		
		btnRecordGrades = new JButton("Record grades");
		btnRecordGrades.setBounds(60, 128, 215, 23);
		contentPane.add(btnRecordGrades);
		
		btnViewGrades = new JButton("View grades");
		btnViewGrades.setBounds(60, 174, 215, 23);
		contentPane.add(btnViewGrades);
		
		btnManageCourseStrcuture = new JButton("Manage Course Strcuture");
		btnManageCourseStrcuture.setBounds(60, 221, 215, 23);
		contentPane.add(btnManageCourseStrcuture);
		
		btnOriginalGrades = new JButton("Original grades");
		btnOriginalGrades.setBounds(60, 266, 215, 23);
		contentPane.add(btnOriginalGrades);
	}
	
	public void addActions(){
		btnManageStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnRecordGrades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnViewGrades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnManageCourseStrcuture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnOriginalGrades.addActionListener(new ActionListener() {
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
