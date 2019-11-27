package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class PickPartFrame extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public PickPartFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 361, 407);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPickACategory = new JLabel("Pick a Category:");
		lblPickACategory.setBounds(50, 53, 150, 15);
		contentPane.add(lblPickACategory);
		
		JLabel lblPickAPart = new JLabel("Pick a Part:");
		lblPickAPart.setBounds(50, 154, 150, 15);
		contentPane.add(lblPickAPart);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(50, 78, 244, 21);
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(50, 179, 244, 21);
		contentPane.add(comboBox_1);
		
		JButton btnNext = new JButton("Next");
		btnNext.setBounds(211, 307, 93, 23);
		contentPane.add(btnNext);
	}

}
