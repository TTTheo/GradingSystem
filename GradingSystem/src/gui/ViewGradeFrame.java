package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewGradeFrame extends JFrame implements FrameActions{

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnApplyStatistics;
	private JButton btnCalculateFinal;
	private JButton btnCurveFinalGrade;
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
		init();
		addActions();
	}
	
	public void init(){
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 20, 452, 355);
		contentPane.add(scrollPane);
		
		Object[] title={"name","category1","category2"};
		Object[][] content=new Object[2][3];
		table = new JTable(content, title);
		scrollPane.setViewportView(table);
		
		btnApplyStatistics = new JButton("Apply Statistics");
		btnApplyStatistics.setBounds(26, 395, 129, 23);
		contentPane.add(btnApplyStatistics);
		
		btnCalculateFinal = new JButton("Calculate Final");
		btnCalculateFinal.setBounds(165, 395, 129, 23);
		contentPane.add(btnCalculateFinal);
		
		btnCurveFinalGrade = new JButton("Curve Final Grade");
		btnCurveFinalGrade.setBounds(304, 395, 142, 23);
		contentPane.add(btnCurveFinalGrade);
	}
	
	public void addActions(){
		btnApplyStatistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnCalculateFinal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnCurveFinalGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}
	
	public void alert(String message){
        JOptionPane.showMessageDialog(null, message);
    }

	// Open the semester frame next
	public void openNext() {
		SemesterFrame next = new SemesterFrame();
		next.setVisible(true);
		dispose();
	}

	// This is the first window, no previous window exists
	public void openPrevious() {
		dispose();
	}

}
