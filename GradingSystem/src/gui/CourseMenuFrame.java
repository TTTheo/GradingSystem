package gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import backend.Backend;

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
	private JButton btnBack;

	private Backend backend;

	public CourseMenuFrame(Backend c) {
		backend = c;

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
		
		btnRecordGrades = new JButton("Record grades (Not Implemented)");
		btnRecordGrades.setBackground(SystemColor.controlHighlight);
		btnRecordGrades.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnRecordGrades.setBounds(164, 163, 236, 36);
		contentPane.add(btnRecordGrades);
		
		btnViewGrades = new JButton("View grades (Not Implemented)");
		btnViewGrades.setBackground(SystemColor.controlHighlight);
		btnViewGrades.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnViewGrades.setBounds(164, 245, 236, 36);
		contentPane.add(btnViewGrades);
		
		btnManageCourseStrcuture = new JButton("Manage Course Structure");
		btnManageCourseStrcuture.setBackground(SystemColor.controlHighlight);
		btnManageCourseStrcuture.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnManageCourseStrcuture.setBounds(164, 324, 236, 36);
		contentPane.add(btnManageCourseStrcuture);
		
		btnOriginalGrades = new JButton("Original grades (Not implemented)");
		btnOriginalGrades.setBackground(SystemColor.controlHighlight);
		btnOriginalGrades.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnOriginalGrades.setBounds(164, 402, 236, 36);
		contentPane.add(btnOriginalGrades);

		btnBack = new JButton("Back");
		btnBack.setBackground(SystemColor.controlHighlight);
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnBack.setBounds(164, 490, 236, 36);
		contentPane.add(btnBack);
	}
	
	public void addActions(){
		btnManageStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageStudentsFrame manageStu=new ManageStudentsFrame(backend);
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
				ManageStructureFrame manageCourse=new ManageStructureFrame(backend);
				manageCourse.setVisible(true);
			}
		});
		
		btnOriginalGrades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPrevious();
			}
		});
	}

	public void alert(String message){
        JOptionPane.showMessageDialog(null, message);
    }

	public void openNext() {
		dispose();
	}

	// Go back to the frame that opened this
	public void openPrevious() {
		AllCoursesFrame c = new AllCoursesFrame(backend);
		c.setVisible(true);
		dispose();
	}

}
