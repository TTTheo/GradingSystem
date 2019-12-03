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

public class ManageStudentsFrame extends JFrame implements FrameActions{

	private JPanel contentPane;
	private JButton btnAddStudent;	
	private JButton btnDeleteStudent;
	private JButton btnWithdrawStudent;
	/**
	 * Create the frame.
	 */
	public ManageStudentsFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 364, 393);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		init();
		addActions();
	}
	
	public void init(){
		btnAddStudent = new JButton("Add Student");
		btnAddStudent.setBounds(47, 80, 238, 23);
		contentPane.add(btnAddStudent);
		
		btnDeleteStudent = new JButton("Delete Student");
		btnDeleteStudent.setBounds(47, 143, 238, 23);
		contentPane.add(btnDeleteStudent);
		
		btnWithdrawStudent = new JButton("Withdraw Student");
		btnWithdrawStudent.setBounds(47, 210, 238, 23);
		contentPane.add(btnWithdrawStudent);
	}
	
	public void addActions(){
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnDeleteStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnWithdrawStudent.addActionListener(new ActionListener() {
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
