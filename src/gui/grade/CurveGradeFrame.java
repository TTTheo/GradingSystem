package gui.grade;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.FrameActions;
import gui.SemesterFrame;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CurveGradeFrame extends JFrame implements FrameActions{

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblPercentageOfCurve;
	private JButton btnSure;
	/**
	 * Create the frame.
	 */
	public CurveGradeFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		init();
		addActions();
	}
	
	public void init(){
		textField = new JTextField();
		textField.setBounds(96, 111, 242, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblPercentageOfCurve = new JLabel("Percentage of curve:");
		lblPercentageOfCurve.setBounds(96, 82, 140, 15);
		contentPane.add(lblPercentageOfCurve);
		
		btnSure = new JButton("Proceed");
		btnSure.setBounds(287, 209, 93, 23);
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
