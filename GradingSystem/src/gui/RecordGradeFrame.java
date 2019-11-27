package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RecordGradeFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;


	/**
	 * Create the frame.
	 */
	public RecordGradeFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 369, 404);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStudentsName = new JLabel("Student's name");
		lblStudentsName.setBounds(50, 26, 100, 15);
		contentPane.add(lblStudentsName);
		
		JLabel lblScore = new JLabel("Score:");
		lblScore.setBounds(50, 65, 66, 15);
		contentPane.add(lblScore);
		
		textField = new JTextField();
		textField.setBounds(46, 91, 104, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lbltotalScore = new JLabel("/total score");
		lbltotalScore.setBounds(162, 94, 92, 15);
		contentPane.add(lbltotalScore);
		
		JLabel lblComment = new JLabel("Comment:");
		lblComment.setBounds(48, 142, 102, 15);
		contentPane.add(lblComment);
		
		textField_1 = new JTextField();
		textField_1.setBounds(46, 167, 258, 117);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPrevious.setBounds(46, 312, 93, 23);
		contentPane.add(btnPrevious);
		
		JButton btnNext = new JButton("Next");
		btnNext.setBounds(211, 312, 93, 23);
		contentPane.add(btnNext);
	}

}
