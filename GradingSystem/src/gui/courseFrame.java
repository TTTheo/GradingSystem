package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class courseFrame extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public courseFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 344, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSemesterName = new JLabel("semester name");
		lblSemesterName.setBounds(32, 29, 111, 15);
		contentPane.add(lblSemesterName);
		
		JButton btnAddCourse = new JButton("Add course");
		btnAddCourse.setBounds(31, 68, 112, 23);
		contentPane.add(btnAddCourse);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(32, 143, 190, 21);
		contentPane.add(comboBox);
		
		JLabel lblCourse = new JLabel("Course:");
		lblCourse.setBounds(32, 118, 66, 15);
		contentPane.add(lblCourse);
		
		JButton btnSure = new JButton("View");
		btnSure.setBounds(206, 291, 93, 23);
		contentPane.add(btnSure);
	}

}
