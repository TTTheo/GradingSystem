package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddStudentFrame extends JFrame implements FrameActions{

	private JPanel contentPane;
	private JButton btnAddMultipleStudents;	
	private JButton btnAddASingle;
	/**
	 * Create the frame.
	 */
	public AddStudentFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 347, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		init();
		addActions();
	}
	
	public void init(){
		btnAddMultipleStudents = new JButton("Add Multiple Students");
		btnAddMultipleStudents.setBounds(68, 89, 200, 23);
		contentPane.add(btnAddMultipleStudents);
		
		btnAddASingle = new JButton("Add Single Student");
		btnAddASingle.setBounds(68, 166, 200, 23);
		contentPane.add(btnAddASingle);
	}
	
	public void addActions(){
		btnAddMultipleStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnAddASingle.addActionListener(new ActionListener() {
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
