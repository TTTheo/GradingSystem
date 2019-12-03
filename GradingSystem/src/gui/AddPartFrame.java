package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddPartFrame extends JFrame implements FrameActions{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblCategory;	
	private JComboBox comboBox;	
	private JLabel lblNameOfPart;		
	private JLabel lblPercentage;		
	private JButton btnAdd;
	/**
	 * Create the frame.
	 */
	public AddPartFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 335, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		init();
		addActions();
	}
	
	public void init(){
		lblCategory = new JLabel("Category:");
		lblCategory.setBounds(45, 34, 104, 15);
		contentPane.add(lblCategory);
		
		comboBox = new JComboBox();
		comboBox.setBounds(44, 59, 227, 21);
		contentPane.add(comboBox);
		
		lblNameOfPart = new JLabel("Name of Part:");
		lblNameOfPart.setBounds(45, 113, 155, 15);
		contentPane.add(lblNameOfPart);
		
		textField = new JTextField();
		textField.setBounds(45, 138, 226, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblPercentage = new JLabel("Percentage:");
		lblPercentage.setBounds(45, 189, 118, 15);
		contentPane.add(lblPercentage);
		
		textField_1 = new JTextField();
		textField_1.setBounds(45, 214, 226, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		btnAdd = new JButton("Add");
		btnAdd.setBounds(178, 293, 93, 23);
		contentPane.add(btnAdd);
	}
	
	public void addActions(){
		btnAdd.addActionListener(new ActionListener() {
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
