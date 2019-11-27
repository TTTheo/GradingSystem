package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class semesterFrame extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public semesterFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 362, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddSemester = new JButton("Add semester");
		btnAddSemester.setBounds(33, 28, 129, 23);
		contentPane.add(btnAddSemester);
		
		JLabel lblSemester = new JLabel("Semester:");
		lblSemester.setBounds(33, 77, 82, 15);
		contentPane.add(lblSemester);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(33, 110, 177, 21);
		contentPane.add(comboBox);
		
		JButton btnSure = new JButton("View");
		btnSure.setBounds(223, 295, 93, 23);
		contentPane.add(btnSure);
	}
}
