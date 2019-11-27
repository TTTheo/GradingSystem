package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class ManageStudentsFrame extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public ManageStudentsFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 364, 393);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddStudent = new JButton("Add Student");
		btnAddStudent.setBounds(47, 80, 238, 23);
		contentPane.add(btnAddStudent);
		
		JButton btnDeleteStudent = new JButton("Delete Student");
		btnDeleteStudent.setBounds(47, 143, 238, 23);
		contentPane.add(btnDeleteStudent);
		
		JButton btnWithdrawStudent = new JButton("Withdraw Student");
		btnWithdrawStudent.setBounds(47, 210, 238, 23);
		contentPane.add(btnWithdrawStudent);
	}

}
