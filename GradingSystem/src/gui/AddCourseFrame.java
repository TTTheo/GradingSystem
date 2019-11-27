package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class AddCourseFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

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
		
		JLabel lblCourseName = new JLabel("Course name:");
		lblCourseName.setBounds(38, 40, 111, 15);
		contentPane.add(lblCourseName);
		
		JLabel lblNumberOfCategories = new JLabel("Number of categories:");
		lblNumberOfCategories.setBounds(38, 173, 179, 15);
		contentPane.add(lblNumberOfCategories);
		
		textField = new JTextField();
		textField.setBounds(38, 64, 220, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblApplyOldStructure = new JLabel("Apply old structure:");
		lblApplyOldStructure.setBounds(38, 108, 152, 15);
		contentPane.add(lblApplyOldStructure);
		
		textField_1 = new JTextField();
		textField_1.setBounds(38, 198, 220, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(38, 133, 220, 21);
		contentPane.add(comboBox);
		
		JButton btnNext = new JButton("Next");
		btnNext.setBounds(177, 279, 93, 23);
		contentPane.add(btnNext);
	}
}
