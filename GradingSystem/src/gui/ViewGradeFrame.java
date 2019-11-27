package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class ViewGradeFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public ViewGradeFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 491, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 20, 452, 355);
		contentPane.add(scrollPane);
		
		Object[] title={"name","category1","category2"};
		Object[][] content=new Object[2][3];
		table = new JTable(content, title);
		scrollPane.setViewportView(table);
		
		JButton btnApplyStatistics = new JButton("Apply Statistics");
		btnApplyStatistics.setBounds(26, 395, 129, 23);
		contentPane.add(btnApplyStatistics);
		
		JButton btnCalculateFinal = new JButton("Calculate Final");
		btnCalculateFinal.setBounds(165, 395, 129, 23);
		contentPane.add(btnCalculateFinal);
		
		JButton btnCurveFinalGrade = new JButton("Curve Final Grade");
		btnCurveFinalGrade.setBounds(304, 395, 142, 23);
		contentPane.add(btnCurveFinalGrade);
	}

}
