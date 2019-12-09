package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import objects.Course;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddCourseFrame extends JFrame implements FrameActions{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblCourseName;
	private JLabel lblNumberOfCategories;
	private JLabel lblApplyOldStructure;	
	private JComboBox comboBox;	
	JButton btnNext;
	/**
	 * Create the frame.
	 */
	public AddCourseFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 317, 362);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		init();
		addActions();
	}
	
	public void init(){
		lblCourseName = new JLabel("Course name:");
		lblCourseName.setBounds(38, 40, 111, 15);
		contentPane.add(lblCourseName);
		
		lblNumberOfCategories = new JLabel("Number of categories:");
		lblNumberOfCategories.setBounds(38, 173, 179, 15);
		contentPane.add(lblNumberOfCategories);
		
		textField = new JTextField();
		textField.setBounds(38, 64, 220, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblApplyOldStructure = new JLabel("Apply old structure:");
		lblApplyOldStructure.setBounds(38, 108, 152, 15);
		contentPane.add(lblApplyOldStructure);
		
		textField_1 = new JTextField();
		textField_1.setBounds(38, 198, 220, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setBounds(38, 133, 220, 21);
		contentPane.add(comboBox);
		
		btnNext = new JButton("Next");
		btnNext.setBounds(177, 279, 93, 23);
		contentPane.add(btnNext);
	}
	
	public void addActions(){
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String courseName = textField.getText();
				try {
					int categoryNum = Integer.parseInt(textField_1.getText());
					Course newCourse = new Course(courseName, categoryNum);
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
