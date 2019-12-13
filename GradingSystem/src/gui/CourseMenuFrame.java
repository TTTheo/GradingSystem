package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.grade.RecordGradeFrame;
import gui.grade.ViewGradeFrame;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;

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
		setBounds(100, 100, 574, 583);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		init();
		addActions();
	}
	
	public void init(){
		
		lblNameOfCourse = new JLabel("Name of course");
		lblNameOfCourse.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNameOfCourse.setBounds(164, 24, 162, 26);
		contentPane.add(lblNameOfCourse);
		
		btnManageStudents = new JButton("Manage students");
		btnManageStudents.setBackground(SystemColor.controlHighlight);
		btnManageStudents.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnManageStudents.setBounds(164, 82, 236, 36);
		contentPane.add(btnManageStudents);
		
		btnRecordGrades = new JButton("Record grades");
		btnRecordGrades.setBackground(SystemColor.controlHighlight);
		btnRecordGrades.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnRecordGrades.setBounds(164, 163, 236, 36);
		contentPane.add(btnRecordGrades);
		
		btnViewGrades = new JButton("View grades");
		btnViewGrades.setBackground(SystemColor.controlHighlight);
		btnViewGrades.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnViewGrades.setBounds(164, 245, 236, 36);
		contentPane.add(btnViewGrades);
		
		btnManageCourseStrcuture = new JButton("Manage Course Strcuture");
		btnManageCourseStrcuture.setBackground(SystemColor.controlHighlight);
		btnManageCourseStrcuture.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnManageCourseStrcuture.setBounds(164, 324, 236, 36);
		contentPane.add(btnManageCourseStrcuture);
		
		btnOriginalGrades = new JButton("Original grades");
		btnOriginalGrades.setBackground(SystemColor.controlHighlight);
		btnOriginalGrades.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnOriginalGrades.setBounds(164, 402, 236, 36);
		contentPane.add(btnOriginalGrades);
	}
	
	public void addActions(){
		btnManageStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageStudentsFrame manageStu=new ManageStudentsFrame();
				manageStu.setVisible(true);
			}
		});
		
		btnRecordGrades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//RecordGradeFrame recordGrade=new RecordGradeFrame();
				//recordGrade.setVisible(true);
			}
		});
		
		btnViewGrades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ViewGradeFrame viewGrade=new ViewGradeFrame();
				//viewGrade.setVisible(true);
			}
		});
		
		btnManageCourseStrcuture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageCourseFrame manageCourse=new ManageCourseFrame();
				manageCourse.setVisible(true);
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
