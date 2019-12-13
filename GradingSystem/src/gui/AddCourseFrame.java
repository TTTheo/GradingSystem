package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.CourseDao;
import objects.Course;
import objects.Semester;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;

public class AddCourseFrame extends JFrame implements FrameActions{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblCourseName;
	private JLabel lblNumberOfCategories;
	private JLabel lblApplyOldStructure;	
	private JComboBox comboBox;	
	private JButton btnNext;
	private JButton btnCancel;
	/**
	 * Create the frame.
	 */
	public AddCourseFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 552, 585);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		init();
		addActions();
	}
	
	public void init(){
		lblCourseName = new JLabel("Course name:");
		lblCourseName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCourseName.setBounds(76, 53, 220, 32);
		contentPane.add(lblCourseName);
		
		lblNumberOfCategories = new JLabel("Number of categories:");
		lblNumberOfCategories.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNumberOfCategories.setBounds(79, 282, 204, 32);
		contentPane.add(lblNumberOfCategories);
		
		textField = new JTextField();
		textField.setBounds(76, 98, 361, 32);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblApplyOldStructure = new JLabel("Apply old structure:");
		lblApplyOldStructure.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblApplyOldStructure.setBounds(76, 157, 220, 34);
		contentPane.add(lblApplyOldStructure);
		
		textField_1 = new JTextField();
		textField_1.setBounds(76, 327, 361, 32);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setBackground(SystemColor.control);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 17));
		comboBox.setBounds(76, 205, 361, 34);
		contentPane.add(comboBox);
		
		btnNext = new JButton("Next");
		btnNext.setBackground(SystemColor.controlHighlight);
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNext.setBounds(330, 413, 107, 32);
		contentPane.add(btnNext);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBackground(SystemColor.controlHighlight);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnCancel.setBounds(76, 413, 107, 32);
		contentPane.add(btnCancel);
	}
	
	public void addActions(){
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String courseName = textField.getText();
				try {
					CourseDao c_DAO = new CourseDao();
					int categoryNum = Integer.parseInt(textField_1.getText());
					//Because the Semester Frame and Course Frame are still in work, I have created a mock up Semester Instance to store inside DB
					Course newCourse = new Course(new Semester("FALL2019", 2019),
												  courseName, 
												  categoryNum);
					
					try {
						c_DAO.insert(newCourse);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					AddCategoryFrame nextFrame = new AddCategoryFrame(newCourse, categoryNum);
					nextFrame.setVisible(true);
					dispose();
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Invalid Input for the number of categories");
				}
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
