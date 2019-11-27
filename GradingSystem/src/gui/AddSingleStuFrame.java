package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AddSingleStuFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;


	/**
	 * Create the frame.
	 */
	public AddSingleStuFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 355, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStudentName = new JLabel("Student Name:");
		lblStudentName.setBounds(51, 46, 108, 15);
		contentPane.add(lblStudentName);
		
		textField = new JTextField();
		textField.setBounds(51, 71, 232, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblStudentId = new JLabel("Student ID:");
		lblStudentId.setBounds(51, 113, 76, 15);
		contentPane.add(lblStudentId);
		
		textField_1 = new JTextField();
		textField_1.setBounds(51, 138, 232, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblStudentEmail = new JLabel("Student Email:");
		lblStudentEmail.setBounds(51, 181, 108, 15);
		contentPane.add(lblStudentEmail);
		
		textField_2 = new JTextField();
		textField_2.setBounds(51, 206, 232, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(213, 288, 93, 23);
		contentPane.add(btnAdd);
	}

}
