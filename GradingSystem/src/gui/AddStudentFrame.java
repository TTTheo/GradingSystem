package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class AddStudentFrame extends JFrame {

	private JPanel contentPane;



	/**
	 * Create the frame.
	 */
	public AddStudentFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 347, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddMultipleStudents = new JButton("Add Multiple Students");
		btnAddMultipleStudents.setBounds(68, 89, 200, 23);
		contentPane.add(btnAddMultipleStudents);
		
		JButton btnAddASingle = new JButton("Add Single Student");
		btnAddASingle.setBounds(68, 166, 200, 23);
		contentPane.add(btnAddASingle);
	}

}
