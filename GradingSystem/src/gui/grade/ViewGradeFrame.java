package gui.grade;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.FrameActions;
import gui.SemesterFrame;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;

public class ViewGradeFrame extends JFrame implements FrameActions{

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnApplyStatistics;
	private JButton btnCalculateFinal;
	private JButton btnCurveFinalGrade;
	private JLabel CourseNameLabel;
	private JButton EditStudentBtn;
	private JButton ApplyChangesBtn;
	private JButton DisposeChangesBtn;
	/**
	 * Create the frame.
	 */
	public ViewGradeFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		init();
		addActions();
	}
	
	public void init(){
		
		Object[] title={"name","category1","category2"};
		Object[][] content=new Object[2][3];
		table = new JTable(content, title);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(6, 57, 568, 461);
		contentPane.add(scrollPane);
		
		btnApplyStatistics = new JButton("Apply Statistics");
		btnApplyStatistics.setBounds(614, 180, 129, 35);
		contentPane.add(btnApplyStatistics);
		
		btnCalculateFinal = new JButton("Calculate Final");
		btnCalculateFinal.setBounds(614, 261, 129, 35);
		contentPane.add(btnCalculateFinal);
		
		btnCurveFinalGrade = new JButton("Curve Final Grade");
		btnCurveFinalGrade.setBounds(614, 339, 129, 35);
		contentPane.add(btnCurveFinalGrade);
		
		CourseNameLabel = new JLabel("<Course Name>");
		CourseNameLabel.setBounds(32, 18, 172, 16);
		contentPane.add(CourseNameLabel);
		
		EditStudentBtn = new JButton("Edit Student...");
		EditStudentBtn.setBounds(216, 10, 129, 35);
		contentPane.add(EditStudentBtn);
		
		ApplyChangesBtn = new JButton("Apply Changes");
		ApplyChangesBtn.setBackground(new Color(0, 0, 255));
		ApplyChangesBtn.setForeground(Color.BLUE);
		ApplyChangesBtn.setBounds(118, 519, 129, 35);
		contentPane.add(ApplyChangesBtn);
		
		DisposeChangesBtn = new JButton("Dispose Changes");
		DisposeChangesBtn.setForeground(Color.red);
		DisposeChangesBtn.setBounds(343, 519, 129, 35);
		contentPane.add(DisposeChangesBtn);
		
		JButton EditCategoriesBtn = new JButton("Edit Categories...");
		EditCategoriesBtn.setBounds(374, 10, 129, 35);
		contentPane.add(EditCategoriesBtn);
		
		JButton AddCommentBtn = new JButton("Add Comment");
		AddCommentBtn.setBounds(614, 98, 129, 35);
		contentPane.add(AddCommentBtn);
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
