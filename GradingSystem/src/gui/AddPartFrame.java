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
import java.awt.Font;

public class AddPartFrame extends JFrame implements FrameActions{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblCategory;	
	private JComboBox comboBox;	
	private JLabel lblNameOfPart;		
	private JLabel lblPercentage;		
	private JButton btnAdd;
	private JButton btnNewButton;
	/**
	 * Create the frame.
	 */
	public AddPartFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 589, 609);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		init();
		addActions();
	}
	
	public void init(){
		lblCategory = new JLabel("Category:");
		lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCategory.setBounds(65, 62, 104, 23);
		contentPane.add(lblCategory);
		
		comboBox = new JComboBox();
		comboBox.setBounds(65, 98, 423, 31);
		contentPane.add(comboBox);
		
		lblNameOfPart = new JLabel("Name of Part:");
		lblNameOfPart.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNameOfPart.setBounds(65, 160, 155, 28);
		contentPane.add(lblNameOfPart);
		
		textField = new JTextField();
		textField.setBounds(65, 201, 423, 31);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblPercentage = new JLabel("Percentage:");
		lblPercentage.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPercentage.setBounds(67, 258, 118, 23);
		contentPane.add(lblPercentage);
		
		textField_1 = new JTextField();
		textField_1.setBounds(67, 294, 421, 31);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAdd.setBounds(395, 394, 93, 23);
		contentPane.add(btnAdd);
		
		btnNewButton = new JButton("Back");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(72, 393, 97, 25);
		contentPane.add(btnNewButton);
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
