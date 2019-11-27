package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class ManageCourseFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageCourseFrame frame = new ManageCourseFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ManageCourseFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 315, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddCategory = new JButton("Add Category");
		btnAddCategory.setBounds(67, 52, 164, 23);
		contentPane.add(btnAddCategory);
		
		JButton btnAddPart = new JButton("Add Part");
		btnAddPart.setBounds(67, 112, 164, 23);
		contentPane.add(btnAddPart);
		
		JButton btnAdjustPercentage = new JButton("Adjust Percentage");
		btnAdjustPercentage.setBounds(67, 174, 164, 23);
		contentPane.add(btnAdjustPercentage);
	}

}
