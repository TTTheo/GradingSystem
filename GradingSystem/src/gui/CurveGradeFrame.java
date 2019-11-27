package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class CurveGradeFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public CurveGradeFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(96, 111, 242, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPercentageOfCurve = new JLabel("Percentage of curve:");
		lblPercentageOfCurve.setBounds(96, 82, 140, 15);
		contentPane.add(lblPercentageOfCurve);
		
		JButton btnSure = new JButton("Proceed");
		btnSure.setBounds(287, 209, 93, 23);
		contentPane.add(btnSure);
	}

}
