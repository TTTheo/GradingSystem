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
import javax.swing.JTree;

public class ViewGradeFrame extends JFrame implements FrameActions{

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel CourseNameLabel;
	/**
	 * Create the frame.
	 */
	public ViewGradeFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		init();
		addActions();
	}
	
	public void init(){
		
		Object[] title={"name","category1","category2"};
		Object[][] content=new Object[2][3];
		contentPane.setLayout(null);
		table = new JTable(content, title);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(203, 57, 568, 461);
		contentPane.add(scrollPane);
		
		CourseNameLabel = new JLabel("<Course Name>");
		CourseNameLabel.setBounds(32, 18, 172, 16);
		contentPane.add(CourseNameLabel);
		
		JButton EditModeBtn = new JButton("Edit Mode");
		EditModeBtn.setBounds(341, 530, 129, 35);
		EditModeBtn.setForeground(Color.RED);
		contentPane.add(EditModeBtn);
		
		JTree CategoryTree = new JTree();
		CategoryTree.setBounds(15, 57, 188, 457);
		contentPane.add(CategoryTree);
	}
	
	public void addActions(){
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
