package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import backend.CourseBackend;
public class SemesterFrame extends JFrame implements FrameActions{

	private JPanel contentPane;
	private JButton btnAddSemester;
	private JLabel lblSemester;
	private JComboBox comboBox;
	private JButton btnSure;

	private CourseBackend backend = new CourseBackend();

	/**
	 * Create the frame.
	 */
	public SemesterFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 362, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		init();
		addActions();
	}
	
	public void init(){
		btnAddSemester = new JButton("Add semester");
		btnAddSemester.setBounds(33, 28, 129, 23);
		contentPane.add(btnAddSemester);
		
		lblSemester = new JLabel("Semester:");
		lblSemester.setBounds(33, 77, 82, 15);
		contentPane.add(lblSemester);
		
		comboBox = new JComboBox();
		comboBox.setBounds(33, 110, 177, 21);
		contentPane.add(comboBox);
		
		btnSure = new JButton("View");
		btnSure.setBounds(223, 295, 93, 23);
		contentPane.add(btnSure);
	}
	
	public void addActions(){
		btnSure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}
	
	public void alert(String message){
        JOptionPane.showMessageDialog(null, message);
    }

	// Open ?? next
	public void openNext() {
		dispose();
	}

	// Go back to the login page
	public void openPrevious() {
	    LoginFrame login = new LoginFrame();
	    login.setVisible(true);
		dispose();
	}
}
