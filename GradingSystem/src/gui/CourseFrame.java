package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CourseFrame extends JFrame implements FrameActions{

	private JPanel contentPane;
	private JLabel lblSemesterName;
	private JButton btnAddCourse;	
	private JComboBox comboBox;
	private JLabel lblCourse;	
	private JButton btnSure;
	/**
	 * Create the frame.
	 */
	public CourseFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 344, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		init();
		addActions();
	}
	
	public void init(){
		lblSemesterName = new JLabel("semester name");
		lblSemesterName.setBounds(32, 29, 111, 15);
		contentPane.add(lblSemesterName);
		
		btnAddCourse = new JButton("Add course");
		btnAddCourse.setBounds(31, 68, 112, 23);
		contentPane.add(btnAddCourse);
		
		comboBox = new JComboBox();
		comboBox.setBounds(32, 143, 190, 21);
		contentPane.add(comboBox);
		
		lblCourse = new JLabel("Course:");
		lblCourse.setBounds(32, 118, 66, 15);
		contentPane.add(lblCourse);
		
		btnSure = new JButton("View");
		btnSure.setBounds(206, 291, 93, 23);
		contentPane.add(btnSure);
	}
	
	public void addActions(){
		btnAddCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnSure.addActionListener(new ActionListener() {
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
