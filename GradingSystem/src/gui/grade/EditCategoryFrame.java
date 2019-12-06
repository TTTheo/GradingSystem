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

public class EditCategoryFrame extends JFrame implements FrameActions{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private JLabel lblCategoryName;
	private JLabel lblNumberOfParts;
	private JLabel lblPercentageOfThis;	
	private JScrollPane scrollPane;		
	private JButton btnNext;
	private JButton btnSetParts;

	/**
	 * Create the frame.
	 */
	public EditCategoryFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 372, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		init();
		addActions();
	}
	
	public void init(){
		lblCategoryName = new JLabel("Category name:");
		lblCategoryName.setBounds(30, 26, 112, 15);
		contentPane.add(lblCategoryName);
		
		lblNumberOfParts = new JLabel("Number of parts in this category:");
		lblNumberOfParts.setBounds(30, 82, 236, 15);
		contentPane.add(lblNumberOfParts);
		
		textField = new JTextField();
		textField.setBounds(30, 51, 282, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(30, 107, 282, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		lblPercentageOfThis = new JLabel("Percentage of this category:");
		lblPercentageOfThis.setBounds(30, 138, 282, 15);
		contentPane.add(lblPercentageOfThis);
		
		textField_2 = new JTextField();
		textField_2.setBounds(30, 163, 282, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 249, 282, 107);
		contentPane.add(scrollPane);
		
		Object[][] content=new Object[2][3];
		Object[] title={"Name","Percentage","Total score"};
		table = new JTable(content,title);
		scrollPane.setViewportView(table);
		
		
		btnNext = new JButton("Next");
		btnNext.setBounds(219, 366, 93, 23);
		contentPane.add(btnNext);
		
		btnSetParts = new JButton("Set parts");
		btnSetParts.setBounds(219, 198, 93, 23);
		contentPane.add(btnSetParts);
	}
	
	public void addActions(){
		btnSetParts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnNext.addActionListener(new ActionListener() {
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
