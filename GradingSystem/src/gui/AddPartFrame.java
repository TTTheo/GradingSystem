package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AddPartFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the frame.
	 */
	public AddPartFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 335, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setBounds(45, 34, 104, 15);
		contentPane.add(lblCategory);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(44, 59, 227, 21);
		contentPane.add(comboBox);
		
		JLabel lblNameOfPart = new JLabel("Name of Part:");
		lblNameOfPart.setBounds(45, 113, 155, 15);
		contentPane.add(lblNameOfPart);
		
		textField = new JTextField();
		textField.setBounds(45, 138, 226, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPercentage = new JLabel("Percentage:");
		lblPercentage.setBounds(45, 189, 118, 15);
		contentPane.add(lblPercentage);
		
		textField_1 = new JTextField();
		textField_1.setBounds(45, 214, 226, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(178, 293, 93, 23);
		contentPane.add(btnAdd);
	}

}
