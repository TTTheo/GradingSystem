package gui.grade;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.FrameActions;
import gui.SemesterFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class EditCategoryFrame extends JFrame implements FrameActions{

	private JPanel contentPane;
	private JTable CategoryTable;
	private JScrollPane scrollPane;		
	private JButton AddNewBtn;
	private JButton DeleteBtn;
	private JTextField textField;
	private JButton EditSelectedButton;

	/**
	 * Create the frame.
	 */
	public EditCategoryFrame() {
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
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 118, 520, 378);
		contentPane.add(scrollPane);
		
		Object[][] content=new Object[2][3];
		Object[] title={"Name","Percentage","Total score"};
		CategoryTable = new JTable(content,title);
		scrollPane.setViewportView(CategoryTable);
		
		AddNewBtn = new JButton("Add New");
		AddNewBtn.setBounds(605, 291, 129, 35);
		contentPane.add(AddNewBtn);
		
		DeleteBtn = new JButton("Delete Selected");
		DeleteBtn.setBounds(605, 197, 129, 35);
		contentPane.add(DeleteBtn);
		
		JLabel CourseNameLabel = new JLabel("<Course Name>");
		CourseNameLabel.setBounds(28, 46, 163, 16);
		contentPane.add(CourseNameLabel);
		
		JLabel lblNewLabel = new JLabel("Selected:");
		lblNewLabel.setBounds(605, 119, 79, 16);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(605, 145, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton ApplyChangesButton = new JButton("Apply Changes");
		ApplyChangesButton.setForeground(Color.BLUE);
		ApplyChangesButton.setBackground(Color.BLUE);
		ApplyChangesButton.setBounds(116, 522, 129, 35);
		contentPane.add(ApplyChangesButton);
		
		JButton CancelButton = new JButton("Cancel");
		CancelButton.setForeground(Color.RED);
		CancelButton.setBounds(326, 522, 129, 35);
		contentPane.add(CancelButton);
		
		EditSelectedButton = new JButton("Edit Selected");
		EditSelectedButton.setBounds(605, 244, 129, 35);
		contentPane.add(EditSelectedButton);
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
