package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import objects.Student;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class AddSingleStuFrame extends JFrame implements FrameActions{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblStudentName;	
	private JLabel lblStudentId;	
	private JLabel lblStudentEmail;	
	private JButton btnAdd;
	private JTextField textField_3;
	private JTextField textField_4;
	private List<Student> students = new ArrayList<Student>();

	/**
	 * Create the frame.
	 */
	public AddSingleStuFrame(List<Student> students) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		init();
		addActions();
		this.students=students;
	}
	
	public void init(){
		lblStudentName = new JLabel("First Name:");
		lblStudentName.setBounds(51, 46, 108, 15);
		contentPane.add(lblStudentName);
		
		textField = new JTextField();
		textField.setBounds(51, 71, 335, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblStudentId = new JLabel("ID:");
		lblStudentId.setBounds(52, 195, 76, 15);
		contentPane.add(lblStudentId);
		
		textField_1 = new JTextField();
		textField_1.setBounds(51, 223, 335, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		lblStudentEmail = new JLabel("Email:");
		lblStudentEmail.setBounds(51, 286, 108, 15);
		contentPane.add(lblStudentEmail);
		
		btnAdd = new JButton("Add");
		btnAdd.setBounds(293, 392, 93, 23);
		contentPane.add(btnAdd);
		
		textField_3 = new JTextField();
		textField_3.setBounds(51, 314, 335, 22);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(51, 122, 108, 16);
		contentPane.add(lblLastName);
		
		textField_4 = new JTextField();
		textField_4.setBounds(51, 151, 335, 22);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
	}
	
	public void addActions(){
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fname=textField.getText();
				String lname=textField_4.getText();
				String ID=textField_1.getText();
				//String gender=textField_2.getText();
				String email=textField_3.getText();
				Student singleStu=new Student(fname,lname,ID,null);
				students.add(singleStu);
			}
		});
	}
	
	public void setStudents(List<Student> students) {
		this.students=students;
	}
	
	public List<Student> getStudents(){
		return this.students;
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
